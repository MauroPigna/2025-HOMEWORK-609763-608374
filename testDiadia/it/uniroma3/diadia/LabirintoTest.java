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
		labirinto= Labirinto.newBuilder().getLabirinto();
		stanza1= new Stanza("N11");
		stanza2=new Stanza("Bagno");
	}
	
	@Test
	void testLabirinto_nuovo() {
		assertNull(labirinto.getStanzaVincente());
		assertNull(labirinto.getStanzaIniziale());
	}

	@Test
	void testLabirinto_stanza1Corrente() {
		labirinto.setStanzaIniziale(stanza1);
		assertEquals(labirinto.getStanzaIniziale(), stanza1);
	}
	
	@Test
	void testLabirinto_stanzaVincente() {
		labirinto.setStanzaVincente(new Stanza("Biblioteca"));
		assertEquals(labirinto.getStanzaVincente().getNome(), "Biblioteca");
	}
	
	
	@Test
	void testLabirinto_adiacenti() {
		labirinto.setStanzaIniziale(stanza1);
		labirinto.getStanzaIniziale().impostaStanzaAdiacente("sud", stanza2);
		labirinto.setStanzaVincente(stanza2);
		assertTrue(labirinto.getStanzaIniziale().getStanzaAdiacente("sud")==labirinto.getStanzaVincente());
	}
	
	@Test
	void testLabirinto_unaSolaStanza() {
		labirinto.setStanzaIniziale(stanza1);
		assertTrue(labirinto.getStanzaIniziale()==stanza1);
	}
	
	@Test
	void testLabirinto_dueStanze() {
		labirinto.setStanzaIniziale(stanza1);
		labirinto.setStanzaVincente(stanza2);
		labirinto.getStanzaIniziale().impostaStanzaAdiacente("nord", stanza2);
		assertEquals(labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome(), "Bagno");
		assertTrue(labirinto.getStanzaIniziale().getStanzaAdiacente("nord")==stanza2);
		assertFalse(labirinto.getStanzaVincente()==labirinto.getStanzaIniziale());
	}

}
