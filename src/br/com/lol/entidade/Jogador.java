package br.com.lol.entidade;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.lol.IA.Temporizador;
import br.com.lol.armas.Arma;
import br.com.lol.armas.Calibre12;
import br.com.lol.armas.UsaArma;
import br.com.lol.gerenciadores.ImageManager;
import br.com.lol.gerenciadores.SpriteAnimation;

public class Jogador extends Personagem {

	private List<Projetil> tiros;
	private BufferedImage imagem;
	private Pulo pulo;
	protected SpriteAnimation spritesDireita;
	protected SpriteAnimation spritesEsquerda;
	protected BufferedImage paradoDireita;
	protected BufferedImage paradoEsquerda;
	protected BufferedImage pulandoDireita;
	protected BufferedImage pulandoEsquerda;
	protected BufferedImage abaixadoDireita;
	protected BufferedImage abaixadoEsquerda;
	protected BufferedImage[] cabaças;
	protected BufferedImage[] lampiaoMorto;
	private SpriteAnimation spritesVoandoDireita;
	private SpriteAnimation spritesVoandoEsquerda;
	private BufferedImage imgVoando;
	private SpriteAnimation fumaçaSprite;
	private boolean modoVoador;
	private boolean estaAbaixado;
	private int yAbaixado;
	private int alturaAbaixado;
	private int larguraAbaixado;
	private Thread threadDaFumaça;
	private Thread threadDaMana;
	private Thread threadRecuperacao;
	private Temporizador timeFumaça;
	private Temporizador timeMana;	
	private Temporizador timeRecuperacao;
	private int displayMana;

	public boolean isModoVoador() {
		return modoVoador;
	}

	public SpriteAnimation getSpritesVoandoDireita() {
		return spritesVoandoDireita;
	}

	public void setSpritesVoandoDireita(SpriteAnimation spritesVoandoDireita) {
		this.spritesVoandoDireita = spritesVoandoDireita;
	}

	public SpriteAnimation getSpritesVoandoEsquerda() {
		return spritesVoandoEsquerda;
	}

	public void setSpritesVoandoEsquerda(SpriteAnimation spritesVoandoEsquerda) {
		this.spritesVoandoEsquerda = spritesVoandoEsquerda;
	}

