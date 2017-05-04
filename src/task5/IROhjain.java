package task5;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.utility.Delay;
import task3.Mittari;
import task4.Auto;

public class IROhjain {
	private IRSTarkistin infrapuna;
	private Auto moottorit;
	private Mittari ajastin;
	
	public IROhjain(Port iRPort, Port motorRight, Port motorLeft) {
		this.infrapuna = new IRSTarkistin(iRPort);
		this.moottorit =new Auto(motorRight, motorLeft);
		this.ajastin = new Mittari();
	}
	
	public void aja() {
		this.infrapuna.start();
		LCD.clear();
		LCD.drawString("Aloitettu", 0, 0);
		
		while (!Button.ESCAPE.isDown()) {
			if (etaisyystarkistus()) {
				kaukoOhjaus();
			}
			ajastettuTulostus("Etaisyys: " + this.infrapuna.getEtaisyys(), 3, 1000);
		}
		
		this.infrapuna.terminate();
		LCD.clear();
		LCD.drawString("Lopetetaan...", 0, 0);
	}
	
	public void kaukoOhjaus() {
		int hidas = 300;
		int nopea = 500;
		this.moottorit.syncStart();
		switch (this.infrapuna.getKaukoOnce()) {
			case 1:
				this.moottorit.vasemmalle();
				break;
			case 2:
				if (!this.moottorit.isKaynnisssa()) {
					this.moottorit.kaynnista(hidas);
					this.moottorit.eteen();
				} else {
					this.moottorit.pysayta();
				}
				break;
			case 3:
				this.moottorit.oikealle();
				break;
			case 4:
				if (this.moottorit.getNopeus() != nopea) {
					this.moottorit.vaihdaNopeus(nopea);
				} else {
					this.moottorit.vaihdaNopeus(hidas);
				}
				break;
			case 9:
				break;
		}
		this.moottorit.syncStop();
	}
	
	public void ajastettuTulostus(String str, int rivi,  float delay) {
		if (!this.ajastin.isStarted()) {
			LCD.clear(rivi);
			LCD.drawString(str, 0, rivi);
			this.ajastin.startTimer();
		} else if (this.ajastin.getDurationMs() > delay) {
			this.ajastin.stopTimer();
		}
	}
	
	public boolean etaisyystarkistus() {
		float etaisyys = this.infrapuna.getEtaisyys();
		if (etaisyys <= 10) {
			this.moottorit.syncStart();
			this.moottorit.pysayta();
			this.moottorit.syncStop();
			return false;
		}
		return true;
	}
}
