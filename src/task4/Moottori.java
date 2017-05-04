package task4;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class Moottori {
	
	private EV3LargeRegulatedMotor moottori;
	
	public Moottori(Port portti) {
		this.moottori = new EV3LargeRegulatedMotor(portti);
	}
	
	public void eteen() {
		this.moottori.forward();
	}
	
	public void taakse() {
		this.moottori.backward();
	}
	
	public void pysayta() {
		this.moottori.stop();
	}
	
	public void vaihdaNopeus(int nopeus) {
		this.moottori.setSpeed(nopeus);
	}
	
	public void sulje() {
		this.moottori.close();
	}
	
	public EV3LargeRegulatedMotor getMotor() {
		return this.moottori;
	}

}
