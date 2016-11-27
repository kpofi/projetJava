package chemins;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

     private final List<Arc> arcs;
     private Set<Sommet> noeudsvisites;
     private Set<Sommet> noeudspasencorevisite;
     private Map<Sommet, Sommet> predecesseurs;
     private Map<Sommet, Integer> distance;

     public Dijkstra(Graphe g) {
             // On créer une copie
             this.arcs = new ArrayList<Arc>(g.getArcs());
     }

     public void executerAlgo(Sommet source) {
    	 	 noeudsvisites = new HashSet<Sommet>();
    	 	 noeudspasencorevisite = new HashSet<Sommet>();
             distance = new HashMap<Sommet, Integer>();
             predecesseurs = new HashMap<Sommet, Sommet>();
             distance.put(source, 0);
             noeudspasencorevisite.add(source);
             while (noeudspasencorevisite.size() > 0) {
                     Sommet noeud = getMinimum(noeudspasencorevisite);
                     noeudsvisites.add(noeud);
                     noeudspasencorevisite.remove(noeud);
                     trouverDistanceMinimal(noeud);
             }
     }

     private void trouverDistanceMinimal(Sommet noeud) {
             List<Sommet> noeudsadjacent = recupvoisin(noeud);
             for (Sommet s : noeudsadjacent) {
                     if (distanceMinimale(s) > distanceMinimale(s)
                                     + getDistance(noeud, s)) {
                             distance.put(s, distanceMinimale(noeud)
                                             + getDistance(noeud, s));
                             predecesseurs.put(s, noeud);
                             noeudspasencorevisite.add(s);
                     }
             }

     }

     /**
      * Retourne la distance entre le Sommet noeud et le Sommet s.
      * @param noeud
      * @param s
      * @return
      */
     private int getDistance(Sommet noeud, Sommet s) {
             for (Arc a : arcs) {
                     if (a.getSource().equals(noeud)
                                     && a.getDestination().equals(s)) {
                             return a.getPoids();
                     }
             }
             throw new RuntimeException("Impossible");
     }

     private List<Sommet> recupvoisin(Sommet noeud) {
             List<Sommet> voisins = new ArrayList<Sommet>();
             for (Arc a : arcs) {
                     if (a.getSource().equals(noeud)
                                     && !estVisite(a.getDestination())) {
                             voisins.add(a.getDestination());
                     }
             }
             return voisins;
     }

     /**
      * Retourne le sommet de distance minimale
      * @param sommets
      * @return
      */
     private Sommet getMinimum(Set<Sommet> sommets) {
             Sommet minimum = null;
             for (Sommet s : sommets) {
                     if (minimum == null) {
                             minimum = s;
                     } else {
                             if (distanceMinimale(s) < distanceMinimale(minimum)) {
                                     minimum = s;
                             }
                     }
             }
             return minimum;
     }

     /**
      * Le sommet s a il été visité
      * @param s
      * @return
      */
     private boolean estVisite(Sommet s) {
             return noeudsvisites.contains(s);
     }

     /**
      * Retourne la valeur minimale jusqu'à maintenant. Si elle n'est pas encore initialisé on met tout à l'infini.
      * @param destination
      * @return
      */
     private int distanceMinimale(Sommet destination) {
             Integer d = distance.get(destination);
             if (d == null) {
                     return Integer.MAX_VALUE;
             } else {
                     return d;
             }
     }

     
     public LinkedList<Sommet> getPath(Sommet s) {
             LinkedList<Sommet> chemin = new LinkedList<Sommet>();
             Sommet step = s;
             // Un chemin existe?
             if (predecesseurs.get(step) == null) {
                     return null;
             }
             chemin.add(step);
             while (predecesseurs.get(step) != null) {
                     step = predecesseurs.get(step);
                     chemin.add(step);
             }
             // Remet dans le bon ordre.
             Collections.reverse(chemin);
             return chemin;
     }

	
	
}
