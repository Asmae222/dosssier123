package fr.epsi.service;

import fr.epsi.model.Article;
import fr.epsi.model.Panier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandeServiceTest {

    private CommandeService service;
    private Panier panier;

    @BeforeEach
    void setUp() {
        service = new CommandeService();
        panier = new Panier();
    }

    @Test
    @DisplayName("Total correct pour 3 stylos à 2€")
    void calculerTotal_TroisStylos_RetourneSix() {
        panier.ajouter(new Article("Stylo", 2.0), 3);

        double total = service.calculerTotal(panier);

        assertEquals(6.0, total, 0.001);
    }

    @Test
    @DisplayName("Exception si panier null")
    void calculerTotal_PanierNull_DeclencheException() {
        assertThrows(IllegalArgumentException.class, () -> service.calculerTotal(null));
    }

    @Test
    @DisplayName("Exception si panier vide")
    void calculerTotal_PanierVide_DeclencheException() {
        assertThrows(IllegalArgumentException.class, () -> service.calculerTotal(new Panier()));
    }

    @Test
    @DisplayName("Remise de 10% appliquée correctement")
    void appliquerRemise_Total100_Remise10_Retourne90() {
        double resultat = service.appliquerRemise(100.0, 10);

        assertEquals(90.0, resultat, 0.001);
    }

    @Test
    @DisplayName("Catégorie PETITE si total inférieur à 50")
    void categoriserCommande_Total40_RetournePetite() {
        String categorie = service.categoriserCommande(40.0);

        assertEquals("PETITE", categorie);
    }

    @Test
    @DisplayName("Catégorie MOYENNE si total entre 50 et 199.99")
    void categoriserCommande_Total100_RetourneMoyenne() {
        String categorie = service.categoriserCommande(100.0);

        assertEquals("MOYENNE", categorie);
    }

    @Test
    @DisplayName("Catégorie GRANDE si total supérieur ou égal à 200")
    void categoriserCommande_Total250_RetourneGrande() {
        String categorie = service.categoriserCommande(250.0);

        assertEquals("GRANDE", categorie);
    }
}