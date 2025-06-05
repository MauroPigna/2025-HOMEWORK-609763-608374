package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {
	
	private static final String MESSAGGIO_DOVE = Proprietà.getMessaggioQualeDirezione();
	
	public ComandoVai() {
		this.setNome(this.getClass().getSimpleName());
	}

	/**
	* esecuzione del comando
	*/
	@Override 
	public void esegui(Partita partita) {
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		Stanza prossimaStanza=null;
		if(super.getParametro()==null) {
			super.getIO().mostraMessaggio(MESSAGGIO_DOVE);
		return; }
		prossimaStanza=stanzaCorrente.getStanzaAdiacente(super.getParametro());
		if(prossimaStanza==null) {
			super.getIO().mostraMessaggio("Direzione inesistente!");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		super.getIO().mostraMessaggio(partita.getStanzaCorrente().getNome());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		}

}
