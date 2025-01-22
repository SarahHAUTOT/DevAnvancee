package application;

import java.io.File;
import java.util.List;

import javax.swing.SwingUtilities;

import application.metier.Correspondance;
import application.metier.Metier;
import application.metier.TextComparant;
import application.metier.TextCompare;
import application.vue.FrameAccueil;

/**
 * Classe Controleur
 * @author : Plein de gens
 * @version : 1.0.0 - 06/01/2025
 * @since : 06/01/2025
 */
public class Controleur
{
	public static final int BASE_NB_MIN_MOTS = 10;
	public static final int BASE_NB_MAX_MOTS = 60;
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

	public void nettoyerComparants()
	{
		this.metier.nettoyerComparants();
	}
	public void nettoyerCompare()
	{
		this.metier.nettoyerCompare();
	}

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
		SwingUtilities.invokeLater(Controleur::new);
	}
  
} 
  