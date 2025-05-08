package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica fab;
	
	@BeforeEach
	public void setup() {
		fab=new FabbricaDiComandiFisarmonica();
	}

	@Test
	void test_vai() {
		fab.costruisciComando("vai");
		assertEquals(fab.getNome(), "vai");
	}
	
	@Test
	void test_vaiNord() {
		fab.costruisciComando("vai nord");
		assertEquals(fab.getNome(), "vai");
		assertEquals(fab.getParametro(), "nord");
	}
	
	@Test
	void test_nullo() {
		assertTrue(fab.getNome()==null);
		assertTrue(fab.getParametro()==null);
	}

}
