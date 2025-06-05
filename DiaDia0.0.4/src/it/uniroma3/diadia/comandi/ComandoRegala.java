package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoRegala extends AbstractComando{
	private static final String MESSAGGIO_CON_CHI = Proprietà.getMessaggioConChi();
	final static private String MESSAGGIO_ATTREZZO_INESISTENTE = Proprietà.getMessaggioAttrezzoInesistente();

	
	public ComandoRegala() {
		super.setNome(this.getClass().getSimpleName());
	}
	
	@Override 
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(super.getParametro()==null || !borsa.hasAttrezzo(getParametro())) {
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_INESISTENTE);
			return;
		}
		if(stanzaCorrente.getPersonaggio()!=null) {
			super.getIO().mostraMessaggio(stanzaCorrente.getPersonaggio().riceviRegalo(borsa.getAttrezzo(getParametro()), partita));
			borsa.removeAttrezzo(getParametro());
		}
		if(stanzaCorrente.getPersonaggio()==null) super.getIO().mostraMessaggio(MESSAGGIO_CON_CHI);
	}

}
