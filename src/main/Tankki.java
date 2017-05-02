package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;

import liikkuvat.Tykki;
import sensorit.Infrapuna;

public class Tankki {
	
	private Tykki tykki;
	private Infrapuna infrapuna;

	public Tankki() {
		this.infrapuna = new Infrapuna(SensorPort.S1);
		this.infrapuna.start();
		
		this.tykki = new Tykki(MotorPort.D, MotorPort.C);
		this.tykki.start();
	}
	
	public void aloita() {
		int kanava0, kanava1;
		while (!Button.ESCAPE.isDown()) {
			
			kanava0 = this.infrapuna.getKanava0();
			kanava1 = this.infrapuna.getKanava1();

			if (kanava0 == 1 || kanava0 == 2) {
				this.tykki.pyoritaAlustaaSulavasti(kanava0);
				while (this.infrapuna.getKanava0() == kanava0) {
				}
				this.tykki.lopetaAlustanPyoriminen();
			} else if (kanava0 == 3) {
				this.tykki.ammuTykilla();
			}
			
		}
		this.infrapuna.lopeta();
		this.tykki.lopeta();
		
	}
	
}
