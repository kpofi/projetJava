package chemins;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import elements.Carte;
import elements.Case;
import elements.NatureTerrain;
import events.Deplacer;
import robots.Robot;
import simulation.Simulateur;

public class TrouverChemin {

    private List<Sommet> sommets;
    private List<Arc> arcs;
    private Carte c;
    private int[] ponderation = new int[NatureTerrain.values().length];
    private Graphe g;
    
    public TrouverChemin(Carte c, int [] p){
    	this.c = c;
    	this.ponderation = p;
    	this.constructionDuGraphe();
    }
    
    public void constructionDuGraphe() {
    	
    	sommets = new ArrayList<Sommet>();
    	int n = c.getNbLignes();
    	int m = c.getNbColonnes();
    	Case ca;
    	Sommet ajout;
    	

    	int compteur = 0;
    	/* On créer n*m sommets */
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			/* On récupère le sommet */
    			ca = c.getCase(i, j);
    			ajout = new Sommet(ca, String.valueOf(compteur));
    			sommets.add(compteur, ajout);
    			compteur ++;
    		}
    	}

    	/* On va créer les arcs  du contour */
    	arcs = new ArrayList<Arc>();
    	int numArc = 0;
    	int indexCourant;
    	Sommet courant;
    	Sommet voisinDroit;
    	Sommet voisinGauche;
    	Sommet voisinBas;
    	Sommet voisinHaut;
    	
    	/* Première colonne */
    	for (int p = 0; p < n; p++) {
    		indexCourant = p*m;
    		courant = sommets.get(indexCourant);
    		
    		if (p == 0) {
    			
    			voisinBas = sommets.get(indexCourant + m);
    			voisinDroit = sommets.get(indexCourant + 1);
    			arcs.add(new Arc(numArc, courant, voisinDroit, ponderation[voisinDroit.getSommet().getNature().ordinal()]));
    			numArc++;
    			arcs.add(new Arc(numArc, courant, voisinBas, ponderation[voisinBas.getSommet().getNature().ordinal()]));
    			numArc++;
    					
    		} else if (p == n-1) {
    		
    			voisinHaut = sommets.get(indexCourant - m);
    			voisinDroit = sommets.get(indexCourant + 1);
    			arcs.add(new Arc(numArc, courant, voisinDroit, ponderation[voisinDroit.getSommet().getNature().ordinal()]));
    			numArc++;
    			arcs.add(new Arc(numArc, courant, voisinHaut, ponderation[voisinHaut.getSommet().getNature().ordinal()]));
    			numArc++;
    			
    			
    		} else {
    			
    			voisinBas = sommets.get(indexCourant + m);
    			voisinHaut = sommets.get(indexCourant - m);
    			voisinDroit = sommets.get(indexCourant + 1);
    			arcs.add(new Arc(numArc, courant, voisinDroit, ponderation[voisinDroit.getSommet().getNature().ordinal()]));
    			numArc++;
    			arcs.add(new Arc(numArc, courant, voisinHaut, ponderation[voisinHaut.getSommet().getNature().ordinal()]));
    			numArc++;
    			arcs.add(new Arc(numArc, courant, voisinBas, ponderation[voisinBas.getSommet().getNature().ordinal()]));
    			numArc++;
    		}		
    		
    	}

    	/* Dernière colonne */
    	for (int p = 1; p < n+1; p++) {
    		indexCourant = p*m -1;
    		courant = sommets.get(indexCourant);
    		
    		if (p == 1) {
    			
    			voisinBas = sommets.get(indexCourant + m);
    			voisinGauche = sommets.get(indexCourant - 1);
    			arcs.add(new Arc(numArc, courant, voisinGauche, ponderation[voisinGauche.getSommet().getNature().ordinal()]));
    			numArc++;
    			arcs.add(new Arc(numArc, courant, voisinBas, ponderation[voisinBas.getSommet().getNature().ordinal()]));
    			numArc++; 			
    					
    		} else if (p == n) {
    		
    			voisinHaut = sommets.get(indexCourant - m);
    			voisinGauche = sommets.get(indexCourant - 1);
    			arcs.add(new Arc(numArc, courant, voisinGauche, ponderation[voisinGauche.getSommet().getNature().ordinal()]));
    			numArc++;
    			arcs.add(new Arc(numArc, courant, voisinHaut, ponderation[voisinHaut.getSommet().getNature().ordinal()]));
    			numArc++;
    			
    			
    		} else {
    			
    			voisinBas = sommets.get(indexCourant + m);
    			voisinHaut = sommets.get(indexCourant - m);
    			voisinGauche = sommets.get(indexCourant - 1);
    			arcs.add(new Arc(numArc, courant, voisinGauche, ponderation[voisinGauche.getSommet().getNature().ordinal()]));
    			numArc++;
    			arcs.add(new Arc(numArc, courant, voisinHaut, ponderation[voisinHaut.getSommet().getNature().ordinal()]));
    			numArc++;
    			arcs.add(new Arc(numArc, courant, voisinBas, ponderation[voisinBas.getSommet().getNature().ordinal()]));
    			numArc++;
    		}		
    		
    	}

    	/* Première ligne */
    	/* On commence à deux car on a déjà fait la première case */
    	for (int p = 2; p < m; p++) {
    		indexCourant = p;
    		courant = sommets.get(indexCourant);
    			
    		voisinBas = sommets.get(indexCourant + m);
    		voisinGauche = sommets.get(indexCourant - 1);
    		voisinDroit = sommets.get(indexCourant + 1);
    		arcs.add(new Arc(numArc, courant, voisinGauche, ponderation[voisinGauche.getSommet().getNature().ordinal()]));
    		numArc++;
    		arcs.add(new Arc(numArc, courant, voisinDroit, ponderation[voisinDroit.getSommet().getNature().ordinal()]));
    		numArc++;
    		arcs.add(new Arc(numArc, courant, voisinBas, ponderation[voisinBas.getSommet().getNature().ordinal()]));
    		numArc++;		
    		
    	}
    	/* Dernière ligne */
    	for (int p = 1; p < m-1; p++) {
    		indexCourant = (n-1)*m + p;
    		courant = sommets.get(indexCourant);
    			
    		voisinHaut = sommets.get(indexCourant - m);
    		voisinGauche = sommets.get(indexCourant - 1);
    		voisinDroit = sommets.get(indexCourant + 1);
    		arcs.add(new Arc(numArc, courant, voisinGauche, ponderation[voisinGauche.getSommet().getNature().ordinal()]));
    		numArc++;
    		arcs.add(new Arc(numArc, courant, voisinHaut, ponderation[voisinHaut.getSommet().getNature().ordinal()]));
    		numArc++;
    		arcs.add(new Arc(numArc, courant, voisinDroit, ponderation[voisinDroit.getSommet().getNature().ordinal()]));
    		numArc++;		
    		
    	}
    	
    	/* L'intérieur */
    	// On boucle sur la colonne
    	for ( int p = 2; p < m; p++) {
    		// On boucle sur la ligne
    		for ( int b = 1; b < n-1; b++) {
    			indexCourant = m*b + (p-1);
    			courant = sommets.get(indexCourant);
    			voisinHaut = sommets.get(indexCourant - m);
        		voisinGauche = sommets.get(indexCourant - 1);
        		voisinDroit = sommets.get(indexCourant + 1);
        		voisinBas = sommets.get(indexCourant + m);

        		arcs.add(new Arc(numArc, courant, voisinGauche, ponderation[voisinGauche.getSommet().getNature().ordinal()]));
        		numArc++;
        		arcs.add(new Arc(numArc, courant, voisinHaut, ponderation[voisinHaut.getSommet().getNature().ordinal()]));
        		numArc++;
        		arcs.add(new Arc(numArc, courant, voisinBas, ponderation[voisinBas.getSommet().getNature().ordinal()]));
        		numArc++;
        		arcs.add(new Arc(numArc, courant, voisinDroit, ponderation[voisinDroit.getSommet().getNature().ordinal()]));
        		numArc++;        		
    		}   		
    		
    	}
    	
    	g = new Graphe(sommets, arcs);
    	
    }
    
    
    public void calculPlusCourtChemin(Case casDep, Case casArr, Simulateur si, int id) {
    	
    	Dijkstra d = new Dijkstra(g);
    	d.executerAlgo(sommets.get(casDep.getColonne()+c.getNbColonnes()*casDep.getLigne()));
    	LinkedList<Sommet> path = d.getPath(sommets.get((casArr.getColonne()+c.getNbColonnes()*casArr.getLigne())));
        seRendreSurUneCase (path, si, id);
    }
    
    public LinkedList<Sommet> listePlusCourtChemin(Case casDep, Case casArr, Simulateur si) {
    	
    	Dijkstra d = new Dijkstra(g);
    	d.executerAlgo(sommets.get(casDep.getColonne()+c.getNbColonnes()*casDep.getLigne()));
    	LinkedList<Sommet> path = d.getPath(sommets.get((casArr.getColonne()+c.getNbColonnes()*casArr.getLigne())));
    	return path;
    	
    }
    

    public void seRendreSurUneCase (LinkedList<Sommet> p, Simulateur s, int i) {
    	
    	Carte carte = s.getData().getCarte();
    	long d = s.getDate();
		Robot r = s.getData().getTabRobot()[i];
		if (p != null) {
	    	for (Sommet so : p) {
	    		Case c = s.getData().getCarte().getCase(so.getSommet().getLigne() , so.getSommet().getColonne());
	    		Deplacer de = new Deplacer(d, r, s, carte, c);
	    		s.ajouteEvenement(de);
	    		d++;
	    	}
		}
    	
    }
}
