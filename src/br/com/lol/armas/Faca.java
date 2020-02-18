package br.com.lol.armas;

import java.io.IOException;
import br.com.lol.entidade.Jogador;
import br.com.lol.entidade.Projetil;
import br.com.lol.gerenciadores.ImageManager;

public class Faca extends Arma{
	
	public Faca(int tempo, Jogador j, int x,int y) {
		super(tempo, j);
		this.x = x;
		this.y = y;
	}
	
	public void usar(int direcao) {
		if(isAcesso()){
			verificaImagemBala(direcao);
			if(direcao > 0){
			this.balas.add(new Projetil(this.x + 65, this.y - 120, direcao, 10, 5, this.imagemTiro, this.codigo));
			}else{
				this.balas.add(new Projetil(this.x - 6, this.y - 120, direcao, 10, 5, this.imagemTiro, this.codigo));
			}
		}
			
	}
	
	private void verificaImagemBala(int direcao){
		try{
		if(direcao > 0){
			this.imagemTiro = ImageManager.getInstance().loadImage("br/com/lol/imagens/facaInvertida.png");
		}else{
			this.imagemTiro = ImageManager.getInstance().loadImage("br/com/lol/imagens/faca.png");
		}
		}catch(IOException e){
			e.getMessage();
		}
	}
}
