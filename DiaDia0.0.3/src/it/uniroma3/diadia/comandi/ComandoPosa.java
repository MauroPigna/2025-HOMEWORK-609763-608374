package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando{
	private String oggetto;
	
	@Override
    public void setParametro(String parametro) {
    	this.oggetto=parametro;
    }
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzacorrente = partita.getLabirinto().getStanzaCorrente();
		Borsa borsa=partita.getGiocatore().getBorsa();
		if(oggetto==null) {
			System.out.println("Che oggetto vuoi posare? Devi specificarne il nome dopo aver scritto il comando.");
			return;
		}
		if(borsa.hasAttrezzo(oggetto)) {
			stanzacorrente.addAttrezzo(borsa.removeAttrezzo(oggetto));
			borsa.removeAttrezzo(oggetto);
			if(stanzacorrente.hasAttrezzo(oggetto) && !borsa.hasAttrezzo(oggetto)) System.out.println("Oggetto posato con successo!");
		}
		else {
			System.out.println("Questo oggetto non si trova nella borsa!");
		    return;
		}
	}

}
