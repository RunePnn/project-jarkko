package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import liikkuvat.Tykki;
import liikkuvat.Pyorat;
import sensorit.Infrapuna;

public class Tankki {
	
	private Tykki tykki;
	private Infrapuna infrapuna;
	private Pyorat pyorat;
	
	public Tankki() {
		this.infrapuna = new Infrapuna(SensorPort.S1);
		this.infrapuna.start();
		
		this.tykki = new Tykki(MotorPort.D, MotorPort.C);
		this.tykki.start();
		
		this.pyorat = new Pyorat(MotorPort.A, MotorPort.B);
	}
	
	public void aloita() {
		int kanava0;

		while (!Button.ESCAPE.isDown()) {
			
			kanava0 = this.infrapuna.getKanava0();
			
			if (kanava0 == 1 || kanava0 == 2 || kanava0 == 10 || kanava0 == 11) {
				
				switch(kanava0) {
				case 1:
					// aja eteen
					break;
				case 2:
					// aja taakse
					break;
				case 10:
					// kaanny vasemmalle
					this.tykki.pyoritaAlustaaSulavasti(0);
					break;
				case 11:
					// kaanny oikealle
					this.tykki.pyoritaAlustaaSulavasti(1);
					break;
				}
				
				// silmukassa kunnes nappia ei enaa paineta
				while (infrapuna.getKanava0() == kanava0) {}
				
				if (kanava0 == 1 || kanava0 == 2) {
					// this.pyorat.pysayta();
				} else if (kanava0 == 10 || kanava0 == 11) {
					this.tykki.lopetaAlustanPyoriminen();
				}
				
			}
			else if (kanava0 == 5) {
				this.tykki.ammuTykilla();
			}
			

			/*
			switch(kanava0) {
			case 1:
				//pyorat.eteen(-100);
				break;
			case 2:
				// aja taakse
				break;
			case 3:
				// kaanny vasemmalle
				break;
			case 4:
				// kaanny oikealle
				break;
			case 10:
				this.tykki.pyoritaAlustaaSulavasti(0);
				while (infrapuna.getKanava0() == kanava0) {}
				this.tykki.lopetaAlustanPyoriminen();
				break;
			case 11:
				this.tykki.pyoritaAlustaaSulavasti(1);
				while (infrapuna.getKanava0() == kanava0) {}
				this.tykki.lopetaAlustanPyoriminen();
				break;
			case 5:
				this.tykki.ammuTykilla();
				break;
			}
			
			*/
			
		}
		
		Delay.msDelay(1000);
		this.infrapuna.lopeta();
		this.tykki.lopeta();
		
	}
	
}
