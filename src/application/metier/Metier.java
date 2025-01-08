package application.metier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metier 
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */

	// Texte entrez par l'utilisateur 
	String       compare;
	List<String> lstComparant;



	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public Metier()
	{
		this.lstComparant = new ArrayList<String>();
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Accesseur                                                */
	/* ------------------------------------------------------------------------------------------------------ */




	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                                Méthode                                                 */
	/* ------------------------------------------------------------------------------------------------------ */


	/**
	 * Ajoute un texte a la liste des textes a comparer
	 * @param nomFic
	 */
	public void ajouterFichier (String nomFic) { this.lstComparant.add( Metier.recupTexteFichier(nomFic));}


	/**
	 * Ajoute un texte a la liste des textes a comparer
	 * @param texte
	 */
	public void ajouterTexte (String texte) { this.lstComparant.add(texte); }


	/**
	 * Set le texte a compare par un texte
	 * @param texte
	 */
	public void setCompareTexte (String texte ) { this.compare = texte; }


	/**
	 * Set le texte a compare par un fichier
	 * @param nomFic
	 */
	public void setCompareFic   (String nomFic) { this.compare = Metier.recupTexteFichier(nomFic); }


	/**
	 * Récupère le texte a comparer
	 * @param nomFic
	 * @return
	 */
	private static String recupTexteFichier (String nomFic)
	{
		String texte = "";
		try
		{
			Scanner sc = new Scanner(new File(nomFic),"UTF-8");
			while (sc.hasNextLine())
			{
				texte += sc.nextLine();
			}
			sc.close();
		}
		catch (Exception e)
		{
			System.out.println("Erreur lors de la lecture du fichier");
		}
		return texte;
	}

	




	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                                 MAIN                                                   */
	/* ------------------------------------------------------------------------------------------------------ */
	public static void main(String[] args) 
	{
		Metier m = new Metier();
		m.ajouterFichier("src/application/metier/fichier1.txt");
		m.ajouterFichier("src/application/metier/fichier2.txt");
		m.ajouterTexte("Bonjour je suis un texte");
		m.setCompareFic("src/application/metier/fichier3.txt");
		// m.setCompareTexte("Bonjour je suis un texte");	


		System.out.println("Texte a comparer : \n\t" + m.compare);
		System.out.println("Texte a comparer : " );

		for (String s : m.lstComparant)
		{
			System.out.println("\t" + s);
			System.out.println("\n\n[HEEEEEEEEEEEEEEEEEEEEEEEY]\n\n");
		}
	}
}
