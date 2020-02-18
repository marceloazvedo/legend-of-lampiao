package br.com.lol.IA;

public class Temporizador implements Runnable {

	private long time;
	
	public Temporizador(long time){
		this.time = time;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
