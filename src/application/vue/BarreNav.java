package application.vue;



import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class BarreNav extends JToolBar implements ActionListener
{

	private FrameAccueil	frameAccueil;
	private JPanel			panel;
	private JButton			btnLogo;
	private JButton			btnPageArriere;
	private JButton			btnPageAvant;

	private JButton			btnTexte1;
	private JButton			btnTexte2;
	private JButton			btnParametreAnalyse;
	private JButton			btnAnalyse;

	public BarreNav(FrameAccueil frameAccueil)
	{

		this.frameAccueil = frameAccueil;
		this.setBackground(new Color(200, 200, 200));
		this.setFocusable(false);
		this.setBackground(FrameAccueil.COULEUR_SECONDAIRE);
		this.setLayout(new BorderLayout());
		
		/*-------------------------------*/
		/* Création des composants       */
		/*-------------------------------*/

		
		this.btnLogo = new JButton(this.getIcon("images/logo.png"));
		this.btnLogo.setToolTipText("Ecureuil");
		this.btnLogo.setActionCommand("logo");
		this.btnLogo.setBackground(FrameAccueil.COULEUR_FOND);
		

		this.btnPageArriere = new JButton(this.getIcon("images/undo.png"));
		this.btnPageArriere.setToolTipText("Page arrière");
		this.btnPageArriere.setActionCommand("pageArriere");
		this.btnPageArriere.setBackground(FrameAccueil.COULEUR_FOND);
		
		this.btnPageAvant = new JButton(this.getIcon("images/redo.png"));
		this.btnPageAvant.setToolTipText("Page Avant");
		this.btnPageAvant.setActionCommand("pageAvant");
		this.btnPageAvant.setBackground(FrameAccueil.COULEUR_FOND);

		this.btnTexte1 = new JButton(this.getIcon("images/texte.png"));
		this.btnTexte1.setToolTipText("Nouveau texte");
		this.btnTexte1.setActionCommand("compare");
		this.btnTexte1.setBackground(FrameAccueil.COULEUR_FOND);

		this.btnTexte2 = new JButton(this.getIcon("images/textes.png"));
		this.btnTexte2.setToolTipText("Texte comparant");
		this.btnTexte2.setActionCommand("comaprants");
		this.btnTexte2.setBackground(FrameAccueil.COULEUR_FOND);
		
		this.btnParametreAnalyse = new JButton(this.getIcon("images/parametre.png"));
		this.btnParametreAnalyse.setToolTipText("Paramêtre de l'analyse");
		this.btnParametreAnalyse.setActionCommand("parametreAna");
		this.btnParametreAnalyse.setBackground(FrameAccueil.COULEUR_FOND);

		this.btnAnalyse = new JButton(this.getIcon("images/analyse.png"));
		this.btnAnalyse.setToolTipText("Analyse");
		this.btnAnalyse.setActionCommand("analyse");
		this.btnAnalyse.setBackground(FrameAccueil.COULEUR_FOND);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.panel.setBackground(FrameAccueil.COULEUR_SECONDAIRE);

		//this.panel.add( this.btnLogo ); // TODO
		this.panel.add( this.btnPageArriere );
		this.panel.add( this.btnPageAvant );
		this.panel.add( this.btnTexte1 );
		this.panel.add( this.btnTexte2 );
		this.panel.add( this.btnParametreAnalyse );
		// this.panel.add( this.btnAnalyse );

		this.add(panel, BorderLayout.CENTER);


		/*-------------------------------*/
		/* Activation des composants     */
		/*-------------------------------*/
		
		this.btnLogo				.addActionListener(this);
		this.btnPageArriere			.addActionListener(this);
		this.btnPageAvant			.addActionListener(this);
		this.btnTexte1				.addActionListener(this);
		this.btnTexte2				.addActionListener(this);
		this.btnParametreAnalyse	.addActionListener(this);
		// this.btnAnalyse				.addActionListener(this);
	}

	@Override
	public void actionPerformed ( ActionEvent e)
	{

		/*                   */
		/* Navigation rapide */
		/*                   */
		if (this.btnPageArriere      == e.getSource()) this.frameAccueil.pagePrecedente();
		if (this.btnPageAvant        == e.getSource()) this.frameAccueil.pageSuivante  ();


		/*                               */
		/* Redirige vers une page direct */
		/*                               */
		
		// Redirection a la première page
		if (this.btnLogo == e.getSource() || this.btnTexte1 == e.getSource()) this.frameAccueil.afficherPage(FrameAccueil.PAGE_ACCUEIL);

		// Redirection a la deuxième page
		if (this.btnTexte2 == e.getSource()) this.frameAccueil.afficherPage(FrameAccueil.PAGE_COMPARAISON);

		// Paramètres
		if (this.btnParametreAnalyse == e.getSource()) this.frameAccueil.afficherPageParametre();

		// Analyse : Pas possible, faut les textes 
		// if (this.btnAnalyse == e.getSource()) this.frameAccueil.pageSuivante();

	}


	
	private ImageIcon getIcon(String resourcePath) 
	{
        URL resource = getClass().getResource(resourcePath);
        if (resource == null) {
			System.out.println("Pas trouvé : " + resourcePath);
            return null;
        }

		Image img = new ImageIcon(resource).getImage();
		img = img.getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);

       return new ImageIcon(img);
    }
}