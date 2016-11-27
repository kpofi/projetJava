package robots;
import java.util.LinkedList;

import chemins.Sommet;
import elements.Carte;
import elements.Case;
import simulation.Simulateur;

public abstract class RobotTerrestre extends Robot {
	
		
	public RobotTerrestre(Case c, int v, int i) {
		super(c, v, i);
	}
	
	public LinkedList<Sommet> cheminPointEau(Carte carte, Case dep, Case dest, Simulateur simu) {
		LinkedList<Sommet> courtChemin;
		
		Case d = dest;
		if ((dest.getLigne() + 1 < carte.getNbLignes())&&!carte.getCase(dest.getLigne() + 1, dest.getColonne()).getNature().getNom().equals("EAU")){
			d = carte.getCase(dest.getLigne() + 1, dest.getColonne());
		}
		else if ((dest.getLigne() > 0)&&!carte.getCase(dest.getLigne() - 1, dest.getColonne()).getNature().getNom().equals("EAU")) {
			d = carte.getCase(dest.getLigne() - 1, dest.getColonne());
		}
		else if ((dest.getColonne() + 1 < carte.getNbColonnes())&&!carte.getCase(dest.getLigne(), dest.getColonne() + 1).getNature().getNom().equals("EAU")) {
			d = carte.getCase(dest.getLigne(), dest.getColonne() + 1);
		}
		else if (dest.getColonne() > 0){
			d = carte.getCase(dest.getLigne(), dest.getColonne() - 1);
		}
		
		courtChemin = super.getChemin().listePlusCourtChemin(dep, d, simu);
		return courtChemin;
	}
	
}
