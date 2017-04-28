package sensorit;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;

public class Infrapuna extends Thread {
	
	private EV3IRSensor infrapuna;
	
	private int lukema;
	private boolean paalla;
	
	public Infrapuna(Port portti) {
		this.infrapuna = new EV3IRSensor(portti);
		this.paalla = true;
	}
	
	public void run() {
		while (this.paalla) {
			this.lukema = this.infrapuna.getRemoteCommand(0);
		}
	}
	
	public int getLukema() {
		return this.lukema;
	}
	
	public void lopeta() {
		this.paalla = false;
	}
}
