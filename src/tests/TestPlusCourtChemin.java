package tests;
import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import chemins.TrouverChemin;
import elements.Carte;
import io.LecteurDonnees;
import simulation.DonneesSimulation;
import simulation.Simulateur;


public class TestPlusCourtChemin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Données de la simulation
		DonneesSimulation data;
		
		try {
            data = LecteurDonnees.creerDonnees("cartes/desertOfDeath-20x20.map");
            Simulateur simu = new Simulateur(data);
            
            Carte c = data.getCarte();
            
            // Les robots bougent en même temps!!!!
            TrouverChemin t = new TrouverChemin (c, data.getTabRobot()[0].getTabPonderation());
            t.calculPlusCourtChemin(data.getTabRobot()[0].getCase(), c.getCase(19, 0), simu, 0);

            TrouverChemin t2 = new TrouverChemin (c, data.getTabRobot()[3].getTabPonderation());
            t2.calculPlusCourtChemin(data.getTabRobot()[3].getCase(), c.getCase(19, 0), simu, 3);
            
            TrouverChemin t3 = new TrouverChemin (c, data.getTabRobot()[2].getTabPonderation());
            t3.calculPlusCourtChemin(data.getTabRobot()[2].getCase(), c.getCase(0, 19), simu, 2);
            
            TrouverChemin t4 = new TrouverChemin (c, data.getTabRobot()[1].getTabPonderation());
            t4.calculPlusCourtChemin(data.getTabRobot()[1].getCase(), c.getCase(0, 19), simu, 1);

            TrouverChemin t5 = new TrouverChemin (c, data.getTabRobot()[4].getTabPonderation());
            t5.calculPlusCourtChemin(data.getTabRobot()[4].getCase(), c.getCase(0, 19), simu, 4);
            
            TrouverChemin t6 = new TrouverChemin (c, data.getTabRobot()[5].getTabPonderation());
            t6.calculPlusCourtChemin(data.getTabRobot()[5].getCase(), c.getCase(0, 19), simu, 5);
            
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
		
	}

}
