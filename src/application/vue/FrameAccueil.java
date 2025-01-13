package application.vue;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** Frame d'accueil
  * @author : Plein de gens
  * @version : 1.0.0 - 06/01/2025
  * @since : 06/01/2025
  */

public class FrameAccueil extends JFrame
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */
  
	private PanelParametre panelParametre;
  
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public FrameAccueil ( )
	{
		/* Paramètres de la frame */ 
		this.setTitle("Application");
		
		/* Ajout du panel de paramètrage */
		this.panelParametre = new PanelParametre ( this );
		this.add(panelParametre);
		
		/* Afficher la frame */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(200,200);
		this.pack();
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
	public List<String> getLstText()
	{
		return new ArrayList<>(); // TODO : RELIER AU CONTROLEUR POUR RECUPERER LES COMAPRE
	}	

	public List<String> getLstPlagiatDetecte()
	{
		return new ArrayList<>(); // TODO : RELIER AU CONTROLEUR POUR RECUPERER LES PHRASE QUI ONT ETAIT DETECTE COMME PLAGIE
	}	
}
