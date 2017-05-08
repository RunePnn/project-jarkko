package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import liikkuvat.Tykki;
import liikkuvat.Pyorat;
import sensorit.BeaconTracker;

public class Automaatti {
	
	private Tykki tykki;
	private BeaconTracker infrapuna;
	private Pyorat pyorat;
	private float suunta;
	private float kantama;
	private int etsintaSuunta;
	
	/**
	 * Luokka, joka hoitaa automaattiohjauksen
	 * @author viljamha
	 *
	 */
	
	public Automaatti() {
		this.infrapuna = new BeaconTracker(SensorPort.S1);
		this.suunta = 0;
		this.kantama = 0;
		
		this.tykki = new Tykki(MotorPort.D, MotorPort.C);
		this.tykki.start();
		
		this.pyorat = new Pyorat(MotorPort.A, MotorPort.B);
		
		this.etsintaSuunta = 0;
		
	}
	
	/**
	 * Funktio, joka aloittaa automaattiohjauksen
	 * kunnes ohjelma suljetaan
	 */
	
	public void aloita() {
		while(!Button.ENTER.isDown()) {
			float seekSample[] = this.infrapuna.getSample();
			
			this.suunta = seekSample[0];
			this.kantama = seekSample[1];
			
			LCD.drawString(this.suunta + "            ", 0, 1);
			LCD.drawString(this.kantama + "            ", 0, 0);
			
//			this.alustanNopeus = (int) (5 * Math.abs(this.suunta));
//			if (this.alustanNopeus > 50) {
//				this.alustanNopeus = 50;
//			}
			
			if (this.suunta > 2) {
				this.tykki.asetaAlustanNopeus(this.alustanNopeus);
				this.tykki.pyoritaAlustaaSulavasti(0); // Vasemmalle
				Button.LEDPattern(3);
			} else if (this.suunta < -2) {
				this.tykki.asetaAlustanNopeus(this.alustanNopeus);
				this.tykki.pyoritaAlustaaSulavasti(1); // Oikealle
				Button.LEDPattern(3);
			} else if (this.suunta > 0) {
				this.tykki.asetaAlustanNopeus(2);
				this.tykki.pyoritaAlustaaSulavasti(0); // Oikealle
				Button.LEDPattern(3);
			} else if (this.suunta < 0) {
				this.tykki.asetaAlustanNopeus(2);
				this.tykki.pyoritaAlustaaSulavasti(1); // Oikealle
				Button.LEDPattern(3);
			} else if (this.suunta == 0 && this.kantama == Float.POSITIVE_INFINITY) {
				Button.LEDPattern(0);
				this.tykki.asetaAlustanNopeus(50);
				if (this.tykki.getPosition() > 60) {
					this.etsintaSuunta = 1;
				} else if (this.tykki.getPosition() < -60) {
					this.etsintaSuunta = 0;
				}
				this.tykki.pyoritaAlustaaSulavasti(this.etsintaSuunta);
				this.pyorat.eteen(300);
			} else {
				Button.LEDPattern(1);
				this.tykki.lopetaAlustanPyoriminen();
				if (this.kantama < 30) {
					this.tykki.ammuTykilla();
				}
			}
			
			if (this.kantama < 30) {
				this.pyorat.pysayta();
			} else if (!(this.suunta == 0 && this.kantama == Float.POSITIVE_INFINITY)) {
				this.pyorat.eteen(600);
				int temp = (int) (this.tykki.getPosition() + (this.suunta * 2));
				if (temp > 50) {
					temp = 50;
				}
				this.pyorat.kaanny(1, temp);
			} else {
				this.pyorat.eteen(600);
				this.pyorat.suorista();
			}
		}
		this.pyorat.pysayta();
		this.tykki.lopeta();
	}
}
