package br.com.lol.armas;

import br.com.lol.entidade.Jogador;

public class Calibre50 extends Arma implements UsaArma {
	
	public Calibre50(Jogador j){
		super(1500, j);
		this.codigo = 3;
	}

	@Override
	public void usar(int direcao) {
		// TODO Auto-generated method stub
		
	}

}
