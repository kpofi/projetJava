package commandant;

import java.util.LinkedList;

import chemins.Sommet;
import elements.Carte;
import elements.Case;
import elements.Incendie;
import events.Deplacer;
import events.EteindreFeu;
import events.Remplir;
import robots.Robot;
import robots.RobotAPatte;
import simulation.DonneesSimulation;
import simulation.Simulateur;

public class ChefPompier {
	
	private DonneesSimulation d;
	private Incendie[] tabIncendie;
	private Robot[] tabRobot;
	private Boolean[] incendieAffecte;
	private Boolean[] incendieEteint;
	private Boolean[] robotOccupe;
	private int nbIncentieRestant;
	private Case[] posCour;
	private long[] dateRobot;
	private Simulateur simu;
	private int[] nbLitreIncendie;
	
	public ChefPompier(DonneesSimulation d, Simulateur simu) {
		this.d = d;
		/* On travaille sur une copie, ce sera plus facile d'accès*/
		this.tabIncendie = d.getTabIncendie();
		this.incendieAffecte = new Boolean[this.tabIncendie.length];
		this.incendieEteint = new Boolean[this.tabIncendie.length];
		this.tabRobot = d.getTabRobot();
		this.robotOccupe = new Boolean[this.tabRobot.length];
		this.simu = simu;
		this.posCour = new Case[this.tabRobot.length];
		this.dateRobot = new long[this.tabRobot.length];
		this.nbLitreIncendie = new int[this.tabIncendie.length];
		
		for (int i = 0; i<tabIncendie.length; i++) {
			this.incendieAffecte[i] = false;
			this.incendieEteint[i] = false;
			this.nbLitreIncendie[i] = this.tabIncendie[i].getLitres();
		}
		
		for (int i = 0; i < tabRobot.length; i++) {
			this.robotOccupe[i] = false;
			this.posCour[i] = this.tabRobot[i].getCase();
			this.dateRobot[i] = 0;
		}
		
		this.nbIncentieRestant = this.tabIncendie.length;
		
	}
	
	
	
	private void vaRemplir(int i, Robot r) {
		Case dep = this.posCour[i];
		Case dest = r.pointEau(this.d, dep);
		LinkedList<Sommet> liste = r.cheminPointEau(this.d.getCarte(), dep, dest, this.simu);
		long tDep;
		long date;
		date = this.dateRobot[i];
		Carte carte = this.d.getCarte();
		if (liste != null) {
			for (Sommet s : liste) {
				tDep = r.getTempsDeplacement(carte, s.getSommet());
				
				date = date + tDep;
				this.dateRobot[i] = date;
				Deplacer deplace = new Deplacer(date,r,this.simu,carte,s.getSommet());
				this.simu.ajouteEvenement(deplace);
				this.posCour[i] = s.getSommet();
			}
			this.dateRobot[i] = date;
		}
		this.dateRobot[i] = date + (r.getTempsRemplissage()/5);
		
		Remplir remp = new Remplir(this.dateRobot[i],r,dest);
		this.simu.ajouteEvenement(remp);
	}
	

	private void allerSurIncendie(int i, Robot r, Incendie ince) {
		Case dep = this.posCour[i];
		Case dest = ince.getCase();
		LinkedList<Sommet> liste = r.getChemin().listePlusCourtChemin(dep, dest, this.simu);
		long tDep;
		long date;
		date = this.dateRobot[i];
		Carte carte = this.d.getCarte();
		if (liste != null) {
			for (Sommet s : liste) {
				
				tDep = r.getTempsDeplacement(carte, s.getSommet());
				
				Case c = this.simu.getData().getCarte().getCase(s.getSommet().getLigne() , s.getSommet().getColonne());
				date = date + tDep;
				Deplacer deplace = new Deplacer(date,r,this.simu,carte,c);
				this.simu.ajouteEvenement(deplace);
				
				this.posCour[i] = s.getSommet();
			}
			this.dateRobot[i] = date;
			
		}
	}
	
	private void soccuperDeIncendie(int i, int j, Robot robot, Incendie incendie) {
		while (this.nbLitreIncendie[j] > 0) {
			
			if (robot.getReservoir() == 0) {
				// va remplir
				vaRemplir(i,robot);
				
			}
			// aller sur l'incendie
			allerSurIncendie(i,robot,incendie);
			// Eteindre
			long date = this.dateRobot[i];
			Case posCour = this.posCour[i];
			if (robot instanceof RobotAPatte) {
				date += (long) ((incendie.getLitres()/robot.getDebit())/5);
			}
			else {
				date += (long) ((robot.getReservoir()/robot.getDebit())/5);
			}
			robot.setReservoir();
			EteindreFeu eteint = new EteindreFeu(date,robot,posCour,incendie,this.simu);
			
			this.nbLitreIncendie[j] -= robot.getReservoir();
			robot.setReservoir(0);
			this.simu.ajouteEvenement(eteint);
			
			this.dateRobot[i] = date;
		}
		robot.setReservoir();

		this.incendieEteint[j] = true;
		this.nbIncentieRestant--;
	}
	
	private void affectation(int i, Robot r, int j, Incendie incendie) {
		// s'occuper de l'incendie
		this.robotOccupe[i] = true;
		soccuperDeIncendie(i,j,r,incendie);
	}
	
	
	public void resoudreLeProbleme () {

		while (this.nbIncentieRestant != 0) {
			
			for (int i = 0; i < this.tabRobot.length; i++) {
				
				
				for (int j = 0; j < this.tabIncendie.length; j++) {
					
					if (!this.robotOccupe[i]){
						
						if ( (!this.incendieAffecte[j]) || (!this.incendieEteint[j]) )  {
							this.robotOccupe[i] = true;
							
							Robot r = this.tabRobot[i];
							this.incendieAffecte[j] = true;
							
							Incendie incendie = this.tabIncendie[j];
							
							affectation(i,r,j,incendie);
						}
					}
				}

			  }
			
			  for (int k = 0 ; k<this.robotOccupe.length; k++) {
				  this.robotOccupe[k] = false;
			  }

		}

	}
	
}
