package br.com.lol.entidade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import br.com.lol.IA.Temporizador;
import br.com.lol.gerenciadores.FontManager;
import br.com.lol.gerenciadores.ImageManager;

public class Bau extends Entidade {
	
	private BufferedImage bauFechado;
	private BufferedImage bauAbertoCheio;
	private BufferedImage bauAbertoVazio;
	private int estado;
	private int estadoNoAr;
	private static int FECHADO = 1;
	private static int CHEIO = 2;
	private static int VAZIO = 3;
	private static int CAINDO = 1;
	private Temporizador temporizador, tempoDesenho;
	private Thread threadDoTempo, threadStringDesenhos;
	private String[] itens;
	private boolean mostarItensPegos;
	Font fntItens;
	
	public Bau(int x){
		try {
			bauAbertoVazio = ImageManager.getInstance().loadImage("br/com/lol/imagens/bauAbertoVazio.png");
			bauAbertoCheio = ImageManager.getInstance().loadImage("br/com/lol/imagens/bauAbertoCheio.png");
			bauFechado = ImageManager.getInstance().loadImage("br/com/lol/imagens/bauFechado.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.estado = FECHADO;
		this.setEstadoNoAr(CAINDO);
		this.x = x;
		this.y = 100;
		this.largura = 60;
		this.altura = 40;
		this.temporizador = new Temporizador(500);
		tempoDesenho = new Temporizador(1500);
		threadDoTempo = new Thread(temporizador);
		threadStringDesenhos = new Thread(tempoDesenho);
		itens = new String[]{"iten 1","iten 2", "iten 3"};
		mostarItensPegos = true;
		this.fntItens = FontManager.getInstance().loadFont("br/com/lol/fonts/comicz.ttf", FontManager.BOLD, 30);
		
	}
	
	public void desenharEventos(Graphics2D g){
		if(estado == FECHADO)
			g.drawImage(getImagem(), this.x, this.y, this.largura,this.altura, null);
		else if(estado == CHEIO)
			g.drawImage(getImagem(), this.x, this.y, this.largura+20,this.altura, null);
		else{
			g.drawImage(getImagem(), this.x, this.y, this.largura+20,this.altura, null);
			
			if(mostarItensPegos && threadStringDesenhos.getState() == Thread.State.NEW)
				threadStringDesenhos.start();
			
			g.setColor(Color.YELLOW);
			
			if(mostarItensPegos && threadStringDesenhos.getState() != Thread.State.TERMINATED){
				for (int i = 0; i<itens.length;i++) {
					g.drawString(itens[i], 680, (i*15)+15);
				}
			} else if(threadStringDesenhos.getState() != Thread.State.TERMINATED) {
				mostarItensPegos = false;
			}
		}
		
	}
	
	public void alterarEstado(){
		if(this.threadDoTempo.getState() == Thread.State.NEW){
			threadDoTempo.start();
			if(estado == FECHADO){
				this.estado = CHEIO;
			}else if(estado == CHEIO){
				this.estado = VAZIO;
			}
		} else if(this.threadDoTempo.getState() == Thread.State.TERMINATED) {
			this.threadDoTempo = new Thread(temporizador);
		}
		
	}
	
	public BufferedImage getImagem(){
		if(estado == FECHADO){
			return bauFechado;
		}else if(estado == CHEIO){
			return bauAbertoCheio;
		} else{
			return bauAbertoVazio;
		}
	}
	public Rectangle getBounds(){
		return new Rectangle(this.x, this.y, 60, 40);
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
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
