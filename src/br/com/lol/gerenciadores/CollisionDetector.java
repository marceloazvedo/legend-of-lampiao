package br.com.lol.gerenciadores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import br.com.lol.IA.Temporizador;
import br.com.lol.armas.Arma;
import br.com.lol.entidade.Bau;
import br.com.lol.entidade.EntidadePlataforma;
import br.com.lol.entidade.Inimigo;
import br.com.lol.entidade.Jogador;
import br.com.lol.entidade.MestreStage1;
import br.com.lol.entidade.Projetil;
import br.com.lol.sounds.SoundBilbe;

public class CollisionDetector {
	
	private List<EntidadePlataforma> listaDeEntidades;
	private List<EntidadePlataforma> listaVerticalDireita;
	private List<EntidadePlataforma> listaVerticalEsquerda;
	private Thread threadTime;
	private Temporizador time;
	private Thread threadTiro;
	private Temporizador tiros;
	private Thread threadChefe;
	private Temporizador chefe;

	public CollisionDetector(List<EntidadePlataforma> listaDePlataformas) {
		listaDeEntidades = listaDePlataformas;

		listaVerticalDireita = new ArrayList<>();
		listaVerticalEsquerda = new ArrayList<>();
		for (EntidadePlataforma entidadePlataforma : listaDePlataformas) {
			getListaVerticalEsquerda().add(new EntidadePlataforma(entidadePlataforma.getX(), entidadePlataforma.getY()+10, 10, 600-entidadePlataforma.getY()));
			getListaVerticalDireita().add(new EntidadePlataforma(entidadePlataforma.getX()+entidadePlataforma.getLargura()-10, entidadePlataforma.getY()+10, 10, 600-entidadePlataforma.getY()));
		}
		for (EntidadePlataforma entidadePlataforma : getListaVerticalDireita()) {
			entidadePlataforma.init();
		}
		for (EntidadePlataforma entidadePlataforma : getListaVerticalEsquerda()) {
			entidadePlataforma.init();
		}
		time = new Temporizador(3000);
		threadTime = new Thread(time);
		tiros = new Temporizador(1000);
		threadTiro = new Thread(tiros);
		chefe = new Temporizador(1000);
		threadChefe = new Thread(chefe);
	}

	public boolean colisaoPlataforma(Jogador personagem) {
		boolean colisao = false;
		for (int i = 0; i < listaDeEntidades.size(); i++) {
			colisao = listaDeEntidades.get(i).getBounds()
					.intersects(personagem.getBounds())&&(listaDeEntidades.get(i).getY()==(personagem.getY()+personagem.getAltura()));
			if (colisao) {
				return true;
			}
		}
		return false;
	}
	
	public boolean colisaoPlataformaVerticalDireira(Jogador personagem){
		boolean colisao = false;
		for(EntidadePlataforma plataforma : listaVerticalDireita){
			colisao = plataforma.getBounds().intersects(personagem.getBounds());			
			if(colisao){
				return colisao;
			}
		}
		return colisao;
	}
	
	public boolean colisaoPlataformaVerticalEsquerda(Jogador personagem){
		boolean colisao = false;
		for(EntidadePlataforma plataforma : listaVerticalEsquerda){
			colisao = plataforma.getBounds().intersects(personagem.getBounds());
			if(colisao){				
				return colisao;
			}
		}
		return colisao;
	}
	
	public void desenharVertical(Graphics2D g){
		g.setColor(Color.lightGray);
		for (EntidadePlataforma vertical : getListaVerticalDireita()) {
			g.drawImage(vertical.getSprite(), vertical.getX(), vertical.getY(), null);
		}
		for (EntidadePlataforma vertical : getListaVerticalEsquerda()) {
			g.drawImage(vertical.getSprite(), vertical.getX(), vertical.getY(), null);
		}
	}
	
