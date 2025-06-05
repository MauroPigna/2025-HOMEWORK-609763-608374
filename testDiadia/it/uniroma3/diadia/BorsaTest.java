package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

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
	
	@Test
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
	
	@Test
	void testBorsa_nuovoMetodoOrdinatiPerPeso() {
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo1);
		Attrezzo b = new Attrezzo("b", 2);
		borsa.addAttrezzo(b);
		List<Attrezzo> a = borsa.getContenutoOrdinatoPerPeso();
		assertTrue(a.contains(attrezzo1));
		assertTrue(a.contains(attrezzo2));
		assertTrue(a.size()==3);
		assertEquals(a.get(0).getNome(), "osso");
		assertEquals(a.get(1).getNome(), "b");
		assertEquals(a.get(2).getNome(), "spada");
	}
	
	@Test
	void testBorsa_nuovoMetodoOrdinatiPerNome() {
		Attrezzo a = new Attrezzo("a", 1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(a);
		SortedSet<Attrezzo> s = borsa.getContenutoOrdinatoPerNome();
		assertTrue(s.first().getNome().equals(a.getNome()));
		assertEquals(s.last().getNome(), "spada");
	}
	
	
	@Test
	void testBorsa_metodoMappa() {
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		Map<Integer,Set<Attrezzo>> m = borsa.getContenutoRaggruppatoPerPeso();
		assertTrue(m.get(attrezzo1.getPeso()).contains(attrezzo1));
		assertFalse(m.get(attrezzo1.getPeso()).contains(attrezzo2));
		assertTrue(m.get(attrezzo2.getPeso()).contains(attrezzo2));
		assertFalse(m.get(attrezzo2.getPeso()).contains(attrezzo1));
	}
	
	@Test
	void testBorsa_ordinatiPesoENome() {
		borsa.addAttrezzo(new Attrezzo("am",1));
		borsa.addAttrezzo(new Attrezzo("ab",1));
		borsa.addAttrezzo(new Attrezzo("z",3));
		borsa.addAttrezzo(new Attrezzo("d",4));
		SortedSet<Attrezzo> s = borsa.getSortedSetOrdinatoPerPeso();
		assertEquals(s.first().getNome(), "ab");
		assertEquals(s.last().getNome(), "d");
	}


}
