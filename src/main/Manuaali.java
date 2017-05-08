package main;

import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import liikkuvat.Tykki;
import liikkuvat.Pyorat;
import sensorit.Infrapuna;


/**
 * Manuaali luokka ohjaa robotin renkaita ja tykkia kaukosaatimen signaalin perusteella.
 * 
 * @author Rune
 */
public class Manuaali {
	
	private Tykki tykki;
	private Pyorat pyorat;
	private Infrapuna infrapuna;
	private int komentoNyk;
	private int komentoEd;
	
	/**
	 * Luo uuden Manuaali olion.
	 */
	public Manuaali() {
		this.infrapuna = new Infrapuna(SensorPort.S4);
		this.infrapuna.start();
		
		this.tykki = new Tykki(MotorPort.D, MotorPort.C);
		this.tykki.start();
		
		this.pyorat = new Pyorat(MotorPort.A, MotorPort.B);
		this.pyorat.start();
		
		this.komentoNyk = -1;
		this.komentoEd = -1;
	}
	
	/**
	 * Kaynnistaa manuaalitilan, jossa robottia ohjataan kaukosaatimen komennoilla.
	 */
	public void aloita() {
		while (!Button.ENTER.isDown()) {
			
			this.komentoNyk = this.infrapuna.getKanava0();
			
			if (this.komentoNyk != this.komentoEd) {
				ohjaa();
				suorista();
			}
						
			this.komentoEd = this.komentoNyk;
		}
		
		lopeta();
	}
	
	/**
	 * Antaa robotille ohjaustoiminnon riippuen kaukosaatimelta vastaaanotetusta signaalista.
	 */
	
	private void ohjaa() {
		switch(this.komentoNyk) {
			case 1:
				// aja eteen
				this.pyorat.eteen();
				break;
			case 2:
				// aja taakse
				this.pyorat.taakse();
				break;
			case 3:
				// kaanna tykki vasemmalle
				this.tykki.pyoritaAlustaaSulavasti(1);
				break;
			case 4:
				// kaanna tykki oikealle
				this.tykki.pyoritaAlustaaSulavasti(0);
				break;
			case 5:
				// aja eteen vasemmalle
				this.pyorat.eteen();
				this.pyorat.kaanny(1);
				break;
			case 6:
				// aja eteen oikealle
				this.pyorat.eteen();
				this.pyorat.kaanny(0);
				break;
			case 7:
				// aja taakse vasemmalle
				this.pyorat.taakse();
				this.pyorat.kaanny(1);
				break;
			case 8:
				// aja taakse oikealle
				this.pyorat.taakse();
				this.pyorat.kaanny(0);
				break;
			case 11:
				// ammu tykilla
				this.tykki.ammuTykilla();
				break;
		}
	}
	
	
	/**
	 * Suoristaa renkaat tai tykin, kun niita ei enaa ohjata.
	 */
	private void suorista() {
		if ((this.komentoEd == 3 || this.komentoEd == 4)
				&& !(this.komentoNyk == 3 || this.komentoNyk == 4)) {
			this.tykki.lopetaAlustanPyoriminen();
			
		} else if ((this.komentoEd == 1 || this.komentoEd == 2 || this.komentoEd == 5 || this.komentoEd == 6
				|| this.komentoEd == 7 || this.komentoEd == 8) 
				&& !(this.komentoNyk == 1 || this.komentoNyk == 2
					|| this.komentoNyk == 5 || this.komentoNyk == 6
					|| this.komentoNyk == 7 || this.komentoNyk == 8)) {
			this.pyorat.pysayta();
		}
	}
	
	/**
	 * Lopettaa ohjelman kayttamat saikeet.
	 */
	private void lopeta() {
		Delay.msDelay(1000);
		this.infrapuna.lopeta();
		this.tykki.lopeta();
		this.pyorat.terminate();
	}
}
