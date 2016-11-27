package simulation;

import elements.Incendie;
import events.Events;
import gui.GUISimulator;
import gui.Simulable;

import java.awt.Color;
import java.util.ArrayList;

import robots.Robot;

/**
 * cet objet est associé à une fenêtre graphique GUISimulator, dans laquelle
 * il peut se dessiner.
 * De plus il hérite de Simulable, donc il définit deux méthodes next() et 
 * restart() invoquées par la fenêtre graphique de simulation selon les
 * commandes entrées par l'utilisateur.
 */
public class Simulateur implements Simulable {
    private GUISimulator gui;
    
    private long dateSimulation = 0;

    private DonneesSimulation data;
    
    private ArrayList<Events> listEvent = new ArrayList<Events>();

    public Simulateur(DonneesSimulation data) {
    	int width, height;
    	width = data.getCarte().getNbLignes() * data.getCarte().getTailleCases();
    	height = data.getCarte().getNbColonnes() * data.getCarte().getTailleCases();
    	
    	// crée la fenêtre graphique dans laquelle dessiner
    	this.gui = new GUISimulator(width, height, Color.BLACK);
    	this.data = data;
    	
    	
    	this.gui.setSimulable(this); // association a la gui
    	draw();
    }
    
    private void incrementeDate () {
    	this.dateSimulation ++;
    }
    
    public DonneesSimulation getData() {
    	return this.data;
    }
    
    public long getDate() {
    	return this.dateSimulation;
    }
    
    public void ajouteEvenement(Events e) {
    	int j;
    	for (j = 0; j < this.listEvent.size(); j++) {
    		if(this.listEvent.get(j).getDate() > e.getDate()) {
    			break;
    		}
    	}
    	this.listEvent.add(j,e);
    }
    
    @Override
    public void next() {
    	executeEvenementDate(this.dateSimulation);
    	incrementeDate();
    }

    @Override
    public void restart() {
    	this.dateSimulation = 0;
    }

    
    /**
     * Execute tous les évènements de la liste
     */
    public void executeListeEvenement() {
    	for (Events e : this.listEvent) {
    		e.execute();
    		draw();
    	}
    }
    
    /**
     * Execute les évènements à la date d
     * @param d
     */
    public void executeEvenementDate(long d) {
    	int i;
    	
    	for (i=0; i<this.listEvent.size(); i++ ) {

    		if (this.listEvent.get(i).getDate() == d) { 
    			this.listEvent.get(i).execute();
    		}
    		draw();
    		if (this.listEvent.get(i).getDate() > d) {
    			break;
    		}
    	}
    }
    
    public void executeEvenement(Events event) {
    	event.execute();
    	draw();
    }
    

     /**
     * Dessine le simulateur.
     */
    public void draw() {
    	this.gui.reset();	// clear the window
    	/** dessin de la carte */
    	int nbLignes, nbColonnes;
    	nbLignes = this.data.getCarte().getNbLignes();
    	nbColonnes = this.data.getCarte().getNbColonnes();
		for (int i = 0; i < nbLignes; i++) {
			for (int j = 0; j < nbColonnes; j++) {
				this.data.getCarte().getCase(i, j).draw(this.gui, this.data.getCarte());
		    }
		}
		
		/** dessin des incendies */
		Incendie[] tabIncendies = this.data.getTabIncendie();
		for (int i = 0; i < tabIncendies.length; i++) {
			tabIncendies[i].draw(this.gui, this.data.getCarte());
		}
	
		/** dessin des robots */
		Robot[] tabRobots = this.data.getTabRobot();
		for (int j = 0; j < tabRobots.length; j++) {
			tabRobots[j].draw(this.gui, this.data.getCarte());
		}
	}
    
}
