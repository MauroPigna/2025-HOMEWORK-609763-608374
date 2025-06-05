package it.uniroma3.diadia;

import java.util.List;
import java.util.ArrayList;

public class IOSimulator implements IO{
	
	private List<String> comandiDaEseguire;
	private List<String> messaggiMostrati;
	
	public IOSimulator() {
		this.comandiDaEseguire=new ArrayList<>();
		this.messaggiMostrati=new ArrayList<>();
	}
	
	public IOSimulator aggiungiComandoDaEseguire(String comando) {
		this.comandiDaEseguire.add(comando);
		return this;
	}
	
	
	public List<String> getStampeEseguite() {
		return this.messaggiMostrati;
	}
	
	public List<String> getComandi(){
		return this.comandiDaEseguire;
	}
	
	@Override
	public String leggiRiga() {
		return this.comandiDaEseguire.remove(0);
	}
	
	@Override
	public void mostraMessaggio(String mes) {
		this.messaggiMostrati.add(mes);
	}

}
