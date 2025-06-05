package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

class FabbricaDiComandiRiflessivaTest {
	
	private final FabbricaDiComandiRiflessiva fab = new FabbricaDiComandiRiflessiva();
	private final String parametro = "prametro";
	
	private String istruzione;

	@Test
	void test_ComandoNull() {
		istruzione=null;
		assertEquals("nonvalido", fab.costruisciComando(istruzione).getNome());
	}
	
	@Test
	void test_NomiComandi() {
		istruzione="vai";
		assertEquals(istruzione, fab.costruisciComando(istruzione).getNome());
		istruzione="posa";
		assertEquals(istruzione, fab.costruisciComando(istruzione).getNome());
		istruzione="prendi";
		assertEquals(istruzione, fab.costruisciComando(istruzione).getNome());
	}
	
	@Test
	void test_Parametri() {
		istruzione="vai "+parametro;
		assertEquals(parametro, fab.costruisciComando(istruzione).getParametro());
		istruzione="prendi "+parametro;
		assertEquals(parametro, fab.costruisciComando(istruzione).getParametro());
		istruzione="posa "+parametro;
		assertEquals(parametro, fab.costruisciComando(istruzione).getParametro());
	}
	
}
