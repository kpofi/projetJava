package chemins;


import java.util.List;

public class Graphe {

	private final List<Sommet> sommets;
	private final List<Arc> arcs;
	
	public Graphe(List<Sommet> sommets, List<Arc> arcs) {
		this.sommets = sommets;
		this.arcs = arcs;
	}
	
	public List<Arc> getArcs() {
		return this.arcs;
	}
	
	public List<Sommet> getSommets() {
		return this.sommets;
	}



}
	
