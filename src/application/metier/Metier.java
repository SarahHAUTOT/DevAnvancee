package application.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metier {
	/*------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------Attributs----------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/

	// Texte entrez par l'utilisateur
	TextCompare compare;
	List<TextComparant> lstComparant;
	int minGram = 5;

	// Analyse des textes comparants

	/*------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------Constructeur-------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/

	public Metier() {
		this.lstComparant = new ArrayList<TextComparant>();
	}

	/*------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------Accesseur----------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/

	/*------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------Méthode-----------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/

	public List<Correspondance> compare() {
		List<Correspondance> correspondances = new ArrayList<Correspondance>();
		for (TextComparant comparant : lstComparant) {
			System.out.println("comparaison d'un fichier");
			correspondances.addAll(detecterPlagiat(comparant));
		}

		for (Correspondance correspondance : correspondances) {
			System.out.println(correspondance);
		}

		return correspondances;
	}

	// Détection de plagiat avec positions
public List<Correspondance> detecterPlagiat(TextComparant comparant) {
    List<Correspondance> correspondances = new ArrayList<>();
    int n = comparant.getMotsNormalises().size();
    int i = 0;

    while (i < n) {
        boolean correspondanceTrouvee = false;
        StringBuilder nGramBuilder = new StringBuilder();

        // Construire dynamiquement les n-grammes en partant de `i`
        for (int taille = 1; taille <= this.compare.getMotsNormalises().size() && i + taille <= n; taille++) {
            if (taille > 1) {
                nGramBuilder.append(" ");
            }
            nGramBuilder.append(comparant.getMotsNormalises().get(i + taille - 1).word);

            String nGramCandidate = nGramBuilder.toString();

            if (this.compare.getNGrams().containsKey(nGramCandidate)) {
                // Correspondance trouvée
                PlageDeMots comparedRange = new PlageDeMots(
                        comparant.getMotsNormalises().get(i).start,
                        comparant.getMotsNormalises().get(i + taille - 1).end);
                PlageDeMots referenceRange = this.compare.getNGrams().get(nGramCandidate);

                correspondances.add(new Correspondance(nGramCandidate, comparedRange, referenceRange, comparant));
                i += taille; // Sauter les mots inclus dans ce n-gramme
                correspondanceTrouvee = true;
                break;
            }
        }

        if (!correspondanceTrouvee) {
            i++; // Passer au mot suivant
        }
    }

    return correspondances;
}


	/**
	 * Ajoute un texte a la liste des textes a comparer
	 * 
	 * @param nomFic
	 */
	public void ajouterFichier(String nomFic) {
		this.lstComparant.add(new TextComparant(Metier.recupTexteFichier(nomFic)));
	}

	/**
	 * Ajoute un texte a la liste des textes a comparer
	 * 
	 * @param texte
	 */
	public void ajouterTexte(String texte) {
		this.lstComparant.add(new TextComparant(texte));
	}

	/**
	 * Set le texte a compare par un texte
	 * 
	 * @param texte
	 */
	public void setCompareTexte(String texte) {
		this.compare = new TextCompare(texte, minGram);
		;
	}

	/**
	 * Set le texte a compare par un fichier
	 * 
	 * @param nomFic
	 */
	public void setCompareFic(String nomFic) {
		this.compare = new TextCompare(Metier.recupTexteFichier(nomFic), minGram);
	}

	/**
	 * Récupère le texte a comparer
	 * 
	 * @param nomFic
	 * @return
	 */
	private static String recupTexteFichier(String nomFic) {
		String texte = "";
		try {
			Scanner sc = new Scanner(new File(nomFic), "UTF-8");
			while (sc.hasNextLine()) {
				texte += sc.nextLine();
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture du fichier");
		}
		return texte;
	}

	/*------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------MAIN---------------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/
	public static void main(String[] args) {
		Metier m = new Metier();
		int nbFic = 4;
		for (int i = 1; i < nbFic + 1; i++) {
			m.ajouterFichier("src/application/metier/fichier" + i + ".txt");
			System.out.println("fichier lu");
		}
		m.setCompareFic("src/application/metier/fichierCompa.txt");
		System.out.println("fichier compar lu");
		m.compare();
	}
}
