
/*
 * File: Navigation.java
 * Written by: Sean Lawlor
 * ECSE 211 - Design Principles and Methods, Head TA
 * Fall 2011
 * Ported to EV3 by: Francois Ouellet Delorme
 * Fall 2015
 * 
 * Movement control class (turnTo, travelTo, flt, localize)
 */
import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class Navigation {
	final static int FAST = 200, SLOW = 100, ACCELERATION = 4000;
	final static double DEG_ERR = 3.0, CM_ERR = 1.0;
	private Odometer odometer;
	private EV3LargeRegulatedMotor leftMotor, rightMotor;
	private USPoller usPoller;
	private EV3LargeRegulatedMotor usMotor;

	public static final int ROTATE_SPEED = 50;
	private static final int BAND_WIDTH = 15;
	private static final double WALL_DISTANCE = 15;
	private static final int ANGLE_LIMIT = 110;
	private static final int BB_FAST_SPEED = 260;
	private static final int BB_SLOW_SPEED = 115;
	private static final int BB_OFFSET = 3;
	private static final int RIGHT_ANGLE = 90;

	public Navigation(Odometer odo, USPoller usPoller, EV3LargeRegulatedMotor usMotor) {
		this.odometer = odo;
		this.usPoller = usPoller;
		this.usMotor = usMotor;

		EV3LargeRegulatedMotor[] motors = this.odometer.getMotors();
		this.leftMotor = motors[0];
		this.rightMotor = motors[1];

		// set acceleration
		this.leftMotor.setAcceleration(ACCELERATION);
		this.rightMotor.setAcceleration(ACCELERATION);
	}

	/*
	 * Functions to set the motor speeds jointly
	 */
	public void setSpeeds(float lSpd, float rSpd) {
		this.leftMotor.setSpeed(lSpd);
		this.rightMotor.setSpeed(rSpd);
		if (lSpd < 0)
			this.leftMotor.backward();
		else
			this.leftMotor.forward();
		if (rSpd < 0)
			this.rightMotor.backward();
		else
			this.rightMotor.forward();
	}

	public void setSpeeds(int lSpd, int rSpd) {
		this.leftMotor.setSpeed(lSpd);
		this.rightMotor.setSpeed(rSpd);
		if (lSpd < 0)
			this.leftMotor.backward();
		else
			this.leftMotor.forward();
		if (rSpd < 0)
			this.rightMotor.backward();
		else
			this.rightMotor.forward();
	}

	/*
	 * Float the two motors jointly
	 */
	public void setFloat() {
		this.leftMotor.stop();
		this.rightMotor.stop();
		this.leftMotor.flt(true);
		this.rightMotor.flt(true);
	}

	/*
	 * TravelTo function which takes as arguments the x and y position in cm
	 * Will travel to designated position, while constantly updating it's
	 * heading
	 */
	public void travelTo(double x, double y, boolean avoidance) {
		double minAng;
		while (Math.abs(x - odometer.getX()) > CM_ERR || Math.abs(y - odometer.getY()) > CM_ERR) {
			minAng = (Math.atan2(y - odometer.getY(), x - odometer.getX())) * (180.0 / Math.PI);
			if (minAng < 0)
				minAng += 360.0;
			this.turnTo(minAng, false);
			this.setSpeeds(FAST, FAST);

			// IF THE SENSOR DETECTS AN OBJECT
			if (usPoller.getDistance() < WALL_DISTANCE && avoidance) {
				// STOP MOTORS
				this.setSpeeds(0, 0);

				this.turnTo(odometer.getTheta() + RIGHT_ANGLE, true);
				usMotor.rotateTo(80);
				double lastTheta = odometer.getTheta();

				// ENTER INTO BANG BANG CONTROLLER MODE
				wallfollower_loop: while (true) {

					int bang_bang_error = (int) (usPoller.getDistance() - BB_OFFSET);

					// ROBOT IS AT THE RIGHT DISTANCE FROM WALL, KEEP
					// GOING
					if (Math.abs(bang_bang_error) <= BAND_WIDTH) {
						this.setSpeeds(FAST, FAST);
					}

					// ROBOT IS TOO FAR FROM THE WALL - INCREASE OUTSIDE
					// WHEEL, DECREASE
					// INSIDE WHEEL
					if (bang_bang_error > BAND_WIDTH) {
						this.setSpeeds(BB_FAST_SPEED , BB_SLOW_SPEED );
					}

					// ROBOT IS TOO CLOSE TO WALL - DECRASE OUTISDE INCREASE INSIDE
					if (bang_bang_error < BAND_WIDTH) {
						this.setSpeeds(BB_SLOW_SPEED , BB_FAST_SPEED );
					}

					// IF THE ROBOT HAS SAFELY "HALF-CONTOURED" THE
					// WALL, BREAK OUT OF LOOP
					if (Math.abs(odometer.getTheta() - lastTheta) > ANGLE_LIMIT) {
						// ROTATE SENSOR BACK TO ORIGINAL POSITION
						// STOP MOTORS
						this.setSpeeds(0, 0);
						usMotor.rotateTo(0);
						break wallfollower_loop;
					}

				}
			}
		}
		this.setSpeeds(0, 0);
	}

	/*
	 * TurnTo function which takes an angle and boolean as arguments The boolean
	 * controls whether or not to stop the motors when the turn is completed
	 */
	public void turnTo(double angle, boolean stop) {

		double error = angle - this.odometer.getTheta();

		while (Math.abs(error) > DEG_ERR) {

			error = angle - this.odometer.getTheta();

			if (error < -180.0) {
				this.setSpeeds(-SLOW, SLOW);
			} else if (error < 0.0) {
				this.setSpeeds(SLOW, -SLOW);
			} else if (error > 180.0) {
				this.setSpeeds(SLOW, -SLOW);
			} else {
				this.setSpeeds(-SLOW, SLOW);
			}
		}

		if (stop) {
			this.setSpeeds(0, 0);
		}
	}

	/*
	 * Go foward a set distance in cm
	 */
	// public void goForward(double distance) {
	// this.travelTo(Math.cos(Math.toRadians(this.odometer.getTheta())) *
	// distance, Math.cos(Math.toRadians(this.odometer.getTheta())) * distance);
	//
	// }

	public void goForward(double distance, boolean avoidance) {
		this.travelTo(odometer.getX() + Math.cos(Math.toRadians(this.odometer.getTheta())) * distance,
				odometer.getY() + Math.sin(Math.toRadians(this.odometer.getTheta())) * distance, avoidance);

	}

	public EV3LargeRegulatedMotor getLeftMotor() {
		return leftMotor;
	}

	public void setLeftMotor(EV3LargeRegulatedMotor leftMotor) {
		this.leftMotor = leftMotor;
	}

	public EV3LargeRegulatedMotor getRightMotor() {
		return rightMotor;
	}

	public void setRightMotor(EV3LargeRegulatedMotor rightMotor) {
		this.rightMotor = rightMotor;
	}
}
