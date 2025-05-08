package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{
	private String parametro;
	
	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {
			System.out.println(partita.getLabirinto().getStanzaCorrente().getDescrizione());
			System.out.println(partita.getGiocatore().getCfu());
			System.out.println(partita.getGiocatore().getBorsa());
			return;
		}
	}

}
