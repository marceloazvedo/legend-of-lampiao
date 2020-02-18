package br.com.lol.entidade;

public class Pulo extends Entidade{
	
	private boolean noChao;
	private int alturaChao;
	private static final int ALTURA_MAX = 100;
	private boolean maxAtingido;
	
	public Pulo(int y){
		this.speed = 10;
		this.noChao = true;
		this.alturaChao = y;
		this.maxAtingido = false;
	}
	
	public void start(int posAtual){
		if(noChao){
			noChao = false;
			posAtual -= speed;
		}else{
			if(posAtual < alturaChao + ALTURA_MAX){
				posAtual -= speed;
			}else
				maxAtingido = true;
		}
	}
	
	public boolean isNoChao() {
		return noChao;
	}

	public void setNoChao(boolean noChao) {
		this.noChao = noChao;
	}

	public int getAlturaChao() {
		return alturaChao;
	}

	public void setAlturaChao(int alturaChao) {
		this.alturaChao = alturaChao;
	}

	public boolean isMaxAtingido() {
		return maxAtingido;
	}

	public void setMaxAtingido(boolean maxAtingido) {
		this.maxAtingido = maxAtingido;
	}

	public static int getAlturaMax() {
		return ALTURA_MAX;
	}

	public void decrementar(int posAtual){
		if(posAtual > alturaChao){
			posAtual += speed;
		}
	}
}
