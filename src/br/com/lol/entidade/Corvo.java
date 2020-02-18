package br.com.lol.entidade;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import br.com.lol.gerenciadores.ImageManager;

public class Corvo extends Inimigo{

	private BufferedImage descendo;
	private BufferedImage subindo;
	private BufferedImage descendo_invertido;
	private BufferedImage subindo_invertido;
	
	private boolean estaSubindo;
	private boolean estaDescendo;
	private boolean visible;
	
	public Corvo(int x, int y, int direcao, Jogador j) {
		super(x, y, direcao,j);
		this.speed = 3;
		this.direcao = direcao;
		this.estaDescendo = true;
		this.estaSubindo = false;
		this.visible = true;
		try {
			this.descendo = ImageManager.getInstance().loadImage("br/com/lol/imagens/corvo_descendo.png");
			this.descendo_invertido = ImageManager.getInstance().loadImage("br/com/lol/imagens/corvo_invertido_descendo.png");
			this.subindo = ImageManager.getInstance().loadImage("br/com/lol/imagens/corvo_subindo.png");
			this.subindo_invertido = ImageManager.getInstance().loadImage("br/com/lol/imagens/corvo_invertido_subindo.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizarImagem(){
		if(this.direcao > 0){
			if(isEstaDescendo()){
				this.imagem = this.descendo;
			}else{
				this.imagem = this.subindo;
			}
		}else{
			if(isEstaDescendo()){
				this.imagem = this.descendo_invertido;
			}else{
				this.imagem = this.subindo_invertido;
			}
		}
	}
	
	public void atualizarPosicao(){
		if(this.direcao > 0){
			if(this.x >= 400 || this.y >= 600){
				this.setEstaSubindo(true);
				this.setEstaDescendo(false);
			}
			if((this.x > 800) || (this.y < 0)){
				this.setVisible(false);
			}
		}else{
			if(this.x <= 400 || this.y >= 600){
				this.setEstaSubindo(true);
				this.setEstaDescendo(false);
			}
			if((this.y < 0) || (this.x < 0)){
				this.setVisible(false);
			}
		}
	}
	
	public void seMexer(){
		if(this.direcao > 0){
			if(this.isEstaDescendo()){
				this.x += this.speed;
				this.y += this.speed;
			}else{
				this.x += this.speed;
				this.y -= this.speed;
			}
			atualizarImagem();
			atualizarPosicao();
		}else{
			if(this.isEstaDescendo()){
				this.x -= this.speed;
				this.y += this.speed;
			}else{
				this.x -= this.speed;
				this.y -= this.speed;
			}
			atualizarImagem();
			atualizarPosicao();
		}
	}

	public boolean isEstaSubindo() {
		return estaSubindo;
	}

	public void setEstaSubindo(boolean estaSubindo) {
		this.estaSubindo = estaSubindo;
	}

	public boolean isEstaDescendo() {
		return estaDescendo;
	}

	public void setEstaDescendo(boolean estaDescendo) {
		this.estaDescendo = estaDescendo;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(this.x, this.y, 45, 45 );
	}

}
