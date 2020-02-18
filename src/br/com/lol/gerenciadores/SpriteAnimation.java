package br.com.lol.gerenciadores;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteAnimation {

	private int currentImageIndex;
	private int indexInc;
	private boolean loop;
	private List<BufferedImage> images;
	
	public SpriteAnimation(){
		this.currentImageIndex = 0;
		this.indexInc = 1;
		this.loop = false;
		images = new ArrayList<BufferedImage>();
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}
	
	public void addImage(BufferedImage img){
		this.images.add(img);
	}
	
	public void update(int currentTick){
		if(currentTick % 3 == 0){
			currentImageIndex += indexInc;
			if(currentImageIndex == images.size() || currentImageIndex == -1){
				if(loop){
					indexInc *= -1;
					currentImageIndex += indexInc;
				}else{
					currentImageIndex = 0;
				}
			}
		}
	}
	
	public BufferedImage getImage(){
		return this.images.get(currentImageIndex);
	}
	
	public List<BufferedImage> getImagens(){
		return this.images;
	}
}
