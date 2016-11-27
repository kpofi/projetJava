package events;

import robots.Robot;
import elements.Case;

public class Remplir extends Events{
	
	private Robot r;
	
	private Case dest;
	
	/** constructeur */
	public Remplir(long date, Robot r, Case dest) {
		super(date);
		this.r = r;
		this.dest = dest;
	}
	
	
	public void execute() {	
		this.r.remplir(this.dest);
	}
}
