package src.application.vue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private String       comaprant;
	private List<String> lstPlagiatDetecte;

	/* ------------------------------------------------------------------------------------------------------ */
	/*                                              Constructeur                                              */
	/* ------------------------------------------------------------------------------------------------------ */
	
	public PanelResultat ( FrameAccueil frame )
	{
		this.frameAccueil = frame;



		/*     PANEL NORD     */
		JPanel panelNorth = new JPanel(new GridLayout(2, 1,0,50));
		panelNorth.add(new JLabel("Aucune similitude détecté "));
		panelNorth.add(new JLabel("Nous n'avons pas réussi à détecter de similarité selon les données fournis. "));
		
		

		/*     PANEL CENTRE     */
		this.lstText           = this.frameAccueil.getLstText();
		this.lstPlagiatDetecte = this.frameAccueil.getLstPlagiatDetecte();
		JPanel panelCenter = new JPanel(new GridLayout(this.lstText.size(), this.lstPlagiatDetecte.size() > 0 ? 2 : 1 , 50, 0));


		for (String texte : this.lstText) 
		{
			JTextPane textPane = new JTextPane();
			textPane.setEditable(false);

			StyledDocument doc = textPane.getStyledDocument();
			SimpleAttributeSet defaultAttr = new SimpleAttributeSet();
			SimpleAttributeSet highlightAttr = new SimpleAttributeSet();
			
			StyleConstants.setBackground(highlightAttr, Color.YELLOW);

			try 
			{
				doc.remove(0, doc.getLength());
				doc.insertString(0, texte, defaultAttr);

				for (String plagiat : this.lstPlagiatDetecte) {
					int index = texte.indexOf(plagiat);
					while (index >= 0) 
					{
						// int startContext = Math.max(0, index - 20);
						// int endContext = Math.min(texte.length(), index + plagiat.length() + 20);
						doc.setCharacterAttributes(index, plagiat.length(), highlightAttr, false);
						index = texte.indexOf(plagiat, index + plagiat.length());
					}
				}
			} catch (BadLocationException e) {
				e.printStackTrace();
			}

			panelCenter.add(new JScrollPane(textPane));

			if (!this.lstPlagiatDetecte.isEmpty()) 
			{
				JTextPane contextPane = new JTextPane();
				contextPane.setEditable(false);
				StringBuilder contextText = new StringBuilder();

				for (String plagiat : this.lstPlagiatDetecte) 
				{
					if (texte.contains(plagiat)) {
						int index = texte.indexOf(plagiat);
						while (index >= 0) 
						{
							int startContext = Math.max(0, index - 20);
							int endContext = Math.min(texte.length(), index + plagiat.length() + 20);
							contextText.append("... ")
								.append(texte.substring(startContext, endContext))
								.append(" ...\n");
							index = texte.indexOf(plagiat, index + plagiat.length());
						}
					}
				}
				contextPane.setText(contextText.toString());
				panelCenter.add(new JScrollPane(contextPane));
			}
		}



		/*     PANEL SUD     */
		JPanel panelSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.btnRetour = new JButton("Faire une nouvelle analyse");
		panelSouth.add(this.btnRetour);



		/*     AJOUT DES PANELS     */
		this.setLayout(new BorderLayout(10,10));
		this.add(panelNorth , BorderLayout.NORTH );
		this.add(panelCenter, BorderLayout.CENTER);
		this.add(panelSouth , BorderLayout.SOUTH );

		this.btnRetour.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnRetour) {
			// TODO : Changer pour retourner au panel principal
		}
	}

	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.add(new PanelResultat(new FrameAccueil()));
		frame.pack();
		frame.setVisible(true);
	}
}
