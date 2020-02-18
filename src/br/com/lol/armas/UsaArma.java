package br.com.lol.armas;

import java.awt.Rectangle;

import br.com.lol.entidade.Jogador;

public interface UsaArma extends Runnable {

	public void usar(int direcao);
	public Rectangle getBounds();
	
}
