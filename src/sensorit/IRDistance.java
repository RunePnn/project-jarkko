package sensorit;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;

public class IRDistance {
	private EV3IRSensor infrapuna;
	private float[] distSample;
	private boolean paalla;
	
	public IRDistance(Port portti) {
		this.infrapuna = new EV3IRSensor(portti);
		this.distSample = new float[this.infrapuna.sampleSize()];
		
		this.paalla = true;
	}
	
	public void run() {
		while (this.paalla) {
			this.infrapuna.fetchSample(distSample, 0);
		}
	}
	
	public float getDistance() {
		return this.distSample[0];
	}
	
	public void lopeta() {
		this.paalla = false;
	}
}
