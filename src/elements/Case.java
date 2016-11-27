package elements;

import java.awt.image.ImageObserver;

import gui.GUISimulator;
import gui.ImageElement;

public class Case {

  private int ligne;
  private int colonne;
  private NatureTerrain nature;

  /** Constructeurs */
  public Case(int l, int c, NatureTerrain n){
    this.ligne = l;
    this.colonne = c;
    this.nature = n;
  }
  /** Par défaut */
  public Case() {
    this.nature = NatureTerrain.TERRAIN_LIBRE;
    this.ligne = 0;
    this.colonne = 0;
  }
  
  /** setter */
  public void setLigne(int ligne) {
	  this.ligne = ligne;
  }
  
  public void setColonne(int colonne) {
	  this.colonne = colonne;
  }

  /** Accesseurs */
  public int getLigne() {
    return this.ligne;
  }

  public int getColonne() {
    return this.colonne;
  }

  public NatureTerrain getNature() {
    return this.nature;
  }
  
  @Override
  public boolean equals(Object c) {
	  // On compare juste les positions
	  if (c instanceof Case) {
		  Case tmp = (Case) c;
		  if (this.ligne == tmp.getLigne() && this.colonne == tmp.getColonne()) {
			  return true;
		  }
		  return false;
	  }
	  else {
		  return false;
	  }
  }
  
  public void draw(GUISimulator gui, Carte c) {
	  int x, y;
	  int tailleCases = c.getTailleCases();
	  
	  x = this.ligne * tailleCases;
	  y = this.colonne * tailleCases;
	  ImageObserver obs = null;
	  gui.addGraphicalElement(new ImageElement(x, y, this.nature.getFileName(), tailleCases, tailleCases, obs));

  }

}
