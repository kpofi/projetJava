package events;

import robots.Robot;
import simulation.DonneesSimulation;
import elements.Incendie;

public class Intervenir extends Events {
	/** robot */
	private Robot r;
	
	/** donnees de simulation */
	private DonneesSimulation data;
	
	/** quantité d'eau à verser */
	private int quantEau;
	
	/** Constructeur */
	public Intervenir(long date, DonneesSimulation data, Robot r, int quantEau) {
		super(date);
		this.r = r;
		this.data = data;
		this.quantEau = quantEau;
	}
	
	/** Accesseurs */
	public int getQuantEau() {
		return this.quantEau;
	}
	
	/** setters */
	public void setQuantEau(int quantEau) {
		this.quantEau = quantEau;
	}
	
	public void execute() {
		this.r.setReservoir(this.r.getReservoir() - this.quantEau);
		Incendie[] tab = data.getTabIncendie();
		for (Incendie i : tab) {
			if (i.getCase().equals(this.r.getCase())) {
				if(this.r.getReservoir() >= quantEau) {
					i.setNbLitre(i.getLitres() - this.quantEau);
				}
				else {
					i.setNbLitre(i.getLitres() - this.r.getReservoir());
				}
				return;
			}
		}
	}
}
