package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;

class IOSimulatorTest {
	
	static final private String MESSAGGIO_BENVENUTO = Proprietà.getMessaggioBenvenuto();
	static final private String MESSAGGIO_VITTORIA = Proprietà.getMessaggioVittoria();
	static final private String MESSAGGIO_SCONFITTA = Proprietà.getMessaggioSconfitta();
	static final private String MESSAGGIO_FINE = Proprietà.getMessaggioFine();

	private DiaDia diadia;
	private IOSimulator io;



	@BeforeEach
	public void setUp() {
		this.io = new IOSimulator();
	}
	
	@Test
	public void diadiaTest_comandoFine() {
		this.diadia = new DiaDia (
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertEquals(MESSAGGIO_BENVENUTO, this.io.getStampeEseguite().get(0));
		assertEquals(MESSAGGIO_FINE, this.io.getStampeEseguite().get(this.io.getStampeEseguite().size()-1));
	}

	@Test
	public void diadiaTest_comandoVai() {
		this.diadia = new DiaDia (
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains("Direzione inesistente!"));
	}
	
	@Test
	public void diadiaTest_comandoGuarda() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.addAttrezzo("piuma", 1)
						.getLabirinto()),
				this.io.aggiungiComandoDaEseguire("guarda")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains("Hai 20 cfu rimanenti."));
	}
	
	@Test 
	public void diadiaTest_comandoPrendi() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.addAttrezzo("piuma", 1)
						.getLabirinto()),
				this.io.aggiungiComandoDaEseguire("prendi piuma")
				.aggiungiComandoDaEseguire("guarda")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains("Oggetto preso con successo!"));
	}
	
	@Test 
	public void diadiaTest_comandoPosa() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.addAttrezzo("piuma", 1)
						.getLabirinto()),
				this.io.aggiungiComandoDaEseguire("prendi piuma")
				.aggiungiComandoDaEseguire("posa piuma")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains("Oggetto posato con successo!"));
	}

	@Test
	public void diadiaTest_vintaInStanzaVincente() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.setStanzaVincente("aula 1")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("comando inutile"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_VITTORIA));
	}
	


	@Test
	public void diadiaTest_vintaDopoSpostamento() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.addStanzaVincente("aula 2")
						.addAdiacenza("aula 1", "aula 2", "nord")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_VITTORIA));
	}

	@Test 
	public void diadiaTest_sconfittaEsaurimentoCFU() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.addStanza("aula 2")
						.addAdiacenza("aula 1", "aula 2", "nord")
						.getLabirinto(),
						new Giocatore(1)), 
				this.io.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_SCONFITTA));
	}
	
	
	@Test
	public void diadiaTest_vintaDopoSbloccaStanzaBloccata() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("aula 1")
						.addAttrezzo("chiave", 1)
						.addStanzaBloccata("aula 2", "nord", "chiave")
						.addStanzaVincente("aula 3")
						.addAdiacenza("aula 1", "aula 2", "nord")
						.addAdiacenza("aula 2", "aula 3", "nord")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("prendi chiave")
				.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("posa chiave")
				.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_VITTORIA));
	}
	
	@Test
	public void diadiaTest_vintaDopoSbloccaStanzaBloccataConChiaveInvertita() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaMagica("aula 1", 1)
						.setStanzaIniziale("aula 1")
						.addStanzaBloccata("aula 2", "nord", "evaihc")
						.addAttrezzo("chiave", 1)
						.addStanzaVincente("aula 3")
						.addAdiacenza("aula 1", "aula 2", "nord")
						.addAdiacenza("aula 2", "aula 3", "nord")
						.addAdiacenza("aula 2", "aula 1", "sud")
						.getLabirinto()), 
				this.io.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("prendi chiave")
				.aggiungiComandoDaEseguire("vai sud")
				.aggiungiComandoDaEseguire("posa chiave")
				.aggiungiComandoDaEseguire("prendi chiave")
				.aggiungiComandoDaEseguire("posa chiave")
				.aggiungiComandoDaEseguire("prendi evaihc")
				.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("posa evaihc")
				.aggiungiComandoDaEseguire("vai nord")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(MESSAGGIO_VITTORIA));
	}

	@Test
	public void diadiaTest_interagisciMago_monolocale() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("stanza")
						.addMago("Merlino", "buonsalve")
						.getLabirinto()),
				this.io.aggiungiComandoDaEseguire("interagisci")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertTrue(this.io.getStampeEseguite().contains(Proprietà.getMessaggioScuse()));
	}
	
	@Test
	public void diadiaTest_regalaMago_monolocale() {
		this.diadia = new DiaDia(
				new Partita(
						Labirinto.newBuilder()
						.addStanzaIniziale("stanza")
						.addMago("Merlino", "buonsalve")
						.addAttrezzo("osso", 2)
						.getLabirinto()),
				this.io.aggiungiComandoDaEseguire("prendi osso")
				.aggiungiComandoDaEseguire("guarda")
				.aggiungiComandoDaEseguire("interagisci")
				.aggiungiComandoDaEseguire("regala osso")
				.aggiungiComandoDaEseguire("prendi osso")
				.aggiungiComandoDaEseguire("guarda")
				.aggiungiComandoDaEseguire("fine"));
		this.diadia.gioca();
		assertFalse(this.io.getStampeEseguite().contains("Sei un vero simpaticone, con una mia magica azione, troverai un nuovo oggetto per il tuo borsone!"));
		assertTrue(this.io.getStampeEseguite().contains(Proprietà.getMessaggioScuse()));
	}
}
