package application.vue;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;

/**
 * @author  Gabriel Roche
 * @since   
 */
@SuppressWarnings("serial")
public class PlaceholderTextArea extends JTextArea {
	
	public static void main(final String[] args) {
		final PlaceholderTextArea tf = new PlaceholderTextArea("");
		tf.setPlaceholder("All your base are belong to us!");
		final Font f = tf.getFont();
		tf.setFont(new Font(f.getName(), f.getStyle(), 30));
		JOptionPane.showMessageDialog(null, tf);
	}
	
	private String placeholder;
	
	public PlaceholderTextArea() {
        super();
    }
	
    public PlaceholderTextArea(String text) {
        super(text);
    }
    
    public PlaceholderTextArea(int rows, int columns) {
        super(rows, columns);
    }
    
    public PlaceholderTextArea(String text, int rows, int columns) {
        super(text, rows, columns);
    }
    
    public PlaceholderTextArea(Document doc) {
        super(doc);
    }
    
    public PlaceholderTextArea(Document doc, String text, int rows, int columns) {
    	super(doc, text, rows, columns);
    }
	
	public String getPlaceholder() {
		return placeholder;
	}
	
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		if (this.placeholder == null || this.placeholder.length() == 0 || this.getText().length() > 0) {
			return;
		}

		final Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
			RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getDisabledTextColor());
		g2d.drawString(placeholder, this.getInsets().left, g.getFontMetrics().getMaxAscent() + this.getInsets().top);
	}
	
	public void setPlaceholder(String s) {
		this.placeholder = s;
	}

}