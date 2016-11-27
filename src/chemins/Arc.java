package chemins;

public class Arc  {
	
    private final Sommet source;
    private final Sommet destination;
    private final int poids;
    private int id;

    public Arc(int id, Sommet source, Sommet destination, int poids) {
    		this.id = id;
            this.source = source;
            this.destination = destination;
            this.poids = poids;
    }

    public Sommet getDestination() {
            return destination;
    }

    public int getId() {
        return id;
    }
    
    public Sommet getSource() {
            return this.source;
    }
    public int getPoids() {
            return this.poids;
    }

    @Override
    public String toString() {
            return source + " " + destination;
    }


}