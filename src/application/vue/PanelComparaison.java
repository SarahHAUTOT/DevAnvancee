package application.vue;

import javax.swing.*;
import java.awt.*;

/** Frame d'accueil
  * @author : Plein de gens
  * @version : 1.0.0 - 06/01/2025
  * @since : 06/01/2025
  */

public class PanelComparaison extends JPanel
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */
  
	private FrameAccueil frameAccueil;
  
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public PanelComparaison ( FrameAccueil frame )
	{
		this.frameAccueil = frame;

		/* Paramètrage du panel */
        this.setLayout(new BorderLayout());

        /* Ajouter le titre du panel */
        JLabel titleLabel = new JLabel("Texte(s) de comparaison", JLabel.LEFT);
        titleLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        this.add(titleLabel, BorderLayout.NORTH);

        /* Créer un panneau pour la liste des textes */ 
        JPanel panelListeTexts = new JPanel();
        panelListeTexts.setLayout(new BoxLayout(panelListeTexts, BoxLayout.Y_AXIS));
        panelListeTexts.setBorder(BorderFactory.createTitledBorder("Textes importés (5 importés)"));

		// TODO: A modifier avec le bons nombres de textes
		/*  Ajouter des exemples de textes avec des cases à cocher et boutons de suppression */
        for (int i = 0; i < 5; i++) 
        {
            JCheckBox checkBox = new JCheckBox("\"Il était une fois ...\" (300 caractères)"); //TODO: A rendre modulaire

            panelListeTexts.add(checkBox, BorderLayout.CENTER);
        }

		/* Ajout du panel en format de JScrollPane */
		JScrollPane scPanelListeTexte = new JScrollPane(panelListeTexts);
		this.add(scPanelListeTexte, BorderLayout.WEST);


		/*  Créer un panneau pour l'importation et l'analyse */
		JPanel importPanel = new JPanel();
		importPanel.setLayout(new GridLayout(2, 1));
		importPanel.setBorder(BorderFactory.createTitledBorder("Importation"));
 
		/* Zone de texte pour saisir du texte */
		JTextArea textArea = new JTextArea("Saisir un texte...");
		JScrollPane scrollPane = new JScrollPane(textArea);
		importPanel.add(scrollPane);
 
		/* Ajouter une ligne pour importer un fichier */
		JPanel panelImportation = new JPanel();
		JButton btnImportZone = new JButton("Importer le texte entré");
		JLabel lblOu = new JLabel("OU", JLabel.CENTER);
		JButton btnImportTexts = new JButton("Importer un .txt");
		
		panelImportation.add(btnImportZone);
		panelImportation.add(lblOu);
		panelImportation.add(btnImportTexts);
 
		importPanel.add(panelImportation);
 
		this.add(importPanel, BorderLayout.CENTER);
 
		/*  Ajouter un bouton "Analyser" */
		JButton analyzeButton = new JButton("Analyser");
		this.add(analyzeButton, BorderLayout.SOUTH);
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
}
