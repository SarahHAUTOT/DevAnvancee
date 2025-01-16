package application.vue;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/** Frame d'accueil
  * @author : Plein de gens
  * @version : 1.0.0 - 06/01/2025
  * @since : 06/01/2025
  */

public class FrameAccueil extends JFrame implements ActionListener
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
  
	private File      fichierSelect;
	private JButton   btnSuivant;
	private JButton   btnImporter;
	private JTextArea saisieTexte;

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public FrameAccueil()
	{
		/* Création des composants */
		this.fichierSelect = null;
        this.saisieTexte = new JTextArea("Saisir un texte...");
        this.btnImporter = new JButton  ("Importer un .txt");
        this.btnSuivant  = new JButton  ("Suivant");
		this.panelParametre = new PanelParametre ( this );

		/* Configuration des composants */
		// Bouton Importer
		this.btnImporter.setBackground(FrameAccueil.COULEUR_PRIMAIRE);

		// Zone de texte
		this.saisieTexte.setBorder(BorderFactory.createLineBorder(FrameAccueil.COULEUR_SECONDAIRE, 1));
		this.saisieTexte.setBorder(BorderFactory.createCompoundBorder(
			this.saisieTexte.getBorder(), 
			BorderFactory.createEmptyBorder(5, 10, 5, 10)));

		// Bouton suivant
		this.btnSuivant.setBorder(BorderFactory.createLineBorder(FrameAccueil.COULEUR_SECONDAIRE, 2));
		this.btnSuivant.setBackground(FrameAccueil.COULEUR_PRIMAIRE);
		this.btnSuivant.setPreferredSize(new Dimension(200, 50));

		/* Configuration de la frame */
		this.setLayout(new BorderLayout());
		this.setSize(FrameAccueil.DEFAULT_WIDTH, FrameAccueil.DEFAULT_HEIGHT);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		/* Placements des composants */
		// Bande supérieure
		this.add(panelParametre, BorderLayout.NORTH);

		// Panel Principale
        JPanel panelPrincipale = new JPanel();
		panelPrincipale.setBackground(FrameAccueil.COULEUR_FOND);
		panelPrincipale.setLayout(new BorderLayout());

		// Panel Titre
		// panelPrincipale.add(this.panelTitre("Texte suspecté de plagiat"), BorderLayout.NORTH);

        // Panel Central
        // panelPrincipale.add(this.panelSaisieTexte(0.65, 0.6), BorderLayout.CENTER);
        panelPrincipale.add(new PanelResultat(this), BorderLayout.CENTER);

        // Bouton "Suivant"
		JPanel panelSuivant = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelSuivant.add(this.btnSuivant);
		panelSuivant.setBackground(FrameAccueil.COULEUR_FOND);
        // panelPrincipale.add(panelSuivant, BorderLayout.SOUTH);

		// Panel Bordure gauche 
		JPanel panelBordure = new JPanel();
		panelBordure.setBackground(COULEUR_SECONDAIRE);

		this.add(panelPrincipale, BorderLayout.CENTER);
		this.add(panelBordure, BorderLayout.WEST);

		this.btnImporter.addActionListener(this);
		this.btnSuivant .addActionListener(this);

		this.setVisible(true);
		// this.pack();
	}

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                       Méthode de la classe                                             */
	/* ------------------------------------------------------------------------------------------------------ */

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

	/**
	 * Création des composants pour l'importation ou l'écriture d'un texte
	 * @param longueur de la saisie de texte en pourcentage par rapport a la taille de la frame
	 * @param hauteur  de la saisie de texte en pourcentage par rapport a la taille de la frame
	 * @return un panel
	 */
	private JPanel panelSaisieTexte(double longueur, double hauteur)
	{
		JPanel panelImportation = new JPanel();
        panelImportation.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.BOTH;

        // Panel Central : Zone de texte
        this.saisieTexte.setLineWrap(true);
        this.saisieTexte.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(this.saisieTexte);

		int l = (int) (FrameAccueil.DEFAULT_WIDTH  * longueur);
		int h = (int) (FrameAccueil.DEFAULT_HEIGHT * hauteur);
		scrollPane.setPreferredSize(new Dimension(l, h));

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        panelImportation.add(scrollPane, c);

        // Panel Central : Ou Texte
        JLabel ouLabel = new JLabel("OU");
        c.gridx = 1;
        c.gridy = 1;
        c.gridheight = 1;
        panelImportation.add(ouLabel, c);

        // Panel Central : Bouton d'importation
        c.gridx = 2;
        c.gridy = 1;
        panelImportation.add(this.btnImporter, c);

		panelImportation.setBackground(FrameAccueil.COULEUR_FOND);

		return panelImportation;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (this.btnImporter == e.getSource())
		{
			this.ouvrirFichier();
		}

		if (this.btnSuivant == e.getSource())
		{
			if (this.fichierSelect == null && this.saisieTexte.getText().isBlank())
				this.ouvrirFichier();
			else
				return; // TODO : Passer à la frame suivante
		}
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

			this.fichierSelect = fichier;
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
		else
		{
			return null;
		}
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
	
	public static void main(String[] args)
	{
		new FrameAccueil();
	}
}
