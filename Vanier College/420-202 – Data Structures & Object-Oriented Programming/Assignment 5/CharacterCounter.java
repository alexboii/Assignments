import java.util.Scanner;

public class CharacterCounter {

	public static void main(String[] args) {

		System.out.println("\nEnter size of your list: ");
		Scanner keyboard = new Scanner(System.in);
		int size = keyboard.nextInt();

		char[] list = new char[size];

		for (int k = 0; k < list.length; ++k) {
			System.out.println("\nEnter a name for the position # " + k + " :");
			list[k] = keyboard.next().charAt(0);
		}

		System.out.println("\nEnter your search query: ");
		char input = keyboard.next().charAt(0);
		System.out.println("");


		int times = isMembers(list, input, size);
		System.out.println("The characters is in the list " + times);

	}

	public static int isMembers(char[] list, char input, int size) {
		int temp = 0;
			if (isMembers(list, input, size - 1) <= temp) {

				if (input == list[size] && size <= list.length) {
					System.out.println("Input found at position " + size);
					System.out.println("Program terminated. ");
					temp++;
				}
				
				return temp;
				
			}

			return temp;

}
}
