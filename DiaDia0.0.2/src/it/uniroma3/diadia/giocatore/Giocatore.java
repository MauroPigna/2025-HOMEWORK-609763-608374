package it.uniroma3.diadia.giocatore;

public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	
	private int cfu;
	private Borsa borsa1;
	
	public Giocatore() {
		this.cfu=CFU_INIZIALI;
		this.borsa1= new Borsa();
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
}
