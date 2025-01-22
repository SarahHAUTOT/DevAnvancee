package application.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import application.metier.Correspondance;
import application.metier.PlageDeMots;

/**
 * Frame d'accueil
 * @author : Plein de gens
 * @version : 1.0.0 - 06/01/2025
 * @since : 06/01/2025
 */
@SuppressWarnings("serial")
public class PanelResultat extends JPanel implements ActionListener
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */

	private FrameAccueil frameAccueil;
	private JButton      btnAccueil;

	private String       compare;
	private List<Correspondance> lstPlagiatDetecte;

	private final static int NB_CARA = 15;

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */

	public PanelResultat ( FrameAccueil frame ) { this.frameAccueil = frame; }


	public void genererAffichage ( )
	{
		this.removeAll();

		this.lstPlagiatDetecte = this.frameAccueil.getLstPlagiatDetecte();
		this.compare         = this.frameAccueil.getCompare().getTextOriginal();


		/*     PANEL NORD     */
		JPanel panelNorth = new JPanel(new GridLayout(2, 1,0,10));


		int nbPlag = this.lstPlagiatDetecte.size();
		if (nbPlag > 0)
		{
			panelNorth.add(this.frameAccueil.panelTitre("Oh oh ! Il semblerait que ces textes se ressemblent..."));
			panelNorth.add(this.frameAccueil.panelSousTitre("Nous avons détecté " + nbPlag + " instance"+ (nbPlag > 1 ? "s " : " ") + "de similitude."));
		}
		else
		{
			panelNorth.add(this.frameAccueil.panelTitre("Aucune similitude détecté "));
			panelNorth.add(this.frameAccueil.panelSousTitre("Nous n'avons pas réussi à détecter de similarité selon les données fournis. "));
		}
		
		

		/*     PANEL CENTRE     */

		JScrollPane sp = new JScrollPane();
		this.configureScrollPaneSensitivity(sp);
		
				
		Border border = BorderFactory.createLineBorder(FrameAccueil.COULEUR_PRIMAIRE);
		border        = BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10));

		if (nbPlag == 0)
		{
			JTextArea text = new JTextArea(this.compare);
			text.setEditable(false);
			text.setLineWrap(true);
   			text.setWrapStyleWord(true);
			
    		text.setBorder(border);

			sp.setViewportView(text);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		} else {
			
			JPanel panelGrid = new JPanel(new GridLayout(nbPlag, 2, 0, 10));

			for (Correspondance correspondance : this.lstPlagiatDetecte)
			{
				/*       */
				/* TEXTE */
				/*       */

				PlageDeMots pmCompare = correspondance.getCompareRange();
				PlageDeMots pmComparant = correspondance.getComparantRange();


				int debSus = pmCompare.getStart()                       < PanelResultat.NB_CARA     ? 0                     : pmCompare.getStart()  - PanelResultat.NB_CARA;
				int finSus = pmCompare.getEnd() + PanelResultat.NB_CARA > this.compare.length() - 1 ? this.compare.length() : pmCompare.getEnd()    +  PanelResultat.NB_CARA;
				String sSus = this.compare.substring(debSus, finSus);


				String ref = correspondance.getTexteComparant().getTextOriginal();	

				int debRef = pmComparant.getStart()                       < PanelResultat.NB_CARA ? 0            : pmComparant.getStart() - PanelResultat.NB_CARA;
				int finRef = pmComparant.getEnd() + PanelResultat.NB_CARA > ref.length() - 1      ? ref.length() : pmComparant.getEnd()   + PanelResultat.NB_CARA;
				String sRef = ref.substring(debRef, finRef);
			
				/*          */
				/* COUPABLE */
				/*          */ 

				// JScrollPane du coupable
				JScrollPane spSus = new JScrollPane();
				this.configureScrollPaneSensitivity(spSus);

				// JTextArea coupable
				JTextArea textSus = new JTextArea(sSus);
				textSus.setEditable(false);
				textSus.setLineWrap(true);
				textSus.setWrapStyleWord(true);
				textSus.setBorder(border);

				// Surligner une portion spécifique dans le texte suspect
				Highlighter highlighterSus = textSus.getHighlighter();
				HighlightPainter painterSus = new DefaultHighlighter.DefaultHighlightPainter(this.frameAccueil.getCouleur1());
				try {
					// | 15 et que higlhit start a 20, faut calculer 5
					// | commence à 15; finit à 30, finit à 25 debase 
					highlighterSus.addHighlight(pmCompare.getStart() - debSus, pmCompare.getEnd() - debSus + 1, painterSus);
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Ajout
				spSus.setViewportView(textSus);



				
			
				/*           */
				/* REFERENCE */
				/*           */

				// Panel de la référence
				JScrollPane spRef = new JScrollPane();
				this.configureScrollPaneSensitivity(spRef);

				JTextArea textRef = new JTextArea(sRef);
				textRef.setEditable(false);
				textRef.setLineWrap(true);
				textRef.setWrapStyleWord(true);


				// Surligner une portion spécifique dans le texte suspect
				Highlighter      highlighterRef = textRef.getHighlighter();
				HighlightPainter painterRef     = new DefaultHighlighter.DefaultHighlightPainter(this.frameAccueil.getCouleur2());
				try {
					highlighterRef.addHighlight(pmComparant.getStart() - debRef	, pmComparant.getEnd() - debRef + 1, painterRef);
				} catch (Exception e) {
					e.printStackTrace();
				}

				spRef.setViewportView(textRef);
				textRef.setBorder(border);


				panelGrid.add(spSus);
				panelGrid.add(spRef);
			}

			sp.setViewportView(panelGrid);
		}


		/*     PANEL SUD     */
		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.btnAccueil = new JButton("Faire une nouvelle analyse");
		panelSouth.add(this.btnAccueil);



		/*     AJOUT DES PANELS     */
		this.setLayout(new BorderLayout(10,10));
		this.add(panelNorth , BorderLayout.NORTH );
		this.add(sp         , BorderLayout.CENTER);
		this.add(panelSouth , BorderLayout.SOUTH );

		this.btnAccueil.addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnAccueil)
		{
			this.frameAccueil.afficherPage(FrameAccueil.PAGE_ACCUEIL);
			this.frameAccueil.reinitialiserMetier();
		}
	}
	
	

	private void configureScrollPaneSensitivity(JScrollPane scrollPane)
	{
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();

		verticalScrollBar  .setUnitIncrement(16);  
		horizontalScrollBar.setUnitIncrement(16);

		verticalScrollBar  .setBlockIncrement(100);   
		horizontalScrollBar.setBlockIncrement(100); 
	}
}
