package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi extends AbstractComando{
	
	private static final String MESSAGGIO_ATTREZZO_INESISTENTE = Proprietà.getMessaggioAttrezzoInesistente();
	private static final String MESSAGGIO_ATTREZZO_PRESO = Proprietà.getMessaggioAttrezzoPreso();
	private static final String MESSAGGIO_ATTREZZO_NON_PRESO = Proprietà.getMessaggioAttrezzoNonPreso();
	
	public ComandoPrendi() {
		this.setNome(this.getClass().getSimpleName());
	}
	
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(super.getParametro()==null || !stanzaCorrente.hasAttrezzo(super.getParametro()) 
				|| partita.getGiocatore().getBorsa().getPeso()+stanzaCorrente.getAttrezzo(getParametro()).getPeso()>Proprietà.getPesoMaxBorsa()) {
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_INESISTENTE);
			return;
		}
		borsa.addAttrezzo(stanzaCorrente.getAttrezzo(super.getParametro()));
		stanzaCorrente.removeAttrezzo(borsa.getAttrezzo(super.getParametro()));
		if(borsa.hasAttrezzo(super.getParametro()) && !stanzaCorrente.hasAttrezzo(super.getParametro())) 
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_PRESO);
		else super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_NON_PRESO);
	}

}
