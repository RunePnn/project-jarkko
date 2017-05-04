package task3;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;

public class Laskuri {
	private Kosketusanturi anturi;
	private Mittari ajastin;
	private int painalluksia;
	private double pisteet;

	public Laskuri() {
		this.anturi = new Kosketusanturi(SensorPort.S1);
		this.ajastin = new Mittari();
		this.painalluksia = 0;
		this.pisteet = 0;
	}
	
	
	// Avaa p‰‰valikon. Pelaaja voi valita, mit‰ moodia pelataan.
	public void aloitus() {
		while (true) {
			LCD.clear();
			LCD.drawString("Vasen = laske", 0, 0); // Aloita moodi joka laskee painalluksia
			LCD.drawString("Oikea = mittaa", 0, 1); // Aloita moodi joka mittaa kahden painalluksen v‰lisen ajan.
			LCD.drawString("Escape = lopeta", 0, 2);
			int keycode = Button.waitForAnyPress();
			if (keycode == 16) {
				laske();
			} else if (keycode == 8) {
				mittaa();
			} else if (keycode == 32) {
				LCD.clear();
				LCD.drawString("Lopetetaan...", 0, 2);
				Delay.msDelay(2000);
				anturi.close();
				break;
			}
		}
	}
	
	// Laskee, kuinka l‰helle p‰‰stiin ideaaliaikaa ajanmittauspeliss‰.
	public double laskePisteet(double aika) {
		if (this.ajastin.isStarted()) {
			this.pisteet = Math.abs(5.0 - aika);
		} else {
			this.pisteet = 0;
		}
		return this.pisteet;
	}
	
	//Ajanmittaus pelimoodi
	public void mittaa() {
		boolean aloitettu = false;
		int keycode;
		while (!Button.ESCAPE.isDown()) {
			if (!this.ajastin.isStarted() && !aloitettu) {
				LCD.clear();
				LCD.drawString("Paina kytkinta", 0, 0);
				LCD.drawString("aloittaaksesi", 0, 1);
				LCD.drawString("tavoite: 5 sek", 0, 2);
				aloitettu = true;
			}
			
			if (this.anturi.mittaa()) {
				if (this.ajastin.isStarted()) {
					double aika = ajastin.getDuration();
					LCD.clear();
					LCD.drawString(String.format("Aika: %.3f", aika), 0, 0);
					LCD.drawString(String.format("Etaisyys: %.3f", laskePisteet(aika)), 0, 1);
					LCD.drawString("Mitataanko", 0, 3);
					LCD.drawString("Uudelleen? ", 0, 4);
					LCD.drawString("Esc lopettaa", 0, 6);
					this.ajastin.stopTimer();
					keycode = Button.waitForAnyPress();
					LCD.clear();
					if (keycode != 32) {
						LCD.drawString("Nollataan...", 0, 2);
						Delay.msDelay(2000);
						aloitettu = false;
					} else {
						break;
					}
				} else {
					LCD.clear();
					LCD.drawString("Ajastin aloitettu", 0, 2);
					ajastin.startTimer();
				}
			}
		}
		this.ajastin.stopTimer();
		LCD.clear();
		LCD.drawString("Lopetetaan...", 0, 2);
		Delay.msDelay(1500);
	}

	//Lasketaan painallusten m‰‰ri‰ kosketusanturista.
	public void laske() {
		boolean aloitettu = false;
		boolean pelataan = false;
		while (true) {
			if (!aloitettu) {
				this.painalluksia = 0;
				LCD.clear();
				LCD.drawString("Painallusten lasku", 0, 0);
				LCD.drawString("aloitettu", 0, 1);
				LCD.drawString("Paina kytkinta", 0, 2);
				LCD.drawString("Escape lopettaa", 0, 4);
				aloitettu = true;
				pelataan = true;
			}
			
			if (pelataan) {
				if (!Button.ESCAPE.isDown()) {
					if (this.anturi.mittaa()) {
						this.painalluksia++;
						LCD.drawString("Painalluksia: " + this.painalluksia, 0, 3);
					}
				} else {
					pelataan = false;
				}
			} else {
				LCD.clear();
				LCD.drawString("Painalluksia: " + this.painalluksia, 0, 0);
				Delay.msDelay(2000);
				LCD.drawString("Halutaanko laskea ", 0, 1);
				LCD.drawString("uudelleen? ", 0, 2);
				LCD.drawString("Escape lopettaa", 0, 4);
				int keycode = Button.waitForAnyPress();
				LCD.clear();
				if (keycode != 32) {
					LCD.drawString("Nollataan...", 0, 2);
					Delay.msDelay(2000);
					aloitettu = false;
				} else {
					break;
				}
			}
		}
	}

}
