package fr.epsi.service;

import fr.epsi.model.Article;
import fr.epsi.model.Panier;

import java.util.Map;

public class CommandeService {

    public double calculerTotal(Panier panier) {
        if (panier == null || panier.estVide()) {
            throw new IllegalArgumentException("Panier vide ou null");
        }

        double total = 0;
        for (Map.Entry<Article, Integer> e : panier.getArticles().entrySet()) {
            total += e.getKey().getPrix() * e.getValue();
        }
        return total;
    }

    public double appliquerRemise(double total, int pourcentage) {
        if (pourcentage < 0 || pourcentage > 100) {
            throw new IllegalArgumentException("Remise invalide");
        }
        return total * (1 - pourcentage / 100.0);
    }

    public String categoriserCommande(double total) {
        if (total < 50) {
            return "PETITE";
        } else if (total < 200) {
            return "MOYENNE";
        } else {
            return "GRANDE";
        }
    }
}