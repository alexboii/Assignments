import java.util.Scanner;


public class Part1Demo {

	public static void main (String args[])
	{
		
		Partone m = new Partone();
		
		while(true){
			
		System.out.println("Enter an expression or press Enter to exit:");
		
		Scanner keyboard = new Scanner(System.in);
		
		String temp = keyboard.nextLine();
	
	
		if(temp.equals(""))
		{
			System.exit(0);
		}
		else{
			System.out.println("Input expression: " + temp);
			m.check(temp);
		}
		
		}
	}
}
