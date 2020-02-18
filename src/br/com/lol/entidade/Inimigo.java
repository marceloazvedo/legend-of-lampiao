package br.com.lol.entidade;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import br.com.lol.gerenciadores.SpriteAnimation;

public class Inimigo extends Personagem{
	
	protected Jogador jogador;
	
	protected String identificador;
	
	protected int marco0X;
	
	protected SpriteAnimation spriteDireita;
	protected SpriteAnimation spriteEsquerda;
	
	private boolean visible;
	
	protected BufferedImage imagem;
	
	protected boolean radarLampiao;
	protected static int CAINDO = 1;
	protected static int REPOUSO = 0;
	private int estadoNoAr;
	
	public Inimigo(int x, int y, int direcao, Jogador j){
		this.jogador = j;
		this.setVisible(true);
		this.radarLampiao = false;
		this.x = x;
		this.y = y;
		this.direcao = direcao;
		this.speed = 3;
		this.estadoNoAr = CAINDO;
	}
	
	public void seMexer(){
		
	}
	
	public void desativarRadar(){
		this.radarLampiao = false;
	}
	
	public void ativarRadar(){
		this.radarLampiao = true;
	}
	
	public BufferedImage getImagem(){
		return this.imagem;
	}
	
	public String getIdentificador(){
		return this.identificador;
	}
	
	public void update(int currentTick){
		
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
	
	public boolean isVisible(){
		return this.visible;
	}

	public int getMarco0X() {
		return marco0X;
	}

	public void setMarco0X(int marco0x) {
		marco0X = marco0x;
	}
	
	public void caindo(){
		if(getEstadoNoAr() == CAINDO){
			this.y += 5;
		}
	}

	public int getEstadoNoAr() {
		return estadoNoAr;
	}

	public void setEstadoNoAr(int estadoNoAr) {
		this.estadoNoAr = estadoNoAr;
	}
	
	public void renderInimigo(Graphics2D g){
		seMexer();
		g.drawImage(getImagem(), getX(), getY(), 80, 80, null);
		g.setColor(Color.RED);
		g.fillRect(getX(), getY()-10, this.energia*20, 5);
	}
	
	public void decrementarEnergia() {
		if (this.energia > 0)
			this.energia--;
		if (this.energia == 0) {
			setVisible(false);
		}
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
