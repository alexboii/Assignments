
public class FlatRatePoints extends AbstractPoints {

	double rate;

	public FlatRatePoints(double max_points, double rate) {
		super(max_points);
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public double pointsEarned(Purchase purchase) {
		double points = 0;

		if (purchase.getPurchase_total() > ZERO) {
			points = this.getRate() < getMaxPoints() ? this.getRate() : getMaxPoints();
		}

		return points;
	}

}
