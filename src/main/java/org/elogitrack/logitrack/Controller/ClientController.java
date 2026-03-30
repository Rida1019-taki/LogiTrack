package org.elogitrack.logitrack.controller;

import org.elogitrack.logitrack.dto.clientdto.ClientRequestDTO;
import org.elogitrack.logitrack.dto.clientdto.ClientResponseDTO;
import org.elogitrack.logitrack.model.Client;
import org.elogitrack.logitrack.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> addClient(@RequestBody ClientRequestDTO dto) {
        ClientResponseDTO response = clientService.saveClient(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients(){
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id){

        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
