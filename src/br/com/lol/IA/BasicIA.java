package br.com.lol.IA;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import br.com.lol.entidade.Corvo;
import br.com.lol.entidade.Inimigo;
import br.com.lol.entidade.InimigoForte;
import br.com.lol.entidade.InimigoFraco;
import br.com.lol.entidade.Jogador;
import br.com.lol.gerenciadores.ImageManager;
import br.com.lol.gerenciadores.SpriteAnimation;

public class BasicIA{
	
	private SpriteAnimation spriteCareca;
	private SpriteAnimation spriteCarecaInvertido;
	private SpriteAnimation spriteVelha;
	private SpriteAnimation spriteVelhaInvertido;
	private SpriteAnimation spriteGordo;
	private SpriteAnimation spriteGordoInvertido;
	private SpriteAnimation spriteCarecaTerno;
	private SpriteAnimation spriteCarecaTernoInvertido;
	
	private Jogador jogador;
	
	public BasicIA(Jogador j){
		this.jogador = j;
		inicializarImagensInimigos();
	}
	
	private void inicializarImagensInimigos(){
		try {
			this.spriteCareca = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/zombi_careca.png", 4);
			this.spriteCarecaInvertido = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/zombi_careca_invertido.png", 4);
			this.spriteVelha = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/zombi_arrastado.png", 4);
			this.spriteVelhaInvertido = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/zombi_arrastado_invertido.png", 4);
			this.spriteGordo = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/zombi_gordo.png", 4);
			this.spriteGordoInvertido = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/zombi_gordo_invertido.png", 4);
			this.spriteCarecaTerno = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/zombi_careca_terno.png", 4);
			this.spriteCarecaTernoInvertido = ImageManager.getInstance().loadSpriteAnimation(
					"br/com/lol/imagens/zombi_careca_terno_invertido.png", 4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void adicionarInimigosLimit(List<Inimigo> inimigos, int x, int y){
		Inimigo i ;
		Random rnd = new Random();
		int choice = rnd.nextInt(3);
		while(choice < 0){
			choice = rnd.nextInt(3);
		}
		switch(choice){
		case 0: 
			i = new InimigoFraco(x, y, 1, jogador);
			i.setSpriteDireita(this.spriteCareca);
			i.setSpriteEsquerda(this.spriteCarecaInvertido);
			inimigos.add(i);
			break;
		case 1:
			i = new InimigoFraco(x, y, 1, jogador);
			i.setSpriteDireita(this.spriteGordo);
			i.setSpriteEsquerda(this.spriteGordoInvertido);
			inimigos.add(i);
			break;
		case 2:
			i = new InimigoFraco(x, y, 1, jogador);
			i.setSpriteDireita(this.spriteCarecaTerno);
			i.setSpriteEsquerda(this.spriteCarecaTernoInvertido);
			inimigos.add(i);
			break;
		case 3:
			i = new InimigoFraco(x, y, 1, jogador);
			i.setSpriteDireita(this.spriteVelha);
			i.setSpriteEsquerda(this.spriteVelhaInvertido);
			inimigos.add(i);
			break;
		}
	}
	
	public void runIa(List<Inimigo> inimigos){
		for(int i =0; i < inimigos.size(); i++){
			inimigos.get(i).seMexer();
		}
	}
	
	public void adicionarCorvos(List<Corvo> corvos){
		Random rnd = new Random();
		if(rnd.nextBoolean()){
//			corvos.add(new Corvo(0, 0, 1, jogador));
		}else{
//			corvos.add(new Corvo(800, 0, -1, jogador));
		}
	}
	
	public void ativarRadar(List<Inimigo> inimigos){
		for(int i =0; i< inimigos.size(); i++){
			int distancia = this.jogador.getX() - inimigos.get(i).getX();
			if(distancia < 0){
				distancia *= -1;
			}
			if(distancia <= 300){
				inimigos.get(i).ativarRadar();
			}else{
				inimigos.get(i).desativarRadar();
			}
		}
	}

}