	public void colisaoBaus(Jogador jogador, List<Bau> baus){
		for (Bau bau : baus) {
			for (EntidadePlataforma entidade : listaDeEntidades) {
				if(!bau.getBounds().intersects(entidade.getBounds())){
					bau.caindo();
				} else {
					bau.setEstadoNoAr(2);
				}
			}
		}
		for (Bau bau : baus) {
			if(jogador.getBounds().intersects(bau.getBounds())){
				bau.alterarEstado();
			}
		}
	}
	
	public void colisaoInimigos(List<Inimigo> inimigos){
		for (Inimigo inimigo : inimigos) {
			inimigo.caindo();
			for (EntidadePlataforma entidade : listaDeEntidades) {
				if(entidade.getBounds().intersects(inimigo.getBounds())){
					inimigo.setEstadoNoAr(0);
					break;
				} else {
					inimigo.setEstadoNoAr(1);
				}
			}
			for (EntidadePlataforma entidade : listaVerticalDireita) {
				if(entidade.getBounds().intersects(inimigo.getBounds())){
					inimigo.setDirecao(1);
				}
			}
			for (EntidadePlataforma entidade : listaVerticalEsquerda) {
				if(entidade.getBounds().intersects(inimigo.getBounds())){
					inimigo.setDirecao(-1);
				}
			}
		}
	}
	
	public void colisaoInimigosContraJogador(Jogador jogador, List<Inimigo> inimigos){
		if(threadTime.getState() == Thread.State.NEW){
		for (Inimigo inimigo : inimigos) {		
			if(inimigo.getBounds().intersects(jogador.getBounds()) && jogador.getEnergia()>0){
				new SoundBilbe().playDor();
				jogador.decramentarEnergia();
				threadTime.start();
			}
		}
		} else if(threadTime.getState() == Thread.State.TERMINATED) {
			threadTime = new Thread(time);
		}
	}

	public List<EntidadePlataforma> getListaDeEntidades() {
		return listaDeEntidades;
	}

	public void setListaDeEntidades(List<EntidadePlataforma> listaDeEntidades) {
		this.listaDeEntidades = listaDeEntidades;
	}

	public List<EntidadePlataforma> getListaVerticalDireita() {
		return listaVerticalDireita;
	}

	public void setListaVerticalDireita(List<EntidadePlataforma> listaVerticalDireita) {
		this.listaVerticalDireita = listaVerticalDireita;
	}

	public List<EntidadePlataforma> getListaVerticalEsquerda() {
		return listaVerticalEsquerda;
	}

	public void setListaVerticalEsquerda(List<EntidadePlataforma> listaVerticalEsquerda) {
		this.listaVerticalEsquerda = listaVerticalEsquerda;
	}

	public void colisaoProjetil(Arma tiro, List<Inimigo> inimigos) {
		if (threadTiro.getState() == Thread.State.NEW) {
			Inimigo inimigo;
			boolean colisao = false;
			for (int i = 0; i < inimigos.size(); i++) {
				inimigo = inimigos.get(i);
				for (int j = 0; j < tiro.getBalas().size(); j++) {
					Projetil projetil = tiro.getBalas().get(j);
					colisao = inimigo.getBounds().intersects(projetil.getBounds());
					if (colisao) {
						inimigo.decrementarEnergia();
						new SoundBilbe().playDorZumbi();
						threadTiro.start();
						break;
					}
				}
				if (colisao) {
					break;
				}
			}
			for (int i = 0; i < inimigos.size(); i++) {
				inimigo = inimigos.get(i);
				if (!inimigo.isVisible()) {
					inimigos.remove(inimigo);
				}
			}
		} else if (threadTiro.getState() == Thread.State.TERMINATED) {
			threadTiro = new Thread(tiros);
		}
	}

	public void colisaoComOChefao(Jogador jogador, MestreStage1 mestre) {
		if (threadChefe.getState() == Thread.State.NEW) {
			threadChefe.start();
			if (jogador.getBounds().intersects(mestre.getBounds())) {
				jogador.decramentarEnergia();
			}
		} else if (threadChefe.getState() == Thread.State.TERMINATED) {
			threadChefe = new Thread(chefe);
		}

	}
}
