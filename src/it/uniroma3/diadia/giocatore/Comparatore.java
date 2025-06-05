package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Comparatore implements Comparator<Attrezzo>{
	
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		if(a1.getPeso()<a2.getPeso()) return -1;
		if(a1.getPeso()==a2.getPeso()) return 0;
		return 1;
	}
}
