package application;

import application.vue.FrameAccueil;
import application.vue.FrameParametre;

import java.awt.Color;
import java.io.File;
import java.util.List;

import application.metier.*;

/** Classe Controleur
  * @author : Plein de gens
  * @version : 1.0.0 - 06/01/2025
  * @since : 06/01/2025
  */

public class Controleur
{
	public static final int BASE_NB_MIN_MOTS = 5;
	public static final int BASE_NB_MAX_MOTS = 55;
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
		this.metier = new Metier(Controleur.BASE_NB_MIN_MOTS,Controleur.BASE_NB_MAX_MOTS);
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

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                       Liaison Paramètres-Métier                                        */
	/* ------------------------------------------------------------------------------------------------------ */

	public int   getNbMinMots ( ) { return FrameAccueil.getNbMinMots(); }
    public int   getNbMaxMots ( ) { return FrameAccueil.getNbMaxMots(); }

	public boolean majNombre (int min, int max ) { return (this.metier.setMaxGram(max) && this.metier.setMinGram(min)); }

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                                 Main                                                   */
	/* ------------------------------------------------------------------------------------------------------ */

	public static void main(String[] args) 
	{
		new Controleur();
	}
  
} 
  