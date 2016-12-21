
// Lab2.java
//
//package ev3Odometer;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

// LAB 2 ODOMETRY
// ALEXANDER BRATYSHKIN - 260684228
// WEIPING REN - 260613810

public class Lab3 {

	// Static Resources:
	// Left motor connected to output A
	// Right motor connected to output D
	private static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("A"));
	private static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("D"));
	// Sensor and rotating motor
	private static final EV3LargeRegulatedMotor usMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("B"));
	private static final EV3UltrasonicSensor usSensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S4"));

	// CONSTANTS 
	public static final double WHEEL_RADIUS = 2.1;
	public static final double TRACK = 14.8; 
	public static final int[][] WAYPOINTS_DEMO_1 = { { 60, 30 }, { 30, 30 }, { 30, 60 }, { 60, 0 } };
	public static final int[][] WAYPOINTS_DEMO_2 = { { 0, 60 }, { 60, 0 } };

	public static int[][] points = new int[2][4];

	public static void main(String[] args) {
		int buttonChoice;

		final TextLCD t = LocalEV3.get().getTextLCD();

		SampleProvider usDistance = usSensor.getMode("Distance"); 
		float[] usData = new float[usDistance.sampleSize()]; 
		UltrasonicPoller usPoller = new UltrasonicPoller(usDistance, usData);

		do {
			// clear the display
			t.clear();

			// ask the user whether the motors should drive in a square or float
			t.drawString("< Left | Right >", 0, 0);
			t.drawString("       |        ", 0, 1);
			t.drawString(" Demo  |  Demo  ", 0, 2);
			t.drawString("   1 	 | 	 2   ", 0, 3);
			t.drawString("       |       ", 0, 4);

			buttonChoice = Button.waitForAnyPress();
		} while (buttonChoice != Button.ID_LEFT && buttonChoice != Button.ID_RIGHT);

		if (buttonChoice == Button.ID_LEFT) {
			// INITIATE SIMPLE NAVIGATION
			Odometer odometer = new Odometer(leftMotor, rightMotor, TRACK);
			odometer.start();
			OdometryDisplay odometryDisplay = new OdometryDisplay(odometer, t);
			odometryDisplay.start();
			Navigator navigator = new Navigator(odometer, leftMotor, rightMotor, WAYPOINTS_DEMO_1, usMotor, usPoller, false, TRACK);
			navigator.start();

		} else {
			//INITIATE NAVIGATION WITH AVOIDANCE 
			Odometer odometerAvoid = new Odometer(leftMotor, rightMotor, TRACK);
			odometerAvoid.start();
			OdometryDisplay odometryDisplay = new OdometryDisplay(odometerAvoid, t);
			odometryDisplay.start();
			Navigator navigatorObstacle = new Navigator(odometerAvoid, leftMotor, rightMotor, WAYPOINTS_DEMO_2, usMotor, usPoller, true, TRACK);
			usPoller.start();
			new Thread(navigatorObstacle).start();
		}

		while (Button.waitForAnyPress() != Button.ID_ESCAPE)
			;
		System.exit(0);
	}
}