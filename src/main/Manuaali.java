package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;
import liikkuvat.Tykki;
import liikkuvat.Pyorat;
import sensorit.Infrapuna;

public class Manuaali {
	
	private Tykki tykki;
	private Pyorat pyorat;
	private Infrapuna infrapuna;
	private int komentoNyk;
	private int komentoEd;
	
	public Manuaali() {
		this.infrapuna = new Infrapuna(SensorPort.S4);
		this.infrapuna.start();
		
		this.tykki = new Tykki(MotorPort.D, MotorPort.C);
		this.tykki.start();
		
		this.pyorat = new Pyorat(MotorPort.A, MotorPort.B);
		
		this.komentoNyk = -1;
		this.komentoEd = -1;
	}
	
	public void aloita() {
		boolean luettu = false;
		while (!Button.ENTER.isDown()) {
			
			this.komentoNyk = this.infrapuna.getKanava0();
			
			if (this.komentoNyk != this.komentoEd) {
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
						this.pyorat.kaanny(0);
						break;
					case 6:
						// aja eteen oikealle
						this.pyorat.eteen();
						this.pyorat.kaanny(1);
						break;
					case 7:
						// aja taakse vasemmalle
						this.pyorat.taakse();
						this.pyorat.kaanny(0);
						break;
					case 8:
						// aja taakse oikealle
						this.pyorat.taakse();
						this.pyorat.kaanny(1);
						break;
				}
				//Talletetaan edellinen komento ja merkitään komento luetuksi
				
				//Viime komento ei ollut tykin käännöskomento
				if ((this.komentoEd == 3 || this.komentoEd == 4)
						&& !(this.komentoNyk == 3 || this.komentoNyk == 4)) {
					this.tykki.lopetaAlustanPyoriminen();
					
				} else if ((this.komentoEd == 1 || this.komentoEd == 2 || this.komentoEd == 5 || this.komentoEd == 6
						|| this.komentoEd == 7 || this.komentoEd == 8) 
						&& !(this.komentoNyk == 1 || this.komentoNyk == 2
							|| this.komentoNyk == 5 || this.komentoNyk == 6
							|| this.komentoNyk == 7 || this.komentoNyk == 8)) {
					this.pyorat.pysayta();
					this.pyorat.suorista();
				}
			}
			
			if ((this.komentoNyk == 9 && this.komentoEd == 0) || (this.komentoEd == 9 && this.komentoNyk != 9)) {
				this.tykki.ammuTykilla();
			}
			
			this.komentoEd = this.komentoNyk;
			
			
//			if (komentoNyk != 11) {
//				
//				switch(komentoNyk) {
//				case 1:
//					// aja eteen
//					this.pyorat.eteen();
//					break;
//				case 2:
//					// aja taakse
//					this.pyorat.taakse();
//					break;
//				case 3:
//					// kaanna tykki vasemmalle
//					this.tykki.pyoritaAlustaaSulavasti(0);
//					break;
//				case 4:
//					// kaanna tykki oikealle
//					this.tykki.pyoritaAlustaaSulavasti(1);
//					break;
//				case 5:
//					// aja eteen vasemmalle
//					this.pyorat.eteen();
//					this.pyorat.kaanny(0);
//					break;
//				case 6:
//					// aja eteen oikealle
//					this.pyorat.eteen();
//					this.pyorat.kaanny(1);
//					break;
//				case 7:
//					// aja taakse vasemmalle
//					this.pyorat.taakse();
//					this.pyorat.kaanny(0);
//					break;
//				case 8:
//					// aja taakse oikealle
//					this.pyorat.taakse();
//					this.pyorat.kaanny(1);
//					break;
//				}
//				
//				// silmukassa kunnes nappia ei enaa paineta
//				while (infrapuna.getKanava0() == komentoNyk && komentoNyk != 0) {}
//				
//				if (komentoNyk != 3 && komentoNyk != 4) {
//					this.pyorat.pysayta();
//					this.pyorat.suorista();
//				} else {
//					this.tykki.lopetaAlustanPyoriminen();
//				}
//				
//			}
//			else if (komentoNyk == 11) {
//				this.tykki.ammuTykilla();
//			}
		}
		
		Delay.msDelay(1000);
		this.infrapuna.lopeta();
		this.tykki.lopeta();
		
	}
	
}
