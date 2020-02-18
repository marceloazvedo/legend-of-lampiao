package br.com.lol.IA;

import java.util.Random;

import br.com.lol.entidade.MestreStage1;

public class IaChefe implements Runnable{
	
	private MestreStage1 mestre;
	
	public IaChefe(MestreStage1 mestre){
		this.mestre = mestre;
	}
	
	public void runIA(){
		if(this.mestre.getEnergia() > 5){
			runModeNormal();
		}else if(this.mestre.getEnergia() > 2){
			runModeArretado();
		}else if(this.mestre.getEnergia() >1){
			runModeCaoNosCoro();
		}
	}
	private void runModeArretado(){
		int x = new Aleatorio(5, 10).sorteio();
		int y = new Aleatorio(5, 10).sorteio();
		Random rnd = new Random();
		System.out.println("MODO ARRETADO");
		if (rnd.nextBoolean()) {
			this.mestre.andar(x);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.mestre.jump(x, y);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void runModeNormal(){
		int x = new Aleatorio(0, 5).sorteio();
		int y = new Aleatorio(0, 5).sorteio();
		System.out.println("MODO NORMAL");
		Random rnd = new Random();
		if (rnd.nextBoolean()) {
			this.mestre.andar(x);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.mestre.jump(x, y);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private void runModeCaoNosCoro() {
		int x = new Aleatorio(10, 15).sorteio();
		int y = new Aleatorio(10, 15).sorteio();
		Random rnd = new Random();
		System.out.println("CAO NOS COURO");
		if (rnd.nextBoolean()) {
			this.mestre.andar(x);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			this.mestre.jump(x, y);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		runIA();
	}

}


