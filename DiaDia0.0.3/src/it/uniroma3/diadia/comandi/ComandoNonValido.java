package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNonValido implements Comando {
	private String indicazione;
	
	@Override
    public void setParametro(String parametro) {
    	this.indicazione = parametro;
    }
	
	@Override
	public void esegui (Partita partita) {
			System.out.println("Comando non valido!"
					+ "");
			return;
	}

}
