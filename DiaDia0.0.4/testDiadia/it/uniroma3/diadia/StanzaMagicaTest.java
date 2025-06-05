package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private StanzaMagica stanza;
	private Attrezzo a1;
	private Attrezzo a2;
	private Attrezzo a3;
	
	@BeforeEach
	public void setup() {
		stanza=new StanzaMagica("Atrio", 2);
		a1 = new Attrezzo("osso", 1);
		a2 = new Attrezzo("spada", 5);
		a3 = new Attrezzo("cacciavite", 3);
	}

	@Test
	void test_unAttrezzo() {
		stanza.addAttrezzo(a1);
		assertEquals(stanza.getAttrezzo(a1.getNome()), a1);
	}
	
	@Test
	void test_dueAttrezzi() {
		stanza.addAttrezzo(a1);
		stanza.addAttrezzo(a2);
		assertTrue(stanza.getAttrezzo(a1.getNome()).getNome().equals(a1.getNome()));
		assertEquals(stanza.getAttrezzo(a2.getNome()).getNome(), a2.getNome());
	}
	
	@Test
	void test_superataSoglia() {
		stanza.addAttrezzo(a1);
		stanza.addAttrezzo(a2);
		stanza.addAttrezzo(a3);
		assertTrue(stanza.hasAttrezzo(a1.getNome()));
		assertFalse(stanza.hasAttrezzo(a3.getNome()));
		assertTrue(stanza.hasAttrezzo("etivaiccac"));
	}

}
