package br.com.lol.observador;

public interface Sujeito {

	public void notifyObserver();
	
	public void addObserver(Observer obs);
	
	public void removeObserver(Observer obs);
	
	public void setChanges(int iterator);
	
	public void makeChanges();
	
}
