package main;

import lejos.hardware.sensor.EV3IRSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.MeanFilter;

public class IRSChecker extends Thread {
	private EV3IRSensor irSensor;
	private float distance = 0;
	private float[] beaconData;
	private int remoteCmd = 0;
	private boolean stopSampling = false;

	public IRSChecker(EV3IRSensor sensor) {
		this.irSensor = sensor;
	}

	public float getDistance() {
		return this.distance;
	}

	public float[] getBeaconData() {
		return this.beaconData;
	}

	public int getRemoteCmd() {
		return this.remoteCmd;
	}

	public void stopSampling() {
		this.stopSampling = true;
	}

	// this is run when the thread is started
	public void run() {
		while (!stopSampling) {
			SampleProvider dist = irSensor.getDistanceMode();
			// tai SampleProvider dist2 = irSensor.getMode("Distance");

			
			 float[] distSample = new float[dist.sampleSize()];
			 dist.fetchSample(distSample, 0); 
			 this.distance = distSample[0];
			 

			// stack a filter on the sensor that gives the running average of
			// the last 5 samples
			//SampleProvider averagedDist = new MeanFilter(dist, 5);

			// initialize an array of floats for fetching samples
			//float[] distSample = new float[averagedDist.sampleSize()];

			// fetch a sample
			//averagedDist.fetchSample(distSample, 0);
			//this.distance = distSample[0];

			/*
			 * SampleProvider beacon = irSensor.getSeekMode(); float[]
			 * beaconSample = new float[beacon.sampleSize()];
			 * beacon.fetchSample(beaconSample, 0); this.beaconData =
			 * beaconSample;
			 */

			this.remoteCmd = irSensor.getRemoteCommand(0);

		}
	}
}
