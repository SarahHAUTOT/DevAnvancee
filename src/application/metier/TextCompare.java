package application.metier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextCompare extends TextComparant {
    private Map<String, PlageDeMots> nGrams;

    public TextCompare(String text, int minGram, int maxGram) {
        super(text, "comparé");
        this.nGrams = genererNGrammes(normalizedWords, minGram, maxGram);
    }

    public Map<String, PlageDeMots> getNGrams() {
        return nGrams;
    }

        // Génération des n-grammes avec leurs positions originales
    public static Map<String, PlageDeMots> genererNGrammes(List<PositionMot> words, int minGram,
            int maxGram) {
        Map<String, PlageDeMots> nGrams = new HashMap<String, PlageDeMots>();
        int n = words.size();

        for (int size = minGram; size <= maxGram; size++) {
            for (int i = 0; i <= n - size; i++) {
                String nGram = String.join(" ",
                        words.subList(i, i + size).stream().map(w -> w.word).toList());
                int start = words.get(i).start;
                int end = words.get(i + size - 1).end;
                nGrams.put(nGram, new PlageDeMots(start, end));
            }
        }
        return nGrams;
    }
}
