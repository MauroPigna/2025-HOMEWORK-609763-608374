package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{

	private static final String MESSAGGIO_CON_CHI =

			"Chi dovrei salutare?...";

	public ComandoSaluta(){
		super.setNome(this.getClass().getSimpleName());
	}

	@Override 
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio=partita.getStanzaCorrente().getPersonaggio();
		if(personaggio==null) super.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);
		else super.getIO().mostraMessaggio(personaggio.saluta());
	}

}
