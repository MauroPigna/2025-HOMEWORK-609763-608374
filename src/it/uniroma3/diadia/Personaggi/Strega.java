package it.uniroma3.diadia.Personaggi;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	private Set<Attrezzo> att;
	
	private static final String MESSAGGIO_RISATA = Proprietà.getMessaggioRisata();
	private static final String MESSAGGIO_NON_SALUTATA = Proprietà.getMessaggioNonSalutata();
	private static final String MESSAGGIO_INGANNO = Proprietà.getMessaggioInganno();
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
		this.att=new HashSet<Attrezzo>();
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		this.att.add(attrezzo);
		return MESSAGGIO_RISATA;
	}
	
	@Override 
	public String agisci(Partita partita) {
		if (!this.haSalutato()) {
			Set<String> l1 =partita.getStanzaCorrente().getDirezioni();
			Iterator<String> iter = l1.iterator();
			Stanza provv = partita.getStanzaCorrente().getStanzaAdiacente(iter.next());
			while(iter.hasNext()) {
				if(partita.getStanzaCorrente().getStanzaAdiacente(iter.next()).getAttrezzi().size()<provv.getAttrezzi().size())
					provv=partita.getStanzaCorrente().getStanzaAdiacente(iter.next());
			}
			partita.setStanzaCorrente(provv);
			return MESSAGGIO_NON_SALUTATA;
		}
		Set<String> l2 =partita.getStanzaCorrente().getDirezioni();
		Iterator<String> iter = l2.iterator();
		Stanza provv = partita.getStanzaCorrente().getStanzaAdiacente(iter.next());
		while(iter.hasNext()) {
			if(partita.getStanzaCorrente().getStanzaAdiacente(iter.next()).getAttrezzi().size()>provv.getAttrezzi().size())
				provv=partita.getStanzaCorrente().getStanzaAdiacente(iter.next());
		}
		partita.setStanzaCorrente(provv);
		return MESSAGGIO_INGANNO;
	}

}
