package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
//import lejos.hardware.port.SensorPort;
//import lejos.hardware.sensor.EV3IRSensor;

public class Setup {
	
	public Setup () {
		
	}
	
	public void start() {
		LCD.drawString("Jarkko", 0, 0);
		LCD.drawString("Kuulantyontaja", 0, 1);
	//	LCD.drawString("Esc lopettaa", 0, 3);
		LCD.drawString("Vasen automaatti", 0, 4);
		LCD.drawString("Oikea manuaali", 0, 5);
		
		int keycode = Button.waitForAnyPress();
		
		LCD.clear();
		
		if (keycode == Button.ID_LEFT) {
			Automaatti irAnturi = new Automaatti();
			irAnturi.aloita();
		} else if (keycode == Button.ID_RIGHT) {
			Manuaali tankki = new Manuaali();
			tankki.aloita();
		}
	}
}
