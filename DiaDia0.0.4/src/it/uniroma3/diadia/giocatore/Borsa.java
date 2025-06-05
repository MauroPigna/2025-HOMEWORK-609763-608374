package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = Proprietà.getPesoMaxBorsa();
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		attrezzi.add(attrezzo);
		return true;
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore=this.attrezzi.iterator();
		while(iteratore.hasNext()) {
		     a=iteratore.next();
		     if(a.getNome().equals(nomeAttrezzo)) {
		    	 return a;
		     }
		}
		return null;
	}

	public int getPeso() {
		int peso = 0;
		Attrezzo a=null;
		Iterator<Attrezzo> iteratore=this.attrezzi.iterator();
		while(iteratore.hasNext()) {
		     a=iteratore.next();
		     peso+=a.getPeso();
		}

		return peso;
	}
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while (iteratore.hasNext()) {
			a = iteratore.next();
			if (a.getNome().equals(nomeAttrezzo)) {
				iteratore.remove();
				return a;
			}
		}
		return null;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		Attrezzo a=null;

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			Iterator<Attrezzo> iteratore=this.attrezzi.iterator();
			while(iteratore.hasNext()) {
			     a=iteratore.next();
			     s.append(a+" ");
			}
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		Comparatore c = new Comparatore();
		List<Attrezzo> a = new ArrayList<Attrezzo>();
		a.addAll(attrezzi);
		
		a.sort(c);
		return a;
	}
	
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> a = new TreeSet<Attrezzo>();
		a.addAll(attrezzi);
		return a;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer,Set<Attrezzo>> m = new HashMap<>();
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		
		while(iteratore.hasNext()) {
			Attrezzo a = iteratore.next();
			Set<Attrezzo> peso = m.get(a.getPeso());
			if(!m.containsKey(a.getPeso())) peso= new TreeSet<Attrezzo>();
			peso.add(a);
			m.put(a.getPeso(), peso);
		}
		return m;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		Comparatore c = new Comparatore();
		SortedSet<Attrezzo> a = new TreeSet<Attrezzo>(attrezzi);
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(c);
		s.addAll(a);
		return s;
	}
	
}
