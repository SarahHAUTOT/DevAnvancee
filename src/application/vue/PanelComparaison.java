package application.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/** Frame d'accueil
 * @author : Plein de gens
* @version : 1.0.0 - 06/01/2025
* @since : 06/01/2025
*/

public class PanelComparaison extends JPanel implements ActionListener
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */

	private FrameAccueil  frameAccueil;
	private PanelResultat panelResultat;
	private JPanel panelListeTexte;

	private ArrayList<JLabel> listeLabel;

	private JButton btnAnalyser;
	private JButton btnImporter;
	private JButton btnImporterTexte;

	private JTextArea saisieTexte;

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public PanelComparaison ( FrameAccueil frame, PanelResultat panelResultat )
	{
		this.panelResultat = panelResultat;

		/* Création des composants */
		this.btnAnalyser = new JButton("Analyser");
		this.btnImporter = new JButton("Importer un .txt");
		this.btnImporterTexte = new JButton("Importer le texte entré");
		this.saisieTexte = new JTextArea("Saisir un texte...");
		this.listeLabel = new ArrayList<JLabel>();

		this.frameAccueil = frame;
		this.frameAccueil.styliserBouton(this.btnAnalyser);
		this.frameAccueil.styliserBouton(this.btnImporter);
		this.frameAccueil.styliserBouton(this.btnImporterTexte);

		this.saisieTexte.setBorder(BorderFactory.createLineBorder(FrameAccueil.COULEUR_SECONDAIRE, 1));
		this.saisieTexte.setBorder(BorderFactory.createCompoundBorder(
			this.saisieTexte.getBorder(), 
			BorderFactory.createEmptyBorder(5, 10, 5, 10)));

		this.saisieTexte.setLineWrap(true);
		this.saisieTexte.setWrapStyleWord(true);

		this.btnAnalyser.setPreferredSize(new Dimension(200, 50));
		this.btnImporter.setPreferredSize(new Dimension(200, 50));
		this.btnImporterTexte.setPreferredSize(new Dimension(200, 50));

		/* Paramètrage du panel */
		this.setLayout(new BorderLayout());

		/* Ajouter le titre du panel */
		this.add(this.frameAccueil.panelTitre("Texte(s) de comparaison"), BorderLayout.NORTH);

		/* Créer un panneau pour la liste des textes */ 
		this.panelListeTexte = new JPanel();
		this.panelListeTexte.setLayout(new BoxLayout(this.panelListeTexte, BoxLayout.Y_AXIS));
		this.panelListeTexte.setBorder(
			BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(5, 10, 5, 10),
				"Textes importés"));
		this.panelListeTexte.setBackground(FrameAccueil.COULEUR_FOND);

		/* Ajout du panel en format de JScrollPane */
		JScrollPane scPanelListeTexte = new JScrollPane(this.panelListeTexte);
		this.add(scPanelListeTexte, BorderLayout.WEST);

		/*  Créer un panneau pour l'importation et l'analyse */
		JPanel importPanel = new JPanel();
		importPanel.setLayout(new GridLayout(2, 1));
		importPanel.setBorder(BorderFactory.createTitledBorder(
			BorderFactory.createTitledBorder(
				BorderFactory.createEmptyBorder(5, 10, 5, 10),
				"Importation")));
		importPanel.setBackground(FrameAccueil.COULEUR_FOND);

		/* Zone de texte pour saisir du texte */
		JScrollPane scrollPane = new JScrollPane(this.saisieTexte);
		importPanel.add(scrollPane);

		/* Ajouter une ligne pour importer un fichier */
		JPanel panelImportation = new JPanel();
		JLabel lblOu = new JLabel("OU", JLabel.CENTER);
		
		panelImportation.add(this.btnImporterTexte);
		panelImportation.add(lblOu);
		panelImportation.add(this.btnImporter);
		panelImportation.setBackground(FrameAccueil.COULEUR_FOND);

		importPanel.add(panelImportation);

		this.add(importPanel, BorderLayout.CENTER);

		/*  Ajouter un bouton "Analyser" */
		JPanel panelAnalyser = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelAnalyser.add(this.btnAnalyser);
		panelAnalyser.setBackground(FrameAccueil.COULEUR_FOND);
		this.add(panelAnalyser, BorderLayout.SOUTH);

		/*  Ecouteur boutons */
		this.btnAnalyser.addActionListener(this);
		this.btnImporter.addActionListener(this);
		this.btnImporterTexte.addActionListener(this);
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Accesseur                                                */
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Modificateur                                              */
	/* ------------------------------------------------------------------------------------------------------ */

	public void reinitialiserPanel()
	{
		this.listeLabel.removeAll(this.listeLabel);
		this.panelListeTexte.removeAll();
		this.panelListeTexte.validate();
		this.repaint();
	}


	/* ------------------------------------------------------------------------------------------------------ */
	/*                                    Méthode Ecouteur Bouton                                             */
	/* ------------------------------------------------------------------------------------------------------ */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnImporter)
		{
			File fichier = this.frameAccueil.ouvrirFichier(null);
			if (fichier == null) return;

			// Ajout du fichier dans la liste de la frame accueil
			this.frameAccueil.ajouterFichier(fichier, fichier.getName());

			// Ajout d'un nouveau texte dans le panel
			JLabel label = new JLabel(fichier.getName());
			this.listeLabel.add(label);
			label.setBackground(FrameAccueil.COULEUR_FOND);
			this.panelListeTexte.add(label, BorderLayout.CENTER);
			this.panelListeTexte.setBorder(
				BorderFactory.createTitledBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10),
					"Textes importés ("+ this.frameAccueil.getComparants().size() +" importés)")));

			this.validate();
			this.repaint(); // Rafraichissement de la liste de texte
		}

		if (e.getSource() == this.btnImporterTexte)
		{
			// Ajout d'un nouveau texte dans le panel
			int endIndex = (this.saisieTexte.getText().length() < 12) ? this.saisieTexte.getText().length() : 12; 
			String titre = 
			this.saisieTexte.getText().substring(0, endIndex) + "... " +
				"(" + this.saisieTexte.getText().length() +" caractères)";

			JLabel label = new JLabel(titre);
			label.setBackground(FrameAccueil.COULEUR_FOND);
			this.frameAccueil.ajouterTexte(this.saisieTexte.getText(), titre);
			
			this.panelListeTexte.add(label, BorderLayout.CENTER);
			this.panelListeTexte.setBorder(
				BorderFactory.createTitledBorder(
					BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10),
					"Textes importés ("+ this.frameAccueil.getComparants().size() +" importés)")));

			this.validate();
			this.repaint(); // Rafraichissement de la liste de texte

		}

		if (e.getSource() == this.btnAnalyser)
		{
			if (!this.frameAccueil.getComparants().isEmpty())
			{
				panelResultat.genererAffichage();
				this.frameAccueil.pageSuivante(); // Page de chargement
			}
		}
	}
}
