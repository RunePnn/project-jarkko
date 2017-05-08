package sensorit;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3IRSensor;

/**
 * @author Teemu Eerola
 * @since 2017-05-08
 */

public class Infrapuna extends Thread {
	
	private EV3IRSensor infrapuna;
	
	private int kanava0 = 0;
	private int kanava1 = 0;
	
	private boolean paalla;
	
	/**
	 * @param portti Sensoriportti infrapunasensoria varten.
	 */
	public Infrapuna(Port portti) {
		this.infrapuna = new EV3IRSensor(portti);
		this.paalla = true;
	}
	
	/**
	 * Infrapunan run-metodi. Lukee kaukosäätimen kanavan 0 arvoa.
	 * @return Ei mitään
	 */
	public void run() {
		while (this.paalla) {
			this.kanava0 = this.infrapuna.getRemoteCommand(0);
			//this.kanava1 = this.infrapuna.getRemoteCommand(1);
		}
	}

	 /**
	   * Metodi kanavan 0 arvon hakemiseen.
	   * @return int Kanavan 0 arvo
	   */
	public int getKanava0() {
		return this.kanava0;
	}
	
	 /**
	   * Metodi kanavan 1 arvon hakemiseen.
	   * @return int Kanavan 1 arvo
	   */
	public int getKanava1() {
		return this.kanava1;
	}
	
	 /**
	   * Metodi infrapunasäikeen lopettamiseksi.
	   * @return Ei mitään
	   */
	public void lopeta() {
		this.paalla = false;
	}
}
