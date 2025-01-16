package application.vue;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import application.Controleur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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

	public static final Color COULEUR_SECONDAIRE = Color.decode("#0E8088");
	public static final Color COULEUR_PRIMAIRE   = Color.decode("#B0E3E6");
	public static final Color COULEUR_FOND       = Color.decode("#FBFBFB");

	private PanelParametre panelParametre;
	private PanelSuspect   panelSuspect;
	private PanelResultat  panelResultat;

	private JPanel[] panels;
	private int      idPanel;

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
		this.panelSuspect   = new PanelSuspect  ( this );
		this.panelResultat  = new PanelResultat ( this );
		this.fichiers       = new ArrayList<File>();
		this.panels         = new JPanel[3];

		this.panels[0] = this.panelSuspect;
		this.panels[1] = this.panelResultat;
		this.panels[2] = new JPanel(); // TODO : this.panelComparaison
		this.idPanel = 0;

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
		this.add(this.panels[0], BorderLayout.CENTER);
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

	public void pageSuivante()
	{
		this.remove(this.panels[this.idPanel]);
		this.idPanel = (this.panels.length > this.idPanel) ? this.idPanel +1 : this.idPanel;
		this.majPanel();
	}

	public void pagePrecedente()
	{
		this.remove(this.panels[this.idPanel]);
		this.idPanel = (this.idPanel > 0) ? this.idPanel -1 : this.idPanel;
		this.majPanel();
	}

	private void majPanel()
	{
		System.out.println(this.idPanel);
		System.out.println(this.panels[this.idPanel]);
		this.add(this.panels[this.idPanel], BorderLayout.CENTER);
		this.validate();
		this.repaint();
	}

	void styliserBouton(JButton btn)
	{
		btn.setBorder(BorderFactory.createLineBorder(FrameAccueil.COULEUR_SECONDAIRE, 2));
		btn.setBackground(FrameAccueil.COULEUR_PRIMAIRE);
	}

	public void ouvrirFichier(File repertoire)
	{
		File fichier = this.selectionnerFichier("Sélectionner un texte à ouvrir", repertoire);

		if (fichier != null)
		{
			// Vérifier que le fichier est au format .txt
			if (!fichier.getName().endsWith(".txt"))
			{
				JOptionPane.showMessageDialog(this, "Uniquement les fichier .txt sont pris en charge.");
				this.ouvrirFichier(fichier);
				return;
			}

			this.fichiers.add(fichier);
		}
	}

	/**
	 * Création du panel qui décrit l'étape
	 * @param titre affiché sur le panel
	 * @return un panel
	 */
	JPanel panelTitre(String titre)
	{
		JPanel panelTitre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel titreLbl = new JLabel(titre);
		titreLbl.setFont(new Font(FrameAccueil.POLICE_TEXTE, Font.BOLD, 24));

		titreLbl  .setForeground(FrameAccueil.COULEUR_SECONDAIRE);	// Couleur du texte
		panelTitre.setBackground(FrameAccueil.COULEUR_FOND);		// Couleur du fond

		panelTitre.add(titreLbl);
		
		return panelTitre;
	}

	/**
	 * Création du panel qui décrit l'étape
	 * @param titre affiché sur le panel
	 * @return un panel
	 */
	JPanel panelTexte(String titre)
	{
		JPanel panelTitre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel titreLbl = new JLabel(titre);
		titreLbl.setFont(new Font(FrameAccueil.POLICE_TEXTE, Font.BOLD, 12));

		titreLbl  .setForeground(FrameAccueil.COULEUR_SECONDAIRE);	// Couleur du texte
		panelTitre.setBackground(FrameAccueil.COULEUR_FOND);		// Couleur du fond

		panelTitre.add(titreLbl);
		
		return panelTitre;
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

	public String getComparant()
	{
		return "Once upon a time there was a lovely princess. But she had an enchantment upon her of a fearful sort, which could only be broken by Love's first kiss. She was locked away in a castle guarded by a terrible fire breathing dragon. Many brave knights had attempted to free her from this dreadful prison, but none prevailed. She waited in the dragon's keep in the highest room of the tallest tower for her true love and true love's first kiss. Like that's ever going to happen. What a loony. Shrek Beware Stay out I think he's in here. All right. Lets get it! Hold on. Do you know what that thing can do to you? Yeah. He'll groan into your bones for his brains. Well actually that would be a giant. Now Ogres, huh, they are much worse. They'll make a soup from your freshly peeled skin. They'll chew your livers, squeeze the jelly from your eyes. Actually, it's quite good on toast. Back, back beast, back! I warned you! Right. This is the part, where you run away. Yeah! And stay out. Wanted. Fairytale creatures. Right, this one is full. Take it away. Give me that. Your fine days are over. -25 pieces of silver for the witch. Next. -Come on. Sit down there! And be quiet! This cage is so small.";
	}
}