package sensorit;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;
import liikkuvat.Tykki;

public class BeaconTracker extends Thread {
	private EV3IRSensor sensor;
	private float seekSample[];
	
	public BeaconTracker(Port portti) {
		this.sensor = new EV3IRSensor(portti);
		
		this.sensor.setCurrentMode(1);
		SensorMode seek = this.sensor.getSeekMode();
		this.seekSample = new float[seek.sampleSize()];
	}
	
	public float[] getSample() {
		this.sensor.fetchSample(seekSample, 0);
		return seekSample;
	}
}
