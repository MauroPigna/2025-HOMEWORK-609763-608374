package it.uniroma3.diadia;

import java.io.IOException;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	private Stanza stanzaCorrente;
	private Labirinto labirinto;
	private boolean finita;
	private Giocatore player;
	
	public Partita(Labirinto l){
		this.finita = false;
		this.labirinto = l;
		this.player = new Giocatore();
		this.setStanzaCorrente(labirinto.getStanzaIniziale());
		}
	
	public Partita(Labirinto l, Giocatore g){
		this.finita = false;
		this.labirinto = l;
		this.player = g;
		this.setStanzaCorrente(labirinto.getStanzaIniziale());
		}
	
	public Partita(String nomeFile) throws FormatoFileNonValidoException, IOException {
		this(Labirinto.newBuilder(nomeFile).getLabirinto(), new Giocatore());
	}
	
	public Partita() throws FormatoFileNonValidoException, IOException {
		this("Labirinto1");
	}
	
	
	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto=labirinto;
	}
	
	public Giocatore getGiocatore() {
		return player;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.labirinto.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.getGiocatore().getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}


	public boolean giocatoreIsVivo() {
		if(this.player.getCfu()!=0) return true;
		return false;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

}

