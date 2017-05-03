package main;

import sensorit.BeaconTracker;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;


public class Robo {
	
	public static void main(String[] args) {
		
		LCD.drawString("Jarkko", 0, 0);
		LCD.drawString("Kuulantyontaja", 0, 1);
//		LCD.drawString("Esc lopettaa", 0, 3);
		LCD.drawString("Vasen automaatti", 0, 4);
		LCD.drawString("Oikea manuaali", 0, 5);
		
		int keycode = Button.waitForAnyPress();
		
		LCD.clear();
		
		if (keycode == Button.ID_LEFT) {
			BeaconTracker irAnturi = new BeaconTracker(new EV3IRSensor(SensorPort.S1));
			irAnturi.start();
		} else if (keycode == Button.ID_RIGHT) {
			Tankki tankki = new Tankki();
			tankki.aloita();
		}
	}
}
