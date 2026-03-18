package org.elogitrack.logitrack.Service;

import org.elogitrack.logitrack.Model.Produit;
import org.elogitrack.logitrack.Repository.LigneCommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatistiqueService {
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;

    public Produit getTopProduit(){
        return ligneCommandeRepository.findTopProduct();
    }
}
