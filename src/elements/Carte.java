package elements;

public class Carte {

  private int tailleCases;

  private Case[][] carte;

  /** Matrice n*m avec n le nombre de lignes et m le nombre de colonnes */
  public Carte(int n, int m, int t) {
    this.tailleCases = t;
    this.carte = new Case[n][m];
    for (int i = 0; i < n; i++) {
    	for (int j = 0; j < m; j++) {
    		this.carte[i][j] = new Case(i,j,NatureTerrain.TERRAIN_LIBRE);
    	}
    }
  }

  public void setCaseCarte(int l, int c, Case ca) {
    this.carte[l][c] = ca;
  }

  public int getNbLignes() {
    return this.carte.length;
  }

  public int getNbColonnes(){
    if(this.carte.length == 0) {
      return 0;
    }
    return this.carte[0].length;
  }

  public int getTailleCases() {
    return this.tailleCases;
  }

  public Case getCase(int l, int c) {
    return carte[l][c];
  }

  public Case getVoisin(Case src, Direction dir) {
    if(dir == Direction.NORD) {
      return this.carte[src.getLigne()-1][src.getColonne()];
    }
    if(dir == Direction.SUD) {
      return this.carte[src.getLigne()+1][src.getColonne()];
    }
    if(dir == Direction.OUEST) {
      return this.carte[src.getLigne()][src.getColonne()+1];
    }
    /** On a obligatoirement dir == Direction.EST */
    return this.carte[src.getLigne()][src.getColonne()-1];
  }

  
  /**
   * permet de savoir s'il existe un voisin dans la direction indiquée
   * @param src
   * @param dir
   * @return
   */
  public boolean voisinExiste(Case src, Direction dir) {
    if(dir == Direction.NORD) {
      /** Pour ne pas sortir de la matrice */
      if(src.getLigne() != 0) {
        return true;
      }
    }
    if(dir == Direction.SUD) {
      if(src.getLigne() <= this.getNbLignes()) {
        return true;
      }
    }
    if(dir == Direction.OUEST) {
      if(src.getColonne() <= this.getNbColonnes()) {
        return true;
      }
    }
    if(dir == Direction.EST) {
      if(src.getColonne() != 0) {
        return true;
      }
    }
    return false;
  }


}
