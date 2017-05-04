package task5;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.filter.MeanFilter;
import lejos.utility.Delay;

public class IRSTarkistin extends Thread{
	private EV3IRSensor sensori;
	private SensorMode etaisyysMoodi;
	private float[] etaisyysSample;
	private int nykKauko;
	private int edKauko;
	private boolean running;
	
	public IRSTarkistin (Port port) {
		this.sensori = new EV3IRSensor(port);
		this.etaisyysMoodi = this.sensori.getDistanceMode();
		etaisyysSample = new float[this.etaisyysMoodi.sampleSize()];
		this.running = true;
		this.edKauko = -1;
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
	public void terminate() {
		this.running = false;
	}
	
	@Override
	public void run() {
		while (isRunning()) {
			lueEtaisyys();
			lueKaukosaadin();
		}
		this.sensori.close();
	}
	
	//Luetaan kaukos‰‰timen signaali
	public void lueKaukosaadin() {
		this.nykKauko = this.sensori.getRemoteCommand(0);
	}
	
	// Luetaan et‰isyys infrapunasensorilla
	public void lueEtaisyys () {
		this.etaisyysMoodi.fetchSample(this.etaisyysSample, 0);
	}
	
	//Tarkistaa, onko kaukos‰‰timen signaali muuttunut.
	//Palauttaa 0, jos signaali ei ole muuttunut.
	public int getKaukoOnce() {
		if (getNykKauko() != getEdKauko()) {
			this.edKauko = this.nykKauko;
			return this.nykKauko;
		} else {
			return 0;
		}
	}
	
	public int getNykKauko() {
		return this.nykKauko;
	}
	
	public int getEdKauko() {
		return this.edKauko;
	}
	
	public float getEtaisyys() {
		return this.etaisyysSample[0];
	}
}