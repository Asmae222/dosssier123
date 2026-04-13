package fr.epsi.service;

import fr.epsi.model.Article;
import fr.epsi.model.Panier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandeServiceIT {

    private final CommandeService service = new CommandeService();

    @Test
    @DisplayName("Pipeline complète : panier → total → remise → catégorie")
    void pipelineComplete_PanierMixte_ResultatCoherent() {
        Panier panier = new Panier();
        panier.ajouter(new Article("Stylo", 2.0), 10);
        panier.ajouter(new Article("Cahier", 5.0), 4);

        double total = service.calculerTotal(panier);
        double apresRemise = service.appliquerRemise(total, 10);
        String categorie = service.categoriserCommande(apresRemise);

        assertEquals(40.0, total, 0.001);
        assertEquals(36.0, apresRemise, 0.001);
        assertEquals("PETITE", categorie);
    }
}