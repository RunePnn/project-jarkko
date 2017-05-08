package liikkuvat;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * @author Teemu Eerola
 * @since 2017-05-08
 */

public class Tykki extends Thread {
	
	public EV3MediumRegulatedMotor alusta;
	private EV3LargeRegulatedMotor tykki;
	
	private int rotaatio;
	
	private boolean paalla;
	
	/**
	 * Tykin konstruktori.
	 * @param porttiAlusta MotorPort pyorivaa alustaa varten
	 * @param porttiTykki MotorPort ampuvaa tykkia varten
	 */
	public Tykki(Port porttiAlusta, Port porttiTykki) {
		this.alusta = new EV3MediumRegulatedMotor(porttiAlusta);
		this.alusta.setSpeed(50);
		
		this.tykki = new EV3LargeRegulatedMotor(porttiTykki);
		this.tykki.setSpeed(800);
		
		this.rotaatio = 0;
		
		this.paalla = true;
	}
	
	/**
	 * Tykin run-metodi.
	 * @return Ei mitään
	 */
	public void run() {
		while (this.paalla) {
			
		}
	}
	
	 /**
	   * Metodi tykin alustan pyörittämiseen. Tykki voi kääntyä
	   * kuitenkin maksimissaan vain 180 astetta kumpaankin suuntaan
	   * johtojen takia.
	   * @param asteet Pyoritettava astemäärä
	   * @return Ei mitään
	   */
	public void pyoritaAlustaa(int asteet) {
		if (Math.abs(this.rotaatio + asteet) <= 180) {
			this.rotaatio += asteet;
			this.alusta.rotate(asteet);
		}
	}
	
	 /**
	   * Metodi tykin alustan pyörittämiseen sulavasti. Alusta pyörii
	   * 90 astetta tai kunnes se pysäytetään.
	   * @param suunta 0 oikea; 1 vasen
	   * @return Ei mitään
	   */
	public void pyoritaAlustaaSulavasti(int suunta) {
		this.alusta.setSpeed(75);
		if (suunta == 0) {
			this.alusta.rotateTo(-90, true);
		} else if (suunta == 1) {
			this.alusta.rotateTo(90, true);
		}
	}
	
	 /**
	   * Metodi alustan pyörimisen lopettamiseksi.
	   */
	public void lopetaAlustanPyoriminen() {
		this.alusta.setSpeed(0);
		this.alusta.stop();
	}
	
	 /**
	   * Metodi tykin ampumiselle.
	   * @return Ei mitään
	   */
	public void ammuTykilla() {
		this.tykki.rotate(360);
	}
	
	/**
	   * Metodi, joka kääntää tykin alkuperäiseen asentoon
	   * ja lopettaa säikeen.
	   * @return Ei mitään
	   */
	public void lopeta() {
		this.alusta.setSpeed(150);
		this.alusta.rotateTo(0);
		this.paalla = false;
	}

}
