package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {
	
	private Partita partita;
	private ComandoPosa comando;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Stanza stanza;
	private Borsa borsa;
	
	@BeforeEach
	public void setup() {
		partita=new Partita();
		comando=new ComandoPosa();
		attrezzo1=new Attrezzo("spada", 5);
		attrezzo2=new Attrezzo("sasso", 1);
		stanza= new Stanza("Studio");
		borsa=new Borsa();
		partita.getLabirinto().setStanzaCorrente(stanza);
		partita.getGiocatore().setBorsa(borsa);
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
	}

	@Test
	void test_unOggetto() {
		comando.setParametro("spada");
		comando.esegui(partita);
		assertTrue(!borsa.hasAttrezzo("spada") && stanza.hasAttrezzo("spada"));
	}
	
	@Test
	void test_dueOggetti() {
		comando.setParametro("spada");
		comando.esegui(partita);
		comando.setParametro(attrezzo2.getNome());
		comando.esegui(partita);
		assertFalse(borsa.hasAttrezzo(attrezzo1.getNome()) && !stanza.hasAttrezzo(attrezzo1.getNome())
				&& borsa.hasAttrezzo(attrezzo2.getNome()) && !stanza.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	void test_oggettoInesistente() {
		comando.setParametro("mazza");
		comando.esegui(partita);
		assertFalse(borsa.hasAttrezzo("mazza") || stanza.hasAttrezzo("mazza"));
	}

}
