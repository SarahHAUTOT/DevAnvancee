package application;

import application.vue.FrameAccueil;

/** Classe Controleur
  * @author : Plein de gens
  * @version : 1.0.0 - 06/01/2025
  * @since : 06/01/2025
  */

public class Controleur
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */
  
	@SuppressWarnings("unused")
	private FrameAccueil frameAccueil;
  
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public Controleur()
	{
		this.frameAccueil = new FrameAccueil();
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Accesseur                                                */
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Modificateur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                                Méthode                                                 */
	/* ------------------------------------------------------------------------------------------------------ */

	public static void main(String[] args) 
	{
		new Controleur();
	}
  
} 
  