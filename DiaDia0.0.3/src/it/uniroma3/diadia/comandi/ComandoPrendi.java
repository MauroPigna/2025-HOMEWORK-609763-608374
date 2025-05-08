package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando{
	private String oggetto;
	
	@Override 
	public void setParametro(String parametro) {
		this.oggetto=parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente=partita.getLabirinto().getStanzaCorrente();
		Borsa borsa = partita.getGiocatore().getBorsa();
		if(oggetto==null) {
			System.out.println("Quale oggetto vuoi prendere? Devi specificarne il nome dopo aver scritto il comando.");
			return;
		}
		if(!stanzaCorrente.hasAttrezzo(oggetto)) {
			System.out.println("L'oggetto non si trova in questa stanza!");
			return;
		}
		borsa.addAttrezzo(stanzaCorrente.getAttrezzo(oggetto));
		stanzaCorrente.removeAttrezzo(borsa.getAttrezzo(oggetto));
		if(borsa.hasAttrezzo(oggetto) && !stanzaCorrente.hasAttrezzo(oggetto)) System.out.println("Oggetto preso con successo!");
	}

}
