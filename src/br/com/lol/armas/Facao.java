package br.com.lol.armas;

import br.com.lol.entidade.Jogador;

public class Facao extends Arma implements UsaArma{
	
	public Facao(Jogador j){
		super(1, j);
		this.codigo = 1;
	}

	@Override
	public void run() {
		
	}

	@Override
	public void usar(int direcao) {
		
	}

}
