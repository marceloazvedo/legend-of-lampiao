package br.com.lol.IA;

import java.util.Random;

import br.com.lol.entidade.Mestre;
import br.com.lol.entidade.MestreStage1;

public class PuloIA implements Runnable {
	
	private int x;
	private int y;
	private boolean status = false;
	private boolean statusAndar = false;
	private MestreStage1 mestre;
	private int speedX;
	private int speedY;
	
	public void run() {
		if (!this.isStatus()) {
			this.setStatus(true);
			if(this.mestre.getDirecao() > 0){
			for (int i = 0; i < 10; i++) {
				this.setY(this.getY() - this.speedY);
				this.setX(this.getX() + this.speedX);
				//this.mestre.getSpriteDireita().setLoop(true);
				try {
					Thread.sleep(1000/30);
				} catch (InterruptedException e) {
					System.out.println("Erro na Thread");
					e.printStackTrace();
				}
			}
			if(new Random().nextBoolean()){
				this.mestre.getArma().update(this.getX(), this.getY());
				this.mestre.jogarFacas();
			}
			for (int i = 0; i < 10; i++) {
				this.setY(this.getY() + this.speedY);
				this.setX(this.getX() + this.speedX);
				//this.mestre.getSpriteDireita().setLoop(true);
				try {
					Thread.sleep(1000 / 30);
				} catch (InterruptedException e) {
					System.out.println("Erro na Thread");
					e.printStackTrace();
				}
			}
			this.setStatus(false);
			this.setStatusAndar(false);
		}else{
			for (int i = 0; i < 10; i++) {
				this.setY(this.getY() - this.speedY);
				this.setX(this.getX() - this.speedX);
				//this.mestre.getSpriteEsquerda().setLoop(true);
				try {
					Thread.sleep(1000 / 30);
				} catch (InterruptedException e) {
					System.out.println("Erro na Thread");
					e.printStackTrace();
				}
			}
			if(new Random().nextBoolean()){
				this.mestre.getArma().update(this.getX(), this.getY());
				this.mestre.jogarFacas();
			}
			for (int i = 0; i < 10; i++) {
				this.setY(this.getY() + this.speedY);
				this.setX(this.getX() - this.speedX);
				//this.mestre.getSpriteEsquerda().setLoop(true);
				try {
					Thread.sleep(1000 / 30);
				} catch (InterruptedException e) {
					System.out.println("Erro na Thread");
					e.printStackTrace();
				}
			}
			this.setStatus(false);
			this.setStatusAndar(false);
		}
		}
		if(!this.statusAndar){
			this.statusAndar = true;
			if(this.mestre.getDirecao() > 0){
				for(int i = 0; i < 10; i++){
				this.setX(getX() + this.speedX);
				this.mestre.getSpriteDireita().setLoop(true);
				this.mestre.setImagem(this.mestre.getSpriteDireita().getImage());
				try {
					Thread.sleep(1000/30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				if(new Random().nextBoolean()){
					this.mestre.getArma().update(this.getX(), this.getY());
					this.mestre.jogarFacas();
				}
				this.status = false;
				this.statusAndar = false;
			}else{
				for(int i = 0; i < 10; i ++){
				this.setX(getX() - this.speedX);
				this.mestre.getSpriteEsquerda().setLoop(true);
				this.mestre.setImagem(this.mestre.getSpriteEsquerda().getImage());
				try {
					Thread.sleep(1000/30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				if(new Random().nextBoolean()){
					this.mestre.getArma().update(this.getX(), this.getY());
					this.mestre.jogarFacas();
				}
				this.statusAndar = false;
				this.status = false;
			}
			
		}
	}

	public Mestre getMestre() {
		return mestre;
	}


	public void setMestre(MestreStage1 mestre) {
		this.mestre = mestre;
	}


	public int getX() {
		return x;
	}


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setSpeedX(int x){
		this.speedX = x;
	}
	
	public void setSpeedY(int y){
		this.speedY = y;
	}

	public void setStatusAndar(boolean valor){
		this.statusAndar = valor;
	}
	
}