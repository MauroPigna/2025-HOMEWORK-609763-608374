package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;
	private IOConsole console;

	public DiaDia(IOConsole console) {
		this.console = console;
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);		
		do		
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		if(comandoDaEseguire.getNome()==null) return false;
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		String stringa="";
		for(int i=0; i< elencoComandi.length; i++) 
			stringa= stringa+elencoComandi[i]+" ";
		console.mostraMessaggio(stringa);
	}
	
	
	/**
	 * Comando per prendere un attrezzo dalla stanza corrente e spostarlo nella borsa
	 * @param nomeAttrezzo
	 */
	
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo ==null || !this.partita.getLabirinto().getStanzaCorrente().hasAttrezzo(nomeAttrezzo)) 
			console.mostraMessaggio("Oggetto non valido. Che oggetto vuoi prendere?");
		else{
			Attrezzo attrezzoDaPrendere = this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			if(this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere) && 
					this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere)) {
				console.mostraMessaggio("Oggetto aggiunto alla borsa!");
			}
			else
				console.mostraMessaggio("Impossibile prendere questo oggetto!");
		}
		int cfu = partita.getGiocatore().getCfu();
		
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		console.mostraMessaggio("cfu: " + cfu);
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	
	
	/**
	 * Comando per posare un attrezzo dalla borsa nella stanza corrente
	 * @param nomeAttrezzo
	 */
	
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo ==null || !this.partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) 
			console.mostraMessaggio("Oggetto non valido. Che oggetto vuoi posare?");
		else{
			Attrezzo attrezzoDaPosare = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			if(this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoDaPosare) && 
					this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo)!=null) {
				console.mostraMessaggio("Oggetto lasciato nella stanza!");
			}
			else
				console.mostraMessaggio("Impossibile posare questo oggetto!");
		}
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		console.mostraMessaggio("cfu: " + partita.getGiocatore().getCfu());
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}
	

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu-1);
		}
		console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		console.mostraMessaggio("cfu " + partita.getGiocatore().getCfu());
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole ioconsole = new IOConsole();
		DiaDia gioco = new DiaDia(ioconsole);
		gioco.gioca();
	}
}
