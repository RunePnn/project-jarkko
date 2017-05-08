package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import liikkuvat.Pyorat;
import liikkuvat.Tykki;
import sensorit.BeaconTracker;

/**
 * Luokka, joka hoitaa robotin automaattiohjauksen
 * @author viljamha
 *
 */

public class Automaatti {
	
	private Tykki tykki;
	private Pyorat pyorat;
	private BeaconTracker infrapuna;
	
	private float suunta;
	private float kantama;
	
	private int alustanNopeus;
	private int etsintaSuunta;
	
	/**
	 * Automaattiohjauksen konstruktori
	 *
	 */
	
	public Automaatti() {
		this.infrapuna = new BeaconTracker(SensorPort.S4);
		this.infrapuna.start();
		
		this.tykki = new Tykki(MotorPort.D, MotorPort.C);
		this.tykki.start();
		
		this.pyorat = new Pyorat(MotorPort.A, MotorPort.B);
		
		this.suunta = 0;
		this.kantama = 0;
		this.alustanNopeus = 50;
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
			
			autoTykki(this.suunta, this.kantama);
			autoPyorat(this.suunta, this.kantama);
		}
		
		// pysayta oliot
		this.pyorat.pysayta();
		this.tykki.lopeta();
		this.infrapuna.terminate();
	}
	
	/**
	 * Funktio ohjaa tykkia kohteen suuntaan 
	 * annettujen parametrien kautta
	 * @param suunta kohteen suunta tykkiin nahden
	 * @param kantama kohteen kantama
	 */
	
	private void autoTykki(float suunta, float kantama) {
		// Automaattisen tykin kaantymisen koodi
		if (suunta > 2) { // Jos kohde on vasemmmalla
			this.tykki.asetaAlustanNopeus(this.alustanNopeus);
			this.tykki.pyoritaAlustaaSulavasti(0); // kaanna Vasemmalle
			Button.LEDPattern(3);
		} else if (suunta < -2) { // jos kohde on oikealla
			this.tykki.asetaAlustanNopeus(this.alustanNopeus);
			this.tykki.pyoritaAlustaaSulavasti(1); // kaanna Oikealle
			Button.LEDPattern(3);
		} else if (suunta > 0) { // jos kohda on hieman vasemmalla
			this.tykki.asetaAlustanNopeus(2);
			this.tykki.pyoritaAlustaaSulavasti(0); // kaanna hieman vasemmalle
			Button.LEDPattern(3);
		} else if (suunta < 0) { // jos kohde on hieman oikealla
			this.tykki.asetaAlustanNopeus(2);
			this.tykki.pyoritaAlustaaSulavasti(1); // kaanna hieman Oikealle
			Button.LEDPattern(3);
		} else if (suunta == 0 && kantama == Float.POSITIVE_INFINITY) { // jos kohdetta ei loydy...
			Button.LEDPattern(0);
			this.tykki.asetaAlustanNopeus(50);
			if (this.tykki.haeSijainti() > 60) { // ...niin kaanna tykkia etsien uutta kohdetta
				this.etsintaSuunta = 1;
			} else if (this.tykki.haeSijainti() < -60) {
				this.etsintaSuunta = 0;
			}
			this.tykki.pyoritaAlustaaSulavasti(this.etsintaSuunta);
			this.pyorat.eteen(300);
		} else {
			Button.LEDPattern(1);
			this.tykki.lopetaAlustanPyoriminen();
			if (kantama < 40) { // jos kohde on riittavan lahella, niin ammu
				this.tykki.ammuTykilla();
			}
		}
	}
	
	/**
	 * Funktio ohjaa robotin pyoria
	 * pyoria kaannetaan kohteen suuntaan
	 * robotti pysahtyy kohteen ollessa lahella
	 * @param suunta kohteen suunta tykkiin nahden
	 * @param kantama kohteen kantama
	 */
	
	private void autoPyorat(float suunta, float kantama) {
		// Automattisen pyorien kaantamisen ja ajamisen koodi
		if (kantama < 40) { // Jos riittavan lahella, niin pysahdy
			this.pyorat.pysayta();
		} else if (kantama > 60) {  // jos riittavan kaukana, niin ala liikkumaan
			this.pyorat.eteen(600);
		} else if (!(suunta == 0 && kantama == Float.POSITIVE_INFINITY)) { // jos kohde on loytynyt
			int temp = (int) (this.tykki.haeSijainti() + (suunta * 2));
			if (temp > 50) {
				temp = 50;
			}
			this.pyorat.kaanny(0, temp); // Kaanny kohteen suuntaan
		} else { // Jos ei kohdetta loydy, niin aja suoraan
			this.pyorat.eteen(600);
			this.pyorat.suorista();
		}
	}
}
