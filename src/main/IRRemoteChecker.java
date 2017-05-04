package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3IRSensor;

public class IRRemoteChecker extends Thread {
	private EV3IRSensor sensor;
	private int remoteCmd;

	public IRRemoteChecker(EV3IRSensor sensor) {
		this.sensor = sensor;
		this.remoteCmd = 0;
	}
	
	@Override
	public void run() {
		while(Button.ESCAPE.isDown()) {
			this.remoteCmd = this.sensor.getRemoteCommand(0);
		}
	}
	
	public int getRemoteCmd() {
		return this.remoteCmd;
	}
}
