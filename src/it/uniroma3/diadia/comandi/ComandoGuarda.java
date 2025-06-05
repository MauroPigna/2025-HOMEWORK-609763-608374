package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoGuarda extends AbstractComando{
	
	public ComandoGuarda() {
		this.setNome(this.getClass().getSimpleName());
	}

	@Override
	public void esegui(Partita partita) {

		super.getIO().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		super.getIO().mostraMessaggio("Hai "+partita.getGiocatore().getCfu()+" cfu rimanenti.");
		super.getIO().mostraMessaggio(""+partita.getGiocatore().getBorsa());
		return;
	}

}
