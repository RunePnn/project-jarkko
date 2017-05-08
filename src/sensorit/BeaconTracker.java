package sensorit;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;

/**
 * Luokka, joka vastaanottaa beaconin sijainnin ja kantaman.
 * @author viljamha
 */

public class BeaconTracker extends Thread {
	private EV3IRSensor sensor;
	private float seekSample[];
	
	private boolean running;
	
	/**
	 * Luokka, joka vastaanottaa beaconin sijainnin ja kantaman.
	 * @param portti Anturin portti, jossa Infrapuna-anturi on
	 */
	
	public BeaconTracker(Port portti) {
		this.sensor = new EV3IRSensor(portti);
		
		this.sensor.setCurrentMode(1);
		SensorMode seek = this.sensor.getSeekMode();
		this.seekSample = new float[seek.sampleSize()];
		
		this.running = true;
	}
	
	@Override
	public void run() {
		while(isRunning()) {
			this.sensor.setCurrentMode(1);
			this.sensor.fetchSample(seekSample, 0);
		}
		this.sensor.close();
 	}
	
	/**
	 * Ottaa näytteen anturin antamasta tiedosta.
	 */
	
	public float[] getSample() {
		this.sensor.fetchSample(seekSample, 0);
		return seekSample;
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
	public void terminate() {
		this.running = false;
	}
}
