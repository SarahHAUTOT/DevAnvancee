package application.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import application.Controleur;

public class Metier {
	/*------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------Attributs----------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/

	// Texte entrez par l'utilisateur
	TextCompare compare;
	List<TextComparant> lstComparant;
	int minGram;
	int maxGram;

	// Analyse des textes comparants

	/*------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------Constructeur-------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/

	public Metier(int minGram, int maxGram) {
		this.compare = null;

		if (minGram < maxGram) {
			this.minGram = minGram;
			this.maxGram = maxGram;
		} else {
			this.minGram = 5;
			this.maxGram = 55;
		}
		this.lstComparant = new ArrayList<TextComparant>();
	}

	/*------------------------------------------------------------------------------------------------------*/
	/*-----------------------------------------------Accesseur----------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/

	public TextCompare getCompare() { return this.compare; }
	public List<TextComparant> getComparants() { return this.lstComparant; }

	/*------------------------------------------------------------------------------------------------------*/
	/*------------------------------------------------Méthode-----------------------------------------------*/
	/*------------------------------------------------------------------------------------------------------*/

	public List<Correspondance> compare() {

		List<Correspondance> correspondances = new ArrayList<Correspondance>();
		for (TextComparant comparant : lstComparant) {
			correspondances.addAll(detecterPlagiat(comparant));
		}

		return correspondances;
	}

	// Détection de plagiat avec ajustement après un n-gram trouvé
	public List<Correspondance> detecterPlagiat(TextComparant comparant) {
		List<Correspondance> correspondances = new ArrayList<>();
		int n = comparant.getMotsNormalises().size();
		int i = 0;

		while (i < n) {
			boolean correspondanceTrouvee = false;

			// Rechercher le plus grand n-gramme correspondant
			for (int taille = maxGram; taille >= minGram; taille--) {
				if (i + taille <= n) {
					String nGramCandidate = String.join(" ",
							comparant.getMotsNormalises().subList(i, i + taille).stream().map(w -> w.word).toList());

					if (this.compare.getNGrams().containsKey(nGramCandidate)) {
						// Correspondance trouvée
						PlageDeMots comparantRange = new PlageDeMots(
								comparant.getMotsNormalises().get(i).start,
								comparant.getMotsNormalises().get(i + taille - 1).end);
						PlageDeMots compareRange = this.compare.getNGrams().get(nGramCandidate);

						correspondances
								.add(new Correspondance(nGramCandidate, compareRange, comparantRange, comparant));

						// Si un n-gramme de taille maxGram est trouvé, vérifier après lui avec minGram
						if (taille == maxGram && i + taille < n) {
							i += taille - (minGram - 1); // Avancer juste avant la fin pour permettre d'autres
															// détections
						} else {
							i += taille; // Sauter les mots inclus dans ce n-gramme
						}

						correspondanceTrouvee = true;
						break;
					}
				}
			}

			// Passer au mot suivant si aucune correspondance n'a été trouvée
			if (!correspondanceTrouvee) {
				i++;
			}
		}

		return this.fusionnerCorrespondances(correspondances);
	}

	// Fusionner les correspondances superposées ou adjacentes
	public List<Correspondance> fusionnerCorrespondances(List<Correspondance> correspondances) {
		if (correspondances.isEmpty()) {
			return correspondances;
		}

		// Trier les correspondances par plage de mots comparée (début croissant)
		correspondances.sort(Comparator.comparingInt(c -> c.getCompareRange().debut));

		List<Correspondance> fusionnees = new ArrayList<>();
		Correspondance actuelle = correspondances.get(0);

		for (int i = 1; i < correspondances.size(); i++) {
			Correspondance suivante = correspondances.get(i);

			// Vérifier si les plages de mots se chevauchent ou se touchent
			if (actuelle.getCompareRange().fin >= suivante.getCompareRange().debut - 1) {
				// Fusionner les plages
				PlageDeMots plageCompareFusionnee = new PlageDeMots(
						Math.min(actuelle.getCompareRange().debut, suivante.getCompareRange().debut),
						Math.max(actuelle.getCompareRange().fin  , suivante.getCompareRange().fin  ));

				PlageDeMots plageComparantFusionnee = new PlageDeMots(
					Math.min(actuelle.getComparantRange().debut, suivante.getComparantRange().debut),
					Math.max(actuelle.getComparantRange().fin  , suivante.getComparantRange().fin  ));

				// Fusionner le texte (ajouter un espace entre deux n-grammes)
				String texteFusionne = actuelle.getTexte() + " " + suivante.getTexte();

				// Créer une nouvelle correspondance fusionnée
				actuelle = new Correspondance(texteFusionne, plageCompareFusionnee, plageComparantFusionnee,
						actuelle.getTexteComparant());
			} else {
				// Ajouter la correspondance actuelle à la liste et passer à la suivante
				fusionnees.add(actuelle);
				actuelle = suivante;
			}
		}

		// Ajouter la dernière correspondance
		fusionnees.add(actuelle);

		return fusionnees;
	}

	public boolean setMinGram(int min) {
		if (min < maxGram) {
			this.minGram = min;
			if (this.compare!=null) {
				this.compare = new TextCompare(compare.getTextOriginal(), this.minGram, this.maxGram);
			}
			return true;
		}
		return false;
	}

	public boolean setMaxGram(int max) {
		if (this.minGram < max) {
			this.maxGram = max;
			if (this.compare!=null) {
				this.compare = new TextCompare(compare.getTextOriginal(), this.minGram, this.maxGram);
			}
			return true;
		}
		return false;
	}

	/**
	 * Nettoyer la liste des textes comparants
	 * 
	 */
	public void nettoyerComparants() {
		this.lstComparant.removeAll(this.lstComparant);
	}

	/**
	 * Nettoyer le texte comparé
	 * 
	 */
	public void nettoyerCompare() {
		this.compare = null;
	}

	/**
	 * Ajoute un texte a la liste des textes a comparer
	 * 
	 * @param fichier
	 */
	public void ajouterFichier(File fichier, String nom) {
		this.lstComparant.add(new TextComparant(Metier.recupTexteFichier(fichier), nom));
	}

	/**
	 * Ajoute un texte a la liste des textes a comparer
	 * 
	 * @param texte
	 */
	public void ajouterTexte(String texte, String nom) {
		this.lstComparant.add(new TextComparant(texte, nom));
	}

	/**
	 * Set le texte a compare par un texte
	 * 
	 * @param texte
	 */
	public void setCompareTexte(String texte) {
		this.compare = new TextCompare(texte, this.minGram, this.maxGram);
	}

	/**
	 * Set le texte a compare par un fichier
	 * 
	 * @param fichier
	 */
	public void setCompareFic(File fichier) {
		this.compare = new TextCompare(Metier.recupTexteFichier(fichier), this.minGram, this.maxGram);
	}

	/**
	 * Récupère le texte a comparer
	 * 
	 * @param fichier
	 * @return
	 */
	private static String recupTexteFichier(File fichier) {
		String texte = "";
		try {
			Scanner sc = new Scanner(fichier, "UTF-8");
			while (sc.hasNextLine()) {
				texte += '\n'+sc.nextLine();
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Erreur lors de la lecture du fichier");
		}
		return texte;
	}
}
