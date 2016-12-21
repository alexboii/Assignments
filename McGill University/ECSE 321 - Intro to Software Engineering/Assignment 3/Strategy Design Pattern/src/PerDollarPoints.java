
public class PerDollarPoints extends AbstractPoints {

	
	double rate;

	public PerDollarPoints(double max_points, double rate) {
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
		double points = this.getRate() * purchase.getPurchase_total();
		
		points = (points > ZERO) ? (points) : (ZERO);
		points = (points <= getMaxPoints()) ? (points) : (getMaxPoints());
		
		return points;
	}
	
	

}
