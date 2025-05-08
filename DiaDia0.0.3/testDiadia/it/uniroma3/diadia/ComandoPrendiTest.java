package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPrendiTest {
	private Partita partita;
	private ComandoPrendi comando;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Stanza stanza;
	private Borsa borsa;
	
	@BeforeEach
	public void setup() {
		partita=new Partita();
		comando=new ComandoPrendi();
		attrezzo1=new Attrezzo("spada", 5);
		attrezzo2=new Attrezzo("sasso", 1);
		stanza= new Stanza("Studio");
		borsa=new Borsa();
		partita.getLabirinto().setStanzaCorrente(stanza);
		partita.getGiocatore().setBorsa(borsa);
		stanza.addAttrezzo(attrezzo1);
		stanza.addAttrezzo(attrezzo2);
	}

	@Test
	void test_unOggetto() {
		comando.setParametro("spada");
		comando.esegui(partita);
		assertFalse(stanza.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	void test_dueOggetti() {
		comando.setParametro("spada");
		comando.esegui(partita);
		comando.setParametro("sasso");
		comando.esegui(partita);
		assertFalse(stanza.hasAttrezzo(attrezzo1.getNome()));
		assertFalse(stanza.hasAttrezzo(attrezzo2.getNome()));
		assertTrue(borsa.hasAttrezzo(attrezzo1.getNome()) && borsa.hasAttrezzo(attrezzo2.getNome()));
	}
	
	@Test
	void test_oggettoSbagliato() {
		comando.setParametro("osso");
		comando.esegui(partita);
		assertFalse(borsa.hasAttrezzo("osso"));
	}

}
