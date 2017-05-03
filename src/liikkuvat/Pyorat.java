package liikkuvat;



import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

public class Pyorat {
//eteen
	//taakse
	private EV3MediumRegulatedMotor kaannosMoottori;
	private EV3LargeRegulatedMotor  paaMoottori;
	
	public Pyorat (Port kaannosPort, Port paaPort){
		this.kaannosMoottori = new EV3MediumRegulatedMotor(kaannosPort);
		this.paaMoottori = new EV3LargeRegulatedMotor(paaPort);
		kaannosMoottori.setSpeed(20);
	}
	
	public void eteen(int nopeus){
		this.paaMoottori.setSpeed(100);
		this.paaMoottori.backward();
//		this.paaMoottori.forward();
	}
	public void kaannos(int kaannosKulma) {
		kaannosKulma =  0;
		
		this.kaannosMoottori.rotateTo(kaannosKulma);
		
	}
		

		
		
	

	
}

