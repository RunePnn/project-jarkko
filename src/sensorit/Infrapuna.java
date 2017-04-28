package sensorit;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;

public class Infrapuna extends Thread {
	
	private EV3IRSensor infrapuna;
	
	private int kanava0;
	private int kanava1;
	
	private boolean paalla;
	
	public Infrapuna(Port portti) {
		this.infrapuna = new EV3IRSensor(portti);
		this.paalla = true;
	}
	
	public void run() {
		while (this.paalla) {
			this.kanava0 = this.infrapuna.getRemoteCommand(0);
			this.kanava1 = this.infrapuna.getRemoteCommand(1);
		}
	}
	
	public int getKanava0() {
		return this.kanava0;
	}
	
	public int getKanava1() {
		return this.kanava1;
	}
	
	public void lopeta() {
		this.paalla = false;
	}
}
