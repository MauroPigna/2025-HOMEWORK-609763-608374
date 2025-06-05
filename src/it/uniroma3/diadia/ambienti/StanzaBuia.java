package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String chiave;
	
	
	public StanzaBuia (String nome, String attrezzo) {
		super(nome);
		this.chiave=attrezzo;
	}
	
	@Override
	public String getDescrizione() {
		String stringa;
		if(this.hasAttrezzo(chiave)) {
			return super.getDescrizione();
		}
		else {
			stringa="qui c'è buio pesto...\nLasciare nella stanza un oggetto che potrebbe risolvere il problema...";
			return stringa;
		}
	}

}
