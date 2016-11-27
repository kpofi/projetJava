package elements;

import java.awt.image.ImageObserver;

import gui.GUISimulator;
import gui.ImageElement;

public class Incendie{

  private int nbLitres;
  private Case c;
  
  /** constructeur */
  public Incendie (Case c, int nbLitres) {
	  this.c = c;
	  this.nbLitres = nbLitres;
  }
  
  /** setters */
  public void setNbLitre(int nbLitre) {
	  if (nbLitre < 0) {
		  this.nbLitres = 0;
	  }
	  else {
		  this.nbLitres = nbLitre;
	  }
  }

  /** accesseurs */
  public int getLitres() {
    return this.nbLitres;
  }
  
  public Case getCase() {
	  return this.c;
  }
  
  public void draw(GUISimulator gui, Carte c) {
	  int x, y;
	  int tailleCases = c.getTailleCases(); 
	  x = this.c.getLigne() * tailleCases;
	  y = this.c.getColonne() * tailleCases;
	  ImageObserver obs = null;
	  if (this.nbLitres > 0) {
		  gui.addGraphicalElement(new ImageElement(x, y, "images/elements/incendie.jpg", tailleCases, tailleCases, obs));
	  }
	  else {
		  
		  gui.addGraphicalElement(new ImageElement(x, y, "images/elements/terrainLibre.jpg", tailleCases, tailleCases, obs));
	  }
	  
  }

}
