package wallFollower;

import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class PController implements UltrasonicController {

	private final int bandCenter, bandwidth;

	private EV3LargeRegulatedMotor leftMotor, rightMotor;
	private int distance;
	private int filterControl;
	
	//CONSTANTS 
	
	private final int motorStraight = 130, FILTER_OUT = 50;
	private final int BANDCENTER_CONSTANT = 7, BANDWIDTH_CONSTANT = 2;
	private final int MAX_DISTANCE = 255;
	private final int DELTA_CONSTANT = 6;
	private final int MIN_SPEED = 0;
	private final int MAX_LEFT_SPEED = 250;
	private final int MAX_RIGHT_SPEED = 270;
	private final int LOW_SPEED = 50;

	public PController(EV3LargeRegulatedMotor leftMotor, EV3LargeRegulatedMotor rightMotor, int bandCenter,
			int bandwidth) {
		// Default Constructor
		this.bandCenter = bandCenter + BANDCENTER_CONSTANT;
		this.bandwidth = bandwidth - BANDWIDTH_CONSTANT;
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
		leftMotor.setSpeed(motorStraight); // Initalize motor rolling forward
		rightMotor.setSpeed(motorStraight);
		leftMotor.forward();
		rightMotor.forward();
		filterControl = 0;
	}

	@Override
	public void processUSData(int distance) {

		// rudimentary filter - toss out invalid samples corresponding to null
		// signal.
		// (n.b. this was not included in the Bang-bang controller, but easily
		// could have).
		//
		
		//MAKE SURE THAT ALL VALUES ABOVE 255 ARE READ AS 255 
		distance = (distance > MAX_DISTANCE) ? MAX_DISTANCE: distance; 
		
		if (distance == MAX_DISTANCE && filterControl < FILTER_OUT) {
			// bad value, do not set the distance var, however do increment the
			// filter value
			filterControl++;
			this.leftMotor.setSpeed(motorStraight);
			this.rightMotor.setSpeed(motorStraight);
			this.leftMotor.forward();
			this.rightMotor.forward();
			System.out.println(filterControl);
			return;
		} else if (distance == MAX_DISTANCE) {
			// true MAX_DISTANCE, therefore set distance to MAX_DISTANCE
			this.distance = distance;
		} else {
			// distance went below MAX_DISTANCE, therefore reset everything.
			filterControl = 0;
			this.distance = distance;
		}
		
		//CALCULATION OF THE ADJUSTMENT CONSTANT 
		int delta =  DELTA_CONSTANT * (((Math.abs(distance - this.bandCenter) - this.bandwidth) < 0)? 0 :  (Math.abs(distance - this.bandCenter) - this.bandwidth));
		
		// delta = (Math.abs(delta) > 200)?(int)Math.signum(delta) * 150 : delta;
		
		//ADJUSTMENT OF THE SPEED OF THE WHEELS BASED ON THE CALCULATED ADJUSTMENT CONSTANT DELTA
		int leftAdjustedSpeed = motorStraight + ((distance < this.bandCenter - this.bandwidth)? +delta : -delta);
		int rightAdjustedSpeed = motorStraight + ((distance < this.bandCenter - this.bandwidth)? -delta : +delta);
		
		//WE MAKE SURE THAT THE SPEED OF THE WHEELS DOES NOT EXCEED A DEFINED MIN AND MAX 
		if (leftAdjustedSpeed < MIN_SPEED) leftAdjustedSpeed = MIN_SPEED;
		if (rightAdjustedSpeed < MIN_SPEED) rightAdjustedSpeed = MIN_SPEED;
		if (leftAdjustedSpeed > MAX_LEFT_SPEED) leftAdjustedSpeed = MAX_LEFT_SPEED;
		if (rightAdjustedSpeed > MAX_RIGHT_SPEED) rightAdjustedSpeed = MAX_RIGHT_SPEED;
		
		//SET UP THE SPEED OF THE WHEELS BASED ON THE ADJUSTMENTS ABOVE 
		this.leftMotor.setSpeed((leftAdjustedSpeed < MIN_SPEED)? LOW_SPEED: leftAdjustedSpeed);
		this.rightMotor.setSpeed((rightAdjustedSpeed < MIN_SPEED)? LOW_SPEED : rightAdjustedSpeed);
		this.leftMotor.forward();
		this.rightMotor.forward();
		
		// TODO: process a movement based on the us distance passed in (P style)
	}

	@Override
	public int readUSDistance() {
		return this.distance;
	}

}
 