/* 
 * OdometryCorrection.java
 */
package ev3Odometer;

import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;

public class OdometryCorrection extends Thread {
	private static final long CORRECTION_PERIOD = 10;
	private Odometer odometer;

	// INITIALIZE COLOR SENSOR
	Port sensorPort = LocalEV3.get().getPort("S1");
	EV3ColorSensor colorSensor = new EV3ColorSensor(sensorPort);

	private static final int LINE_LIGHT = 28;
	private static final double SENSOR_CORRECTION = 2.5; // DISTANCE BETWEEN CENTER AND TRACK
	
	// CONSTANTS 
	private static final double TILE_WIDTH = 30.48;
	private static final double HALF_TILE_WIDTH = TILE_WIDTH / 2;
	private static final double FULL_CIRCLE = Math.PI * 2;
	private static final double ONE_QUARTER_CIRCLE = Math.toRadians(45);
	private static final double THREE_QUARTERS_CIRCLE = Math.toRadians(135);
	private static final double FIVE_QUARTERS_CIRCLE = Math.toRadians(225);
	private static final double SEVEN_QUARTERS_CIRCLE = Math.toRadians(315);

	// constructor
	public OdometryCorrection(Odometer odometer) {
		this.odometer = odometer;
	}

	// run method (required for Thread)
	public void run() {
		long correctionStart, correctionEnd;

		correctionStart = System.currentTimeMillis();

		boolean lineDetected = false;
		colorSensor.setFloodlight(true);

		while (true) {
			correctionStart = System.currentTimeMillis();

			double lightValue = setupLightSensor();

			double correctionX = 0.0;
			double correctionY = 0.0;

			// CHECK IF LINE IS BLACK 
			if (lightValue <= LINE_LIGHT && !lineDetected) {

				double theta = odometer.getTheta();

				double sensorYCorrection = Math.cos(theta) * SENSOR_CORRECTION;
				double sensorXCorrection = Math.sin(theta) * SENSOR_CORRECTION;

				double relativeY = odometer.getY() + sensorYCorrection;
				double relativeX = odometer.getX() + sensorXCorrection;

				// CHECK IF HORIZONTAL LINE OR VERTICAL 
				if (!isMovingHorizontally(theta)) {
					correctionY = HALF_TILE_WIDTH - (relativeY % TILE_WIDTH);
				}
				else {
					correctionX = HALF_TILE_WIDTH - (relativeX % TILE_WIDTH);
				}

				// X & Y ARE NOW CALCULATED BASED ON THE LINE WE JUST CROSSED
				odometer.setY(correctionY + odometer.getY());
				odometer.setX(correctionX + odometer.getX());
				// LINE HAS BEEN DETECTED
				lineDetected = true;
			} else {
				// RESET THE BOOLEAN
				lineDetected = false;
			}
			correctionEnd = System.currentTimeMillis();
			if (correctionEnd - correctionStart < CORRECTION_PERIOD) {
				try {
					Thread.sleep(CORRECTION_PERIOD - (correctionEnd - correctionStart));
				} catch (InterruptedException e) {
					// there is nothing to be done here because it is not
					// expected that the odometry correction will be
					// interrupted by another thread
				}
			}
		}

	}

	public double setupLightSensor() {
		SensorMode mode = colorSensor.getRedMode();
		float[] sample = new float[mode.sampleSize()];
		mode.fetchSample(sample, 0);
		float lightIntensity = sample[0] * 100;

		return lightIntensity;
	}

	public boolean isMovingHorizontally(double theta) {
		return theta >= ONE_QUARTER_CIRCLE && theta < THREE_QUARTERS_CIRCLE
				|| theta >= FIVE_QUARTERS_CIRCLE && theta < SEVEN_QUARTERS_CIRCLE;
	}
}