import java.util.Scanner;


public class RecursiveMultiplication {
	
	public static void main(String[] args)
	{
		while(true){
			
			System.out.println("\nEnter your desired number:");
			
			Scanner keyboard = new Scanner(System.in);
			
			int number = keyboard.nextInt();
			
			System.out.println("Enter the amount of times you want add " + number + " by itself");
			
			int times = keyboard.nextInt();
			
			int total = Multiplication(number, times);
			
			System.out.println("Result: " + total);

		}

	}
	
	private static int Multiplication(int number, int times) {
		
		int total;
		if(times == 1)
			total = number;
		else
			total = number + Multiplication(number, times-1);
		return total;
	}
		

}
