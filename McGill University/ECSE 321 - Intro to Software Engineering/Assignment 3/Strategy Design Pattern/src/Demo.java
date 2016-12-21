// Alexander Bratyshkin
// 260684228

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Demo {

	private static final int TWO_DECIMALS = 100;

	public static void main(String[] args) {
		double rate, threshold, max_points, purchase_total;
		String client_name, points_type;
		boolean run_program = true;

		// FORMATTING USED FOR DISPLAY THE PURCHASE TOTAL LATER ON
		DecimalFormat f = new DecimalFormat("##.00");

		System.out.println("Welcome to the Strategy Design Pattern Demo!");

		while (run_program) {
			AbstractPoints Points = null;

			Scanner scan = new Scanner(System.in);

			System.out.println("\nPlease enter your desired rate per points: ");
			try {
				rate = scan.nextDouble();
			} catch (Exception e) {
				printError();
				continue;
			}

			System.out.println("Please enter the desired maximum amount of points: ");
			try {
				max_points = scan.nextDouble();
			} catch (Exception e) {
				printError();
				continue;
			}

			System.out.println("Please enter the client's name: ");
			scan.nextLine();
			client_name = scan.nextLine();

			System.out.println("Please enter F for flat rate, P for per-dollar, "
					+ "and T for threshold: ");
			points_type = null;
			points_type = scan.nextLine();

			if (points_type.equalsIgnoreCase("F")) {
				Points = new FlatRatePoints(max_points, rate);
			} else if (points_type.equalsIgnoreCase("P")) {
				Points = new PerDollarPoints(max_points, rate);
			} else if (points_type.equalsIgnoreCase("T")) {
				System.out.println("Please enter your desired threshold: ");
				threshold = scan.nextDouble();
				Points = new ThresholdPoints(max_points, threshold, rate);
			} else {
				printError();
				continue;
			}

			System.out.println("Please enter the total of the purchase: ");
			try {
				purchase_total = scan.nextDouble();
			} catch (Exception e) {
				printError();
				continue;
			}

			Purchase purchase = new Purchase(client_name, Date.valueOf(LocalDate.now()), purchase_total);

			System.out.println("Hello, " + purchase.getClient_name() + "\nYou have made a transaction on "
					+ String.valueOf(purchase.getPurchase_date()) + " for a total of $"
					+ f.format(purchase.getPurchase_total()) + " \nYou have earned a total of "
					+ Math.round(Points.pointsEarned(purchase)) + " points\n");

			System.out.println("Do you want to run the code again? (Y/N)");
			String run = scan.next();

			run_program = (run.equalsIgnoreCase("N")) ? (false) : (true);

		}

		System.exit(0);

	}

	public static void printError() {
		System.out.println("You have entered an invalid input! Restarting the program");
	}

}
