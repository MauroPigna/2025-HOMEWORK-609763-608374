package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	private Partita partita;
	private Attrezzo attrezzo;
	private Comando comando;
	private IO io;

	@BeforeEach
	public void setUp() throws Exception {
		Labirinto labirinto = new Labirinto.LabirintoBuilder().addStanzaIniziale("Atrio").addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca").addAdiacenza("Atrio", "Biblioteca", "nord").getLabirinto();
		this.partita = new Partita(labirinto);
		this.attrezzo = new Attrezzo("martello", 2);
		this.comando = new ComandoPrendi();
		Scanner scanner = new Scanner(System.in);
		this.io = new IOConsole(scanner);
		this.comando.setIO(this.io);
	}

	@Test
	public void testAttrezzoPresente() {
		this.partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoNonPresente() {
		this.comando.setParametro("martello");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoNull() {
		this.partita.getStanzaCorrente().addAttrezzo(null);
		this.comando.setParametro(null);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo(null));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo(null));
	}

	@Test
	public void testAttrezzoCheNonSiPuoPrendere() {
		this.partita.getStanzaCorrente().addAttrezzo(new Attrezzo("utensile_pesante", 20));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("utensile_pesante"));
		this.comando.setParametro("utensile_pesante");
		this.comando.esegui(this.partita);
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("utensile_pesante"));
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("utensile_pesante"));
	}

}
