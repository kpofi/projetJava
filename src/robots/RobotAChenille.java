package robots;
import java.awt.image.ImageObserver;

import elements.Carte;
import elements.Case;
import elements.Direction;
import elements.NatureTerrain;
import gui.GUISimulator;
import gui.ImageElement;

public class RobotAChenille extends RobotTerrestre {
	
	public RobotAChenille(Case position, int vitesse, int i) {
		
		super(position, vitesse, i);
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int)(super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = (int)(vitesse/2);
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int)(super.poid/(10 + vitesse/2));
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = vitesse;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = vitesse;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		// En secondes
		this.setTempsRemplissage(300);
		// En litre/sec
		this.setDebit(12.5f);	
		// En litre
		this.setReservoir(2000);
	}
	
	public RobotAChenille(Case position, int i) {
		super(position, 60, i);
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = 30;
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/40);
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = 60;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/70);
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = 60;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/70);
		
		// En secondes
		this.setTempsRemplissage(300);
		// En litre/sec
		this.setDebit(12.5f);
		// En litre
		this.setReservoir(2000);	
	}
	
	/** setters */
	public void setVitesse(float vitesse) {
		
		super.vitesse = vitesse;
		
		super.tabVitesse[NatureTerrain.EAU.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.EAU.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.FORET.ordinal()] = (int) (vitesse/2);
		super.tabPonderation[NatureTerrain.FORET.ordinal()] = (int) (super.poid/(10 + vitesse/2));
		
		super.tabVitesse[NatureTerrain.HABITAT.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.HABITAT.ordinal()] = (int) (super.poid/(10 + vitesse));
		
		super.tabVitesse[NatureTerrain.ROCHE.ordinal()] = 0;
		super.tabPonderation[NatureTerrain.ROCHE.ordinal()] = (int) (super.poid/10);
		
		super.tabVitesse[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) vitesse;
		super.tabPonderation[NatureTerrain.TERRAIN_LIBRE.ordinal()] = (int) (super.poid/(10 + vitesse));
		
	}
	
	public void setReservoir() {
		this.setReservoir(2000);
	}
	
	 // methode pour faire un dessin
	  public void draw(GUISimulator gui, Carte carte) {
		  int x, y;
		  int tailleCases = carte.getTailleCases();
		  x = this.getCase().getLigne() * tailleCases;
		  y = this.getCase().getColonne() * tailleCases;
		  ImageObserver obs = null;
		  gui.addGraphicalElement(new ImageElement(x, y, "images/robots/robotAChenille.jpg", tailleCases, tailleCases, obs));

	  }
	  
	  public void deplacer(Carte carte, Direction direction) {
		  Case cSuiv;
		  
		  switch(direction) {
		  case OUEST:
			  cSuiv = carte.getCase(this.getCase().getLigne() - 1, this.getCase().getColonne());
			  if (!cSuiv.getNature().getNom().equals("EAU")&&!cSuiv.getNature().getNom().equals("ROCHE")) {
				  this.getCase().setLigne(this.getCase().getLigne() - 1);
			  }
			  break;
		  case EST:
			  cSuiv = carte.getCase(this.getCase().getLigne() + 1, this.getCase().getColonne());
			  if (!cSuiv.getNature().getNom().equals("EAU")&&!cSuiv.getNature().getNom().equals("ROCHE")) {
				  this.getCase().setLigne(this.getCase().getLigne() + 1);
			  }			  
			  break;
		  case NORD:
			  cSuiv = carte.getCase(this.getCase().getLigne(), this.getCase().getColonne() + 1);
			  if (!cSuiv.getNature().getNom().equals("EAU")&&!cSuiv.getNature().getNom().equals("ROCHE")) {
				  this.getCase().setColonne(this.getCase().getColonne() + 1);
			  }			  
			  break;
		  case SUD:
			  cSuiv = carte.getCase(this.getCase().getLigne(), this.getCase().getColonne() - 1);
			  if (!cSuiv.getNature().getNom().equals("EAU")&&!cSuiv.getNature().getNom().equals("ROCHE")) {
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
		  
		  this.setReservoir(2000);
	  }

}
