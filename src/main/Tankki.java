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
		
		this.tykki = new Tykki(MotorPort.D, MotorPort.B);
		this.tykki.start();
	}
	
	public void aloita() {
		int lukema;
		while (!Button.ESCAPE.isDown()) {
			
			lukema = this.infrapuna.getLukema();
			
			LCD.drawString(this.tykki.alusta.getPosition() + "            ", 0, 0);
			LCD.drawInt(lukema, 0, 1);
			
			if (lukema == 1 || lukema == 2) {
				this.tykki.pyoritaAlustaaSulavasti(lukema);
				while (this.infrapuna.getLukema() == lukema) {
					LCD.drawString(this.tykki.alusta.getPosition() + "            ", 0, 0);
				}
				
				this.tykki.alusta.stop();
				this.tykki.alusta.rotateTo((int)this.tykki.alusta.getPosition());
				this.tykki.alusta.stop();
				
				//this.tykki.lopetaAlustanPyoriminen();
			}
			
			if (lukema == 3) {
				this.tykki.ammuTykilla();
			}
			
		}
		this.infrapuna.lopeta();
		this.tykki.lopeta();
	}
	
}
