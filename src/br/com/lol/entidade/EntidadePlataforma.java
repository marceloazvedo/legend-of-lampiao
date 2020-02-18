package br.com.lol.entidade;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EntidadePlataforma extends Entidade {

    BufferedImage sprite;
    
	private int largura;
    private int altura;
    
    public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

    public EntidadePlataforma(int x, int y, int width, int height) {
    	pos = new Rectangle();
        pos.setRect(x, y, width, height);
        this.x = x;
        this.y = y;
    }

    public void init(){
        // O sprite da plataforma consiste em uma imagem com um retângulo cinza.
        sprite = new BufferedImage((int) pos.width, (int) pos.height,
                BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = sprite.getGraphics();
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, sprite.getWidth(), sprite.getHeight());
        this.largura = sprite.getWidth();
        this.altura = sprite.getHeight();
    }

    public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

    public void update(int currentTick) {
        // Plataformas não possuem lógica: ficam paradas no lugar.
    }

    public void render(Graphics2D g) {
        // Desenha o sprite da plataforma.
        g.drawImage(sprite, (int) pos.x, (int) pos.y, null);
    }
    
    public Rectangle getBounds(){
    	return new Rectangle(x,y,largura, altura);
    }
}
