package org.elogitrack.logitrack.controller;

import org.elogitrack.logitrack.dto.commandedto.CommandeRequestDTO;
import org.elogitrack.logitrack.dto.commandedto.CommandeResponseDTO;
import org.elogitrack.logitrack.dto.commandedto.UpdateStatutDTO;
import org.elogitrack.logitrack.dto.lignecommandedto.LigneCommandeRequestDTO;
import org.elogitrack.logitrack.model.Commande;
import org.elogitrack.logitrack.repository.CommandeRepository;
import org.elogitrack.logitrack.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService commandeService;
    private final CommandeRepository commandeRepository;

    public CommandeController(CommandeService commandeService,
                              CommandeRepository commandeRepository) {
        this.commandeService = commandeService;
        this.commandeRepository = commandeRepository;
    }

    @PostMapping
    public ResponseEntity<CommandeResponseDTO> createCommande(@RequestBody CommandeRequestDTO commande){
        return new ResponseEntity<>(commandeService.createCommande(commande), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<CommandeResponseDTO> addProduitToCommande(
            @PathVariable Long id,
            @RequestBody LigneCommandeRequestDTO dto){

        return ResponseEntity.ok(commandeService.addProduitToOrder(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeResponseDTO> getCommandeById(@PathVariable Long id){
        return ResponseEntity.ok(commandeService.getCommandeById(id));
    }

    @GetMapping
    public ResponseEntity<List<CommandeResponseDTO>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<CommandeResponseDTO>> getCommandeByClient(@PathVariable Long id){
        return ResponseEntity.ok(commandeService.getCommandeByIdClient(id));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countCommande(){

        return ResponseEntity.ok(commandeService.countCommande());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CommandeResponseDTO> updateStatus(@PathVariable Long id, @RequestBody UpdateStatutDTO status){
        return ResponseEntity.ok(commandeService.updateStatus(id, status));
    }

}
