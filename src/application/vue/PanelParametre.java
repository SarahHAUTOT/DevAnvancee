package application.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Frame d'accueil
* @author : Plein de gens
* @version : 1.0.0 - 16/01/2025
* @since : 06/01/2025
*/

public class PanelParametre extends JPanel
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */

	private Test frameAccueil;

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public PanelParametre ( Test frame )
	{
		this.frameAccueil = frame;

		setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Titre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel titleLabel = new JLabel("Paramètres");
        titleLabel.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        add(titleLabel, gbc);

        // Comparaison
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel comparaisonLabel = new JLabel("Nombre minimum de mots pour être signalé comme plagiat");
        add(comparaisonLabel, gbc);

        gbc.gridx = 1;
        JTextField minWordsField = new JTextField(5);
        minWordsField.setText("2");
        add(minWordsField, gbc);

		// Comparaison
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblNbMax = new JLabel("Nombre maximum de mots pour être signalé comme plagiat");
        add(lblNbMax, gbc);

        gbc.gridx = 1;
        JTextField txtNBMax = new JTextField(5);
        txtNBMax.setText("50");
        add(txtNBMax, gbc);

        // Couleur
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel colorLabel = new JLabel("Couleur");
        add(colorLabel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel surlignage1Label = new JLabel("Surlignage 1");
        add(surlignage1Label, gbc);

        gbc.gridx = 1;
        JPanel surlignage1Color = new JPanel();
        surlignage1Color.setBackground(Color.RED);
        surlignage1Color.setPreferredSize(new Dimension(50, 20));
        add(surlignage1Color, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        JLabel surlignage2Label = new JLabel("Surlignage 2");
        add(surlignage2Label, gbc);

        gbc.gridx = 1;
        JPanel surlignage2Color = new JPanel();
        surlignage2Color.setBackground(Color.GREEN);
        surlignage2Color.setPreferredSize(new Dimension(50, 20));
        add(surlignage2Color, gbc);

        // Bouton Analyser
        gbc.gridy++;
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JButton analyzeButton = new JButton("Analyser");
        add(analyzeButton, gbc);
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
