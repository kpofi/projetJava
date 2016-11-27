package tests;

import io.LecteurDonnees;
import events.*;

import java.io.FileNotFoundException;
import java.util.zip.DataFormatException;

import simulation.DonneesSimulation;
import simulation.Simulateur;

public class TestSimulateurEvenementMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Données de la simulation
		DonneesSimulation data;
		
		try {
            data = LecteurDonnees.creerDonnees("cartes/carteSujet.map");
            Simulateur simulateur = new Simulateur(data);
            
            /* TEST DE l'EVENEMENT DES PROFS */
            for (int i = 2; i<= 10 ; i += 2) {
            	simulateur.ajouteEvenement(new EvenementMessage(i, "[PING]"));
            }
            for (int i = 3; i<= 9 ; i += 3) {
            	simulateur.ajouteEvenement(new EvenementMessage(i, "[PONG]"));
            }
            
            /* Execute les évènements de la liste */
            
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
		
	}

}
