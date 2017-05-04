package task4;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class Variohjain {
	
	private EV3ColorSensor cs1;
	private Auto auto;
	
	private String vari = "musta";
	private String edellinenVari = "valkoinen";

	public Variohjain() {
		 this.cs1 = new EV3ColorSensor(SensorPort.S4);
		 this.auto = new Auto(MotorPort.A, MotorPort.B);
	}
	
	public void aloita() {
		
		LCD.drawString("Sini: eteen", 0, 0);
		LCD.drawString("Puna: pysayta", 0, 1);
		LCD.drawString("Vihr: oikea", 0, 2);
		LCD.drawString("Kelt: vasen", 0, 3);
		LCD.drawString("Valk: nopea", 0, 4);
		LCD.drawString("Pink: hidas", 0, 5);
		
		this.auto.kaynnista(400);
		
		while (!Button.ESCAPE.isDown()) {
			tunnistaVari();
		}
		
		this.cs1.close();
		this.auto.sulje();
	}
	
	public void tunnistaVari() {
		int colorID = this.cs1.getColorID();
		
		this.auto.syncStart();
		
		switch (colorID) {

		case Color.WHITE:
			vari = "valkoinen";
			this.auto.vaihdaNopeus(800);
			break;
			
		case Color.BROWN:
			vari = "ruskea";
			this.auto.vaihdaNopeus(300);
			break;
			
		case Color.GREEN:
			vari = "vihrea";
			this.auto.oikealle();
			break;
			
		case Color.RED:
			vari = "punainen";
			this.auto.pysayta();
			break;
			
		case Color.BLUE:
			vari = "sininen";
			this.auto.eteen();
			break;
			
		case Color.YELLOW:
			vari = "keltainen";
			this.auto.vasemmalle();
			break;
			
		default:
			break;
		}
		
		this.auto.syncStop();
		
		if (!vari.equals(edellinenVari)) {
			LCD.drawString(vari + "               ", 0, 6);
			edellinenVari = vari;
		}
		
	}
}
