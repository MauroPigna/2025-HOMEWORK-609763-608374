package it.uniroma3.diadia;

public class IOSimulator implements IO{
	
	private String[] righeMostrate;
	private String[] righeLette;
	private String[] messaggiProdotti;
	private int indiceRigheLette;
	private int indiceRigheMostrate;
	private int indiceMessaggiProdotti;
	
	public IOSimulator(String[] righeDaLeggere) {
		this.righeLette=righeDaLeggere;
		this.indiceRigheLette=0;
		this.indiceMessaggiProdotti=0;
		this.messaggiProdotti = new String[42 * 23];
	}
	
	public void setMessaggiProdotti(String[] mes) {
		this.messaggiProdotti=mes;
	}
	
	public String[] getMessaggiProdotti() {
		return this.messaggiProdotti;
	}
	
	public void setRigheLette(String[] rig) {
		this.righeLette=rig;
	}
	
	public String[] getRigheLette() {
		return this.righeLette;
	}
	
	public void setRigheMostrate(String[] rm) {
		this.righeMostrate=rm;
	}
	
	public String[] getRighrMostrate() {
		return this.righeMostrate;
	}
	
	@Override
	public String leggiRiga() {
		String riga = this.righeLette[this.indiceRigheLette];
		this.indiceRigheLette++;
		return riga;
	}
	
	@Override
	public void mostraMessaggio(String mes) {
		this.messaggiProdotti[this.indiceMessaggiProdotti]=mes;
		this.indiceMessaggiProdotti++;
	}

}
