package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	
	private StanzaBloccata stanza;
	private Stanza stanza2;
	private Stanza stanza3;
	private Attrezzo att;
	
	@BeforeEach
	public void setup() {
		stanza = new StanzaBloccata("atrio", "nord", "key");
		stanza2=new Stanza("Cucina");
		stanza3=new Stanza("Bagno");
		stanza.impostaStanzaAdiacente("nord", stanza2);
		stanza.impostaStanzaAdiacente("sud", stanza3);
		att= new Attrezzo("key", 1);
	}

	@Test
	void test_direzioneBloccata() {
		assertTrue(stanza.getStanzaAdiacente("nord").getNome().equals("atrio"));
	}
	
	@Test
	void test_dirBloccataMaChiave() {
		stanza.addAttrezzo(att);
		stanza.impostaStanzaAdiacente("nord", stanza2);
		assertEquals(stanza.getStanzaAdiacente("nord").getNome(), stanza2.getNome());
	}
	
	@Test
	void test_stanzaAdiacenteNonBloccata() {
		stanza.impostaStanzaAdiacente("nord", stanza2);
		stanza.impostaStanzaAdiacente("sud", stanza3);
		assertSame(stanza.getStanzaAdiacente("sud"), stanza3);
	}

}
