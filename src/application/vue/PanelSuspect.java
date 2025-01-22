package application.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/** Frame d'accueil
 * @author : Plein de gens
* @version : 1.0.0 - 06/01/2025
* @since : 06/01/2025
*/

public class PanelSuspect extends JPanel implements ActionListener
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */

	private FrameAccueil frameAccueil;

	private JTextArea saisieTexte;

	private JButton   btnSuivant;
	private JButton   btnImporter;
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public PanelSuspect ( FrameAccueil frame )
	{
		this.frameAccueil = frame;

		/* Création des composants */
		this.saisieTexte = new JTextArea("Saisir un texte...");
		this.btnImporter = new JButton  ("Importer un .txt");

		/* Configuration des composants */
		// Bouton Importer
		this.btnImporter.setBackground(FrameAccueil.COULEUR_PRIMAIRE);
		
		// Zone de texte
		this.saisieTexte.setBorder(BorderFactory.createLineBorder(FrameAccueil.COULEUR_SECONDAIRE, 1));
		this.saisieTexte.setBorder(BorderFactory.createCompoundBorder(
			this.saisieTexte.getBorder(), 
			BorderFactory.createEmptyBorder(5, 10, 5, 10)));

		this.btnSuivant  = new JButton  ("Suivant");

		// Bouton suivant
		this.frameAccueil.styliserBouton(this.btnSuivant);
		this.btnSuivant.setPreferredSize(new Dimension(200, 50));

		this.setBackground(FrameAccueil.COULEUR_FOND);
		this.setLayout(new BorderLayout());

		// Panel Titre
		this.add(this.panelTitre("Texte suspecté de plagiat"), BorderLayout.NORTH);

		// Panel Central
		this.add(this.panelSaisieTexte(0.65, 0.6), BorderLayout.CENTER);

		// Panel Bouton "Suivant"
		JPanel panelSuivant = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelSuivant.add(this.btnSuivant);
		panelSuivant.setBackground(FrameAccueil.COULEUR_FOND);
		this.add(panelSuivant, BorderLayout.SOUTH);
	
		/* Ecouteurs des boutons */
		this.btnImporter.addActionListener(this);
		this.btnSuivant.addActionListener(this);
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                                Méthode                                                 */
	/* ------------------------------------------------------------------------------------------------------ */

	public void reinitialiserPanel()
	{
		this.saisieTexte.setText("Saisir un texte...");
		this.repaint();
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
	 * Création des composants pour l'importation ou l'écriture d'un texte
	 * @param longueur de la saisie de texte en pourcentage par rapport a la taille de la frame
	 * @param hauteur  de la saisie de texte en pourcentage par rapport a la taille de la frame
	 * @return un panel
	 */
	JPanel panelSaisieTexte(double longueur, double hauteur)
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
		if (e.getSource() == this.btnImporter)
		{
			File fichier = this.frameAccueil.ouvrirFichier(null);

			if (fichier != null)
			{
				this.frameAccueil.setCompareFic(fichier);
				this.saisieTexte.setText(this.frameAccueil.getCompare().getTextOriginal());
			}
		}

		if (e.getSource() == this.btnSuivant)
		{
			if (this.frameAccueil.getCompare() == null && this.saisieTexte.getText().equals("Saisir un texte...") )
			{
				File fichier = this.frameAccueil.ouvrirFichier(null);
				if (fichier == null) return;

				this.frameAccueil.pageSuivante();
			}
			else
			{
				if (this.frameAccueil.getCompare() == null)
					this.frameAccueil.setCompareTexte(this.saisieTexte.getText());
				this.frameAccueil.pageSuivante();
			}
		}
	}
}
