package br.com.lol.gerenciadores;

public class GameSpeedTracker {

	static public double NANOS_IN_ONE_SECOND = 1e9;
	protected int ticksPerSecond;
	protected long previousNanoTime;
	protected int countedTicks;
	protected int totalTicks;
	
	public void start(){
		previousNanoTime = System.nanoTime();
		countedTicks = 0;
		ticksPerSecond = 0;
		totalTicks = 0;
	}
	
	public int countTick(){
		countedTicks++;
		totalTicks++;
		update();
		return totalTicks;
	}
	
	public void update(){
		if((System.nanoTime() - previousNanoTime) > NANOS_IN_ONE_SECOND){
			ticksPerSecond = countedTicks;
			countedTicks = 0;
			previousNanoTime = System.nanoTime();
		}
	}
	
	public int getTPS(){
		return ticksPerSecond;
	}
	
	public int getTotalTicks(){
		return totalTicks;
	}
	
	
}
