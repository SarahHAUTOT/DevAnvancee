package s6.DevAnvancee.src.application.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;


public class BarreNav extends JToolBar implements ActionListener{

	private Controleur		ctrl;
	private JPanel			panel;
	private JButton			btnPageArriere;
	private JButton			btnPageAvant;

	public BarreNav(Controleur ctrl) {

		this.ctrl = ctrl;
		this.setBackground(new Color(200, 200, 200));
		this.setFocusable(false);
		this.setLayout(new BorderLayout());
		
		/*-------------------------------*/
		/* Création des composants       */
		/*-------------------------------*/


		this.btnPageArriere = new JButton(new ImageIcon("/undo.png"));
		this.btnPageArriere.setToolTipText("Défaire");
		this.btnPageArriere.setActionCommand("Undo");
		
		this.btnPageAvant = new JButton(new ImageIcon("/redo.png"));
		this.btnPageAvant.setToolTipText("Refaire");
		this.btnPageAvant.setActionCommand("Redo");


		/*-------------------------------*/
		/* Positionnement des composants */
		/*-------------------------------*/
		
		this.panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		this.panel.add( this.btnPageArriere );
		this.panel.add( this.btnPageAvant );


		this.add(panel, BorderLayout.CENTER);




		/*-------------------------------*/
		/* Activation des composants     */
		/*-------------------------------*/
		this.btnPageArriere	.addActionListener(this);
		this.btnPageAvant	.addActionListener(this);
		
	}
    
}