package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	
	private Giocatore giocatore;
	private int cfu;
	
	@BeforeEach
	public void setup() {
		giocatore=new Giocatore();
	}

	@Test
	void testGiocatore() {
		assertNotNull(giocatore);
	}
	
	@Test 
	void testGiocatore_6cfu() {
		cfu=6;
		giocatore.setCfu(cfu);
		assertEquals(giocatore.getCfu(), 6);
	}
	
	@Test
	void testGiocatore_0cfu() {
		cfu=0;
		giocatore.setCfu(cfu);
		assertNotEquals(giocatore.getCfu(), 6);
	}
	
	@Test
	void testGiocatore_20cfu() {
		assertTrue(giocatore.getCfu()==20);
	}
	
	@Test
	void testGiocatore_numCfu() {
		cfu=7;
		giocatore.setCfu(cfu);
		int x=giocatore.getCfu();
		assertTrue(cfu==x);
	}
}
