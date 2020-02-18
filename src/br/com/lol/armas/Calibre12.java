package br.com.lol.armas;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import br.com.lol.entidade.Jogador;
import br.com.lol.entidade.Projetil;
import br.com.lol.gerenciadores.ImageManager;
import br.com.lol.gerenciadores.SpriteAnimation;

public class Calibre12 extends Arma implements UsaArma{
	
	private SpriteAnimation tiroDireita;
	private SpriteAnimation tiroEsquerda;
	
	private int contadorDireita;
	private int contadorEsquerda;
	
	public Calibre12(Jogador j){
		super(1500, j);
		this.contadorDireita = 0;
		this.contadorEsquerda = 5;
		this.energia = -1;
		this.codigo = 2;
		try {
			this.tiroDireita = ImageManager.getInstance().loadSpriteAnimation("br/com/lol/imagens/shotgun_direita.png", 6);
			this.tiroEsquerda = ImageManager.getInstance().loadSpriteAnimation("br/com/lol/imagens/shotgun_esquerda.png", 6);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loopImagem(int direcao, Graphics2D g, int x, int y){
		if(direcao > 0){
			for(int i = 0; i< this.tiroDireita.getImagens().size(); i++){
				g.drawImage(this.tiroDireita.getImagens().get(i), x, y, null );
				x += 30;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			for(int i = 0; i< this.tiroEsquerda.getImagens().size(); i++){
				g.drawImage(this.tiroEsquerda.getImagens().get(i), x, y, null );
				x -= 30;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void usar(int direcao) {
		this.direcao = direcao;
		if(isAcesso()){
			if(direcao > 0){
			this.balas.add(new Projetil(this.x + 65, this.y - 95, direcao, 400, 80,  this.imagemTiro, this.codigo));
			}else{
				this.balas.add(new Projetil(this.x - 6, this.y - 95, direcao, 400, 80, this.imagemTiro, this.codigo));
			}
		}
	}
	
	private void verificarImagem(){
		if(this.direcao > 0){
			this.imagemTiro = this.tiroDireita.getImagens().get(contadorDireita);
			this.contadorDireita++;
			if(this.contadorDireita > 5){
				this.contadorDireita = 0;
			}
		}else{
			this.imagemTiro = this.tiroEsquerda.getImagens().get(contadorEsquerda);
			this.contadorEsquerda--;
			if(this.contadorEsquerda < 0){
				this.contadorEsquerda = 5;
			}
		}
	}
	
	public int getDirecao(){
		return this.direcao;
	}
	
	public BufferedImage getImagem(){
		verificarImagem();
		return this.imagemTiro;
	}
	
	public Rectangle getBounds() {
		if(direcao>0)
			return new Rectangle(this.x,this.y-10,400, 100);
		else
			return new Rectangle(this.x-400,this.y-10,400, 100);
	}
	
}
