package org.elogitrack.logitrack.service;

import org.elogitrack.logitrack.dto.clientdto.ClientRequestDTO;
import org.elogitrack.logitrack.dto.clientdto.ClientResponseDTO;
import org.elogitrack.logitrack.model.Client;
import org.elogitrack.logitrack.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    public ClientService(ClientRepository clientRepository,
                         ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    public List<ClientResponseDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(client -> modelMapper.map(client, ClientResponseDTO.class))
                .toList();
    }

    public ClientResponseDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client introuvable"));
        return modelMapper.map(client, ClientResponseDTO.class);
    }

    public ClientResponseDTO findClientByEmail(String email){
        Client client = clientRepository.findClientByEmail(email);
        return modelMapper.map(client , ClientResponseDTO.class);
    }
    public ClientResponseDTO saveClient(ClientRequestDTO dto){
        Client client = modelMapper.map(dto, Client.class);
        Client saved = clientRepository.save(client);
        return modelMapper.map(saved, ClientResponseDTO.class);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
