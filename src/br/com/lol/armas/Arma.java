package br.com.lol.armas;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import br.com.lol.entidade.Entidade;
import br.com.lol.entidade.Jogador;
import br.com.lol.entidade.Projetil;
import br.com.lol.sounds.SoundBilbe;

public class Arma extends Entidade implements Runnable{

	protected int codigo;
	protected int tempo;
	protected int direcao;
	private boolean acesso;
	BufferedImage imagemTiro;
	protected List<Projetil> balas;

	public Arma(int tempo, Jogador j){
		this.x = j.getX();
		this.y = j.getY();
		this.balas = new ArrayList<Projetil>();
		acesso = true;
		this.tempo = tempo;
		this.direcao = j.getDirecao();
	}
	
	public int getCodigo(){
		return this.codigo;
	}
	
	public BufferedImage getImagem(){
		return null;
	}
	
	public void loopImagem(int direcao, Graphics2D g, int x, int y){
		
	}

	@Override
	public void run() {
		if(this.getCodigo() == 2){
			if(isAcesso()){
				setAcesso(false);
				new SoundBilbe().playTiro();
				try {
					Thread.sleep(tempo);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setAcesso(true);
			}
		}else if(isAcesso()){
			setAcesso(false);
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setAcesso(true);
		}
	}

	public boolean isAcesso() {
		return acesso;
	}

	public void setAcesso(boolean acesso) {
		this.acesso = acesso;
	}
	
	public List<Projetil> getBalas(){
		return this.balas;
	}	
}
