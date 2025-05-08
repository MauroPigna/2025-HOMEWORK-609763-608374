package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.attrezzi.*;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	
	@BeforeEach
	public void setup() {
		borsa=new Borsa();
		attrezzo1=new Attrezzo("osso", 1);
		attrezzo2=new Attrezzo("spada", 5);
		attrezzo3=new Attrezzo("casa", 250);
	}
	

	@Test
	void testBorsa_vuota() {
		assertTrue(borsa.isEmpty());
	}
	
	@Test
	void testBorsa_unAttrezzo() {
		assertTrue(borsa.addAttrezzo(attrezzo1));
	}
	
	@Test
	void testBorsa_dueAttrezzi() {
		assertTrue(borsa.addAttrezzo(attrezzo1) && borsa.addAttrezzo(attrezzo2));
	}
	
	@Test
	void testBorsa_haUnAttrezzo() {
		borsa.addAttrezzo(attrezzo1);
		assertTrue(borsa.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	void testBorsa_haDueAttrezzi() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		assertTrue(borsa.hasAttrezzo(attrezzo1.getNome()) && borsa.hasAttrezzo(attrezzo2.getNome()));
	}
	
	@Test
	void testBorsa_troppoPeso() {
		assertFalse(borsa.addAttrezzo(attrezzo3));
	}
	
	@Test
	void testBorsa_dueOggetti() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		assertTrue(borsa.hasAttrezzo(attrezzo1.getNome()) && borsa.hasAttrezzo(attrezzo2.getNome()));
	}
	
	@Test
	void testBorsa_unOggetto() {
		borsa.addAttrezzo(attrezzo1);
		assertTrue(borsa.hasAttrezzo(attrezzo1.getNome()));
	}
	
	@Test
	void testBorsa_unOggettoSbagliato() {
		borsa.addAttrezzo(attrezzo1);
		assertFalse(borsa.hasAttrezzo(attrezzo2.getNome()));
	}
	
	@Test
	void testBorsa_pesoUnOggetto() {
		borsa.addAttrezzo(attrezzo1);
		borsa.getPeso();
		assertTrue(borsa.getPeso()==1);
	}
	
	void testBorsa_pesoDueOggetti() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.getPeso();
		assertTrue(borsa.getPeso()==6);
	}
	
	@Test
	void testBorsa_pesoUnOggettoSbagliato() {
		borsa.addAttrezzo(attrezzo1);
		borsa.getPeso();
		assertFalse(borsa.getPeso()==5);
	}


}
