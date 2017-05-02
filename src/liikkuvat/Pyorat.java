package liikkuvat;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

public class Pyorat {

	private EV3MediumRegulatedMotor kaannosMoottori;
	private EV3LargeRegulatedMotor  paaMoottori;
	
	public Pyorat (Port kaannosPort, Port paaPort){
		this.kaannosMoottori = new EV3MediumRegulatedMotor(kaannosPort);
		this.paaMoottori = new EV3LargeRegulatedMotor(paaPort);
	}
	
	public void eteen(int nopeus){
		this.paaMoottori.setSpeed(nopeus);
		this.paaMoottori.forward();
	}
}