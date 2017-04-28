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
		int lukema;
		while (!Button.ESCAPE.isDown()) {
			
			lukema = this.infrapuna.getKanava0();
			
			LCD.drawString(this.tykki.alusta.getPosition() + "            ", 0, 0);
			LCD.drawInt(lukema, 0, 1);
			
			if (lukema == 1 || lukema == 2) {
				this.tykki.pyoritaAlustaaSulavasti(lukema);
				while (this.infrapuna.getKanava0() == lukema) {
					LCD.drawString(this.tykki.alusta.getPosition() + "            ", 0, 0);
				}

				this.tykki.lopetaAlustanPyoriminen();
			}
			
		}
		this.infrapuna.lopeta();
		this.tykki.lopeta();
		
	}
	
}
