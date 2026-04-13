package fr.epsi.model;

import java.util.HashMap;
import java.util.Map;

public class Panier {
    private Map<Article, Integer> articles = new HashMap<>();

    public void ajouter(Article article, int quantite) {
        articles.merge(article, quantite, Integer::sum);
    }

    public Map<Article, Integer> getArticles() {
        return articles;
    }

    public boolean estVide() {
        return articles.isEmpty();
    }
}