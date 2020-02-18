package br.com.lol.entidade;

import java.awt.Rectangle;

public class Entidade {

	protected int energia;
	protected int x;
	protected int y;
	protected float speed;
	protected Rectangle pos;
	protected int estado;
	protected int altura;
	protected int largura;
	
	public Entidade(){
		//Não faz nada.
	}
	
	public void update(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Entidade(int x, int y){
		pos = new Rectangle(x, y, 30, 30);
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return this.y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public float getSpeed() {
		return speed;
	}


	public void setSpeed(float speed) {
		this.speed = speed;
	}


	public Rectangle getPos() {
		return pos;
	}


	public void setPos(Rectangle pos) {
		this.pos = pos;
	}	
	public int getEnergia() {
		return energia;
	}
	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x , this.y+5, 80, 80);
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}
	
	
}