	public Jogador(int x, int y) {
		this.energia = 3;
		this.x = x;
		this.y = y;
		this.speed = 5;

		this.estado = 4;
		this.estadoDoSalto = 0;
		this.setEstaAbaixado(false);
		this.direcao = 1;
		this.modoVoador = false;
		altura = 80;
		largura = 90;
		this.alturaAbaixado = 10;
		this.larguraAbaixado = 10;
		displayMana = 100;

		this.arma = new Calibre12(this);
		timeFumaça = new Temporizador(600);
		threadDaFumaça = new Thread(timeFumaça);
		timeMana = new Temporizador(1000);
		threadDaMana = new Thread(timeMana);
		timeRecuperacao = new Temporizador(1000);
		threadRecuperacao = new Thread(timeRecuperacao);

		this.tiros = new ArrayList<Projetil>();
		this.spritesDireita = new SpriteAnimation();
		this.spritesEsquerda = new SpriteAnimation();
		this.spritesVoandoDireita = new SpriteAnimation();
		this.spritesVoandoEsquerda = new SpriteAnimation();
		this.setFumaçaSprite(new SpriteAnimation());
		try {
			this.spritesDireita = ImageManager.getInstance()
					.loadSpriteAnimation(
							"br/com/lol/imagens/spriteDireita1.png", 8);
			this.spritesEsquerda = ImageManager.getInstance()
					.loadSpriteAnimation(
							"br/com/lol/imagens/spriteEsquerda1.png", 8);
			this.pulandoDireita = ImageManager.getInstance().loadImage(
					"br/com/lol/imagens/lampia_pulando_normal.png");
			this.pulandoEsquerda = ImageManager.getInstance().loadImage(
					"br/com/lol/imagens/lampia_pulando_invertido.png");
			this.paradoEsquerda = ImageManager.getInstance().loadImage(
					"br/com/lol/imagens/lampiao_invertida.png");
			this.paradoDireita = ImageManager.getInstance().loadImage(
					"br/com/lol/imagens/lampiao_normal.png");
			this.spritesVoandoEsquerda = ImageManager.getInstance()
					.loadSpriteAnimation(
							"br/com/lol/imagens/spriteCarcaraAtt.png", 8);
			this.spritesVoandoDireita = ImageManager
					.getInstance()
					.loadSpriteAnimation(
							"br/com/lol/imagens/spriteCompletoCarcaraDireita1.png",
							8);
			this.setFumaçaSprite(ImageManager.getInstance()
					.loadSpriteAnimation(
							"br/com/lol/imagens/fumaçaSpriteOK1.png", 6));
			this.abaixadoDireita = ImageManager.getInstance().loadImage(
					"br/com/lol/imagens/lampião_abaixado_normal1.png");
			this.abaixadoEsquerda = ImageManager.getInstance().loadImage(
					"br/com/lol/imagens/lampião_abaixado_invertido1.png");
			this.cabaças = new BufferedImage[4];
			this.lampiaoMorto = new BufferedImage[2];
			for (int i = 0; i < 4; i++) {
				cabaças[i] = ImageManager.getInstance().loadImage(
						"br/com/lol/imagens/cabaça" + i + ".png");
			}
			lampiaoMorto[0] = ImageManager.getInstance().loadImage(
					"br/com/lol/imagens/lampiaoMortoEsquerda.png");
			lampiaoMorto[1] = ImageManager.getInstance().loadImage(
					"br/com/lol/imagens/lampiaoMortoDireito.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pular(int chao) {
		if (this.y > chao - Pulo.getAlturaMax()) {
			pulo.start(y);
		} else
			pulo.decrementar(y);
	}

	public BufferedImage getImagem() {
		if (energia == 0) {
			if (direcao > 0)
				return this.lampiaoMorto[1];
			else
				return this.lampiaoMorto[0];
		}
		if (modoVoador == true)
			return this.imgVoando;
		if ((this.estadoDoSalto == 1) || this.estadoDoSalto == 2)
			if (this.direcao == 1)
				return this.pulandoDireita;
			else
				return this.pulandoEsquerda;
		else if (this.estado == 3)
			return this.imagem;
		else if (isEstaAbaixado()) {
			if (direcao == 1)
				return abaixadoDireita;
			else
				return abaixadoEsquerda;
		} else if (direcao == 1)
			return this.paradoDireita;
		else
			return this.paradoEsquerda;
	}

	public void andar(int direcao) {
		if (direcao > 0) {
			spritesDireita.setLoop(true);
			this.imagem = spritesDireita.getImage();
		} else {
			spritesEsquerda.setLoop(true);
			this.imagem = spritesEsquerda.getImage();

		}
	}

	public void updateFly() {
		if (threadDaFumaça.getState() == Thread.State.TERMINATED)
			threadDaFumaça = new Thread(timeFumaça);
		if (isModoVoador()
				&& threadDaFumaça.getState() == Thread.State.TIMED_WAITING) {
			getFumaçaSprite().setLoop(true);
			this.imgVoando = getFumaçaSprite().getImage();
			return;
		}
		if (isModoVoador()) {
			if (direcao > 0) {
				spritesVoandoDireita.setLoop(true);
				this.imgVoando = spritesVoandoDireita.getImage();
			} else {
				spritesVoandoEsquerda.setLoop(true);
				this.imgVoando = spritesVoandoEsquerda.getImage();
			}
		}
	}

	public void atirar() {
		arma.usar(this.direcao);
		new Thread(arma).start();
	}

	public void setArma(UsaArma arma) {
		this.arma = arma;
	}

	public Arma getArma() {
		return (Arma) this.arma;
	}

	public List<Projetil> getTiros() {
		return tiros;
	}

	public void setTiros(List<Projetil> tiros) {
		this.tiros = tiros;
	}

	public SpriteAnimation getSpritesEsquerda() {
		return spritesEsquerda;
	}

	public void setSpritesEsquerda(SpriteAnimation spritesEsquerda) {
		this.spritesEsquerda = spritesEsquerda;
	}

	public SpriteAnimation getSpritesDireita() {
		return spritesDireita;
	}

	public void setSpritesDireita(SpriteAnimation spritesDireita) {
		this.spritesDireita = spritesDireita;
	}

	public void ativarModoVoador() {
		if (displayMana > 0) {
			if (threadDaFumaça.getState() == Thread.State.NEW) {
				threadDaFumaça.start();
				this.y -= 45;
				this.altura = 125;
				this.largura = 135;
				this.modoVoador = true;
			} else if (threadDaFumaça.getState() == Thread.State.TERMINATED) {
				threadDaFumaça = new Thread(timeFumaça);
			}
		}
	}

	public void desativarModoVoador() {
		this.altura = 80;
		this.largura = 90;
		this.modoVoador = false;
	}

	public Rectangle getBounds() {
		if (this.energia == 0){
			return new Rectangle(this.x, this.y, this.largura,
					this.altura+10);
		}
		if (estaAbaixado && !modoVoador) {
			return new Rectangle(this.x, this.y + 80, this.larguraAbaixado,
					this.alturaAbaixado);
		} else if(modoVoador) {
			return new Rectangle(this.x, this.y, this.largura, this.altura+10);
		} else {
			return new Rectangle(this.x, this.y, this.largura, this.altura+10);
		}
	}

	public boolean isEstaAbaixado() {
		return estaAbaixado;
	}

	public void setEstaAbaixado(boolean estaAbaixado) {
		this.estaAbaixado = estaAbaixado;
		yAbaixado = this.y + 35;
		alturaAbaixado = this.altura - 35;
		larguraAbaixado = this.largura - 20;
	}

	public void render(Graphics2D g) {
		if(modoVoador){
			g.setColor(Color.BLUE);
			g.fillRect(this.x, this.y-10, displayMana, 5);
		}
		g.drawImage(cabaças[energia], 10, 50, 110, 100, null);
		if (this.energia == 0){
			desativarModoVoador();
			g.drawImage(getImagem(), this.x, this.y+40, this.largura+10,
					this.altura-30, null);
		}
		else if (estaAbaixado && !modoVoador) {
			g.drawImage(getImagem(), getX(), this.yAbaixado,
					this.larguraAbaixado, this.alturaAbaixado, null);
		} else {
			g.drawImage(getImagem(), getX(), getY(), getLargura(), getAltura(),
					null);
		}
	}

	public SpriteAnimation getFumaçaSprite() {
		return fumaçaSprite;
	}

	public void setFumaçaSprite(SpriteAnimation fumaçaSprite) {
		this.fumaçaSprite = fumaçaSprite;
	}

	public void decramentarEnergia() {
		if (this.energia > 0)
			this.energia--;
	}
	
	public void updateMana() {
		if (displayMana == 0) {
			desativarModoVoador();
		}
		if (modoVoador) {
			if (threadDaMana.getState() == Thread.State.NEW) {
				threadDaMana.start();
				displayMana -= 10;
			} else if (threadDaMana.getState() == Thread.State.TERMINATED) {
				threadDaMana = new Thread(timeMana);
			}
		}
		if (!modoVoador && displayMana < 100) {
			if (threadRecuperacao.getState() == Thread.State.NEW) {
				threadRecuperacao.start();
			} else if (threadRecuperacao.getState() == Thread.State.TERMINATED) {
				threadRecuperacao = new Thread(timeRecuperacao);
				displayMana += 10;
				;
			}
		}
	}
}
