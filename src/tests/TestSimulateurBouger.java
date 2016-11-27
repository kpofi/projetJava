package tests;


import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import simulation.DonneesSimulation;
import simulation.Simulateur;
import elements.Case;
import events.Deplacer;

public class TestSimulateurBouger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Données de la simulation
		DonneesSimulation data;
		
		try {
			data = LecteurDonnees.creerDonnees("cartes/carteSujet.map");
            Simulateur simu = new Simulateur(data);
            
            /* Test de l'evenement deplacer */
            Case d = data.getCarte().getCase(2, 3);
            simu.ajouteEvenement(new Deplacer(1,data.getTabRobot()[0],simu,data.getCarte(),d));
            d = data.getCarte().getCase(2, 4);
            simu.ajouteEvenement(new Deplacer(2,data.getTabRobot()[0],simu,data.getCarte(),d));
            d = data.getCarte().getCase(2, 5);
            simu.ajouteEvenement(new Deplacer(3,data.getTabRobot()[0],simu,data.getCarte(),d));
            d = data.getCarte().getCase(2, 6);
            simu.ajouteEvenement(new Deplacer(4,data.getTabRobot()[0],simu,data.getCarte(),d));
            d = data.getCarte().getCase(3, 6);
            simu.ajouteEvenement(new Deplacer(5,data.getTabRobot()[0],simu,data.getCarte(),d));
            d = data.getCarte().getCase(3, 7);
            simu.ajouteEvenement(new Deplacer(6,data.getTabRobot()[0],simu,data.getCarte(),d));
            
            
            /* Execute les évènements de la liste */
            //simu.executeListeEvenement();

            
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
		
	}

}
