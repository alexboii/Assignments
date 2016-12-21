import java.util.Scanner;


public class ReverseMethod {

	public static void main(String[] args)
	{
		
		while(true){
			
			System.out.println("\nEnter your string: ");
			
			Scanner keyboard = new Scanner(System.in);
			
			String input = keyboard.nextLine();
			System.out.println("");
		
			if(input.equals(""))
			{
				System.out.println("Program terminated. ");
				System.exit(0);
			}
			else{
				System.out.println("Input expression: " + input);
				System.out.println("Reveresed expression: " + reverse(input));
			}
	}
		
	}
	
	public static String reverse(String input){
		
	    if (input.length() == 0) 
	        return input;
	    
	    return reverse(input.substring(1)) + input.charAt(0);
	}
	
}
