package it.uniroma3.diadia.comandi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
    private HashSet<String> elencoComandi;
	
	public ComandoAiuto() {
		this.setNome(this.getClass().getSimpleName());
		elencoComandi = new HashSet<>();
	}
	
	private void creaListaComandi() throws IOException{
		BufferedReader reader = null;
		ClassLoader.getSystemClassLoader();
		try (InputStream in = ClassLoader.getSystemResourceAsStream(ComandoAiuto.class.getPackage().toString().substring(8).replaceAll("[.]","/"))){
			reader = new BufferedReader(new InputStreamReader(in));
			String riga = reader.readLine();
			do {
				riga = reader.readLine();
				if(isComandoNotValido(riga))
					continue;
				String comando = riga.substring(7).replaceAll(".class", "").trim();
				if(comando.isEmpty()) 
					continue;
				this.elencoComandi.add(comando.replace(comando.charAt(0), Character.toLowerCase(comando.charAt(0))));
			}while(riga != null);
		} catch (IOException e) { e.printStackTrace(); 
		} finally { if(reader != null) reader.close(); }
	}
	
	private boolean isComandoNotValido(String riga) {
		return riga == null || !riga.startsWith("Comando") || riga.contains("Test") || riga.contains("$") || riga.contains("NonValido");
	}

	@Override
	public void esegui(Partita partita) {
		
		try {
			creaListaComandi();
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.getIO().mostraMessaggio(elencoComandi.toString());
		
	}

}
