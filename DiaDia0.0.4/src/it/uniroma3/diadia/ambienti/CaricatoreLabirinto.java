package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe CaricatoreLabirinto - Gestisce la creazione 
 * di labirinti letti da file testuali. 
 * 
 * @see LabirintoBuilder
 * @version 4.0
 */
public class CaricatoreLabirinto {
	// utilizzare "_" al posto degli spazi se fanno parte dello stesso argomento 

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";    

	/* prefisso di una singola riga di testo contenente tutti le specifiche delle stanze buie nel formato <nomeStanza> <nomeAttrezzoNecessario> */
	private static final String STANZE_BUIE_MARKER = "Stanze Buie:";             

	/* prefisso di una singola riga di testo contenente tutti le specifiche delle stanze buie nel formato <nomeStanza> <numeroSogliaMagica> oppure <nomeStanza>*/
	private static final String STANZE_MAGICHE_MARKER = "Stanze Magiche:";             

	/* prefisso di una singola riga di testo contenente tutti le specifiche delle stanze buie nel formato <nomeStanza> <nomeDirezioneBloccata> <nomeAttrezzoNecessario> */
	private static final String STANZE_BLOCCATE_MARKER = "Stanze Bloccate:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/* prefisso della riga contenente le specifiche dei personaggi nel formato <nomeStanza> <tipo> <nome> <presentazione> <ciboPreferito> <nomeAttrezzoInBocca> <pesoAttrezzoInBocca>. Solo per cane gli ultimi tre, usare "_" al posto degli spazi */
	private static final String PERSONAGGI_MARKER = "Personaggi:";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	private LineNumberReader reader;
	private LabirintoBuilder labirintoBuilder;

