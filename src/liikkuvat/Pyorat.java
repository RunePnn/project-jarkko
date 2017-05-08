package liikkuvat;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

public class Pyorat extends Thread {
	private EV3MediumRegulatedMotor kaannosMoottori;
	private EV3LargeRegulatedMotor  paaMoottori;
	private boolean running;
	
	/**
	 * Luokka, joka ohjaa robotin ajamiseen kayttamia moottoreita.
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
	 * Oletus nopeus on 600.
	 * @param nopeus Ajonopeus asteina sekunnissa.
	 */
	
	public void eteen(){
		eteen(600);	
	}
	
	public void eteen(int nopeus){
//		Moottorin ajosuunta on oletetulle suunnalle vastakkainen.
		this.paaMoottori.setSpeed(nopeus);
		this.paaMoottori.backward();
		suorista();
	}
	
	/**
	 * Laittaa robotin ajamaan taaksenpain asetetulla nopeudella.
	 * Oletus nopeus on 400.
	 * @param nopeus Ajonopeus asteina sekunnissa.
	 */
	
	public void taakse () {
		taakse(400);
	}
	
	public void taakse (int nopeus) {
//		Moottorin ajosuunta on oletetulle suunnalle vastakkainen.
		this.paaMoottori.setSpeed(nopeus);
		this.paaMoottori.forward();
		suorista();
	}
	
	/**
	 * Suoristaa kaannosrenkaat ja pysayttaa molemmat moottorit.
	 */
	public void pysayta() {
		this.paaMoottori.stop();
		this.kaannosMoottori.rotateTo(0);
		this.kaannosMoottori.stop();
	}
  
	/**
	 * Metodi kaantaa robotin eturenkaat annettuun suuntaan.
	 * Pyoria kaantavaa moottoria kaannetaan vain rajakulmaan asti.
	 * Muut ohjauskomennot keskeyttävät käännöksen.
	 * @param suunta Kaannossuunta. 1 = vasen, 0 = oikea.
	 * @param rajakulma Aseta moottorin kaannoskulma suhteessa suoraan ajoon.
	 */
	public void kaanny(int suunta) {
		kaanny(suunta, 50);
	}
	
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
}