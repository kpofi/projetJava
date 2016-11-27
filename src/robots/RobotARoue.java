package robots;

import java.awt.image.ImageObserver;

import elements.Carte;
import elements.Case;
import elements.Direction;
import elements.NatureTerrain;
import gui.GUISimulator;
import gui.ImageElement;

public class RobotARoue extends RobotTerrestre {

	public RobotARoue(Case position, int vitesse, int i) {
		super(position, vitesse, i);
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) vitesse;
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		// En secondes
		this.setTempsRemplissage(600);
		// En litre/sec
		this.setDebit(20f);
		// En litre
		this.setReservoir(5000);
	}
	
	public RobotARoue(Case position, int i) {
		super(position, 80, i);
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = 80;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/90);
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = 80;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/90);
		
		// En secondes
		this.setTempsRemplissage(600);
		// En litre/sec
		this.setDebit(20f);	
		// En litre
		this.setReservoir(5000);
	}
	
	/** setters */
	public void setVitesse(float vitesse) {
		
		super.vitesse = vitesse;
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int)vitesse;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/(10 + vitesse));
	}
	
	public void setReservoir() {
		super.reservoir = 5000;
	}
	 // methode pour faire un dessin
	  public void draw(GUISimulator gui, Carte carte) {
		  int x, y;
		  int tailleCases = carte.getTailleCases();
		  x = this.getCase().getLigne() * tailleCases;
		  y = this.getCase().getColonne() * tailleCases;
		  ImageObserver obs = null;
		  gui.addGraphicalElement(new ImageElement(x, y, "images/robots/robotARoue.jpg", tailleCases, tailleCases, obs));

	  }
	  
	  public void deplacer(Carte carte, Direction direction) {
		  Case cSuiv;
		 
		  switch(direction) {
		  case OUEST:
			  cSuiv = carte.getCase(this.getCase().getLigne() - 1, this.getCase().getColonne());
			  if (cSuiv.getNature().getNom().equals("TERRAIN_LIBRE")||cSuiv.getNature().getNom().equals("HABITAT")) {
				  this.getCase().setLigne(this.getCase().getLigne() - 1);
			  }
			  break;
		  case EST:
			  cSuiv = carte.getCase(this.getCase().getLigne() + 1, this.getCase().getColonne());
			  if (cSuiv.getNature().getNom().equals("TERRAIN_LIBRE")||cSuiv.getNature().getNom().equals("HABITAT")) {
				  this.getCase().setLigne(this.getCase().getLigne() + 1);
			  }			  
			  break;
		  case NORD:
			  cSuiv = carte.getCase(this.getCase().getLigne(), this.getCase().getColonne() + 1);
			  if (cSuiv.getNature().getNom().equals("TERRAIN_LIBRE")||cSuiv.getNature().getNom().equals("HABITAT")) {
				  this.getCase().setColonne(this.getCase().getColonne() + 1);
			  }			  
			  break;
		  case SUD:
			  cSuiv = carte.getCase(this.getCase().getLigne(), this.getCase().getColonne() - 1);
			  if (cSuiv.getNature().getNom().equals("TERRAIN_LIBRE")||cSuiv.getNature().getNom().equals("HABITAT")) {
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
		  
		  this.setReservoir(5000);
	  }
	
}
