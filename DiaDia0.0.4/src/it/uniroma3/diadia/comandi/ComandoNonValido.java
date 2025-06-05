package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;

public class ComandoNonValido extends AbstractComando {
	private static String MESSAGGIO_COMANDO_SCONOSCIUTO = Proprietà.getMessaggioComandoSconosciuto();
	
	public ComandoNonValido() {
		this.setNome(this.getClass().getSimpleName());
	}

	@Override
	public void esegui (Partita partita) {
			super.getIO().mostraMessaggio(MESSAGGIO_COMANDO_SCONOSCIUTO);
	}

}
