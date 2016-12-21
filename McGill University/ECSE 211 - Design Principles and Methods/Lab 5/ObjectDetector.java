import lejos.hardware.Sound;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;

public class ObjectDetector extends Thread {

	private final double COLOR_SENSOR_THERSHOLD = 10; 
	private final double DISTANCE_THRESHOLD = 20; 
	private TextLCD t;
	private LSPoller lsPoller;
	private USPoller usPoller;

	// constructor
	public ObjectDetector(USPoller usPoller, LSPoller lsPoller, TextLCD t) {
		this.usPoller = usPoller;
		this.lsPoller = lsPoller;
		this.t = t;
	}

	//
	public void run() {
		while (true) {

			t.clear();

			while (usPoller.getDistance() < DISTANCE_THRESHOLD) {
				t.drawString("Object Detected", 0, 0);

				// IF OBJECT WITHIN THRESHOLD AND BLUE, IT'S A BLOCK
				if (usPoller.getDistance() <= COLOR_SENSOR_THERSHOLD && lsPoller.isBlue() == true) {
					t.clear(1);
					t.drawString("Blue block", 0, 1);
				} else {
					// OTHERWISE, IT IS A WOODEN BLOCK
					if (usPoller.getDistance() <= DISTANCE_THRESHOLD) {
						t.clear(1);
						t.drawString("Wooden block", 0, 1);
					} else {
						t.clear(1);
					}
				}
			}
			
			// NO OBJECT IN SIGHT
			t.drawString("No Object", 0, 0);
		}
	}
}
