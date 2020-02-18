package br.com.lol.entidade;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.lol.IA.PuloIA;
import br.com.lol.armas.Arma;
import br.com.lol.armas.Faca;
import br.com.lol.gerenciadores.ImageManager;
import br.com.lol.gerenciadores.SpriteAnimation;

public class MestreStage1 extends Mestre {

	private Faca arma;
	private boolean executando;
	private PuloIA pulo;
	private SpriteAnimation spriteDireita;
	private SpriteAnimation spriteEsquerda;
	
	public MestreStage1(int x, int y,int direcao,Jogador j) {
		super(x, y,direcao, j);
		this.executando = false;
		this.energia = 8;
		this.speed = 15;
		this.pulo = new PuloIA();
		this.pulo.setY(y);
		this.pulo.setX(x);
		this.pulo.setMestre(this);
		this.arma = new Faca(500, j, this.pulo.getX(), this.pulo.getY());
		
		try {
			this.spriteDireita = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/sprite_chefao_direita.png", 8);
			this.spriteEsquerda = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/sprite_chefao_esquerda.png", 8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImagem(int dir){
		return this.imagem;
	}

	public void andar(int speedX) {
		this.pulo.setSpeedX(speedX);
		this.pulo.setStatus(true);
		new Thread(pulo).start();
		atualizarPosicao();
	}

	public void jump(int speedX, int speedY) {
		this.pulo.setSpeedX(speedX);
		this.pulo.setSpeedY(speedY);
		this.pulo.setStatusAndar(true);
		new Thread(pulo).start();
		atualizarPosicao();
	}

	public void jogarFacas() {
		this.arma.usar(this.direcao);
	}
	
	public void atualizarPosicao(){
		if(getPulo().getX() >= 740){
			this.direcao = -1;
		}else if(getPulo().getX() <= 0){
			this.direcao = 1;
		}
	}

	public Faca getArma() {
		return this.arma;
	}

	public void setArma(Faca arma) {
		this.arma = arma;
	}

	public boolean isExecutando() {
		return executando;
	}

	public void setExecutando(boolean executando) {
		this.executando = executando;
	}

	public PuloIA getPulo() {
		return pulo;
	}

	public void setPulo(PuloIA pulo) {
		this.pulo = pulo;
	}
	public SpriteAnimation getSpriteDireita() {
		return spriteDireita;
	}

	public void setSpriteDireita(SpriteAnimation spriteDireita) {
		this.spriteDireita = spriteDireita;
	}

	public SpriteAnimation getSpriteEsquerda() {
		return spriteEsquerda;
	}

	public void setSpriteEsquerda(SpriteAnimation spriteEsquerda) {
		this.spriteEsquerda = spriteEsquerda;
	}
	public void setImagem(BufferedImage imagem){
		this.imagem = imagem;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(this.pulo.getX(), this.pulo.getY(), 100, 80);
	}
	
}

class Aleatorio {

	private Random rnd;
	private int min;

	public Aleatorio(int min, int max) {
		this.rnd = new Random(max);
		this.min = min;
	}

	public int sorteio() {
		int i = rnd.nextInt();
		while (i > this.min) {
			return i;
		}
		return i;
	}

}
