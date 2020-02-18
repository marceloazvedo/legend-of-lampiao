package br.com.lol.gerenciadores;

public class GerenciadorDeTempo implements Runnable {

	private boolean acesso;
	private int tempo;
	
	
	public GerenciadorDeTempo(int tempo){
		this.tempo = tempo;
		setAcesso(false);
	
	}
	
	

	@Override
	public void run() {
		if (!isAcesso()) {
			setAcesso(true);
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setAcesso(false);
		}
		
	
	}

	public boolean isAcesso() {
		return acesso;
	}

	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
	}

	
}
