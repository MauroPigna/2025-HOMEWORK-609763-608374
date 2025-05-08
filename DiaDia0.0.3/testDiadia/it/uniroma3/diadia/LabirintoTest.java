package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class LabirintoTest {
	
	private Labirinto labirinto;
	private Stanza stanza1;
	private Stanza stanza2;
	
	@BeforeEach
	public void setup() {
		labirinto= new Labirinto();
		stanza1= new Stanza("N11");
		stanza2=new Stanza("Bagno");
	}
	
	@Test
	void testLabirinto_nuovo() {
		assertNull(labirinto.getStanzaVincente());
		assertNull(labirinto.getStanzaCorrente());
	}

	@Test
	void testLabirinto_stanza1Corrente() {
		labirinto.setStanzaCorrente(stanza1);
		assertEquals(labirinto.getStanzaCorrente(), stanza1);
	}
	
	@Test
	void testLabirinto_stanzaVincente() {
		labirinto.creaStanze();
		assertEquals(labirinto.getStanzaVincente().getNome(), "Biblioteca");
	}
	
	@Test
	void testLabirinto_stanzaCorrente() {
		labirinto.creaStanze();
		assertEquals(labirinto.getStanzaCorrente().getNome(), "Atrio");
		assertNotNull(labirinto.getStanzaCorrente().getStanzaAdiacente("nord"));
		assertFalse(labirinto.getStanzaCorrente()==labirinto.getStanzaVincente());
	}
	
	@Test
	void testLabirinto_adiacenti() {
		labirinto.setStanzaCorrente(stanza1);
		labirinto.getStanzaCorrente().impostaStanzaAdiacente("sud", stanza2);
		labirinto.setStanzaVincente(stanza2);
		assertTrue(labirinto.getStanzaCorrente().getStanzaAdiacente("sud")==labirinto.getStanzaVincente());
	}
	
	@Test
	void testLabirinto_unaSolaStanza() {
		labirinto.setStanzaCorrente(stanza1);
		assertTrue(labirinto.getStanzaCorrente()==stanza1);
	}
	
	@Test
	void testLabirinto_dueStanze() {
		labirinto.setStanzaCorrente(stanza1);
		labirinto.setStanzaVincente(stanza2);
		labirinto.getStanzaCorrente().impostaStanzaAdiacente("nord", stanza2);
		assertEquals(labirinto.getStanzaCorrente().getStanzaAdiacente("nord").getNome(), "Bagno");
		assertTrue(labirinto.getStanzaCorrente().getStanzaAdiacente("nord")==stanza2);
		assertFalse(labirinto.getStanzaVincente()==labirinto.getStanzaCorrente());
	}

}
