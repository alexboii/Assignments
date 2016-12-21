import lejos.hardware.*;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;

//LAB 4 LOCALIZATION
//ALEXANDER BRATYSHKIN - 260684228
//WEIPING REN - 260613810

public class Lab4 {

	// Static Resources:
	// Left motor connected to output A
	// Right motor connected to output D
	// Ultrasonic sensor port connected to input S1
	// Color sensor port connected to input S2
	private static final EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("A"));
	private static final EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(LocalEV3.get().getPort("D"));
	private static final Port usPort = LocalEV3.get().getPort("S1");
	private static final Port colorPort = LocalEV3.get().getPort("S2");
	public static final double TRACK = 15.8;
	public static final double WHEEL_RADIUS = 2.1;

	public static void main(String[] args) {

	
		SensorModes usSensor = new EV3UltrasonicSensor(usPort);
		SampleProvider usValue = usSensor.getMode("Distance"); 
		float[] usData = new float[usValue.sampleSize()]; 


		SensorModes colorSensor = new EV3ColorSensor(colorPort);
		SampleProvider colorValue = colorSensor.getMode("Red");
		float[] colorData = new float[colorValue.sampleSize()]; 

		final TextLCD t = LocalEV3.get().getTextLCD();

		USLocalizer.LocalizationType type = USLocalizer.LocalizationType.FALLING_EDGE;

		Odometer odometer = new Odometer(leftMotor, rightMotor, 50, true);

		int buttonChoice;

		do {
			// clear the display
			t.clear();

			// ask the user whether the motors should drive in a square or float
			t.drawString("< Left    | Right >", 0, 0);
			t.drawString("          |        ", 0, 1);
			t.drawString(" Falling  |   Rising  ", 0, 2);
			t.drawString("   Edge 	| 	 Edge  ", 0, 3);
			t.drawString("          |       ", 0, 4);

			buttonChoice = Button.waitForAnyPress();
		} while (buttonChoice != Button.ID_LEFT && buttonChoice != Button.ID_RIGHT);

		if (buttonChoice == Button.ID_LEFT) {
			// DO ULTRASONIC SENSOR WITH FALLING EDGE
			type = USLocalizer.LocalizationType.FALLING_EDGE;
		} else {
			// DO ULTRASONIC SENSOR WITH RISING EDGE
			type = USLocalizer.LocalizationType.RISING_EDGE;
		}

		t.clear();
		LCDInfo lcd = new LCDInfo(odometer);

		// DO US LOCALIZATION 
		Navigation navigator = new Navigation(odometer);

		USLocalizer usl = new USLocalizer(odometer, usValue, usData, type);
		usl.doLocalization(navigator);
		

		// WAIT FOR ESCAPE BUTTON TO BE PRESSED FOR NEXT STEP, THEN PERFORM LIGHT LOCALIZATION
		 LightLocalizer lsl = new LightLocalizer(odometer, colorValue, colorData);
		while (Button.waitForAnyPress() != Button.ID_ESCAPE);
		lsl.doLocalization(navigator);

		while (Button.waitForAnyPress() != Button.ID_ESCAPE);
		System.exit(0);

	}

}
