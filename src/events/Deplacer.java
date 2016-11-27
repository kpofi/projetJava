package events;

import robots.Robot;
import simulation.Simulateur;
import elements.Carte;
import elements.Case;
import elements.Direction;

public class Deplacer extends Events {
	/** robot à deplacer */
	private Robot r;
	
	/** Case de destination */
	private Case d;
	
	private Carte c;
	
	private Simulateur simu;
	
	private Direction dir = null;
	
	/** constructeur */
	public Deplacer(long date, Robot r, Simulateur simu, Carte c, Case d) {
		super(date);
		this.r = r;
		this.d = d;
		this.c = c;
		this.simu = simu;
	}
	
	public Deplacer(long date, Robot r, Simulateur simu, Carte c, Direction d) {
		super(date);
		this.r = r;
		this.dir = d;
		this.c = c;
		this.simu = simu;
	}
	
	/** setter */
	public void setCase(Case d) {
		this.d = d;
	}
	
	public void setDirection(Direction dir) {
		this.dir = dir;
	}
	
	/** accesseurs */
	public Robot getRobot() {
		return r;
	}
	
	public Direction getDirection() {
		return this.dir;
	}
	
	public void execute() {
		if (this.dir != null) {
			this.r.deplacer(this.c, this.dir);	
		}
		else {
			this.r.deplacer(this.c, this.d);
		}
		this.simu.draw();
	}
}
