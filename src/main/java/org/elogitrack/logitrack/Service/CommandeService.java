package org.elogitrack.logitrack.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.elogitrack.logitrack.client.NotificationClient;
import org.elogitrack.logitrack.dto.NotificationRequestDTO;
import org.elogitrack.logitrack.dto.commandedto.CommandeRequestDTO;
import org.elogitrack.logitrack.dto.commandedto.CommandeResponseDTO;
import org.elogitrack.logitrack.dto.commandedto.UpdateStatutDTO;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;
import org.elogitrack.logitrack.enums.NotificationType;
import org.elogitrack.logitrack.mapper.CommandeMapper;
import org.elogitrack.logitrack.model.Commande;
import org.elogitrack.logitrack.model.LigneCommande;
import org.elogitrack.logitrack.model.Produit;
import org.elogitrack.logitrack.repository.ClientRepository;
import org.elogitrack.logitrack.repository.CommandeRepository;
import org.elogitrack.logitrack.repository.LigneCommandeRepository;
import org.elogitrack.logitrack.repository.ProduitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ProduitRepository produitRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final CommandeMapper commandeMapper;
    private final NotificationClient notificationClient;

    public CommandeService(
            CommandeRepository commandeRepository,
            ClientRepository clientRepository,
            ProduitRepository produitRepository,
            LigneCommandeRepository ligneCommandeRepository,
            CommandeMapper commandeMapper,
            NotificationClient notificationClient
    ) {
        this.commandeRepository = commandeRepository;
        this.clientRepository = clientRepository;
        this.produitRepository = produitRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.commandeMapper = commandeMapper;
        this.notificationClient = notificationClient;
    }

    @Transactional
    public CommandeResponseDTO createCommande(CommandeRequestDTO dto) {

        var client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable"));

        Commande commande = new Commande();
        commande.setClient(client);
        commande.setDateCommande(LocalDate.now());
        commande.setStatut("EN_ATTENTE");

        commande = commandeRepository.save(commande);

        for (LigneCommandeRequestDTO ligneDto : dto.getProduits()) {

            Produit produit = produitRepository.findById(ligneDto.getProduitId())
                    .orElseThrow(() -> new RuntimeException(
                            "Produit introuvable avec ID: " + ligneDto.getProduitId()
                    ));

            if (ligneDto.getQuantity() == null || ligneDto.getQuantity() <= 0) {
                throw new RuntimeException("La quantité doit être supérieure à 0");
            }

            if (produit.getQuantity() < ligneDto.getQuantity()) {
                throw new RuntimeException(
                        "Stock insuffisant pour le produit : " + produit.getNom()
                                + ". Stock disponible : " + produit.getQuantity()
                );
            }

            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setCommande(commande);
            ligneCommande.setProduit(produit);
            ligneCommande.setQuantity(ligneDto.getQuantity());

            commande.getLigneCommanden().add(ligneCommande);

            produit.setQuantity(produit.getQuantity() - ligneDto.getQuantity());
            produitRepository.save(produit);
        }

        commande = commandeRepository.save(commande);

        sendNotificationHelper(
                client.getEmail(),
                "Votre commande #" + commande.getIdCommande() + " a été créée avec succès.",
                NotificationType.ORDER_CREATED,
                commande.getIdCommande()
        );

        return commandeMapper.toResponseDTO(commande);
    }

    @Transactional
    public CommandeResponseDTO updateStatus(Long id, UpdateStatutDTO dto) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable avec ID: " + id));

        String oldStatut = commande.getStatut();
        String newStatut = dto.getStatut();

        commande.setStatut(newStatut);
        Commande updatedCommande = commandeRepository.save(commande);

        String clientEmail = commande.getClient() != null ? commande.getClient().getEmail() : null;

        if (newStatut != null && !newStatut.equalsIgnoreCase(oldStatut)) {
            if (newStatut.equalsIgnoreCase("EXPETIEE") || newStatut.equalsIgnoreCase("SHIPPED")) {
                sendNotificationHelper(
                        clientEmail,
                        "Votre commande #" + id + " a été expédiée.",
                        NotificationType.ORDER_SHIPPED,
                        id
                );
            } else if (newStatut.equalsIgnoreCase("LIVREE") || newStatut.equalsIgnoreCase("DELIVERED")) {
                sendNotificationHelper(
                        clientEmail,
                        "Votre commande #" + id + " a été livrée.",
                        NotificationType.ORDER_DELIVERED,
                        id
                );
            }
        }

        return commandeMapper.toResponseDTO(updatedCommande);
    }

    private void sendNotificationHelper(String recipientEmail, String message, NotificationType type, Long orderId) {
        if (recipientEmail == null || recipientEmail.isBlank()) {
            log.warn("Impossible d'envoyer la notification : l'email du destinataire est absent pour la commande #{}", orderId);
            return;
        }

        try {
            NotificationRequestDTO request = NotificationRequestDTO.builder()
                    .recipient(recipientEmail)
                    .message(message)
                    .notificationType(type)
                    .orderId(orderId)
                    .build();

            var response = notificationClient.sendNotification(request);

            if (response.getStatusCode().is2xxSuccessful()) {
                log.info("Notification ({}) envoyée avec succès pour la commande #{}", type, orderId);
            } else {
                log.warn("Échec d'envoi de la notification ({}). Statut HTTP: {}", type, response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Erreur lors de l'appel OpenFeign vers le Notification Service pour la commande #{}", orderId, e);
        }
    }

    @Transactional
    public CommandeResponseDTO addProduitToOrder(Long idOrder, LigneCommandeRequestDTO dto) {

        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new RuntimeException("La quantité doit être supérieure à 0");
        }

        Commande commande = commandeRepository.findById(idOrder)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));

        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable"));

        if (produit.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException(
                    "Stock insuffisant. Stock disponible : " + produit.getQuantity()
            );
        }

        var existingLine = commande.getLigneCommanden()
                .stream()
                .filter(line -> line.getProduit().getIdProduit().equals(produit.getIdProduit()))
                .findFirst();

        if (existingLine.isPresent()) {
            LigneCommande line = existingLine.get();
            line.setQuantity(line.getQuantity() + dto.getQuantity());
            ligneCommandeRepository.save(line);
        } else {
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setCommande(commande);
            ligneCommande.setProduit(produit);
            ligneCommande.setQuantity(dto.getQuantity());

            commande.getLigneCommanden().add(ligneCommande);
            ligneCommandeRepository.save(ligneCommande);
        }

        produit.setQuantity(produit.getQuantity() - dto.getQuantity());
        produitRepository.save(produit);
        commandeRepository.save(commande);

        return commandeMapper.toResponseDTO(commande);
    }

    public List<CommandeResponseDTO> getCommandeByIdClient(Long id) {
        return commandeRepository.findCommandeByIdClient(id)
                .stream()
                .map(commandeMapper::toResponseDTO)
                .toList();
    }

    public long countCommande() {
        return commandeRepository.countCommandes();
    }

    public Page<CommandeResponseDTO> getAllCommandes(Pageable pageable) {
        return commandeRepository.findAll(pageable)
                .map(commandeMapper::toResponseDTO);
    }

    public List<CommandeResponseDTO> getByStatut(String statut) {
        return commandeRepository.findByStatut(statut)
                .stream()
                .map(commandeMapper::toResponseDTO)
                .toList();
    }

    public long countByStatut(String statut) {
        return commandeRepository.countByStatut(statut);
    }

    public List<CommandeResponseDTO> recentCommandes() {
        return commandeRepository
                .findTop5ByOrderByDateCommandeDesc()
                .stream()
                .map(commandeMapper::toResponseDTO)
                .toList();
    }

    public CommandeResponseDTO getCommandeById(Long id) {
        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
        return commandeMapper.toResponseDTO(commande);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}