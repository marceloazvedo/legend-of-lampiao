package br.com.lol.auxDisplays;

import java.awt.Color;
import java.awt.Graphics2D;

import br.com.lol.entidade.Display;
import br.com.lol.observador.Observer;
import br.com.lol.observador.Sujeito;

public class DisplayPontuacao implements Observer, Display{

	private Sujeito sujeito;
	private int pontos;
	
	public DisplayPontuacao(Sujeito s, int pontos){
		this.pontos = pontos;
		this.sujeito = s;
		this.sujeito.addObserver(this);
	}
	
	@Override
	public void update(int contador) {
		this.pontos += contador;
	}

	@Override
	public void display(Graphics2D g) {
		g.setColor(Color.RED );
		g.drawString("PONTOS", 650, 40);
		g.drawString("" + this.pontos, 705, 40);
	}
	
}
