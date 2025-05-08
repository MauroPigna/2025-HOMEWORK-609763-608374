package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	private String comando;
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "guarda"};
	
	@Override
	public void setParametro(String parametro) {
		this.comando=parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
		if(comando==null) {
			String stringa="";
			for(int i=0; i< elencoComandi.length; i++) 
				stringa= stringa+elencoComandi[i]+" ";
			System.out.println(stringa);
		}
	}

}
