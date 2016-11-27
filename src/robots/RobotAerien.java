package robots;

import java.util.LinkedList;

import chemins.Sommet;
import elements.Carte;
import elements.Case;
import simulation.Simulateur;

public abstract class RobotAerien extends Robot {
		
	public RobotAerien(Case c, int v, int i) {
		super(c, v, i);
	}
	
	public LinkedList<Sommet> cheminPointEau(Carte carte, Case dep, Case dest, Simulateur simu) {
		LinkedList<Sommet> courtChemin;
		super.setGraphe(carte);
		courtChemin = super.getChemin().listePlusCourtChemin(dep, dest, simu);
		return courtChemin;
	}
	
}
