package br.com.lol.gerenciadores;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

import br.com.lol.gerenciadores.SpriteAnimation;

public class ImageManager {

	static private ImageManager instance;
	private HashMap<String, BufferedImage> images;
	
	private ImageManager(){
		images = new HashMap<String, BufferedImage>();
	}
	
	static public ImageManager getInstance(){
		if(instance == null){
			instance = new ImageManager();
		}
		return instance;
	}
	
	public BufferedImage loadImage(String filename) throws IOException{
		URL url = getClass().getResource("/"+filename);
		if(url == null){
			throw new RuntimeException("A imagem /" + filename + "não foi encontrada");
		}else{
			String path = url.getPath();
			if(images.containsKey(path)){
				return images.get(path);
			}else{
				BufferedImage img = ImageIO.read(url);
				images.put(path, img);
				return img;
			}
		}
	}
	
	public SpriteAnimation loadSpriteAnimation(String filename, int quantFrames) throws IOException{
		
		BufferedImage sheet = loadImage(filename);
		if(sheet.getWidth() % quantFrames != 0){
			throw new RuntimeException("A imagem "+filename+" não possui "
					+quantFrames+" sprites do mesmo tamanho");
		}else{
			SpriteAnimation anim = new SpriteAnimation();
			int w = sheet.getWidth() / quantFrames;
			int h = sheet.getHeight();
			for(int i = 0; i < quantFrames; i++){
				anim.addImage(sheet.getSubimage(i*w, 0, w, h));
			}
			return anim;
		}
		
	}
	
	public SpriteAnimation loadSpriteAnimationVertical(String filename, int quantFrames) throws IOException{
		BufferedImage sheet = loadImage(filename);
		if(sheet.getHeight() % quantFrames != 0){
			throw new RuntimeException("A imagem "+filename+" não possui "
					+quantFrames+" sprites do mesmo tamanho");
		}else{
			SpriteAnimation anim = new SpriteAnimation();
			int w = sheet.getWidth();
			int h = sheet.getHeight() / quantFrames;
			for(int i = 0; i < quantFrames; i++){
				anim.addImage(sheet.getSubimage(0,i *h , w, h));
			}
			return anim;
		}
	}
	
	public BufferedImage loadImage(String filename, int x, int y, int w, int h) throws IOException{
		BufferedImage sheet = loadImage(filename);
		BufferedImage img = sheet.getSubimage(x, y, w, h);
		return img;
	}
	
	public BufferedImage flipImage(BufferedImage img, boolean horizontal,
			boolean vertical) {
		BufferedImage imagem = null;
		if (horizontal) {
			 int w = img.getWidth();  
		     int h = img.getHeight();  
		     BufferedImage dimg = new BufferedImage(w, h, img.getType());  
		     Graphics2D g = dimg.createGraphics();  
		     g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);  
		     g.dispose();  
			imagem = img;
			System.out.println("Passou aqui - 1");
		}
		if (vertical) {
			int w = img.getWidth();
			int h = img.getHeight();
			BufferedImage dimg = dimg = new BufferedImage(w, h, img
					.getColorModel().getTransparency());
			Graphics2D g = dimg.createGraphics();
			g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
			g.dispose();
			imagem = img;
			System.out.println("Passou aqui - 2");
		}
		return img;
	}
}
