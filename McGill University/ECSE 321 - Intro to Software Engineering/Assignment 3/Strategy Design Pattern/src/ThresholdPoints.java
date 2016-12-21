
public class ThresholdPoints extends AbstractPoints {

	double rate, threshold;

	public ThresholdPoints(double max_points, double threshold, double rate) {
		super(max_points);
		this.rate = rate;
		this.threshold = threshold;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
	
	@Override
	public double pointsEarned(Purchase purchase) {
		double points = 0;

		if (purchase.getPurchase_total() >= this.getThreshold()) {
			points = this.getRate() * purchase.getPurchase_total();
			points = (points <= getMaxPoints()) ? (points) : (getMaxPoints());
		}

		return points;
	}
	

}
