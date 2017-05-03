package main;

import sensorit.BeaconTracker;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.utility.Delay;


public class Robo {
	
	public static void main(String[] args) {
		Setup aloitus = new Setup();
		aloitus.start();
	}
}
