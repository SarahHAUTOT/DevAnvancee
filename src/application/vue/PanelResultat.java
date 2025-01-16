package application.vue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;

/** Frame d'accueil
 * @author : Plein de gens
* @version : 1.0.0 - 06/01/2025
* @since : 06/01/2025
*/

public class PanelResultat extends JPanel implements ActionListener
{
	/* ------------------------------------------------------------------------------------------------------ */
	/*                                               Attributs                                                */
	/* ------------------------------------------------------------------------------------------------------ */

	private FrameAccueil frameAccueil;
	private JButton      btnRetour;

	private List<String> lstText;
	private String       comparant;
	private List<String> lstPlagiatDetecte;

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public PanelResultat ( FrameAccueil frame )
	{
		this.frameAccueil = frame;
		this.lstText           = this.frameAccueil.getLstText();
		this.lstPlagiatDetecte = this.frameAccueil.getLstPlagiatDetecte();
		this.comparant         = this.frameAccueil.getComparant();



		/*     PANEL NORD     */
		JPanel panelNorth = new JPanel(new GridLayout(2, 1,0,10));
		panelNorth.add(frame.panelTitre("Aucune similitude détecté "));


		int nbPlag = this.lstPlagiatDetecte.size();
		if (nbPlag > 0)
			panelNorth.add(frame.panelTexte("Nous avons détecté " + nbPlag + " instance"+ (nbPlag > 0 ? "s " : " ") + "de similitude, correspondant à calculer % du text."));
		else
			panelNorth.add(frame.panelTexte("Nous n'avons pas réussi à détecter de similarité selon les données fournis. "));
		
		

		/*     PANEL CENTRE     */

		JScrollPane sp = new JScrollPane();
		this.configureScrollPaneSensitivity(sp);

		if (nbPlag == 0)
		{
			JTextArea text = new JTextArea(this.comparant);
			text.setEditable(false);
			text.setLineWrap(true);
   			text.setWrapStyleWord(true);
			
			Border border = BorderFactory.createLineBorder(FrameAccueil.COULEUR_PRIMAIRE);
    		text.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

			
			
			sp.setViewportView(text);
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		} else {
			
			// TODO : crée le panel central (dans sp) selon comment le truc fonctionnera 

			/*
			 * 
			 */
		}


		/*     PANEL SUD     */
		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.btnRetour = new JButton("Faire une nouvelle analyse");
		panelSouth.add(this.btnRetour);



		/*     AJOUT DES PANELS     */
		this.setLayout(new BorderLayout(10,10));
		this.add(panelNorth , BorderLayout.NORTH );
		this.add(sp         , BorderLayout.CENTER);
		this.add(panelSouth , BorderLayout.SOUTH );

		this.btnRetour.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnRetour) 
			System.out.println("Retour");
			// TODO : Changer pour retourner au panel principal
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
