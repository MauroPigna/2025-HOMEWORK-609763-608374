package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{
	private String parametro;
	
	@Override
	public void setParametro(String parametro) {
		this.parametro=parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
		if(parametro==null) {
			partita.setFinita();
			System.out.println("Grazie di aver giocato!");
			return;
		}
	}

}
