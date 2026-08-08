package org.elogitrack.logitrack.controller;

import jakarta.validation.Valid;
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
    public ResponseEntity<CommandeResponseDTO> createCommande(
            @Valid @RequestBody CommandeRequestDTO commande
    ) {
        return new ResponseEntity<>(
                commandeService.createCommande(commande),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/{id}/products")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CommandeResponseDTO> addProduitToCommande(
            @PathVariable Long id,
            @RequestBody LigneCommandeRequestDTO dto){

        return ResponseEntity.ok(commandeService.addProduitToOrder(id, dto));
    }

    @GetMapping("/status/{statut}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','AGENT')")
    public ResponseEntity<List<CommandeResponseDTO>> getByStatut(
            @PathVariable String statut){

        return ResponseEntity.ok(commandeService.getByStatut(statut));
    }

    @GetMapping("/count/{statut}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Long> countByStatut(
            @PathVariable String statut){

        return ResponseEntity.ok(
                commandeService.countByStatut(statut));
    }

    @GetMapping("/recent")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<List<CommandeResponseDTO>> recent(){

        return ResponseEntity.ok(
                commandeService.recentCommandes());
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

    @PatchMapping("/{id}/statut")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<CommandeResponseDTO> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatutDTO dto) {
        return ResponseEntity.ok(commandeService.updateStatus(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {

        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }

}
