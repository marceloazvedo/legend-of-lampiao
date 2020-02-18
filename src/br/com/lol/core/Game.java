package br.com.lol.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import br.com.lol.gerenciadores.GameSpeedTracker;
import br.com.lol.gerenciadores.InputManager;

public abstract class Game implements WindowListener{

	
	private JFrame mainWindow;
	private boolean activity;
	private boolean pausaNoJogo;
	private BufferStrategy bufferStrategy;
	private GameSpeedTracker speedTracker;
	private int expectedTPS;
	private double expectedNanosPerTick;
	private int maxSkippedFrames;
	protected int pontos;
	protected int estado;
	protected int score;
	protected int tentativas;
	
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getTentativas() {
		return tentativas;
	}

	public void setTentativas(int tentativas) {
		this.tentativas = tentativas;
	}

	public Game(){
		this.mainWindow = new JFrame("LOL- Legends of Lampião");
		this.mainWindow.addWindowListener(this);
		this.mainWindow.setSize(800,600);
		this.mainWindow.addKeyListener(InputManager.getInstance());
		this.mainWindow.getContentPane().setLayout(null);
		this.activity = false;
		this.pausaNoJogo = false;
	}
		
	public void terminate(){
		this.activity = false;
	}
	
	public void run(){
		this.activity = true;
		load();
		expectedTPS = 50;
		expectedNanosPerTick = GameSpeedTracker.NANOS_IN_ONE_SECOND / expectedTPS;
		maxSkippedFrames = 10;
		int skippedFrames = 0;
		long nanoTimeAtNextTick = System.nanoTime();
		
		while(this.activity){
			
			speedTracker.update();
			if(System.nanoTime() > nanoTimeAtNextTick && skippedFrames < maxSkippedFrames){
				nanoTimeAtNextTick += expectedNanosPerTick;
				InputManager.getInstance().update();
				if(!pausaNoJogo){
				update();
				}
				skippedFrames++;
			}else{
				render();
				skippedFrames = 0;
			}
		}
		
		unLoad();
	}
	
	public void load(){
		this.mainWindow.setUndecorated(true);
		this.mainWindow.setIgnoreRepaint(true);
		this.mainWindow.setLocation(100, 100);
		this.mainWindow.setVisible(true);
		this.mainWindow.createBufferStrategy(2);
		this.bufferStrategy = this.mainWindow.getBufferStrategy();
		speedTracker = new GameSpeedTracker();
		onLoad();
		speedTracker.start();
	}
	
	public void unLoad(){
		onUnLoad();
		this.bufferStrategy.dispose();
		this.mainWindow.dispose();
	}
	
	public void update(){
		speedTracker.countTick();
		onUpdate(speedTracker.getTotalTicks());
		Thread.yield();
	}
	
	public void render(){
		Graphics2D g = (Graphics2D) this.bufferStrategy.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, this.mainWindow.getWidth(), this.mainWindow.getHeight());
		onRender(g, speedTracker.getTotalTicks());
		
		g.setColor(Color.black);
		g.fillRect(0, 0, 200, 16);
		g.setColor(Color.white);
		g.setFont(new Font("", Font.BOLD, 12));
		g.drawString(speedTracker.getTPS() + " tps",1 , 12);
		
		g.dispose();
		this.bufferStrategy.show();
	}
	
	public void setPausaNoJogo(boolean valor){
		this.pausaNoJogo = valor;
	}
	
	public boolean isPausaNoJogo(){
		return this.pausaNoJogo;
	}
	
	public int getWidth(){
		return this.mainWindow.getWidth();
	}
	
	public int getHeight(){
		return this.mainWindow.getHeight();
	}
	
	public JFrame getMainWindow(){
		return this.mainWindow;
	}
	
	abstract public void onLoad();
	abstract public void onUpdate(int currentTick);
	abstract public void onUnLoad();
	abstract public void onRender(Graphics2D g, int currentTick);
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		terminate();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}