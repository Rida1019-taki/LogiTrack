package org.elogitrack.logitrack.Controller;

import org.elogitrack.logitrack.Model.Client;
import org.elogitrack.logitrack.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    public Client addClient(Client client){
        return clientService.saveClient(client);
    }

    @GetMapping
    public List<Client> getAllCLient(){
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(Long id){
        return clientService.getClientById(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deleteClient(Long id){
        clientService.deleteClient(id);
    }
}