	public CaricatoreLabirinto(String nomeFile)  throws IOException {
		this.labirintoBuilder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(Reader reader){
		this.labirintoBuilder = Labirinto.newBuilder();
		this.reader = new LineNumberReader(reader);
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaPersonaggi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length()).trim();
		} catch (IOException e) { throw new FormatoFileNonValidoException(e.getMessage()); }
	}

	private String nextTrim(Scanner scanner) {
		return scanner.next().trim().replaceAll("_", " ");
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext())
				result.add(scannerDiParole.next().trim());
		}
		return result;
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze))
			this.labirintoBuilder.addStanza(nomeStanza.trim().replaceAll("_", " "));
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String stanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);

		for(String stanzaBuia : separaStringheAlleVirgole(stanzeBuie)) {
			String nomeStanza = null;
			String nomeAttrezzoNecessario = null;
			try (Scanner scannerLinea = new Scanner(stanzaBuia)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = nextTrim(scannerLinea);
				check(this.isStanzaValida(nomeStanza), nomeStanza +"non definita");
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo necessario "+nomeStanza+"."));
				nomeAttrezzoNecessario = nextTrim(scannerLinea);
			}				
			this.labirintoBuilder.addStanzaBuia(nomeStanza, nomeAttrezzoNecessario);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String stanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);

		for(String stanzaMagica : separaStringheAlleVirgole(stanzeMagiche)) {
			String nomeStanza = null;
			String sogliaMagica = null;
			try (Scanner scannerLinea = new Scanner(stanzaMagica)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = nextTrim(scannerLinea);
				check(this.isStanzaValida(nomeStanza), nomeStanza +"non definita");
				if(scannerLinea.hasNext())
					sogliaMagica = nextTrim(scannerLinea);
			}
			if(sogliaMagica == null) this.labirintoBuilder.addStanzaMagica(nomeStanza);
			else this.labirintoBuilder.addStanzaMagica(nomeStanza, Integer.parseInt(sogliaMagica));
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String stanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);

		for(String stanzaBloccata : separaStringheAlleVirgole(stanzeBloccate)) {
			String nomeStanza = null;
			String nomeDirezione = null;
			String nomeAttrezzoNecessario = null;
			try (Scanner scannerLinea = new Scanner(stanzaBloccata)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = nextTrim(scannerLinea);
				check(this.isStanzaValida(nomeStanza), nomeStanza +"non definita");
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome della direzione "+nomeStanza+"."));
				nomeDirezione = nextTrim(scannerLinea);
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo necessario"+nomeStanza+"per la direzione"+nomeDirezione+"."));
				nomeAttrezzoNecessario = nextTrim(scannerLinea);
			}				
			this.labirintoBuilder.addStanzaBloccata(nomeStanza, nomeDirezione, nomeAttrezzoNecessario);
		}
	}

	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String personaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for(String personaggio : separaStringheAlleVirgole(personaggi)) {
			String nomeStanza = null;
			String tipoPersonaggio = null;
			String nomePersonaggio = null;
			String presentazionePersonaggio = null;
			String ciboPreferito = null;
			String nomeAttrezzoInBocca = null;
			String pesoAttrezzoInBocca = null;
			try (Scanner scannerLinea = new Scanner(personaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza."));
				nomeStanza = nextTrim(scannerLinea);
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il tipo del personaggio in "+nomeStanza+"."));
				tipoPersonaggio = nextTrim(scannerLinea);
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome del "+tipoPersonaggio+" in "+nomeStanza+"."));
				nomePersonaggio = nextTrim(scannerLinea);
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("la presentazione del "+tipoPersonaggio+nomePersonaggio+" in "+nomeStanza+"."));
				presentazionePersonaggio = nextTrim(scannerLinea);
				if(!tipoPersonaggio.equals("Strega")) {
					check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo in bocca al Cane "+nomePersonaggio+" in "+nomeStanza+"."));
					nomeAttrezzoInBocca = nextTrim(scannerLinea);
					check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzoInBocca+" in bocca al Cane "+nomePersonaggio+" in "+nomeStanza+"."));
					pesoAttrezzoInBocca = nextTrim(scannerLinea);
					if(tipoPersonaggio.equals("Cane")) {
						check(scannerLinea.hasNext(), msgTerminazionePrecoce("il cibo preferito del Cane "+nomePersonaggio+" in "+nomeStanza+"."));
						ciboPreferito = nextTrim(scannerLinea);
					}
				}
			}
			collacaPersonaggio(nomeStanza, tipoPersonaggio, nomePersonaggio, presentazionePersonaggio, nomeAttrezzoInBocca, pesoAttrezzoInBocca, ciboPreferito);


		}
	}

	private void collacaPersonaggio(String nomeStanza, String tipoPersonaggio, String nomePersonaggio, String presentazionePersonaggio, String nomeAttrezzoInBocca, String pesoAttrezzoInBocca, String ciboPreferito) throws FormatoFileNonValidoException{
		int peso;
		try {
			check(this.isStanzaValida(nomeStanza), "Personaggio"+tipoPersonaggio+" "+nomePersonaggio+" non collocabile in "+ nomeStanza +"non definita");
			check(tipoPersonaggio.equals("Cane") || tipoPersonaggio.equals("Mago") || tipoPersonaggio.equals("Strega"),msgTerminazionePrecoce("Personaggio "+nomePersonaggio+"non collocabile in "+nomeStanza+" il tipo "+tipoPersonaggio+" non definito"));
			if(tipoPersonaggio.equals("Mago"))
				this.labirintoBuilder.addMago(nomeStanza, nomePersonaggio, presentazionePersonaggio, nomeAttrezzoInBocca, pesoAttrezzoInBocca);
			else if(tipoPersonaggio.equals("Strega"))
				this.labirintoBuilder.addStrega(nomeStanza, nomePersonaggio, presentazionePersonaggio);
			else if(tipoPersonaggio.equals("Cane")) {
				peso = Integer.parseInt(pesoAttrezzoInBocca);
				this.labirintoBuilder.addCane(nomeStanza, nomePersonaggio, presentazionePersonaggio, ciboPreferito, new Attrezzo(nomeAttrezzoInBocca, peso));
			}
			else
				check(false, tipoPersonaggio+" non definito");
		} catch (NumberFormatException e) { check(false, "Peso attrezzo "+nomeAttrezzoInBocca+" non valido"); }
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.labirintoBuilder.setStanzaIniziale(nomeStanzaIniziale);
		this.labirintoBuilder.setStanzaVincente(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = nextTrim(scannerLinea);
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = nextTrim(scannerLinea);
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = nextTrim(scannerLinea);
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.labirintoBuilder.addAttrezzo(nomeStanza, nomeAttrezzo, peso);
		} catch (NumberFormatException e) { check(false, "Peso attrezzo "+nomeAttrezzo+" non valido"); }
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);

		for(String specifcheUscita : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza = null;
			String stanzaDestinazione = null;
			String dir = null;
			try (Scanner scannerDiLinea = new Scanner(specifcheUscita)) {
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				stanzaPartenza = nextTrim(scannerDiLinea);
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				dir = nextTrim(scannerDiLinea);
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				stanzaDestinazione = nextTrim(scannerDiLinea);				
			}
			impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
		} 
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		this.labirintoBuilder.addAdiacenza(stanzaDa, nomeA, dir);
	}

	private boolean isStanzaValida(String nomeStanza) {
		return this.labirintoBuilder.getListaStanze().containsKey(nomeStanza.trim());
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public LabirintoBuilder getLabirintoBuilder() {
		return this.labirintoBuilder;
	}

	public Labirinto getLabirinto() {
		return this.labirintoBuilder.getLabirinto();
	}
}
