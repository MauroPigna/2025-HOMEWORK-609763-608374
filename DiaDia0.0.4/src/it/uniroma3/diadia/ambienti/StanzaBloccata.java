package it.uniroma3.diadia.ambienti;


public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private String lasciapassare;
	
	public StanzaBloccata (String nome) {
		this(nome, null, null);
	}
	
	
	public StanzaBloccata (String nome, String direzione, String lasciapassare) {
		super(nome);
		this.direzioneBloccata=direzione;
		this.lasciapassare=lasciapassare;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if(dir.equals(direzioneBloccata)) {
			if(this.hasAttrezzo(lasciapassare)) return super.getStanzaAdiacente(dir);
			else {
				System.out.println("Direzione bloccata, neccessaria la presenza di "+lasciapassare+" nella stanza.");
				return this;
			}
		}
		else return super.getStanzaAdiacente(dir);
	}

}
