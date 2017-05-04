package main;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;
import task3.Laskuri;
import task4.Variohjain;
import task5.IROhjain;
import task5.IRSTarkistin;

 

public class Robo {
	public static void main(String[] args) {
		
		IROhjain ohjain = new IROhjain(SensorPort.S4, MotorPort.A, MotorPort.B);
		ohjain.aja();
		
//		EV3IRSensor sensor = new EV3IRSensor(SensorPort.S4);
//		IRChecker dist = new IRChecker(sensor);
//		IRRemoteChecker remote = new IRRemoteChecker(sensor);
//		dist.start();
//		remote.start();
	}
	
	public static void genString(String s) {
		//String str = "";
		int x = 4;
		int y = 3;
		int screen = 18 - x;
		
		for (int i = 0; i < s.length(); i++) {
			//str += s.charAt(i);
			if (i < screen) {
				LCD.drawChar(s.charAt(i), x + i, y);
				Delay.msDelay(200);
			} else {
				LCD.drawChar(s.charAt(i), (x+i)-screen, y+1);
				Delay.msDelay(200);
			}
		}
		Delay.msDelay(3000);
		LCD.clearDisplay();
	}
	
	public static void drawTest() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 18; j++) {
				if (j < 10) {
					LCD.drawInt(j, j, i);
				} else {
					LCD.drawInt(j - 10, j, i);
				}
				
				Delay.msDelay(100);
			}
		}
		Delay.msDelay(3000);
		LCD.clearDisplay();
	}
	
	public static void drawRect(int w, int h) {
		if (w < 0 || h < 0) {
			return;
		}
		int xInit = (LCD.SCREEN_WIDTH - w) / 2;
		int yInit = (LCD.SCREEN_HEIGHT - h) / 2;
		LCD.clearDisplay();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				LCD.setPixel(xInit + j, yInit + i, 1);
			}
		}
	}
	
	
}
