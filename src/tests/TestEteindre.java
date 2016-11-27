package tests;

import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import simulation.DonneesSimulation;
import simulation.Simulateur;
import elements.Case;
import events.Deplacer;
import events.EteindreFeu;

public class TestEteindre {
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
            Case d = data.getTabIncendie()[5].getCase();
            simu.ajouteEvenement(new Deplacer(1,data.getTabRobot()[0],simu,data.getCarte(),d));
            simu.ajouteEvenement(new EteindreFeu(2,data.getTabRobot()[0],data.getTabRobot()[0].getCase(),data.getTabIncendie()[5],simu));
            d = data.getCarte().getCase(2, 4);
            simu.ajouteEvenement(new Deplacer(3,data.getTabRobot()[0],simu,data.getCarte(),d));
            
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
		
	}
}
