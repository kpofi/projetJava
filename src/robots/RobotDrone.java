package robots;

import java.awt.image.ImageObserver;

import elements.Carte;
import elements.Case;
import elements.Direction;
import elements.NatureTerrain;
import gui.GUISimulator;
import gui.ImageElement;

public class RobotDrone extends RobotAerien {

	public RobotDrone(Case position, int vitesse, int i) {
		super(position, vitesse, i);
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		// En secondes
		this.setTempsRemplissage(1800);
		// En litre/sec
		this.setDebit(333.3f);
		// En litre
		this.setReservoir(10000);
		
	}
	
	public RobotDrone(Case position, int i) {
		super(position, 100, i);
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 100;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/110);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = 100;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/110);
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = 100;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/110);
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 100;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/110);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = 100;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/110);
		
		// En secondes
		this.setTempsRemplissage(1800);
		// En litre/sec
		this.setDebit(333.3f);
		// En litre
		this.setReservoir(10000);
	}
	
	/** setters */
	public void setVitesse(float vitesse) {
		
		super.vitesse = vitesse;
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/(10 + vitesse));
		
	}
	
	public void setReservoir() {
		super.reservoir = 10000;
	}
	
	 // methode pour faire un dessin
	  public void draw(GUISimulator gui, Carte carte) {
		  int x, y;
		  int tailleCases = carte.getTailleCases();
		  x = this.getCase().getLigne() * tailleCases;
		  y = this.getCase().getColonne() * tailleCases;
		  ImageObserver obs = null;
		  gui.addGraphicalElement(new ImageElement(x, y, "images/robots/robotDrone.jpg", tailleCases, tailleCases, obs));
	  }
	
	  public void deplacer(Carte carte, Direction direction) {
		  
		  switch(direction) {
		  case OUEST:
			  this.getCase().setLigne(this.getCase().getLigne() - 1);
			  break;
		  case EST:
			  this.getCase().setLigne(this.getCase().getLigne() + 1);
			  break;
		  case NORD:
			  this.getCase().setColonne(this.getCase().getColonne() + 1);
			  break;
		  case SUD:
			  this.getCase().setColonne(this.getCase().getColonne() -1);
			  break;
		  default:
		  }
	  }
	  
	  
	  public void deplacer(Carte carte, Case destination) {
		  
		  this.setCase(destination);
	  }
	  
	  public void remplir() {
		  
		  this.setReservoir(10000);
	  }
	  
	
}
