import java.util.Scanner;


public class isMember {

	public static void main(String[] args) {
		
			
		System.out.println("\nEnter size of your list: ");
		Scanner keyboard = new Scanner(System.in);
		int size = keyboard.nextInt();
		
		int[] list = new int[size]; 
		
		for(int k = 0; k < list.length; ++k){
			System.out.println("\nEnter a number for the position # " + k + " :");
			list[k] = keyboard.nextInt();
		}
		
		System.out.println("\nEnter your search query: ");
		int input = keyboard.nextInt();
		System.out.println("");
		
		try{
		isMembers(list, input, size);
		}
		catch(Exception ex){
			System.out.println("Program terminated. ");
			System.exit(0);
		}


	}
	
	public static boolean isMembers(int[] list, int input, int size){
		
		
		while(size >= 0 && size <= list.length){
			
			if(isMembers(list, input, size - 1) == false){
				
				if(input == list[size] && size <= list.length){
					System.out.println("Input found at position " + size);
					System.out.println("Program terminated. ");
					return true;
					
					}
				else{
					System.out.println("No results at position " + size);
					return false;
				}

			}else{
				return true;
			}
			
		}
		
		return false;
	}

}
