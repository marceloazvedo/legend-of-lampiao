package br.com.lol.sounds;

import java.applet.AudioClip;
import java.io.IOException;

import br.com.lol.IA.Temporizador;
import br.com.lol.gerenciadores.AudioManager;

public class SoundBilbe {
	
	private AudioClip tiro_doze;
	private AudioClip dor_lampiao;
	private AudioClip carcara_ativar;
	private AudioClip musica_tema;
	private AudioClip corvos;
	private AudioClip jogar_facas;
	private AudioClip dorZumbi;
	private AudioClip passos;
	
	private Thread threadCorvo;
	private Thread threadDor;
	private Thread threadTiro;
	private Thread threadFacas;
	
	private Temporizador timerDor;
	private Temporizador timerTiro;
	private Temporizador timerFacas;
	private Temporizador timerCorvo;
	
	public SoundBilbe(){
		this.timerCorvo = new Temporizador(3000);
		this.timerDor = new Temporizador(1000);
		this.timerFacas = new Temporizador(500);
		this.timerTiro = new Temporizador(1500);
		
		this.threadDor = new Thread(timerDor);
		this.threadFacas = new Thread(timerFacas);
		this.threadTiro = new Thread(timerTiro);
		this.threadCorvo = new Thread(timerCorvo);
		try {
			this.tiro_doze = AudioManager.getInstance().loadAudio("br/com/lol/sounds/FireGun.wav");
			this.dor_lampiao = AudioManager.getInstance().loadAudio("br/com/lol/sounds/dor_lampiao.wav");
			this.carcara_ativar = AudioManager.getInstance().loadAudio("br/com/lol/sounds/carcara_ativar.wav");
			this.musica_tema = AudioManager.getInstance().loadAudio("br/com/lol/sounds/musica_tema.wav");
			this.jogar_facas = AudioManager.getInstance().loadAudio("br/com/lol/sounds/jogar_facas.wav");
			this.corvos = AudioManager.getInstance().loadAudio("br/com/lol/sounds/corvos.wav");
			this.dorZumbi = AudioManager.getInstance().loadAudio("br/com/lol/sounds/zombiePain.wav");
			this.passos = AudioManager.getInstance().loadAudio("br/com/lol/sounds/step.wav");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playCorvo(){
		if(this.threadCorvo.getState() == Thread.State.NEW){
		this.corvos.play();
		this.threadCorvo.start();
		}else if(this.threadCorvo.getState() == Thread.State.TERMINATED){
			this.threadCorvo = new Thread(timerCorvo);
		}
	}
	
	public void playTiro(){
		if(this.threadTiro.getState() == Thread.State.NEW){
		this.tiro_doze.play();
		this.threadTiro.start();
		}else if(this.threadTiro.getState() == Thread.State.TERMINATED){
			this.threadTiro = new Thread(timerTiro);
		}
	}
	
	public void playTema(){
		this.musica_tema.loop();
	}
	public void playJogarFacas(){
		if(this.threadFacas.getState() == Thread.State.NEW){
		this.jogar_facas.play();
		this.threadFacas.start();
		}else if(this.threadFacas.getState() == Thread.State.TERMINATED){
			this.threadFacas = new Thread(timerFacas);
		}
	}
	public void playCarcara(){
		this.carcara_ativar.play();
	}
	
	public void playDor(){
		if(this.threadDor.getState() == Thread.State.NEW){
		this.dor_lampiao.play();
		}else if(this.threadDor.getState() == Thread.State.TERMINATED){
			this.threadDor = new Thread(timerDor);
		}
	}
	
	public void playPassos(){
		this.passos.play();
	}
	
	public void playDorZumbi(){
		this.dorZumbi.play();
	}
	
	public void exit(){
		stopCarcara();
		stopCorvo();
		stopDor();
		stopJogarFacas();
		stopTema();
		stopTiro();
	}
	
	public void stopCorvo(){
		this.corvos.stop();
	}
	
	public void stopTiro(){
		this.tiro_doze.stop();
	}
	
	public void stopTema(){
		this.musica_tema.stop();
	}
	public void stopJogarFacas(){
		this.jogar_facas.stop();
	}
	public void stopCarcara(){
		this.carcara_ativar.stop();
	}
	public void stopDor(){
		this.dor_lampiao.stop();
	}
	public void stopDorZumbi(){
		this.dorZumbi.stop();
	}
	public void stopPassos(){
		this.passos.stop();
	}
}
