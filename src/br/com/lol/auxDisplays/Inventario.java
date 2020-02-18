package br.com.lol.auxDisplays;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.lol.armas.Bazuca666;
import br.com.lol.armas.Calibre12;
import br.com.lol.armas.Calibre50;
import br.com.lol.armas.Espingarda;
import br.com.lol.armas.Facao;
import br.com.lol.armas.Revolver38;
import br.com.lol.armas.UsaArma;
import br.com.lol.core.Game;
import br.com.lol.dao.DaoGame;
import br.com.lol.entidade.Display;
import br.com.lol.entidade.Jogador;
import br.com.lol.gerenciadores.ImageManager;
import br.com.lol.gerenciadores.InputManager;
import br.com.lol.gerenciadores.SpriteAnimation;

public class Inventario implements Display{
	
	private DaoGame dao;
	
	private Game stage;
	
	private boolean selecionarArmas;
	private boolean selecionarOpcoes;
	private boolean selecionarInventario;
	private boolean selecionandoUmaOpcao;
	private boolean selecionandoUmaOpcaoDoInventario;
	private boolean selecionandoUmaArma;
	private boolean active;
	
	private int x, y;
	private BufferedImage inventario;
	private BufferedImage opcoes;
	private BufferedImage selecionadorVermelho;
	private BufferedImage selecionadorArma;
	private SpriteAnimation escolhasOpcoes;
	private BufferedImage inventarioArmas;
	
	private int indiceImagemOpcoes;
	private int indiceImagemInventario;
	
	private int selecionadorOpcaoY;
	private int selecionadorArmaY;
	private int selecionadorInventarioX;
	
	private int proxArmaY;
	
	private Jogador jogador;
	
	private List<UsaArma> armas;
	private List<Boolean> armasDesenhar;
	private List<Integer> armasPegas;
	
