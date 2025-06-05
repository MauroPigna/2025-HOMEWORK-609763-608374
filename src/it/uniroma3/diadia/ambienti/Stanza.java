package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.Proprietà;
import it.uniroma3.diadia.Personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	private final int NUMERO_MASSIMO_OGGETTI = Proprietà.getNumeroMassimoAttrezzi();
	
	private String nome;
    private List<Attrezzo> attrezzi;
    private Map<String, Stanza> stanzeAdiacenti;
    private AbstractPersonaggio personaggio;
	
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>(Direzioni.values().length);
        this.attrezzi = new ArrayList<>(NUMERO_MASSIMO_OGGETTI);
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	if(this.stanzeAdiacenti.size()>=Direzioni.values().length) return;
        this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        return this.stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if(this.attrezzi.size()==this.NUMERO_MASSIMO_OGGETTI || this.attrezzi.contains(attrezzo)) return false;
        this.attrezzi.add(attrezzo);
        return true;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.getDirezioni())
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if(attrezzo!=null) {
    			risultato.append(attrezzo.toString()+" ");
    		}
    	}
    	risultato.append("\nPersonaggio nella stanza: ");
    	if(this.personaggio!=null) risultato.append(this.personaggio);
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo==null) return false;
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
	    Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
	    Attrezzo a = null;
	    while(iteratore.hasNext()) {
	    	a=iteratore.next();
	    	if(a.getNome().equals(nomeAttrezzo)) {
	    		return a;
	    	}
	    }
	    return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		boolean trovato=false;
		Iterator<Attrezzo> iteratore = this.attrezzi.iterator();
		while(iteratore.hasNext()) {
			Attrezzo a = iteratore.next();
			if(a.equals(attrezzo)){
			    iteratore.remove();
				trovato=true;
			}
		}
		return trovato;
	}


	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
    }

	public Map<String, Stanza> getMapStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza s = (Stanza)o;
		return (this.getNome().equals(s.getNome()) && this.getAttrezzi().containsAll(s.getAttrezzi()) &&  s.getAttrezzi().containsAll(this.getAttrezzi()));
	}
	
	@Override
	public int hashCode() {
		return this.getNome().hashCode();
	}

	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}
	
	public void setPersonaggio(AbstractPersonaggio p) {
		this.personaggio=p;
	}

}
