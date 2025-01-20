package application;

import application.vue.FrameAccueil;

import java.io.File;
import java.util.List;

import application.metier.*;

/**
 * Classe Controleur
 * @author : Plein de gens
 * @version : 1.0.0 - 06/01/2025
 * @since : 06/01/2025
 */
public class Controleur
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */
  
	  private FrameAccueil frameAccueil;
	  private Metier metier;
  
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public Controleur()
	{
		this.metier       = new Metier(5,5);
		this.frameAccueil = new FrameAccueil(this);
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Accesseur                                                */
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Modificateur                                              */
	/* ------------------------------------------------------------------------------------------------------ */

	public FrameAccueil getFrameAccueil() { return this.frameAccueil;}
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                                Méthode                                                 */
	/* ------------------------------------------------------------------------------------------------------ */

	public TextCompare getCompare() { return this.metier.getCompare(); }

	public List<TextComparant> getComparants() { return this.metier.getComparants(); }

	/**
	 * Ajoute un texte a la liste des textes a comparer
	 * 
	 * @param nomFic
	 */
	public void ajouterFichier(File fichier, String nom)
	{
		this.metier.ajouterFichier(fichier, nom);
	}

	/**
	 * Ajoute un texte a la liste des textes a comparer
	 * 
	 * @param texte
	 */
	public void ajouterTexte(String texte, String nom)
	{
		this.metier.ajouterTexte(texte, nom);
	}

	/**
	 * Set le texte a compare par un texte
	 * 
	 * @param texte
	 */
	public void setCompareTexte(String texte)
	{
		this.metier.setCompareTexte(texte);
	}

	/**
	 * Set le texte a compare par un fichier
	 * 
	 * @param nomFic
	 */
	public void setCompareFic(File fichier)
	{
		this.metier.setCompareFic(fichier);
	}

	/**
	 * Set le texte a compare par un fichier
	 * 
	 * @param nomFic
	 */
	public List<Correspondance> getLstPlagiatDetecte()
	{
		return this.metier.compare();
	}

	public static void main(String[] args) 
	{
		new Controleur();
	}
  
} 
  