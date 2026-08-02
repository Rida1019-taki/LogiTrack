package org.elogitrack.logitrack.controller;

import org.elogitrack.logitrack.dto.clientdto.ClientRequestDTO;
import org.elogitrack.logitrack.dto.clientdto.ClientResponseDTO;
import org.elogitrack.logitrack.model.Client;
import org.elogitrack.logitrack.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<ClientResponseDTO> addClient(@RequestBody ClientRequestDTO dto) {
        ClientResponseDTO response = clientService.saveClient(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<Page<ClientResponseDTO>> getAllClients(Pageable pageable) {
        return ResponseEntity.ok(clientService.getAllClients(pageable));
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Long> countClients(){
        return ResponseEntity.ok(clientService.countClients());
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<List<ClientResponseDTO>> search(
            @RequestParam String nom){

        return ResponseEntity.ok(
                clientService.searchByNom(nom));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<ClientResponseDTO> updateClient(
            @PathVariable Long id,
            @RequestBody ClientRequestDTO dto){

        return ResponseEntity.ok(clientService.updateClient(id,dto));
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<ClientResponseDTO> findClientByEmail(@PathVariable String email){
        return ResponseEntity.ok(clientService.findClientByEmail(email));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable("id") Long id){
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id){

        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