	public Inventario(int x, int y, Jogador j, Game g){
	
		this.stage = g;
		
		this.jogador = j;
		
		this.indiceImagemOpcoes = 0;
		this.indiceImagemInventario = 0;
		
		this.selecionadorOpcaoY = 126;
		this.selecionadorArmaY = 274;
		this.selecionadorInventarioX = 16;
		
		this.selecionarArmas = false;
		this.selecionarOpcoes = false;
		this.selecionandoUmaOpcao = false;
		this.selecionandoUmaOpcaoDoInventario = false;
		this.selecionandoUmaArma = false;
		this.active = false;
		
		this.selecionarInventario = false;
		
		this.x = 300;
		this.y = 225;
		
		this.proxArmaY = y + 100; // pode ir até 450
		
		this.armasDesenhar = new ArrayList<Boolean>();
		this.armasPegas = new ArrayList<Integer>();
		this.armas = new ArrayList<UsaArma>();
		
		this.dao = new DaoGame();
		try {
			this.escolhasOpcoes = ImageManager.getInstance().loadSpriteAnimationVertical(
					"br/com/lol/imagens/inventario/escolha_opcoes.png", 5);
		
			
			this.inventario = ImageManager.getInstance().loadImage("br/com/lol/imagens/inventario/inventario.png");
			this.opcoes = ImageManager.getInstance().loadImage("br/com/lol/imagens/inventario/opções.png");
			this.selecionadorVermelho = ImageManager.getInstance().loadImage("br/com/lol/imagens/inventario/selecionador_vermelho.png");
			this.selecionadorArma = ImageManager.getInstance().loadImage("br/com/lol/imagens/inventario/selecionador_Amarelo.png");
			this.inventarioArmas = ImageManager.getInstance().loadImage("br/com/lol/imagens/inventario/escolha.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void runControleEscolhaArma(int currentTick, Graphics2D g) throws InterruptedException{
		int selecionadorX = 48;
		g.drawImage(this.inventarioArmas, 18, 260, null);
		g.drawImage(this.selecionadorArma, selecionadorX, this.selecionadorArmaY, null);
		if(InputManager.getInstance().isPressed(KeyEvent.VK_UP)){
			if(this.selecionadorArmaY >  330 ){
				this.selecionadorArmaY -= 60;
				Thread.sleep(1000/10);
			}
		}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_DOWN)){
			if(this.selecionadorArmaY < 460 ){
				this.selecionadorArmaY += 60;
				Thread.sleep(1000/10);
			}
		}
		
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)){
			this.selecionandoUmaArma = false;
			this.selecionandoUmaOpcaoDoInventario = true;
			Thread.sleep(1000/10);
		}
	}
	
	private void runControleEscolhaOpcoes(int currentTick, Graphics2D g) throws InterruptedException{
		int selecionadorX = 233;
		g.drawImage(this.escolhasOpcoes.getImagens().get(this.indiceImagemOpcoes), selecionadorX, this.selecionadorOpcaoY, null);
		
		if(InputManager.getInstance().isPressed(KeyEvent.VK_DOWN)){
				if(this.selecionadorOpcaoY < 400){
					this.selecionadorOpcaoY += 82;
					this.indiceImagemOpcoes++;
						Thread.sleep(1000/10);
				}
			}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_UP)){
				if(this.selecionadorOpcaoY > 126){
					this.selecionadorOpcaoY -= 82;
					this.indiceImagemOpcoes --;
					Thread.sleep(1000/10);
				}
			}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)){
			this.selecionandoUmaOpcao = false;
			this.selecionarOpcoes = true;
			this.indiceImagemOpcoes = 0;
			this.selecionadorOpcaoY = 126;
			Thread.sleep(1000/20);
		}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ENTER)){
			if(this.indiceImagemOpcoes == 1){
				salvar();
			}
		}
		}
	
	private void runControleInventario(int currentTick, Graphics2D g) throws InterruptedException{
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ENTER)){
				this.selecionandoUmaOpcaoDoInventario = true;
				Thread.sleep(1000/10);
		}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)){
				this.selecionarInventario = false;
				this.active = false;
				Thread.sleep(1000/20);
		}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_RIGHT)){
			this.selecionarInventario = false;
			this.selecionarOpcoes = true;
				Thread.sleep(1000/20);
		}
	}
	
	private void runControleOpcaoDoInventario(Graphics2D g) throws InterruptedException{
		int selecionadorY = 93;
		g.drawImage(this.selecionadorVermelho, this.selecionadorInventarioX, selecionadorY, null);
		if(InputManager.getInstance().isPressed(KeyEvent.VK_RIGHT)){
			if(this.selecionadorInventarioX < 600){
				this.selecionadorInventarioX += 197;
				this.indiceImagemInventario++;
				Thread.sleep(1000/10);
			}
		}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_LEFT)){
			if(this.selecionadorInventarioX > 16){
				this.selecionadorInventarioX -= 197;
				this.indiceImagemInventario --;
				Thread.sleep(1000/10);
			}
		}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ENTER)){
			if(this.indiceImagemInventario == 0){
				this.selecionandoUmaArma = true;
				this.selecionandoUmaOpcaoDoInventario = false;
				Thread.sleep(1000/20);
			}
		}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)){
			this.selecionandoUmaOpcaoDoInventario = false;
			Thread.sleep(1000/20);
		}
	}

	public BufferedImage selecionarArma(int cod){
			return this.escolhasOpcoes.getImagens().get(cod);
	}
	
	
		/*if(InputManager.getInstance().isPressed(KeyEvent.VK_ENTER)){
			int placeY = (this.selecionador2X - 400) / 100;
			int placeX = (this.selecionador2Y - 300) / 25;
			int arma = this.locaisArmas[placeY][placeX];
			if(arma >= 0){
			jogador.setArma(this.armas.get(arma));
			}*/
	
	public void runControleOpcoes(int currentTick,Graphics2D g) throws InterruptedException{
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ENTER)){
				this.selecionandoUmaOpcao = true;
				Thread.sleep(1000/20);
			}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_ESCAPE)){
				this.selecionarOpcoes = false;
				this.active = false;
				Thread.sleep(1000/20);
		}
		if(InputManager.getInstance().isPressed(KeyEvent.VK_LEFT)){
				this.selecionarOpcoes = false;
				this.selecionarInventario = true;
				Thread.sleep(1000/20);
		}
	}
	
	public void salvar(){
		this.dao.salvar(this.jogador.getY(), this.jogador.getX(), this.stage.getPontos(), this.stage.getTentativas());
	}
	
	public void displayInventarioArmas(Graphics2D g){
		
		/*for(int i = 0; i< armasPegas.size(); i++){
			if(armasPegas.get(i) >= 0){
				g.drawImage(selecionarArma(this.armasPegas.get(i)), proxArmaX, proxArmaY, null);
				if(this.proxArmaY < 450){
					this.proxArmaY += 25;
				}else{
					this.proxArmaY = 375;
					if(this.proxArmaX < 575){
						this.proxArmaX += 100;
					}
				}
			}
		}*/
		this.proxArmaY = 400;
	}

	public void displayInventario(Graphics2D g, int currentTick) throws InterruptedException {
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		if(this.selecionarInventario){
			this.selecionarOpcoes = false;
		g.drawImage(this.inventario, 0, 0, null);
		if(selecionandoUmaArma){
			runControleEscolhaArma(currentTick, g);
		}else if(selecionandoUmaOpcaoDoInventario){
			runControleOpcaoDoInventario(g);
		}else{
		runControleInventario(currentTick, g);
		}
		}
		
		if(this.selecionarOpcoes){
			this.selecionarInventario = false;
			g.drawImage(this.opcoes, 0, 0, null);
			if(!selecionandoUmaOpcao){
			runControleOpcoes(currentTick, g);
			}else{
				runControleEscolhaOpcoes(currentTick, g);
			}
		}
	}
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isSelecionarArmas() {
		return selecionarArmas;
	}

	public void setSelecionarArmas(boolean selecionarArmas) {
		this.selecionarArmas = selecionarArmas;
	}

	public boolean isSelecionarOpcoes() {
		return selecionarOpcoes;
	}

	public void setSelecionarOpcoes(boolean selecionarOpcoes) {
		this.selecionarOpcoes = selecionarOpcoes;
	}

	public int getProxArmaY() {
		return proxArmaY;
	}

	public void setProxArmaY(int proxArmaY) {
		this.proxArmaY = proxArmaY;
	}

	@Override
	public void display(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	public boolean isSelecionarInventario() {
		return selecionarInventario;
	}

	public void setSelecionarInventario(boolean selecionarInventario) {
		this.selecionarInventario = selecionarInventario;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
