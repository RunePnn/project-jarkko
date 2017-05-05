package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import liikkuvat.Tykki;
import sensorit.BeaconTracker;

public class Automaatti {
	
	private Tykki tykki;
	private BeaconTracker infrapuna;
	private float suunta;
	private float kantama;
	
	private int etsintaSuunta;
	
	public Automaatti() {
		this.infrapuna = new BeaconTracker(SensorPort.S4);
		this.suunta = 0;
		this.kantama = 0;
		
		this.tykki = new Tykki(MotorPort.D, MotorPort.C);
		this.tykki.start();
		
		this.etsintaSuunta = 0;
		
	}
	
	public void aloita() {
		while(!Button.ENTER.isDown()) {
			float seekSample[] = this.infrapuna.getSample();
			
			this.suunta = seekSample[0];
			this.kantama = seekSample[1];
			
			LCD.drawString(this.suunta + "            ", 0, 1);
			LCD.drawString(this.kantama + "            ", 0, 0);
			
			if (this.suunta > 2) {
				this.tykki.pyoritaAlustaaSulavasti(0); // Vasemmalle
				Button.LEDPattern(3);
			} else if (this.suunta < -2) {
				this.tykki.pyoritaAlustaaSulavasti(1); // Oikealle
				Button.LEDPattern(3);
			} else if (this.suunta == 0 && this.kantama == Float.POSITIVE_INFINITY) {
				if (this.tykki.getPosition() > 60) {
					this.etsintaSuunta = 1;
				} else if (this.tykki.getPosition() < -60) {
					this.etsintaSuunta = 0;
				}
				this.tykki.pyoritaAlustaaSulavasti(this.etsintaSuunta);
				Button.LEDPattern(0);
			} else if (this.kantama < 50){
				this.tykki.lopetaAlustanPyoriminen();
				this.tykki.ammuTykilla();
				Button.LEDPattern(1);
			} else {
				this.tykki.lopetaAlustanPyoriminen();
			}
		}
		this.tykki.lopeta();
	}
}