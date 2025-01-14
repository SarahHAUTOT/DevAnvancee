package src.application.vue;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.application.Controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

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

	// Définition de la taille de la fenêtre
	public static final int DEFAULT_WIDTH  = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth () * 0.8);
	public static final int DEFAULT_HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.85);
	
	public static final String POLICE_TEXTE = "Montserrat";

	public static final Color COULEUR_PRIMAIRE   = Color.decode("#B0E3E6");
	public static final Color COULEUR_SECONDAIRE = Color.decode("#0E8088");
	public static final Color COULEUR_FOND       = Color.decode("#FBFBFB");

	private PanelParametre panelParametre;
	private PanelSuspect panelAccueil;

	private ArrayList<File> fichiers;

	private BarreNav	barreNav;
	private Controleur		ctrl;

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */

	

	public FrameAccueil(Controleur ctrl)
	{
		/* Création des composants */
		this.ctrl = ctrl;
		this.panelParametre = new PanelParametre( this );
		this.panelAccueil   = new PanelSuspect  ( this );
		this.fichiers       = new ArrayList<File>();

		/* Configuration de la frame */
		
		this.barreNav = new BarreNav(this.ctrl);
		this.setLayout(new BorderLayout());
		this.setSize(FrameAccueil.DEFAULT_WIDTH, FrameAccueil.DEFAULT_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/* Placements des composants */
		// Panel Bordure gauche 
		JPanel panelBordure = new JPanel();
		panelBordure.setBackground(COULEUR_SECONDAIRE);

		this.add(this.barreNav, BorderLayout.NORTH);
		this.add(this.panelAccueil, BorderLayout.CENTER);
		this.add(panelBordure, BorderLayout.WEST);

		this.setVisible(true);
		// this.pack();
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Accesseur                                                */
	/* ------------------------------------------------------------------------------------------------------ */

	public ArrayList<File> getFichiers()
	{
		return this.fichiers;
	}

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                       Méthode de la classe                                             */
	/* ------------------------------------------------------------------------------------------------------ */

	void styliserBouton(JButton btn)
	{
		btn.setBorder(BorderFactory.createLineBorder(FrameAccueil.COULEUR_SECONDAIRE, 2));
		btn.setBackground(FrameAccueil.COULEUR_PRIMAIRE);
	}

	public void ouvrirFichier()
	{
		File fichier = this.selectionnerFichier("Sélectionner un texte à ouvrir", null);

		if (fichier != null)
		{
			// Vérifier que le fichier est au format .txt
			if (!fichier.getName().endsWith(".txt"))
			{
				JOptionPane.showMessageDialog(this, "Uniquement les fichier .txt sont pris en charge.");
				this.ouvrirFichier();
				return;
			}

			this.fichiers.add(fichier);
		}
	}
	
	private File selectionnerFichier(String dialogue, File cheminOrigine)
	{
		JFileChooser selectionFichier = new JFileChooser();

		if (cheminOrigine != null)
		{
			selectionFichier.setCurrentDirectory(cheminOrigine);
		}

		selectionFichier.setDialogTitle(dialogue);
		selectionFichier.setApproveButtonText("Sélectionner");
		selectionFichier.setApproveButtonToolTipText("Annuler");
		selectionFichier.setFileFilter(new FileNameExtensionFilter("Fichier Texte", "txt"));
		selectionFichier.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if (selectionFichier.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			return selectionFichier.getSelectedFile();
		}
		else { return null; }
	}

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                   Méthode liaison controleur                                           */
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
