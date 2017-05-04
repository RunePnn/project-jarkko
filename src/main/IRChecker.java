package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;

public class IRChecker extends Thread {
	private EV3IRSensor sensor;
	private float distance;
	
	public IRChecker(EV3IRSensor sensor) {
		this.sensor = sensor;
		this.distance = 0;
	}
	
	@Override
	public void run() {
		while(!Button.ESCAPE.isDown()) {
			
			SampleProvider dist = this.sensor.getDistanceMode();
			float distSample[] = new float[dist.sampleSize()];
			
			this.sensor.fetchSample(distSample, 0);
			this.distance = distSample[0];
			
			int rmtCmd = this.sensor.getRemoteCommand(0);
			
			LCD.drawString("Distance: " + this.distance, 0, 2);
			LCD.drawString("Remote: " + rmtCmd, 0, 1);
		}
	}
	
	public float getDistance() {
		return this.distance;
	}
}
