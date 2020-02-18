package br.com.lol.dao;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.filechooser.FileSystemView;

import br.com.lol.gerenciadores.DataManager;

public class DaoGame{
	
	public DaoGame(){
		
	}
	
	public void salvar(int jogadorY, int jogadorX, int pontos, int tentativas){
		FileSystemView filesys = FileSystemView.getFileSystemView();
		filesys.getRoots();
		try {
			DataManager dm = new DataManager("/br/com/lol/files/save.txt");
			dm.write("jogadorY", jogadorY);
			dm.write("jogadorX", jogadorX);
			dm.write("pontos", pontos);
			dm.write("tentativas", tentativas);
			dm.save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load(int jogadorY, int jogadorX, int pontos, int tentativas){
	
	}
	
}
