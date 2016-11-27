package tests;

import io.LecteurDonnees;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.zip.DataFormatException;

import robots.Robot;
import simulation.DonneesSimulation;
import simulation.Simulateur;
import chemins.Sommet;
import elements.Case;
import events.Deplacer;
import events.Remplir;

public class TestRemplir {
	public static void main(String[] args) {
		// Données de la simulation
		DonneesSimulation data;
		
		try {
			data = LecteurDonnees.creerDonnees("cartes/carteSujet.map");
            Simulateur simu = new Simulateur(data);
            
            /* Test de l'evenement deplacer */
            Case d = data.getCarte().getCase(0, 0);
            
            Robot r = data.getTabRobot()[1];
            long date  = simu.getDate();
            date++;
            simu.ajouteEvenement(new Deplacer(date,r,simu,data.getCarte(),d));
            
            Case dest = r.pointEau(data,d);
            
            LinkedList<Sommet> liste = r.cheminPointEau(data.getCarte(), d, dest, simu);
            
            date++;
            for (Sommet s : liste) {
            	Deplacer dep = new Deplacer(date,r,simu,data.getCarte(),s.getSommet());
            	simu.ajouteEvenement(dep);
            	date++;
            }
            
            simu.ajouteEvenement(new Remplir(date,r,dest));
            date++;
            simu.ajouteEvenement(new Deplacer(date,r,simu,data.getCarte(),d));
            
        } catch (FileNotFoundException e) {
            System.out.println("fichier " + args[0] + " inconnu ou illisible");
        } catch (DataFormatException e) {
            System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
        }
		
	}
}
