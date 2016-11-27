package events;

import elements.Case;
import elements.Incendie;
import robots.Robot;
import simulation.Simulateur;

public class EteindreFeu extends Events {
	
	private Robot robot;
	
	private Incendie incendie;
	
	private Simulateur simu;
	
	private Case posCour;
	
	public EteindreFeu(long date, Robot r, Case posCour, Incendie i, Simulateur simu) {
		super(date);
		this.robot = r;
		this.incendie = i;
		this.simu = simu;
		this.posCour = posCour;
	}
	
	public void execute() {
		
		if (this.posCour.equals(this.incendie.getCase())) {
			
			int tmp = this.incendie.getLitres() - this.robot.getReservoir();
			this.robot.eteindre(this.simu,this.incendie);
			this.incendie.setNbLitre(tmp);
			
		}
		this.simu.draw();
	}
}
