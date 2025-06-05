package it.uniroma3.diadia.Personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	private Attrezzo attrezzo;
	private String ciboPreferito;
	
	private static final String MESSAGGIO_MORSO = Proprietà.getMessaggioMorso();
	private static final String MESSAGGIO_CIBO = Proprietà.getMessaggioCibo();
	private static final String MESSAGGIO_NON_HA_OGGETTO = Proprietà.getMessaggioNonHaOggetto();
	
	
	public Cane(String nome, String presentazione, Attrezzo att, String cibo) {
		super(nome, presentazione);
		this.attrezzo=att;
		this.ciboPreferito=cibo;
	}
	
	
	@Override
	public String riceviRegalo(Attrezzo att, Partita partita) {
		if(!att.getNome().equals(ciboPreferito))
			return this.agisci(partita);
		if(this.attrezzo==null && att.getNome().equals(ciboPreferito))
			return MESSAGGIO_NON_HA_OGGETTO;
		if(!(this.attrezzo==null) && att.getNome().equals(ciboPreferito)) {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			this.attrezzo=null;
			return MESSAGGIO_CIBO; 
		}
		return "";
	}
	
	@Override 
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_MORSO;
	}

}
