package liikkuvat;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

public class Pyorat {
	private EV3MediumRegulatedMotor kaannosMoottori;
	private EV3LargeRegulatedMotor  paaMoottori;
	
	/**
	 * Luokka, joka ohjaa robotin ajamiseen k�ytt�mi� moottoreita.
	 * @param kaannosPort
	 * @param paaPort
	 */

	public Pyorat (Port kaannosPort, Port paaPort){
		this.kaannosMoottori = new EV3MediumRegulatedMotor(kaannosPort);
		this.paaMoottori = new EV3LargeRegulatedMotor(paaPort);
	}
	
	/**
	 * Laittaa robotin ajamaan eteenp�in asetetulla nopeudella.
	 * @param nopeus Ajonopeus
	 */
	public void eteen(int nopeus){
		this.paaMoottori.setSpeed(nopeus);
		this.paaMoottori.forward();	
	}
	
	/**
	 * Laittaa robotin ajamaan taaksep�in asetetulla nopeudella.
	 * @param nopeus Ajonopeus
	 */
	public void taakse (int nopeus) {
		this.paaMoottori.setSpeed(nopeus);
		this.paaMoottori.backward();
	}
  
	/**
	 * Metodi k��nt�� robotin eturenkaat annettuun kulmaan. Jos kulma on suurempi kuin raja-arvo 30,
	 * k��nnet��n py�r�t vain raja-arvoon asti.
	 * @param kulma
	 */
	public void kaanny(int kulma) {
		int rajaKulma = 30;
		if (Math.abs(kulma) <= rajaKulma) {
			this.kaannosMoottori.rotateTo(kulma);
		} else {
			this.kaannosMoottori.rotateTo(rajaKulma);
		}
	}
	
	/**
	 * Metodi k��nt�� robotin eturenkaat suoraan ajoon.
	 */
	public void suorista() {
		kaanny(0);

	}
}