package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;

public class ComandoFine extends AbstractComando{
	
	private static final String MESSAGGIO_FINE= Proprietà.getMessaggioFine();
	
	public ComandoFine() {
		this.setNome(this.getClass().getSimpleName());
	}

	@Override
	public void esegui(Partita partita) {
        super.getIO().mostraMessaggio(MESSAGGIO_FINE);
		partita.setFinita();
	}

}
