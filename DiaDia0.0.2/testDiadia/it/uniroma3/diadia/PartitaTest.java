package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class PartitaTest {
	
	private Partita partita1;
	
	@BeforeEach
	public void setup() {
		partita1= new Partita();
	}

	@Test
	void testPartita_finita() {
		partita1.getGiocatore().setCfu(0);
		assertTrue(partita1.isFinita());
	}
	
	@Test
	void testPartita_finitaStanza() {
		partita1.getLabirinto().setStanzaCorrente(partita1.getLabirinto().getStanzaVincente());;
		assertTrue(partita1.isFinita());
	}
	
	@Test
	void testPartita_persa() {
		partita1.getGiocatore().setCfu(0);
		assertFalse(partita1.vinta());
	}
	
	@Test
	void testPartita_nonFinita() {
		assertFalse(partita1.isFinita());
	}
	
	@Test
	void testPartita_vinta() {
		partita1.getLabirinto().setStanzaCorrente(partita1.getLabirinto().getStanzaVincente());
		assertTrue(partita1.vinta());
	}
	
	@Test
	void testPartita_nuova() {
		assertFalse(partita1.isFinita());
	}

}
