package main;

import java.util.ArrayList;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.utility.Delay;

/**
 * Infrapunasenorin testaus
 * @author hannutam
 *
 */
public class IRSensorTest {

	public static void main(String[] args) {
		EV3IRSensor irSensor = new EV3IRSensor(SensorPort.S4);
		IRSChecker checkerThread = new IRSChecker(irSensor);
		ArrayList<String> modes = irSensor.getAvailableModes();
		LCD.drawString("" + modes, 0, 1);
		checkerThread.start();
		
		while (Button.ENTER.isUp()) {
			float distance = checkerThread.getDistance();
			LCD.clear(9, 2, 10);
			LCD.drawString("Etaisyys: " + distance, 0, 2);
			
			//float beaconDistance = checkerThread.getBeaconData()[0];
			//float beaconBearing = checkerThread.getBeaconData()[1];
			//LCD.drawString("KS etäisyys " + beaconDistance, 0, 3);
			//LCD.drawString("KS kulma " + beaconBearing, 0, 4);
			
			int remoteCommand = checkerThread.getRemoteCmd();
			LCD.drawString("KS painike: " + remoteCommand, 0, 5);
			
			LCD.drawString("Lopetus ENTER", 0, 6);
			Delay.msDelay(100);

		}
		
		checkerThread.stopSampling();
		Delay.msDelay(1000);	//Tarvitaan, että säie ehtii pysähtyä ennen anturin sulkemista
		irSensor.close();
	}

}

