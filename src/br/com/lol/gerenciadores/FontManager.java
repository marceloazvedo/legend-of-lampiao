package br.com.lol.gerenciadores;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;

public class FontManager {

	public static final int PLAIN = 0;
	public static final int BOLD = 1;
	public static final int ITALIC = 2;
	public static final int BOLD_ITALIC = BOLD|ITALIC;
	static private FontManager instance;
	private HashMap<String, Font> fonts;
	
	private FontManager(){
		fonts = new HashMap<String, Font>();
	}
	
	static public FontManager getInstance(){
		if(instance == null){
			instance = new FontManager();
		}
		return instance;
	}
	
	public Font loadFont(String filename, int Style, int size){
		URL url = getClass().getResource("/" +filename);
		if(url == null){
			throw new RuntimeException("A fonte /" + filename + " não foi encontrada");
		}else{
			try{
				Font font = fonts.get(filename);
				if(font == null){
					File file = new File(url.toURI());
					font = Font.createFont(Font.TRUETYPE_FONT, file);
					fonts.put(filename, font);
				}
					if((Style & BOLD) == BOLD){
						font = font.deriveFont(Font.BOLD);
					}
					if((Style & ITALIC) == ITALIC){
						font = font.deriveFont(Font.ITALIC);
					}
					return font;
				}catch(FontFormatException f){
				throw new RuntimeException(f);
			}catch(IOException io){
				throw new RuntimeException(io);
			}catch(URISyntaxException u){
				throw new RuntimeException(u);
			}
		}
	}
	
}