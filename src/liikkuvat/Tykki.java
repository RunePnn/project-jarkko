package liikkuvat;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * 
 * @author teemue
 *
 */

public class Tykki extends Thread {
	
	private EV3MediumRegulatedMotor alusta;
	private EV3LargeRegulatedMotor tykki;
	
	private int rotaatio;
	private int alustanNopeus;
	
	private boolean paalla;
	
	/**
	 * 
	 * @param porttiAlusta MotorPort pyorivaa alustaa varten
	 * @param porttiTykki MotorPort ampuvaa tykkia varten
	 */
	public Tykki(Port porttiAlusta, Port porttiTykki) {
		this.alusta = new EV3MediumRegulatedMotor(porttiAlusta);
		this.alustanNopeus = 50;
		this.alusta.setSpeed(alustanNopeus);
		
		this.tykki = new EV3LargeRegulatedMotor(porttiTykki);
		this.tykki.setSpeed(800);
		
		this.rotaatio = 0;
		
		this.paalla = true;
	}
	
	public void run() {
		while (this.paalla) {
			
		}
		
		this.alusta.close();
		this.tykki.close();
	}
	
	public void pyoritaAlustaa(int asteet) {
		if (Math.abs(this.rotaatio + asteet) <= 180) {
			this.rotaatio += asteet;
			this.alusta.rotate(asteet);
		}
	}
	
	/**
	 * Pyorittaa tykin alustaa annettuun suuntaan, kunnes on kaannytty 90 astetta tai toinen
	 * komento pysayttaa kaannoksen.
	 * @param lukema
	 */
	
	public void pyoritaAlustaaSulavasti(int lukema) {
		this.alusta.setSpeed(this.alustanNopeus);
		if (lukema == 1) {
			this.alusta.rotateTo(-90, true);
		} else {
			this.alusta.rotateTo(90, true);
		}
	}
	
	public void lopetaAlustanPyoriminen() {
		this.alusta.setSpeed(0);
		this.alusta.stop();
	}
	
	public void ammuTykilla() {
		this.tykki.rotate(360);
	}
	
	public void asetaAlustanNopeus(int nopeus) {
		this.alustanNopeus = nopeus;
	}
	
	public float getPosition() {
		return this.alusta.getPosition();
	}
	
	public void lopeta() {
		this.alusta.setSpeed(this.alustanNopeus);
		this.alusta.rotateTo(0);
		this.paalla = false;
	}

}