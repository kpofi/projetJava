package robots;

import java.awt.image.ImageObserver;

import elements.Carte;
import elements.Case;
import elements.Direction;
import elements.Incendie;
import elements.NatureTerrain;
import gui.GUISimulator;
import gui.ImageElement;
import simulation.Simulateur;

public class RobotAPatte extends RobotTerrestre {

	public RobotAPatte(Case position, int vitesse, int i) {
		
		super(position, vitesse, i);
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/(10 +vitesse));
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 10;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/20);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		// En secondes
		this.setTempsRemplissage(0);
		// En litre/sec
		this.setDebit(10);
		// En litre
		this.setReservoir(Integer.MAX_VALUE);
	}
	
	public RobotAPatte(Case position, int i) {
		
		super(position, 30, i);
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = 30;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/40);
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = 30;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/40);
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 10;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/20);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = 30;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/40);
		
		// En secondes
		this.setTempsRemplissage(0);
		// En litre/sec
		this.setDebit(10);
		// En litre
		this.setReservoir(Integer.MAX_VALUE);
		
	}
	
	/** setters */
	public void setVitesse(float vitesse) {
		
		super.vitesse = vitesse;
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 10;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/20);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/(10 + vitesse));
	}
	
	@Override
	public void setReservoir(int reservoir) {
		  super.reservoir = Integer.MAX_VALUE;
	  }
	
	public void setReservoir() {
		super.reservoir = Integer.MAX_VALUE;
	}
	
	
	 // methode pour faire un dessin
	  public void draw(GUISimulator gui, Carte carte) {
		  int x, y;
		  int tailleCases = carte.getTailleCases();
		  x = this.getCase().getLigne() * tailleCases;
		  y = this.getCase().getColonne() * tailleCases;
		  ImageObserver obs = null;
		  gui.addGraphicalElement(new ImageElement(x, y, "images/robots/robotAPatte.jpg", tailleCases, tailleCases, obs));
	  }
	  
	  public void deplacer(Carte carte, Direction direction) {
		  Case cSuiv;
		  
		  switch(direction) {
		  case OUEST:
			  cSuiv = carte.getCase(this.getCase().getLigne() - 1, this.getCase().getColonne());
			  if (!cSuiv.getNature().getNom().equals("EAU")) {
				  this.getCase().setLigne(this.getCase().getLigne() - 1);
			  }
			  break;
		  case EST:
			  cSuiv = carte.getCase(this.getCase().getLigne() + 1, this.getCase().getColonne());
			  if (!cSuiv.getNature().getNom().equals("EAU")) {
				  this.getCase().setLigne(this.getCase().getLigne() + 1);
			  }
			  break;
		  case NORD:
			  cSuiv = carte.getCase(this.getCase().getLigne(), this.getCase().getColonne() + 1);
			  if (!cSuiv.getNature().getNom().equals("EAU")) {
				  this.getCase().setColonne(this.getCase().getColonne() + 1);
			  }
			  break;
		  case SUD:
			  cSuiv = carte.getCase(this.getCase().getLigne(), this.getCase().getColonne() - 1);
			  if (!cSuiv.getNature().getNom().equals("EAU")) {
				  this.getCase().setColonne(this.getCase().getColonne() - 1);
			  }
			  break;
		  default:
		  }
	  }
	  
	  
	  public void deplacer(Carte carte, Case destination) {
		  
		  this.setCase(destination);
	  }
	  
	  public void remplir() {
		  // on ne fait rien car le robot a un reservoir illimité
	  }
	  
	  public void remplir(Carte carte, Case dest, Simulateur simu) {
		  // on ne fait rien car le robot a un reservoir illimité
		  simu.draw();
	  }
	  
	  @Override
	  public void eteindre(Simulateur simu, Incendie incendie) {
		  
		  this.setReservoir(Integer.MAX_VALUE);
	  }
	
}
