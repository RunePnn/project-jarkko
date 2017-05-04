package liikkuvat;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;

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
		this.alusta.setSpeed(50);
		
		this.tykki = new EV3LargeRegulatedMotor(porttiTykki);
		this.tykki.setSpeed(800);
		
		this.rotaatio = 0;
		this.alustanNopeus = 75;
		
		this.paalla = true;
	}
	
	public void run() {
		while (this.paalla) {
			
		}
	}
	
	public void pyoritaAlustaa(int asteet) {
		if (Math.abs(this.rotaatio + asteet) <= 180) {
			this.rotaatio += asteet;
			this.alusta.rotate(asteet);
		}
	}
	
	public void pyoritaAlustaaSulavasti(int suunta) {
		this.alusta.setSpeed(this.alustanNopeus);
		if (suunta == 0) {
			this.alusta.rotateTo(-90, true);
		} else if (suunta == 1) {
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
	
	public void lopeta() {
		this.alusta.setSpeed(150);
		this.alusta.rotateTo(0);
		this.paalla = false;
	}

}

