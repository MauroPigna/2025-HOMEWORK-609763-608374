package it.uniroma3.diadia.ambienti;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.Personaggi.Cane;
import it.uniroma3.diadia.Personaggi.Mago;
import it.uniroma3.diadia.Personaggi.Strega;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	private Stanza stanzaVincente;
	private Stanza stanzaIniziale;
	
	private static Map<String, Stanza> mapStanze;
	
	private Labirinto() {
		this.stanzaIniziale=null;
		this.stanzaVincente=null;
		Labirinto.mapStanze=null;
	}
	
	
    public Map<String, Stanza> getStanze(){
    	return Labirinto.mapStanze;
    }
    
    public void setStanze(Map<String, Stanza> map) {
    	Labirinto.mapStanze=map;
    }
    
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}
	
	public void setStanzaIniziale(Stanza stanza) {
		this.stanzaIniziale = stanza;
	}
	
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}
	
	public static LabirintoBuilder newBuilder(String nomeFile) throws IOException, FormatoFileNonValidoException{
		CaricatoreLabirinto caricatoreLab = new CaricatoreLabirinto(nomeFile);
		caricatoreLab.carica();
		return caricatoreLab.getLabirintoBuilder();
	}
	
	public static LabirintoBuilder newBuilder(){
		return new LabirintoBuilder();
	}
	
	public static class LabirintoBuilder {
		private Labirinto lab;
		private Map<String, Stanza> stanze;
		private String nomeStanzaCorrente;
		
		public LabirintoBuilder() {
			this.lab=new Labirinto();
			this.stanze=new HashMap<>();
			this.nomeStanzaCorrente=null;
		}
		
		public LabirintoBuilder addStanzaIniziale(String nome) {
			Stanza iniziale = new Stanza(nome);
			this.lab.setStanzaIniziale(iniziale);
			stanze.put(nome, iniziale);
			this.nomeStanzaCorrente=nome;
			return this;
		}
		
		public LabirintoBuilder addStanzaVincente(String nome2) {
			Stanza finale = new Stanza(nome2);
			this.lab.setStanzaVincente(finale);
			stanze.put(nome2, finale);
			this.nomeStanzaCorrente=nome2;
			return this;
		}
		
		public LabirintoBuilder setStanzaIniziale(String nom) {
			this.lab.setStanzaIniziale(this.stanze.get(nom));
			return this;
		}
		
		public LabirintoBuilder setStanzaVincente(String noms) {
			this.lab.setStanzaVincente(this.stanze.get(noms));
			return this;
		}
		
		public LabirintoBuilder addStanza(String nome) {
			Stanza s= new Stanza(nome);
			this.stanze.put(nome, s);
			this.nomeStanzaCorrente=nome;
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String nome, int peso) {
			if(stanze.isEmpty() || nomeStanzaCorrente==null) return this;
			stanze.get(nomeStanzaCorrente).addAttrezzo(new Attrezzo(nome, peso));
			return this;
		}
		
		public LabirintoBuilder addAttrezzo(String stanza, String nome, int peso) {
			if(stanze.isEmpty() || stanza==null || !stanze.containsKey(stanza)) return this;
			stanze.get(stanza).addAttrezzo(new Attrezzo(nome, peso));
			return this;
		}
		
		public LabirintoBuilder addAdiacenza(String nomeStanza, String nomeAdiacente, String direz) {
			if(stanze.isEmpty() || !stanze.containsKey(nomeStanza) || !stanze.containsKey(nomeAdiacente) || stanze.get(nomeStanza).getMapStanzeAdiacenti().size()==4) return this;
			stanze.get(nomeStanza).impostaStanzaAdiacente(direz, stanze.get(nomeAdiacente));
			this.nomeStanzaCorrente=nomeStanza;
			return this;
		}
		
		public Map<String, Stanza> getListaStanze(){
			return this.stanze;
		}

		public Labirinto getLabirinto() {
			this.lab.setStanze(stanze);
			return this.lab;
		}

		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			StanzaMagica s= new StanzaMagica(nome, soglia);
			this.stanze.put(nome, s);
			this.nomeStanzaCorrente=nome;
			return this;
		}
		
		public LabirintoBuilder addStanzaMagica(String nome) {
			StanzaMagica s= new StanzaMagica(nome);
			this.stanze.put(nome, s);
			this.nomeStanzaCorrente=nome;
			return this;
		}

		public LabirintoBuilder addStanzaBloccata(String nome, String dire, String key) {
			StanzaBloccata s= new StanzaBloccata(nome, dire, key);
			this.stanze.put(nome, s);
			this.nomeStanzaCorrente=nome;
			return this;
		}

		public LabirintoBuilder addStanzaBuia(String nome, String key) {
			StanzaBuia s= new StanzaBuia(nome, key);
			this.stanze.put(nome, s);
			this.nomeStanzaCorrente=nome;
			return this;
		}
		
		public LabirintoBuilder addCane(String stanza, String nome, String presentazione, String cibo, Attrezzo att) {
			if(this.stanze.isEmpty() || !this.stanze.containsKey(stanza) || stanza==null) {
				return this;
			}
			nomeStanzaCorrente=stanza;
			stanze.get(stanza).setPersonaggio(new Cane(nome, presentazione, att, cibo));
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String presentazione, String cibo, Attrezzo att) {
			if(this.stanze.isEmpty() || nomeStanzaCorrente==null) {
				return this;
			}
			stanze.get(nomeStanzaCorrente).setPersonaggio(new Cane(nome, presentazione, att, cibo));
			return this;
		}
		
		public LabirintoBuilder addMago(String stanza, String nome, String presentazione, String att, String peso) {
			if(this.stanze.isEmpty() || stanza==null || !stanze.containsKey(stanza)) return this;
			stanze.get(stanza).setPersonaggio(new Mago(nome, presentazione, new Attrezzo(att, Integer.parseInt(peso))));
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione, String att, String peso) {
			if(this.stanze.isEmpty() || nomeStanzaCorrente==null ) return this;
			stanze.get(nomeStanzaCorrente).setPersonaggio(new Mago(nome, presentazione, new Attrezzo(att, Integer.parseInt(peso))));
			return this;
		}
		
		public LabirintoBuilder addMago(String stanza, String nome, String presentazione) {
			if(this.stanze.isEmpty() || stanza==null || !stanze.containsKey(stanza)) return this;
			stanze.get(stanza).setPersonaggio(new Mago(nome, presentazione, null));
			return this;
		}
		
		public LabirintoBuilder addMago(String nome, String presentazione) {
			if(this.stanze.isEmpty() || nomeStanzaCorrente==null ) return this;
			stanze.get(nomeStanzaCorrente).setPersonaggio(new Mago(nome, presentazione, null));
			return this;
		}
		
		public LabirintoBuilder addStrega(String stanza, String nome, String presentazione) {
			if(this.stanze.isEmpty() || stanza==null || !stanze.containsKey(stanza)) return this;
			stanze.get(stanza).setPersonaggio(new Strega(nome, presentazione));
			return this;
		}
		
		public LabirintoBuilder addStrega(String nome, String presentazione) {
			if(this.stanze.isEmpty() || nomeStanzaCorrente==null) return this;
			stanze.get(nomeStanzaCorrente).setPersonaggio(new Strega(nome, presentazione));
			return this;
		}

}
}


