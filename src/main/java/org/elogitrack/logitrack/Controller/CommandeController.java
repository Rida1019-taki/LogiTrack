package org.elogitrack.logitrack.controller;

import org.elogitrack.logitrack.dto.commandedto.CommandeRequestDTO;
import org.elogitrack.logitrack.dto.commandedto.CommandeResponseDTO;
import org.elogitrack.logitrack.dto.commandedto.UpdateStatutDTO;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;
import org.elogitrack.logitrack.repository.CommandeRepository;
import org.elogitrack.logitrack.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService,
                              CommandeRepository commandeRepository) {
        this.commandeService = commandeService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CommandeResponseDTO> createCommande(@RequestBody CommandeRequestDTO commande){
        return new ResponseEntity<>(commandeService.createCommande(commande), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/products")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CommandeResponseDTO> addProduitToCommande(
            @PathVariable Long id,
            @RequestBody LigneCommandeRequestDTO dto){

        return ResponseEntity.ok(commandeService.addProduitToOrder(id, dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<CommandeResponseDTO> getCommandeById(@PathVariable Long id){
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<Page<CommandeResponseDTO>> getAllCommandes(Pageable pageable) {
        return ResponseEntity.ok(commandeService.getAllCommandes(pageable));
    }

    @GetMapping("/client/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<List<CommandeResponseDTO>> getCommandeByClient(@PathVariable Long id){
        return ResponseEntity.ok(commandeService.getCommandeByIdClient(id));
    }

    @GetMapping("/count")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Long> countCommande(){
        return ResponseEntity.ok(commandeService.countCommande());
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<CommandeResponseDTO> updateStatus(@PathVariable Long id, @RequestBody UpdateStatutDTO status){
        return ResponseEntity.ok(commandeService.updateStatus(id, status));
    }

}
