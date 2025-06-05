package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.Labirinto;



class PartitaTest {
	Partita game;
	
	@BeforeEach
	public void setUp() throws FormatoFileNonValidoException, IOException {

		this.game = new Partita(Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addStanza("Aula 11")
				.addAdiacenza("Atrio", "Biblioteca", "Nord")
				.addAdiacenza("Atrio", "Aula 11", "Est")
				.getLabirinto());
	}
	

	@Test
	public void testVinta_stanzaVincente() {
		this.game.setStanzaCorrente(this.game.getLabirinto().getStanzaVincente());
		assertTrue(this.game.vinta());
	}
	@Test
	public void testVinta_stanzaNonVincente() {
		this.game.setStanzaCorrente(this.game.getLabirinto().getStanzaIniziale().getStanzaAdiacente("est"));
		assertFalse(this.game.vinta());
	}
	@Test
	public void testVinta_nonVinta() {
		assertFalse(this.game.vinta());
	}
	
	
	@Test
	public void testIsFinita_vinta() {
		this.game.setStanzaCorrente(this.game.getLabirinto().getStanzaVincente());
		assertTrue(this.game.isFinita());
	}
	@Test
	public void testIsFinita_CFUFiniti() {
		this.game.getGiocatore().setCfu(0);
		assertTrue(this.game.isFinita());
	}
	@Test
	public void testIsFinita_persa() {
		assertFalse(this.game.isFinita());
	}
}
