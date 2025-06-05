package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando {
	
	private String nome;
	private String parametro;
	private IO io;
	
	@Override
	public void setParametro(String c) {
		this.parametro=c;
	}
	
	@Override
	public abstract void esegui(Partita p);
	
	public String getParametro() {
		return this.parametro;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String n) {
		this.nome= new String(new StringBuilder(n.replaceAll("Comando", ""))).toLowerCase();
	}
	
	public void setIO(IO io) {
		this.io=io;
	}
	
	public IO getIO() {
		return this.io;
	}

}
