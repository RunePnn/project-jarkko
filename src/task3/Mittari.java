package task3;

public class Mittari {
	
	private long startTime;
	private double duration;
	private boolean started;
	
	public Mittari () {
		this.startTime = 0;
		this.started = false;
	}
	
	public boolean isStarted() {
		return this.started;
	}
	
	public void startTimer() {
		this.startTime = System.currentTimeMillis();
		this.started = true;
	}
	
	public double getDuration() {
		this.duration = (double) (System.currentTimeMillis() - this.startTime)/1000.0;
		return this.duration;
	}
	
	public float getDurationMs() {
		return System.currentTimeMillis() - this.startTime;
	}
	
	public void stopTimer() {
		this.started = false;
	}
}
