package task4;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class Auto {
	
	//private Moottori moottoriOikea;
	//private Moottori moottoriVasen;
	private EV3LargeRegulatedMotor moottoriOikea;
	private EV3LargeRegulatedMotor moottoriVasen;
	private boolean kaynnissa;
	
	public Auto(Port porttiOikea, Port porttiVasen) {
		//this.moottoriOikea = new Moottori(porttiOikea);
		//this.moottoriVasen = new Moottori(porttiVasen);
		this.moottoriOikea = new EV3LargeRegulatedMotor(porttiOikea);
		this.moottoriVasen = new EV3LargeRegulatedMotor(porttiVasen);
		
		RegulatedMotor[] motorList = new RegulatedMotor[1];
		motorList[0] = this.moottoriVasen;
		
		this.moottoriOikea.synchronizeWith(motorList);
		
		this.kaynnissa = false;
	}
	
	public void kaynnista(int nopeus) {
		vaihdaNopeus(nopeus);
		this.kaynnissa = true;
	}
	
	public boolean isKaynnisssa() {
		return this.kaynnissa;
	}
	
	public void eteen() {
		if (this.kaynnissa) {
			this.moottoriOikea.forward();
			this.moottoriVasen.forward();
		}
	}

	public void taakse() {
		if (this.kaynnissa) {
			this.moottoriOikea.backward();
			this.moottoriVasen.backward();
		}
	}
	
	public void pysayta() {
		this.moottoriOikea.stop();
		this.moottoriVasen.stop();
		this.kaynnissa = false;
	}
	
	public void oikealle() {
		if (this.kaynnissa) {
			this.moottoriOikea.backward();
			this.moottoriVasen.forward();
		}
	}
	
	public void vasemmalle() {
		if (this.kaynnissa) {
			this.moottoriOikea.forward();
			this.moottoriVasen.backward();
		}
	}
	
	public void vaihdaNopeus(int nopeusOikea, int nopeusVasen) {
		this.moottoriOikea.setSpeed(nopeusOikea);
		this.moottoriVasen.setSpeed(nopeusVasen);
	}
	
	public void vaihdaNopeus(int nopeus) {
		this.moottoriOikea.setSpeed(nopeus);
		this.moottoriVasen.setSpeed(nopeus);
	}
	
	public int getNopeus() {
		if (this.moottoriOikea.getSpeed() >= this.moottoriVasen.getSpeed()) {
			return this.moottoriOikea.getSpeed();
		} else {
			return this.moottoriVasen.getSpeed();
		}
	}
	
	public void sulje() {
		this.moottoriOikea.close();
		this.moottoriVasen.close();
	}
	
	public void syncStart() {
		this.moottoriOikea.startSynchronization();
	}
	
	public void syncStop() {
		this.moottoriOikea.endSynchronization();
	}
}
