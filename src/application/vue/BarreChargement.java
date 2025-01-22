package application.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serial;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 * @author  Gabriel Roche
 * @since   
 */
public class BarreChargement extends JDialog {

	@Serial
	private static final long serialVersionUID = -8490159629252832349L;
	
	private final JProgressBar barreChargement;
	
	public BarreChargement(int min, int max) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setSize(350, 250);
		this.setLocationRelativeTo(null);
		super.setAlwaysOnTop(true);
		super.setResizable(false);
		
		this.barreChargement = new JProgressBar(JProgressBar.HORIZONTAL, min, max);
		this.barreChargement.setBounds(25, 75, 285, 50);
		this.barreChargement.setStringPainted(true);
		
		JPanel panel = new JPanel(null);
		panel.add(this.barreChargement);
		
		this.add(panel, BorderLayout.CENTER);
		
//		this.setVisible(true);
	}
	
	public void close() {
		this.setVisible(false);
		this.dispose();
	}
	
	public boolean chargementEnCours() {
		return this.getEtatChargement() != this.barreChargement.getMinimum();
	}
	
	public boolean chargementEstTermine() {
		return this.getEtatChargement() == this.barreChargement.getMaximum();
	}
	
	public int getEtatChargement() {
		return this.barreChargement.getValue();
	}
	
	public boolean setValeur(int value) {
		if (value < this.barreChargement.getMinimum())
			return false;
		if (value > this.barreChargement.getMaximum())
			return false;
		this.barreChargement.setValue(value);
		return true;
	}
	
	public synchronized void charger(ChargementBarre action) {
		this.charger(action, null);
	}
	
	/**
	 * <b>/!\ [WORK IN PROGRESS] /!\</b>
	 * 
	 * @param action
	 * @param onFinish
	 */
	public synchronized void charger(ChargementBarre action, Runnable onFinish) {
		if (action == null)
			throw new IllegalArgumentException("action cannot be null!");
		SwingWorker<Void, Integer> wrkr = new SwingWorker<>() {
			@Override
			protected Void doInBackground() throws Exception {
				while (action.statut.estEnCours()) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					publish(action.chargement());
					BarreChargement.this.repaint();
				}
				return null;
			}
			
			@Override
			protected void process(List<Integer> chunks) {
				for (int progress : chunks)
					BarreChargement.this.setValeur(progress);
			}
			
			@Override
			protected void done() {
				BarreChargement.this.setValeur(0);
				if (onFinish != null)
					onFinish.run();
			}
		};
		wrkr.execute();
	}
	
	@Override
	public void setResizable(boolean resizable) {}
	
	
	@Override
	public void setSize(int width, int height) {}
	
	@Override
	public void setSize(Dimension d) {}
	
	// TODO: réviser ceci
	public static abstract class ChargementBarre {
		
		public final Statut statut;
		
		public ChargementBarre(Statut statut) {
			if (statut == null)
				throw new IllegalArgumentException("Statut interface cannot be null!");
			this.statut = statut;
		}
		
		public abstract int chargement();
		
	}
	
	public static interface Statut {
		
		public boolean estEnCours();
		
	}
	
}
