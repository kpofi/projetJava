package simulation;

import java.util.ArrayList;

import robots.Robot;
import elements.Carte;
import elements.Case;
import elements.Incendie;


public class DonneesSimulation {

  private Carte c;
  private Incendie[] tabIncendie;
  private Robot[] tabRobots;
  private ArrayList<Case> reserveEau = new ArrayList<Case>();

  public DonneesSimulation(Carte c, Incendie[] i, Robot[] r) {
      this.c = c;
      this.tabIncendie = i;
      this.tabRobots = r;
      
      // on cree la liste de reserve d'eau
      for (int lig = 0; lig < c.getNbLignes(); lig++) {
      	for (int col = 0; col < c.getNbColonnes(); col++) {
      		if (c.getCase(lig, col).getNature().getNom().equals("EAU")) {
      			this.reserveEau.add(c.getCase(lig, col));
      		}
      	}
      }
  }
  
  public DonneesSimulation(Carte c) {
	  this.c =c;
  }
  
  /** accesseurs */
  public Carte getCarte(){
	  return this.c;
  }
  
  public Incendie[] getTabIncendie() {
	  return this.tabIncendie;
  }
  
  public Robot[] getTabRobot() {
	  return this.tabRobots;
  }
  
  public ArrayList<Case> getReserveEau() {
	  return this.reserveEau;
  }

}
