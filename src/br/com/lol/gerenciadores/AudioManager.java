package br.com.lol.gerenciadores;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class AudioManager {

	static private AudioManager instance;
	private HashMap<String, AudioClip> clips;
	
	public AudioManager(){
		clips = new HashMap<String, AudioClip>();
	}
	
	static public AudioManager getInstance(){
		if(instance == null){
			instance = new AudioManager();
		}
		return instance;
	}
	
	public AudioClip loadAudio(String filename) throws IOException{
		URL url = getClass().getResource("/" + filename);
		if(url == null){
			throw new RuntimeException("O audio /" + filename + " não foi encontrado");
		}else{
			if(clips.containsKey(filename)){
				return clips.get(filename);
			}else{
				AudioClip audio = Applet.newAudioClip(getClass().getResource("/"+filename));
				clips.put(filename, audio);
				return audio;
			}
		}
	}
	
}
