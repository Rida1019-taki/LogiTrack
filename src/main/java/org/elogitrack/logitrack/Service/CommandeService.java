package org.elogitrack.logitrack.service;

import jakarta.transaction.Transactional;
import org.elogitrack.logitrack.dto.commandedto.CommandeRequestDTO;
import org.elogitrack.logitrack.dto.commandedto.CommandeResponseDTO;
import org.elogitrack.logitrack.dto.commandedto.UpdateStatutDTO;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;
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
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ProduitRepository produitRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final CommandeMapper commandeMapper;

    public CommandeService(
            CommandeRepository commandeRepository,
            ClientRepository clientRepository,
            ProduitRepository produitRepository,
            LigneCommandeRepository ligneCommandeRepository,
            CommandeMapper commandeMapper
    ) {
        this.commandeRepository = commandeRepository;
        this.clientRepository = clientRepository;
        this.produitRepository = produitRepository;
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.commandeMapper = commandeMapper;
    }

    @Transactional
    public CommandeResponseDTO createCommande(CommandeRequestDTO dto) {

        var client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() ->
                        new RuntimeException("Client introuvable"));

        Commande commande = new Commande();

        commande.setClient(client);
        commande.setDateCommande(LocalDate.now());
        commande.setStatut("EN_ATTENTE");

        commande = commandeRepository.save(commande);

        for (LigneCommandeRequestDTO ligneDto : dto.getProduits()) {

            Produit produit = produitRepository.findById(ligneDto.getProduitId())
                    .orElseThrow(() ->
                            new RuntimeException(
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

            produit.setQuantity(
                    produit.getQuantity() - ligneDto.getQuantity()
            );

            produitRepository.save(produit);
        }

        commande = commandeRepository.save(commande);

        return commandeMapper.toResponseDTO(commande);
    }

    @Transactional
    public CommandeResponseDTO addProduitToOrder(
            Long idOrder,
            LigneCommandeRequestDTO dto
    ) {

        if (dto.getQuantity() == null || dto.getQuantity() <= 0) {
            throw new RuntimeException("La quantité doit être supérieure à 0");
        }

        Commande commande = commandeRepository.findById(idOrder)
                .orElseThrow(() ->
                        new RuntimeException("Commande introuvable"));

        Produit produit = produitRepository.findById(dto.getProduitId())
                .orElseThrow(() ->
                        new RuntimeException("Produit introuvable"));

        if (produit.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException(
                    "Stock insuffisant. Stock disponible : "
                            + produit.getQuantity()
            );
        }

        var existingLine = commande.getLigneCommanden()
                .stream()
                .filter(line ->
                        line.getProduit()
                                .getIdProduit()
                                .equals(produit.getIdProduit()))
                .findFirst();

        if (existingLine.isPresent()) {

            LigneCommande line = existingLine.get();

            line.setQuantity(
                    line.getQuantity() + dto.getQuantity()
            );

            ligneCommandeRepository.save(line);

        } else {

            LigneCommande ligneCommande = new LigneCommande();

            ligneCommande.setCommande(commande);
            ligneCommande.setProduit(produit);
            ligneCommande.setQuantity(dto.getQuantity());

            commande.getLigneCommanden().add(ligneCommande);

            ligneCommandeRepository.save(ligneCommande);
        }

        produit.setQuantity(
                produit.getQuantity() - dto.getQuantity()
        );

        produitRepository.save(produit);

        commandeRepository.save(commande);

        return commandeMapper.toResponseDTO(commande);
    }

    @Transactional
    public CommandeResponseDTO updateStatus(
            Long id,
            UpdateStatutDTO dto
    ) {

        Commande commande = commandeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Commande introuvable avec ID: " + id
                        ));

        commande.setStatut(dto.getStatut());

        Commande updatedCommande =
                commandeRepository.save(commande);

        return commandeMapper.toResponseDTO(updatedCommande);
    }

    public List<CommandeResponseDTO> getCommandeByIdClient(
            Long id
    ) {

        return commandeRepository.findCommandeByIdClient(id)
                .stream()
                .map(commandeMapper::toResponseDTO)
                .toList();
    }

    public long countCommande() {
        return commandeRepository.countCommandes();
    }

    public Page<CommandeResponseDTO> getAllCommandes(
            Pageable pageable
    ) {

        return commandeRepository.findAll(pageable)
                .map(commandeMapper::toResponseDTO);
    }

    public List<CommandeResponseDTO> getByStatut(
            String statut
    ) {

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

        Commande commande = commandeRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Commande introuvable"
                        ));

        return commandeMapper.toResponseDTO(commande);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }
}