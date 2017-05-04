package task2;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import task2.Logic;

public class Game {
	private Logic logic;
	
	public Game() {
		this.logic = new Logic();
	}
	
	public void kiviSaksetPaperi() {
		while (true) {
			Button.LEDPattern(0);
			LCD.drawString("Vasen = kivi", 0, 0);
			LCD.drawString("Keski = sakset", 0, 1);
			LCD.drawString("Oikea = paperi", 0, 2);
			LCD.drawString("Alas = nollaa pisteet", 0, 3);
			LCD.drawString("Escape = lopeta", 0, 4);
			LCD.drawString(this.logic.getPlayerScore() + " - " + this.logic.getComScore(), 0, 6);
			int keycode = Button.waitForAnyPress();
			LCD.clear();
			Delay.msDelay(1000);
			
			this.logic.getInput(keycode);
			
			if (keycode == 4) {
				LCD.drawString("Nollataan...", 0, 2);
				Delay.msDelay(2500);
				continue;
			} else if (!this.logic.isPlay()) {
				LCD.drawString("Lopetetaan...", 0, 2);
				Delay.msDelay(2500);
				break;
			}
			
			if (logic.getPlayerWeapon() == Weapon.EMPTY) {
				LCD.drawString("Valitse uudelleen", 0, 2);
				Delay.msDelay(2500);
				continue;
			}
			
			gameResult(logic.play());
			
			Delay.msDelay(4000);
			LCD.clear();
		}
	}
	
	public void gameResult(int result) {
		LCD.drawString("Pelaaja: " + weaponChoice(logic.getPlayerWeapon()), 0, 2);
		Delay.msDelay(1000);
		LCD.drawString("Tietokone: " + weaponChoice(logic.getComWeapon()), 0, 3);
		
		Delay.msDelay(1500);
		
		switch (result) {
			case 0:
				LCD.drawString("Havisit pelin", 0, 4);
				Delay.msDelay(500);
				Button.LEDPattern(5);
				this.logic.getComScore().addPoints();
				break;
			case 1:
				LCD.drawString("Voitit pelin", 0, 4);
				Delay.msDelay(500);
				Button.LEDPattern(4);
				this.logic.getPlayerScore().addPoints();
				break;
			case 2:
				LCD.drawString("Tasapeli", 0, 4);
				Delay.msDelay(500);
				Button.LEDPattern(3);
				break;
			default:
				LCD.drawString("Jokin meni pieleen :(((", 0, 4);
				break;
		}
	}
	
	public String weaponChoice(Weapon weapon) {
		
		switch (weapon) {
		case KIVI:
			return "kivi";
		case SAKSET:
			return "sakset";
		case PAPERI:
			return "paperi";
		default:
			return "tyhja";
		}
	}	
}
