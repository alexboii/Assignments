package wallFollower;

import lejos.hardware.motor.*;

public class BangBangController implements UltrasonicController {
	private final int bandCenter, bandwidth;
	private final int motorLow, motorHigh;

	private int distance;
	private EV3LargeRegulatedMotor leftMotor, rightMotor, touchMotor;

	// CONSTANTS

	private final int delta = 175;
	private final int FILTER_OUT = 7;
	private final int MAX_DISTANCE = 255;
	private final int LOW_CONSTANT = 15;
	private final int TIMES_TWO = 2;

	private int filterControl;

	public BangBangController(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor,
			EV3LargeRegulatedMotor touchMotor, int bandCenter, int bandwidth, int motorLow, int motorHigh) {
		// Default Constructor
		this.bandCenter = bandCenter;
		this.bandwidth = bandwidth;
		this.touchMotor = touchMotor;
		this.motorLow = motorLow;
		this.motorHigh = motorHigh;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		leftMotor.setSpeed(motorHigh); // Start robot moving forward
		rightMotor.setSpeed(motorHigh);

		filterControl = 0;
	}

	@Override
	public void processUSData(int distance) {
		this.distance = distance;

		// CALCULATE THE ERROR
		int error = distance - this.bandCenter;

		// IMPLEMENT FILTER CONTROL TO REMOVE AS MANY FALSE NEGATIVES AS
		// POSSIBLE
		if (distance == MAX_DISTANCE) {
			if (filterControl < FILTER_OUT) {
				error = 0;
			}
			filterControl++;
		} else {
			filterControl = 0;
		}

		// THE ERROR IS WITHIN THRESHOLD, THEREFORE IT IS IN THE RIGHT PATH
		if (Math.abs(error) <= this.bandwidth) {
			leftMotor.setSpeed(motorHigh);
			rightMotor.setSpeed(motorHigh);
			leftMotor.forward();
			rightMotor.forward();
		}

		// ROBOT IS TOO FAR FROM THE WALL - INCREASE OUTSIDE WHEEL, DECREASE
		// INSIDE WHEEL
		if (error > this.bandwidth) {
			leftMotor.setSpeed(motorLow + LOW_CONSTANT);
			rightMotor.setSpeed(motorHigh);
			leftMotor.forward();
			rightMotor.forward();
		}

		// ROBOT IS TOO FAR FROM THE WALL - DECREASE OUTSIDE WHEEL, INCREASE
		// INSIDE WHEEL
		if (error < this.bandwidth) {
			leftMotor.setSpeed(motorHigh + delta);
			rightMotor.setSpeed(motorHigh - delta * TIMES_TWO);
			leftMotor.forward();
			rightMotor.forward();
		}

		// ATTEMPT TO IMPLEMENT ROTATING SENSOR, BUT REALIZED THAT PUTTING IT TO
		// A 45 DEGREE ANGLE WAS MUCH MORE EFFICIENT
		// touchMotor.rotateTo(90);
		// touchMotor.rotateTo(0);

	}

	@Override
	public int readUSDistance() {
		return this.distance;
	}
}
