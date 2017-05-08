package liikkuvat;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

/**
 * Ampuva tykki, jota voi pyörittää alustalla.
 * @author Teemu Eerola
 * @since 2017-05-08
 */

public class Tykki {
	
	public EV3MediumRegulatedMotor alusta;
	private EV3LargeRegulatedMotor tykki;
	
	private int rotaatio;
	private int alustanNopeus;
	
	/**
	 * @param porttiAlusta MotorPort pyorivää alustaa varten
	 * @param porttiTykki MotorPort ampuvaa tykkiä varten
	 */
	public Tykki(Port porttiAlusta, Port porttiTykki) {
		this.alusta = new EV3MediumRegulatedMotor(porttiAlusta);
		this.alustanNopeus = 50;
		this.alusta.setSpeed(this.alustanNopeus);
		
		this.tykki = new EV3LargeRegulatedMotor(porttiTykki);
		this.tykki.setSpeed(800);
		
		this.rotaatio = 0;
		
		this.paalla = true;
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
		this.alusta.setSpeed(this.alustanNopeus);
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
	 * Metodi tykin alustan nopeuden muuttamiseksi
	 * @param nopeus haluttu alustan nopeus
	 */
	public void asetaAlustanNopeus(int nopeus) {
		this.alustanNopeus = nopeus;
	}
	
	/**
	 * Metodi tykin alustan sijainnin saamiseksi
	 * @return float sijainti
	 */
	public float haeSijainti() {
		return this.tykki.getPosition();
	}
	
	/**
	   * Metodi, joka kääntää tykin alkuperäiseen asentoon
	   * ja lopettaa säikeen.
	   * @return Ei mitään
	   */
	public void lopeta() {
		Button.LEDPattern(2);
		this.alusta.setSpeed(this.alustanNopeus);
		this.alusta.rotateTo(0);
		
		Delay.msDelay(1000);
		this.alusta.close();
		this.tykki.close();
	}

}
