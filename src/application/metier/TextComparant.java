package application.metier;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class TextComparant {
    protected String originalText;
    protected List<PositionMot> normalizedWords;
    public String nom;

    public TextComparant(String text, String nom) {
        this.originalText = text;
        this.normalizedWords = normaliser(text);
        this.nom = nom;
    }

    public String getTextOriginal() {
        return originalText;
    }

    public List<PositionMot> getMotsNormalises() {
        return normalizedWords;
    }

    public static List<PositionMot> normaliser(String text) {
        List<PositionMot> result = new ArrayList<>();
        int index = 0;
    
        // Remplacer les sauts de ligne et les caractères similaires par un espace
        text = text.replace("\n", " ").replace("\r", " ");
    
        // Convertir les caractères accentués en leurs équivalents non accentués
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("\\p{M}", ""); // Supprimer les marques diacritiques (accents)
    
        while (index < text.length()) {
            char c = text.charAt(index);
    
            if (Character.isLetterOrDigit(c)) {
                int start = index;
                while (index < text.length() && Character.isLetterOrDigit(text.charAt(index))) {
                    index++;
                }
                int end = index;
    
                // Normaliser le mot (enlever les caractères non alphanumériques)
                String word = text.substring(start, end).toLowerCase()
                        .replaceAll("[^a-z0-9]", "");
                System.out.print(word + "|");
    
                // Ajouter à la liste si le mot est non vide
                if (!word.isEmpty()) {
                    result.add(new PositionMot(word, start, end));
                }
            } else {
                index++;
            }
        }
        return result;
    }
}
