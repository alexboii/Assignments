// WROTE AN ABSTRACT CLASS INSTEAD OF AN INTERACE, DOESN'T MAKE SENSE TO USE AN ACTUAL INTERFACE
// BECAUSE OF MAX_POINTS, WHICH WOULD HAVE TO BE DEFINE IN EVERY SINGLE OF THE CHILD CLASSES

public abstract class AbstractPoints {
	
	protected static final double ZERO = 0;

	private double max_points;

	public AbstractPoints(double max_points) {
		this.max_points = max_points;
	}

	public double getMaxPoints() {
		return this.max_points;
	}

	public void setMaxPoints(double max_points) {
		this.max_points = max_points;

	}
	
	public abstract double pointsEarned(Purchase purchase);

}

