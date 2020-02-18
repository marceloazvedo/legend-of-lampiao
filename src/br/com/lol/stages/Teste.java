package br.com.lol.stages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Teste {
	
	private Properties propriedades;
	private OutputStream os;
	File file;
	
	public Teste(){
		this.propriedades = new Properties();
		try {
			file = new File(getClass().getResource("/br/com/lol/files/save.txt").toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.os = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void salvarteste(){
		this.propriedades.setProperty("MyName", "girlian");
		this.propriedades.setProperty("MyAge", "chiiiii");
		try {
			this.propriedades.store(os, "kjhgkjhgkjhg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Teste().salvarteste();
		System.out.println("ja salvou oh");
	}
	
}


