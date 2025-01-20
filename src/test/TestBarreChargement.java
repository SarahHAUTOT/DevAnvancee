package test;

import application.vue.BarreChargement;
import application.vue.BarreChargement.ChargementBarre;

/**
 * @author  Gabriel Roche
 * @since   
 */
public class TestBarreChargement {
	
	private static int i = 0;
	
	/**
	 * @param args
	 * @since 
	 */
	public static void main(String[] args) {
		BarreChargement b = new BarreChargement(0, 100);
		b.setTitle("Titre chargement");
		b.setVisible(true);
		
		i = 0;
		
		b.charger(new ChargementBarre(() -> i < 100) {
			@Override
			public int chargement() {
				
				return ++i;
			}
		});
		
	}
	
}