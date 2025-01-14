package src.application.vue;


import src.application.Controleur;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;


public class BarreNav extends JToolBar implements ActionListener{

	private Controleur		ctrl;
	private JPanel			panel;
	private JButton			btnLogo;
	private JButton			btnPageArriere;
	private JButton			btnPageAvant;

	private JButton			btnTexte1;
	private JButton			btnTexte2;
	private JButton			btnParametreAnalyse;
	private JButton			btnAnalyse;

	public BarreNav(Controleur ctrl) {

		this.ctrl = ctrl;
		this.setBackground(new Color(200, 200, 200));
		this.setFocusable(false);
		this.setBackground(FrameAccueil.COULEUR_SECONDAIRE);
		this.setLayout(new BorderLayout());
		
		/*-------------------------------*/
		/* Création des composants       */
		/*-------------------------------*/

		this.btnLogo = new JButton(new ImageIcon("../images/Logo.png"));
		this.btnLogo.setToolTipText("Ecureuil");
		this.btnLogo.setActionCommand("logo");
		this.btnLogo.setBackground(FrameAccueil.COULEUR_PRIMAIRE);

		this.btnPageArriere = new JButton(new ImageIcon("../../images/undo.png"));
		this.btnPageArriere.setToolTipText("Page arrière");
		this.btnPageArriere.setActionCommand("pageArriere");
		this.btnPageArriere.setBackground(FrameAccueil.COULEUR_PRIMAIRE);
		
		this.btnPageAvant = new JButton(new ImageIcon("../../images/redo.png"));
		this.btnPageAvant.setToolTipText("Page Avant");
		this.btnPageAvant.setActionCommand("pageAvant");
		this.btnPageAvant.setBackground(FrameAccueil.COULEUR_PRIMAIRE);

		this.btnTexte1 = new JButton(new ImageIcon("../images/texte.png"));
		this.btnTexte1.setToolTipText("Nouveau texte");
		this.btnTexte1.setActionCommand("compare");
		this.btnTexte1.setBackground(FrameAccueil.COULEUR_PRIMAIRE);

		this.btnTexte2 = new JButton(new ImageIcon("../images/textes.png"));
		this.btnTexte2.setToolTipText("Texte comparant");
		this.btnTexte2.setActionCommand("comaprants");
		this.btnTexte2.setBackground(FrameAccueil.COULEUR_PRIMAIRE);
		
		this.btnParametreAnalyse = new JButton(new ImageIcon("/application/images/parametre.png"));
		this.btnParametreAnalyse.setToolTipText("Paramêtre de l'analyse");
		this.btnParametreAnalyse.setActionCommand("parametreAna");
		this.btnParametreAnalyse.setBackground(FrameAccueil.COULEUR_PRIMAIRE);

		this.btnAnalyse = new JButton(new ImageIcon("../images/analyse.png"));
		this.btnAnalyse.setToolTipText("Analyse");
		this.btnAnalyse.setActionCommand("analyse");
		this.btnAnalyse.setBackground(FrameAccueil.COULEUR_PRIMAIRE);

		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.panel.setBackground(FrameAccueil.COULEUR_SECONDAIRE);

		this.panel.add( this.btnLogo );
		this.panel.add( this.btnPageArriere );
		this.panel.add( this.btnPageAvant );
		this.panel.add( this.btnTexte1 );
		this.panel.add( this.btnTexte2 );
		this.panel.add( this.btnParametreAnalyse );
		this.panel.add( this.btnAnalyse );

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
		this.btnAnalyse				.addActionListener(this);
	}

	@Override
	public void actionPerformed ( ActionEvent e)
	{
		switch (e.getActionCommand()) {
			case "logo"	-> { }
			case "pageArriere"	-> { }
			case "pageAvant"	-> { }
			case "compare"	-> { }
			case "comaprants"	-> { }
			case "parametreAna"	-> { }
			case "analyse"	-> { }
		}
		this.updateUI();
	}
}