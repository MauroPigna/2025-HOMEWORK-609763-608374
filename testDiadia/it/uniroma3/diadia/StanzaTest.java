package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Stanza stanza1;
	private Stanza stanza2;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setup() {
		stanza1 = new Stanza("Atrio");
		stanza2= new Stanza("Cucina");
		attrezzo = new Attrezzo("osso", 1);
	}

	@Test
	void testStanza_adiacente() {
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		assertEquals(stanza1.getStanzaAdiacente("nord"), stanza2);
	}
	
	@Test
	void testStanza_adiacenteGenerica() {
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		assertNotNull(stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testStanza_nonAdiacente() {
		assertNull(stanza1.getStanzaAdiacente("nord"));
	}
	
	@Test
	void testStanza_getAttrezzo() {
		stanza1.addAttrezzo(attrezzo);
		assertEquals(stanza1.getAttrezzo(attrezzo.getNome()), attrezzo);
	}
	
	@Test
	void testStanza_conAttrezzo() {
		stanza1.addAttrezzo(attrezzo);
		assertTrue(stanza1.hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testStanza_senzaAttrezzo() {
		assertFalse(stanza1.hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testStanza_verifica() {
		stanza1.impostaStanzaAdiacente("sud", stanza2);
		assertTrue(stanza2==stanza1.getStanzaAdiacente("sud"));
	}
	
	@Test
	void testStanza_attrezzoRimosso() {
		stanza1.addAttrezzo(attrezzo);
		stanza1.removeAttrezzo(attrezzo);
		assertFalse(stanza1.hasAttrezzo(attrezzo.getNome()) && stanza1.removeAttrezzo(attrezzo));
	}
	
	@Test
	void test_StanzeAdiacenti() {
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		stanza1.impostaStanzaAdiacente("sud", new Stanza("bagno"));
		stanza1.impostaStanzaAdiacente("ovest", new Stanza("mor"));
		stanza1.impostaStanzaAdiacente("est", new Stanza("mos"));
		stanza1.impostaStanzaAdiacente("sud-ovest", new Stanza("NO"));
		assertFalse(stanza1.getMapStanzeAdiacenti().containsKey("sud-ovest"));
	}

}
