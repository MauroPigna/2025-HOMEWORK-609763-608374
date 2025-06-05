package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	
	private StanzaBuia stanza;
	private Attrezzo att;
	private Attrezzo attSbagliato;
	private Stanza stanza2;
	
	@BeforeEach
	public void setup() {
		stanza = new StanzaBuia("Atrio", "piedeDiPorco");
		att= new Attrezzo("piedeDiPorco", 5);
		attSbagliato = new Attrezzo("osso", 3);
		stanza2=new Stanza("Atrio");
	}
	

	@Test
	void test_stanzaBuia() {
		assertEquals(stanza.getDescrizione(), "qui c'è buio pesto...");
	}
	
	@Test
	void test_stanzaNonPiùBuia() {
		stanza.addAttrezzo(att);
		assertFalse(stanza.getDescrizione().equals("qui c'è buio pesto..."));
	}
	
	@Test
	void test_oggettoSbagliato() {
		stanza.addAttrezzo(attSbagliato);
		assertEquals(stanza.getDescrizione(), "qui c'è buio pesto...");
	}
	
	@Test
	void test_checkDescrizioneCorretta() {
		stanza.addAttrezzo(att);
		stanza2.addAttrezzo(att);
		assertEquals(stanza.getDescrizione(), stanza2.getDescrizione());
	}

}
