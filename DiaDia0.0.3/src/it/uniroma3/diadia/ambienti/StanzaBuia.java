package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{
	
	private String chiave;
	private String nome;
	
	public StanzaBuia(String nome) {
		this("nome", null);
	}
	
	public StanzaBuia (String nome, String attrezzo) {
		super(nome);
		this.chiave=attrezzo;
	}
	
	@Override
	public String getDescrizione() {
		String stringa;
		if(this.hasAttrezzo(chiave)) {
			return super.getDescrizione();
		}
		else {
			stringa="qui c'è buio pesto...";
			return stringa;
		}
	}

}
