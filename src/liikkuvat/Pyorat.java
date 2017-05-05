package liikkuvat;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

public class Pyorat {
	private EV3MediumRegulatedMotor kaannosMoottori;
	private EV3LargeRegulatedMotor  paaMoottori;
	
	/**
	 * Luokka, joka ohjaa robotin ajamiseen kayttamia moottoreita.
	 * @param kaannosPort Kaannosmoottorin porttiasema
	 * @param paaPort Paamoottorin porttiasema
	 */

	public Pyorat (Port kaannosPort, Port paaPort){
		this.kaannosMoottori = new EV3MediumRegulatedMotor(kaannosPort);
		this.paaMoottori = new EV3LargeRegulatedMotor(paaPort);
		
		this.kaannosMoottori.setSpeed(80);
		this.paaMoottori.setSpeed(300);
	}
	
	/**
	 * Laittaa robotin ajamaan eteenpain asetetulla nopeudella.
	 */
	public void eteen(){
//		Moottorin ajosuunta on oletetulle suunnalle vastakkainen.
		this.paaMoottori.backward();	
	}
	
	/**
	 * Laittaa robotin ajamaan taaksep�in asetetulla nopeudella.
	 * @param nopeus Ajonopeus
	 */
	public void taakse () {
//		Moottorin ajosuunta on oletetulle suunnalle vastakkainen.
		this.paaMoottori.forward();
	}
	
	/**
	 * Pysayttaa molemmat moottorit.
	 */
	public void pysayta() {
		this.paaMoottori.stop();
		this.kaannosMoottori.stop();
	}
  
	/**
	 * Metodi kaantaa robotin eturenkaat annettuun suuntaan.
	 * Pyoria kaannetaan vain raja-arvoon 30 asti.
	 * @param suunta Kaannossuunta. 0 = vasen, 1 = oikea.
	 */
	public void kaanny(int suunta) {
		int rajakulma = 45;
		if (suunta == 0) {
			this.kaannosMoottori.rotateTo(rajakulma);
		} else if (suunta == 1) {
			this.kaannosMoottori.rotateTo(-rajakulma);
		}
	}
	
	/**
	 * Metodi kaantaa robotin eturenkaat suoraan ajoon.
	 */
	public void suorista() {
		this.kaannosMoottori.rotateTo(0);

	}
	
	/**
	 * Jos jokin ajomoottori on kaynnissa palautetaan true.
	 * @return True, jos robotin ajomoottorit liikkuvat.
	 */
	public boolean liikkuu() {
		if (this.kaannosMoottori.isMoving() || this.paaMoottori.isMoving()) {
			return true;
		}
		return false;
	}
}