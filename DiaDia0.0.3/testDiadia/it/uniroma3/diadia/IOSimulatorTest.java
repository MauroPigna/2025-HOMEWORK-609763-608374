package it.uniroma3.diadia;

class IOSimulatorTest {
	
	public static IOSimulator PartitaSimulata(String... comandi) {
		IOSimulator io = new IOSimulator(comandi);
		DiaDia diadia = new DiaDia(io);
		diadia.gioca();
		for(int i=0; i<comandi.length; i++)
			diadia.processaIstruzione(comandi[i]);
		return io;
	}
	
	public static void main(String[] args) {
		System.out.println("Benvenuto in questa simulazione generata automaticamente!");
		PartitaSimulata("vai nord");
	};

}
