package org.elogitrack.logitrack.service;

import org.elogitrack.logitrack.dto.clientdto.ClientRequestDTO;
import org.elogitrack.logitrack.dto.clientdto.ClientResponseDTO;
import org.elogitrack.logitrack.mapper.ClientMapper;
import org.elogitrack.logitrack.model.Client;
import org.elogitrack.logitrack.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(
            ClientRepository clientRepository,
            ClientMapper clientMapper
    ) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public Page<ClientResponseDTO> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable)
                .map(clientMapper::toResponseDTO);
    }

    public ClientResponseDTO updateClient(Long id, ClientRequestDTO dto) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Client introuvable"));

        client.setNom(dto.getNom());
        client.setEmail(dto.getEmail());
        client.setTelefone(dto.getTelefone());
        client.setVille(dto.getVille());

        Client updated = clientRepository.save(client);

        return clientMapper.toResponseDTO(updated);
    }

    public long countClients() {
        return clientRepository.countClients();
    }

    public List<ClientResponseDTO> searchByNom(String nom) {

        return clientRepository.findByNomContainingIgnoreCase(nom)
                .stream()
                .map(clientMapper::toResponseDTO)
                .toList();
    }

    public ClientResponseDTO getClientById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Client introuvable"));

        return clientMapper.toResponseDTO(client);
    }

    public ClientResponseDTO findClientByEmail(String email) {

        Client client = clientRepository.findClientByEmail(email);

        if (client == null) {
            throw new RuntimeException("Client introuvable");
        }

        return clientMapper.toResponseDTO(client);
    }

    public ClientResponseDTO saveClient(ClientRequestDTO dto) {

        Client client = clientMapper.toEntity(dto);

        Client saved = clientRepository.save(client);

        return clientMapper.toResponseDTO(saved);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}