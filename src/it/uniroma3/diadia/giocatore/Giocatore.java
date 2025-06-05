package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Proprietà;

public class Giocatore {
	static final private int CFU_INIZIALI = Proprietà.getCFUIniziali();
	
	private int cfu;
	private Borsa borsa1;
	
	public Giocatore() {
		this.cfu=CFU_INIZIALI;
		this.borsa1= new Borsa();
	}
	
	public Giocatore(int i) {
		this.cfu=i;
		this.borsa1=new Borsa();
	}
	
	public int getCfu() {
		return this.cfu;
	}
	
	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
	
	public Borsa getBorsa() {
		return borsa1;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa1=borsa;
	}
}
