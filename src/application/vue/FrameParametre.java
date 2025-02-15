package application.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import application.Controleur;

@SuppressWarnings("serial")
public class FrameParametre extends JFrame implements ActionListener
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */

	private JPanel     panelParametre;
    private Controleur ctrl;
    
    /* Valeur des paramètres */

    private static int    nbMinMots = Controleur.BASE_NB_MIN_MOTS;
    private static int    nbMaxMots = Controleur.BASE_NB_MAX_MOTS;

    private static Color  couleur1  = Color.GREEN;
    private static Color  couleur2  = Color.RED;

    /* Composants à écouter et à modifier */

    private JTextField txtNBMin;
    private JTextField txtNBMax;

    private JButton    btnCouleur1;
    private JButton    btnCouleur2;

    private JButton    btnAnnuler;
    private JButton    btnEnregistrer;

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */

    public FrameParametre ( Controleur ctrl ) 
	{
        this.ctrl = ctrl;

        /* Initialisation du panel */
		initialiserPanel();
		
        /* Paramètrage de la frame */
		this.add(this.panelParametre);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
        this.pack();
	}
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Accesseur                                                */
	/* ------------------------------------------------------------------------------------------------------ */

    public static int   getNbMinMots ( ) { return FrameParametre.nbMinMots; }
    public static int   getNbMaxMots ( ) { return FrameParametre.nbMaxMots; }
    public static Color getCouleur1  ( ) { return FrameParametre.couleur1 ; }
    public static Color getCouleur2  ( ) { return FrameParametre.couleur2 ; }
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Modificateur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                                Méthode                                                 */
	/* ------------------------------------------------------------------------------------------------------ */

	/** Méthode permettant d'initialiser le panel et tous ses composants 
	 * Ne retourne rien car le panel est un attribut
	 */
	private void initialiserPanel ()
	{
		this.panelParametre = new JPanel();

		this.panelParametre.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        /* Initialisation des paramètres de la grille */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        /* Titre de la frame et du panel */
        JLabel lblTitre = new JLabel("Paramètres");
        lblTitre.setFont(new java.awt.Font("Arial", Font.BOLD, 14));
        this.panelParametre.add(lblTitre, gbc);

        /* 1ère Ligne : Nombre minimum de mots */
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblNbMin = new JLabel("Nombre minimum de mots pour être signalé comme plagiat");
        this.panelParametre.add(lblNbMin, gbc);

        gbc.gridx = 1;
        this.txtNBMin = new JTextField(5);
        this.txtNBMin.setText("" + FrameParametre.nbMinMots);
        this.panelParametre.add(this.txtNBMin, gbc);

		/* 2ème Ligne : Nombre maximum de mots */
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel lblNbMax = new JLabel("Nombre maximum de mots pour être signalé comme plagiat");
        this.panelParametre.add(lblNbMax, gbc);

        gbc.gridx = 1;
        this.txtNBMax = new JTextField(5);
        this.txtNBMax.setText("" + FrameParametre.nbMaxMots);
        this.panelParametre.add(this.txtNBMax, gbc);

        /* 3ème Ligne : Couleur de surlignage 1 */
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel surlignage1Label = new JLabel("Couleur Surlignage 1");
        this.panelParametre.add(surlignage1Label, gbc);

        gbc.gridx = 1;
        this.btnCouleur1 = new JButton();
        this.btnCouleur1.setBackground(FrameParametre.couleur1);
        this.btnCouleur1.setPreferredSize(new Dimension(60, 20));
        this.panelParametre.add(this.btnCouleur1, gbc);

        /* 4ème Ligne : Couleur de surlignage 2 */
        gbc.gridy++;
        gbc.gridx = 0;
        JLabel surlignage2Label = new JLabel("Couleur Surlignage 2");
        this.panelParametre.add(surlignage2Label, gbc);

        gbc.gridx = 1;
        this.btnCouleur2 = new JButton();
        this.btnCouleur2.setBackground(FrameParametre.couleur2);
        this.btnCouleur2.setPreferredSize(new Dimension(60, 20));
        this.panelParametre.add(this.btnCouleur2, gbc);

        /* 5ème Ligne : Couleur de surlignage 1 */
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.btnAnnuler = new JButton("Annuler");
        this.panelParametre.add(this.btnAnnuler, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        this.btnEnregistrer = new JButton("Enregistrer");
        this.panelParametre.add(this.btnEnregistrer, gbc);

        /* Ajout de la mise en écoute des composants */

        this.btnCouleur1   .addActionListener(this);
        this.btnCouleur2   .addActionListener(this);

        this.btnAnnuler    .addActionListener(this);
        this.btnEnregistrer.addActionListener(this);
	}

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        /* Bouton de couleur */
        if (e.getSource() == this.btnCouleur1 || e.getSource() == this.btnCouleur2)
        {
            JColorChooser selectCouleur = new JColorChooser();

            /* Afficher le panel de sélection de couleur */
            int option = JOptionPane.showConfirmDialog
            (
                null,
                selectCouleur,
                "Choisir une couleur",
                JOptionPane.OK_CANCEL_OPTION
            );

            /* On récupère la couleur si l'utilisateur clique sur OK*/
            if (option == JOptionPane.OK_OPTION) 
            {
                Color selectedColor = selectCouleur.getColor();
                
                /* On vérifie que celle-ci n'est pas nulle */
                if (selectedColor != null)  
                {
                    ((JButton) e.getSource()).setBackground(selectedColor);
                }
            }
        }

        /* Boutons de modifications */
        if (e.getSource() == this.btnAnnuler)
        {
            this.txtNBMin.setText("" + FrameParametre.nbMinMots);
            this.txtNBMax.setText("" + FrameParametre.nbMaxMots);

            this.btnCouleur1.setBackground(FrameParametre.couleur1);
            this.btnCouleur2.setBackground(FrameParametre.couleur2);
        }
        if (e.getSource() == this.btnEnregistrer)
        {
            FrameParametre.nbMaxMots = Integer.parseInt(this.txtNBMax.getText());
            FrameParametre.nbMinMots = Integer.parseInt(this.txtNBMin.getText());

            FrameParametre.couleur1 = this.btnCouleur1.getBackground();
            FrameParametre.couleur2 = this.btnCouleur2.getBackground();
            
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() throws Exception {
                	FrameParametre.this.ctrl.majNombre(FrameParametre.nbMinMots, FrameParametre.nbMaxMots);
                    return null;
                }

                @Override
                protected void done() {
                    FrameParametre.this.ctrl.getFrameAccueil().repaint();
                }
            };
            worker.execute();
            
            this.dispose();
        }
    }
}
