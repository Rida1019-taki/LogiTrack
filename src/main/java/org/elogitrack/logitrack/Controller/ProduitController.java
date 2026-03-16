package org.elogitrack.logitrack.Controller;

import org.elogitrack.logitrack.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produits")
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;
}
