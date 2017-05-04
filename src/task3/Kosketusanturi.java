package task3;

import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.port.Port;

public class Kosketusanturi {
	private EV3TouchSensor anturi;
	private float[] sample;
	private boolean painettu;
	
	public Kosketusanturi(Port port) {
		this.anturi = new EV3TouchSensor(port);
		this.sample  = new float[this.anturi.sampleSize()];
		this.painettu = false;
		
	}
	
	public boolean isPainettu() {
		return this.painettu;
	}
	
	public boolean mittaa() {
		this.anturi.fetchSample(this.sample, 0);
		
		if (this.sample[0] == 1) {
			this.painettu = true;
		}
		
		if (this.painettu && this.sample[0] == 0) {
			this.painettu = false;
			return true;
		}
		
		return false;
	}
	
	public void close() {
		this.anturi.close();
	}
}
