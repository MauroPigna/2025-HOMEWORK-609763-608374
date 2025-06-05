package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa extends AbstractComando{
	
	private static final String MESSAGGIO_ATTREZZO_INESISTENTE = Proprietà.getMessaggioAttrezzoInesistente();
	private static final String MESSAGGIO_ATTREZZO_POSATO = Proprietà.getMessaggioAttrezzoPosato();
	private static final String MESSAGGIO_ATTREZZO_NON_POSATO = Proprietà.getMessaggioAttrezzoNonPosato();
	
	public ComandoPosa() {
		this.setNome(this.getClass().getSimpleName());
	}
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzacorrente = partita.getStanzaCorrente();
		Borsa borsa=partita.getGiocatore().getBorsa();
		if(super.getParametro()==null) {
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_INESISTENTE);
			return;
		}
		if(borsa.hasAttrezzo(super.getParametro())) {
			stanzacorrente.addAttrezzo(borsa.removeAttrezzo(super.getParametro()));
			borsa.removeAttrezzo(super.getParametro());
			if(stanzacorrente.hasAttrezzo(super.getParametro()) && !borsa.hasAttrezzo(super.getParametro())) 
				super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_POSATO);;
		}
		else {
			super.getIO().mostraMessaggio(MESSAGGIO_ATTREZZO_NON_POSATO);
		    return;
		}
	}

}
