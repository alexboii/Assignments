import java.util.*;


/**
 * Assignment #3 - 24/03/2013 
 * 
 * The demo program which allows the user to interact with the methods defined in 
 * the ContactList class. 
 * 
 * @author Alexander Bratyshkin
 *
 */
public class ContactListDemo
{
	public static char choice;
	static Scanner keyboard = new Scanner(System.in);
	
	public static void main (String[] args)
	{
		System.out.println("Contacts list Application Program");
		System.out.println("");
		ContactList menu = new ContactList();
		
		ContactList.menuString();
		System.out.println("");
		System.out.print("Command: ");
		while(true)
		{
		choice = keyboard.next().charAt(0);
		menu.menu(choice);
		}
	}
}