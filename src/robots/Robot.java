package robots;

import java.util.ArrayList;
import java.util.LinkedList;

import simulation.DonneesSimulation;
import simulation.Simulateur;
import chemins.Sommet;
import chemins.TrouverChemin;
import elements.Carte;
import elements.Case;
import elements.Direction;
import elements.Incendie;
import elements.NatureTerrain;
import gui.GUISimulator;

public abstract class Robot {

  /* Position */
  private Case position;
  
  /* Réservoir en L */
  protected int reservoir;
  
  /* Identifiant de robot */
  private int id;

  /* Vitesse */
  protected float vitesse;
  
  /* temps de remplissage en seconde */
  private int tRemp;
  
  /* debit en litre/sec */
  private float debit;
  
  /* Tableau de vitesse */
  protected int[] tabVitesse = new int[NatureTerrain.values().length];
  
  /* Tableau de ponderation pour le graphe */
  protected int[] tabPonderation = new int[NatureTerrain.values().length];
  
  /* Permet la construction du graphe */
  private TrouverChemin chemin;
  
  /* sert a calculer la ponderation */
  protected int poid = 10000; 

  /**
   * Constructeur
   * @param position permet de reperer le robot
   * @param vitesse vitesse du robot
   * @param id identifiant du robot
   */
  public Robot(Case position, int vitesse, int id) {
    this.position = position;
    this.vitesse = vitesse;
    this.id = id;
  }

  /**
   * crée un graphe à partir d'une carte
   * @param carte
   */
  public void setGraphe (Carte carte) {
	  this.chemin = new TrouverChemin (carte, tabPonderation);
  }

  /* Setter */
  public void setTempsRemplissage(int tRemp) {
	  this.tRemp = tRemp;
  }
  
  public void setDebit(float debit) {
	  this.debit = debit;
  }

  public void setCase(Case position) {
    this.position = position;
  }
  
  public abstract void setVitesse(float vitesse);

  public void setReservoir(int reservoir) {
	  if (reservoir < 0) {
		  this.reservoir = 0;
	  }
	  else {
		  this.reservoir = reservoir;
	  }
  }
  
  public abstract void setReservoir();

  /* Accesseurs */
  public long getTempsDeplacement(Carte carte, Case c) {
	  long tDep;
	  tDep = (long) (10*carte.getTailleCases()/(this.tabVitesse[c.getNature().ordinal()] + 1));
	  
	  return tDep;
  }
  public int getTempsRemplissage() {
	  return this.tRemp;
  }
  
  public int getId() {
	  return this.id;
  }
  
  public float getDebit() {
	  return this.debit;
  }
  
  public Case getCase() {
    return this.position;
  }

  public int getReservoir() {
    return this.reservoir;
  }
  

  public float getVitesse() {
    return this.vitesse;
  }
  
  public int[] getTabVitesse() {
	  return this.tabVitesse;
  }
  
  public int[] getTabPonderation() {
	  return this.tabPonderation;
  }
  
  public int[] getPonderation (Carte carte) {
	  return tabPonderation;
  }
  
  public TrouverChemin getChemin() {
	  return this.chemin;
  }
  
  /**
   * permet de trouver le point d'eau le plus proche de la case de depart
   * @param data
   * @param dep case de depart
   * @return renvoie le point d'eau
   */
  public Case pointEau(DonneesSimulation data, Case dep) {
		Case posRob = dep;
		ArrayList<Case> reserveEau = data.getReserveEau();
		double distMin, dist;
		int indice = 0;
		
		if (reserveEau.size() > 0) {
			distMin = Math.pow(posRob.getLigne() - reserveEau.get(0).getLigne(), 2) + Math.pow(posRob.getColonne() - reserveEau.get(0).getColonne(), 2);
		}
		else {
			return posRob;
		}
		
		for (int i = 0; i < reserveEau.size(); i++) {
			dist = Math.pow(posRob.getLigne() - reserveEau.get(i).getLigne(), 2) + Math.pow(posRob.getColonne() - reserveEau.get(i).getColonne(), 2);
			if (dist < distMin) {
				distMin = dist;
				indice = i;
			}
		}
		
		return reserveEau.get(indice);
	}
  
  /**
   * renvoie le chémin jusqu'au point d'eau
   * @param carte
   * @param dep
   * @param dest
   * @param simu
   * @return
   */
  public abstract LinkedList<Sommet> cheminPointEau(Carte carte, Case dep, Case dest, Simulateur simu);
  
  public abstract void remplir();
  
  public void remplir(Case dest) {
	  if (dest.getNature().getNom().equals("EAU")) {
		  this.remplir();
	  }
  }
  
  /**
   * decremente le reservoir du robot
   * @param simu
   * @param incendie
   */
  public void eteindre(Simulateur simu, Incendie incendie) {
	  
	  if (this.reservoir > incendie.getLitres())
		  this.setReservoir(this.reservoir - incendie.getLitres());
	  else {
		  this.setReservoir(0);
	  }
  }
  
  /**
   * calcul le temps de deplacement
   * @param carte
   * @return
   */
  public int tempsDeplacement(Carte carte) {
	  
	  int denom = this.tabVitesse[this.position.getNature().ordinal()] +1;
	  return (int) (carte.getTailleCases()/denom);
  }
  

  /**
   * dessine la carte
   * @param gui
   * @param carte
   */
  public abstract void draw(GUISimulator gui, Carte carte);
  
  /**
   * deplace le robot selon une direction
   * @param carte
   * @param direction
   */
  public abstract void deplacer(Carte carte, Direction direction);
  
  /**
   * deplace le robot vers la case destination
   * @param carte
   * @param destination
   */
  public abstract void deplacer(Carte carte, Case destination);
  
  public int distance(Case a, Case b) {
	  return (int)(Math.pow(a.getLigne() - b.getLigne(), 2) + Math.pow(a.getColonne() - b.getColonne(), 2));
  }

}	
