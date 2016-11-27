import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import commandant.ChefPompier;
import io.LecteurDonnees;
import simulation.DonneesSimulation;
import simulation.Simulateur;

public class Resolution {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Données de la simulation
		DonneesSimulation data;
		
		try {
            data = LecteurDonnees.creerDonnees("cartes/desertOfDeath-20x20.map");
            Simulateur simu = new Simulateur(data);
            
            ChefPompier p = new ChefPompier(data, simu);
            p.resoudreLeProbleme();
            
            

            
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
		
	}

}
