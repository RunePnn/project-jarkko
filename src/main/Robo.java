package main;

import sensorit.IRTracker;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;


public class Robo {
	
	public static void main(String[] args) {
		IRTracker irAnturi = new IRTracker(new EV3IRSensor(SensorPort.S1));
		irAnturi.start();
		
//		Tankki tankki = new Tankki();
//		tankki.aloita();
	}
	
	public static void genString(int x, int y, String string, int blink) {
		String output = "";
		for (int i = 0; i < string.length(); i++) {
			output += string.charAt(i);
			LCD.drawString(output, x, y);
			Delay.msDelay(200);
		}
		
		for (int i = 1; i <= blink; i++) {
//			LCD.clearDisplay();
			drawCellLine(0,y,16,0);
//			LCD.drawString("Count: " + i, x, y);
			Delay.msDelay(500);
			LCD.drawString(output, x, y);
			Delay.msDelay(500);
		}
//		Delay.msDelay(2000);
//		LCD.clearDisplay();
	}
	
	public static void setCell (int x, int y, int color) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 16; j++) {
				LCD.setPixel(i + (x * 10), j + (y * 16), color);
			}
		}
	}
	
	public static void drawCellLine(int x, int y, int length, int color) {
		for (int i = 0; i < length; i++) {
			setCell(x + i, y, color);
		}
	}
	
	public static void drawRectangle(int x, int y, int sizeX, int sizeY, int hollow) {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				LCD.setPixel(i + (x * 10), j + (y * 16), 1);
			}
		}
		if (hollow > 0) {
			for (int i = hollow; i < sizeX - hollow; i++) {
				for (int j = hollow; j < sizeY - hollow; j++) {
					LCD.setPixel(i + (x * 10), j + (y * 16), 0);
				}
			}
		}
	}
	
	public static void drawDemo() {
		genString(0,0,"Let's draw...",0);
		genString(0,1,"a rectangle!",0);
		drawRectangle(1,3,60,60,0);
		Delay.msDelay(3000);
		drawCellLine(0,0,20,0);
		drawCellLine(0,1,20,0);
		genString(0,1,"Here's another!",0);
		drawRectangle(10,3,40,50,5);
		Delay.msDelay(5000);
		LCD.clearDisplay();
		genString(0,0,"Drawing rectangles",0);
		genString(0,1,"is cool!",3);
		Delay.msDelay(1000);
		drawCellLine(0,0,20,0);
		drawCellLine(0,1,20,0);
		genString(0,0,"But can you draw...",0);
		genString(0,1,"Smilies!",3);
		Delay.msDelay(1000);
		setCell(5,2,1);
		setCell(10,2,1);
		setCell(4,4,1);
		drawCellLine(5,5,6,1);
		setCell(11,4,1);
		Delay.msDelay(2000);
		genString(1,6,"Well here's one",0);
		genString(1,7,"for you :)))",0);
		Delay.msDelay(5000);
	}
	
	public static void annoyingDog() {
		LCD.clearDisplay();
		genString(2, 0, "Annoying dog...", 0);
		Delay.msDelay(1000);
		setCell(5,1,1);
		setCell(8,1,1);
		setCell(4,4,1);
		drawCellLine(5,5,4,1);
		setCell(6,4,1);
		setCell(9,4,1);
		setCell(6,3,1);
		setCell(7,3,1);
		Delay.msDelay(1000);
		genString(2, 7, "On EV3!?!?", 0);
		Delay.msDelay(5000);
		LCD.clearDisplay();
	}

}
