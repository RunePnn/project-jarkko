package liikkuvat;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
/**
 * @luokka, joka hoitaa liikkeet eteen ja taakse sekä käännökset
 * @author robertka
 **/
public class Pyorat extends Thread {
	private EV3MediumRegulatedMotor kaannosMoottori;
	private EV3LargeRegulatedMotor  paaMoottori;
	private boolean running;
	
	/**
	 * @param kaannosPort Kaannosmoottorin porttiasema
	 * @param paaPort Paamoottorin porttiasema
	 */

	public Pyorat (Port kaannosPort, Port paaPort){
		this.kaannosMoottori = new EV3MediumRegulatedMotor(kaannosPort);
		this.paaMoottori = new EV3LargeRegulatedMotor(paaPort);
		
			this.kaannosMoottori.setSpeed(100);
			this.paaMoottori.setSpeed(600);
			
			this.running = true;
		}
		
		@Override
		public void run() {
			while(isRunning()) {
				
			}
			this.kaannosMoottori.close();
			this.paaMoottori.close();
		}
		
		public void terminate() {
			this.running = false;
	 	}
	
	/**
	 * Laittaa robotin ajamaan eteenpain asetetulla nopeudella.
	 */
	public void eteen(){
//		Moottorin ajosuunta on oletetulle suunnalle vastakkainen.
		this.paaMoottori.setSpeed(600);
		this.paaMoottori.backward();	
	}
	
	/**
	 * Laittaa robotin ajamaan eteenpain asetetulla nopeudella.
	 * @param nopeus Ajonopeus
	 */
	
	public void eteen(int nopeus){
//		Moottorin ajosuunta on oletetulle suunnalle vastakkainen.
		this.paaMoottori.setSpeed(nopeus);
		this.paaMoottori.backward();	
	}
	
	/**
	 * Laittaa robotin ajamaan taaksepain asetetulla nopeudella.
	 */
	public void taakse () {
//		Moottorin ajosuunta on oletetulle suunnalle vastakkainen.
		this.paaMoottori.setSpeed(400);
		this.paaMoottori.forward();
	}
	
	/**
	 * Laittaa robotin ajamaan taaksepain asetetulla nopeudella.
	 * @param nopeus Ajonopeus
	 */
	
	public void taakse (int nopeus) {
//		Moottorin ajosuunta on oletetulle suunnalle vastakkainen.
		this.paaMoottori.setSpeed(nopeus);
		this.paaMoottori.forward();
	}
	
	/**
	 * Pysayttaa molemmat moottorit.
	 * Suoristaa myös renkaat
	 */
	public void pysayta() {
		this.paaMoottori.stop();
		this.kaannosMoottori.rotateTo(0);
		this.kaannosMoottori.stop();
	}
  
	/**
	 * Metodi kaantaa robotin eturenkaat annettuun suuntaan.
	 * Pyoria kaannetaan vain raja-arvoon 50 asti.
	 * @param suunta Kaannossuunta. 0 = vasen, 1 = oikea.
	 */
	public void kaanny(int suunta) {
		int rajakulma = 50;
		if (suunta == 0) {
			this.kaannosMoottori.rotateTo(rajakulma, true);
		} else if (suunta == 1) {
			this.kaannosMoottori.rotateTo(-rajakulma, true);
		}
	}
	
	/**
	 * Metodi kaantaa robotin eturenkaat annettuun suuntaan.
	 * Pyoria kaannetaan haluttuun raja-arvoon asti.
	 * @param suunta Kaannossuunta. 0 = vasen, 1 = oikea.
	 */
	
	public void kaanny(int suunta, int rajakulma) {
		if (suunta == 0) {
			this.kaannosMoottori.rotateTo(rajakulma, true);
		} else if (suunta == 1) {
			this.kaannosMoottori.rotateTo(-rajakulma, true);
		}
	}
	
	/**
	 * Metodi kaantaa robotin eturenkaat suoraan ajoon.
	 */
	public void suorista() {
		this.kaannosMoottori.rotateTo(0, true);
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
	
	public boolean isRunning() {
		return this.running;
	}
	
	/**
	* Pysayttaa moottorit, suoristaa kaannosmoottorin ja sulkee lopuksi molemmat moottorit.
	*/
	public void lopeta() {
		pysayta();
		this.kaannosMoottori.close();
		this.paaMoottori.close();
	}
}
