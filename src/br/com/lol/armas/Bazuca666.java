package br.com.lol.armas;

import br.com.lol.entidade.Jogador;

public class Bazuca666 extends Arma implements UsaArma{
	
	public Bazuca666(Jogador j){
		super(5000, j);
		this.codigo = 5;
	}

	@Override
	public void usar(int direcao) {
		// TODO Auto-generated method stub
		
	}

}
