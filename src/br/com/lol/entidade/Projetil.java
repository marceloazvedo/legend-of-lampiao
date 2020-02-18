package br.com.lol.entidade;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Projetil extends Entidade{

	private boolean visible;
	private int direcao;
	private BufferedImage imagem;
	private int altura;
	private int largura;
	private int armaReferencia;
	
	public Projetil(int x, int y, int direcao,int largura, int altura, BufferedImage imagem, int cod){
		this.armaReferencia = cod;
		this.largura = largura;
		this.altura = altura;
		this.imagem = imagem;
		this.x = x +10;
		this.y = y + 125;
		this.energia = -1;
		this.speed = 7;
		this.setVisible(true);
		this.direcao = direcao;
	}
	
	public int getDirecao(){
		return this.direcao;
	}
	
	public void setImagem(BufferedImage img){
		this.imagem = img;
	}
	
	public BufferedImage getImagem(){
		return this.imagem;
	}
	
	public Rectangle getBounds(){
		if(this.armaReferencia == 2){
		if(this.direcao > 0){
		return new Rectangle(x, y, this.largura, this.altura);
		}else{
			return new Rectangle(x - 200, y - 60, this.largura, this.altura);
		}
		}else{
			return new Rectangle(x, y, this.largura, this.altura);
		}
	}
	
	public void posicao(){
		if(direcao>0){
			if(this.x>795)
				this.setVisible(false);
		}
		else{
			if(this.x <5){
				this.setVisible(false);				
			}
		}
	}
	
	public void mover(){
		if(direcao>0){
			this.x += speed;
			if(this.x>795)
				this.setVisible(false);
		}
		else{
			this.x -= speed;
			if(this.x <5){
				this.setVisible(false);				
			}
		}
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds1() {
		if(direcao>0)
			return new Rectangle(this.x,this.y-10,400, 100);
		else
			return new Rectangle(this.x-400,this.y-10,400, 100);
	}

}
