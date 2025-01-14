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
	int minGram = 1;

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

	public void compare() {
		List<Correspondance> correspondance = new ArrayList<Correspondance>();
		for (int index = 0; index < lstComparant.size(); index++) {
			correspondance = detecterPlagiat( lstComparant.get(index));

			// Affichage des résultats
			System.out.println("Résultats pour le texte comparant #" + (index + 1) + ":");
			if (correspondance.isEmpty()) {
				System.out.println("Aucune correspondance trouvée.");
			} else {
				for (Correspondance match : correspondance) {
					System.out.println(match);
				}
			}
			System.out.println("--------------------------------------------------");
		}
	}

	// Détection de plagiat avec positions
	public List<Correspondance> detecterPlagiat(TextComparant comparant) {
		List<Correspondance> correspondance = new ArrayList<Correspondance>();
		int n = comparant.getMotsNormalises().size();
		int i = 0;

		while (i < n) {
			boolean correspondanceTrouvee = false;

			for (int taille = this.compare.getMotsNormalises().size(); taille >= 1; taille--) {
				if (i + taille <= n) {
					String nGramCandidate = String.join(" ",
							comparant.getMotsNormalises().subList(i, i + taille).stream().map(w -> w.word).toList());
					if (this.compare.getNGrams().containsKey(nGramCandidate)) {
						PlageDeMots comparedRange = new PlageDeMots(
								comparant.getMotsNormalises().get(i).start,
								comparant.getMotsNormalises().get(i + taille - 1).end);
						PlageDeMots referenceRange = this.compare.getNGrams().get(nGramCandidate);

						correspondance.add(new Correspondance(nGramCandidate, comparedRange, referenceRange, comparant));
						i += taille;
						correspondanceTrouvee = true;
						break;
					}
				}
			}

			if (!correspondanceTrouvee) {
				i++;
			}
		}
		return correspondance;
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
		m.ajouterFichier("src/application/metier/fichier1.txt");
		m.ajouterFichier("src/application/metier/fichier2.txt");
		m.ajouterTexte("Bonjour je suis un texte");
		m.setCompareFic("src/application/metier/fichier3.txt");
		// m.setCompareTexte("Bonjour je suis un texte");

		System.out.println("Texte a comparer : \n\t" + m.compare);
		System.out.println("Texte a comparer : ");
	}
}
