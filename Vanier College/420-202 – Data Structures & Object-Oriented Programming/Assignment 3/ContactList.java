import java.util.*;


/**
 * Assignment #3 - 24/03/2013 
 * This class provides users with a set of methods which allow them to do several manipulations, from adding new contacts, to editing them, to removing them.
 * I have chosen my list to be a LinkedList, because it allows me to have efficient removal in between elements or at the start (since there is a remove method)
 * with the help of iterators, and also because we don't need to have any random access to elements. By using it, however, I would be giving up some performance
 * due to the fact that LinkedList uses small memory objects, but the program that we had to write consumes very little resources anyways, so the deficit shouldn't
 * be noticeable at all.  
 * 
 * @author Alexander Bratyshkin
 *
 */
public class ContactList {
	
	private String temp;

	Scanner keyboard = new Scanner(System.in);
	
	LinkedList<Contact> list = new LinkedList<Contact>();
	
	/**
	 * Checks whether the array is empty or not to
	 * prevent further manipulations of other methods. 
	 * @return Empty or not. 
	 */
	public boolean isEmpty(){
		
		if(list.size() == 0) { 
			print("You have not entered contacts yet");
			return true;
			
		}
		
		return false;
		
	}
	
	/**
	 * Through the help of a regular expression, method checks if
	 * first name, last name or phone matches the search query. 
	 * @param i Counter
	 * @return Match or not. 
	 */
	public boolean matches(int i) { 
		String match = "(?i:.*" + temp + ".*)"; // Regular expression that looks for case insensitive input regardless of position in string. 
		
		if(list.get(i).getfName().matches(match) || list.get(i).getlName().matches(match) || list.get(i).getPhone().matches(match)) { 
			return true; 
		} 
		
		else { 
			return false; 
		}  
		
	} 
	
	/**
	 * Prints desired display. 
	 * @param str String.
	 */
	public static void print(String str){
		System.out.println(str);
	}
	
	/**
	 * Accepts desired input from user and sets it to
	 * temporary string value. 
	 * @param keyboard
	 */
	public void input(Scanner keyboard){
		this.temp = keyboard.nextLine();
	}
	
	/**
	 * Displays the menu with choices of 
	 * manipulations to the user.  
	 */
	public static void menuString (){
		print("");
		print("Enter a command letter from the following menu:");
		print("");
		print("add (a), look up (l), size (s), edit (e), print (p), remove (r), menu (m), quit (q).");
	}
	
	/**
	 * Takes the user's desired choice from the menu
	 * and executes the corresponding method. 
	 * @param choice User's choice.
	 */
	public void menu(char choice){
		
		Character.toLowerCase(choice);
		
		switch(choice)
		{
		case 'a': add(); break;
		case 'e': edit(); break;
		case 'l': search(); break; 
		case 's': size(); break;
		case 'p': printList(); break;
		case 'r': remove(); break;
		case 'm': menuString(); break;
		case 'q': System.exit(0); break;
		default: 
			print("Invalid input.");
			menuString();
			break;	
		}
		
		print("\nCommand: ");
		
	}
	
	/**
	 * Removes a contact that corresponds to the user's 
	 * entered query. It offers the user the capacity to 
	 * remove the contact that matches the best the user's
	 * entry.
	 */
	public void remove() {
		
		if(isEmpty());
		else{
			
		print("Remove what? ");
		input(keyboard);

		boolean noMatch = true;
		

		for(int i = 0; i < list.size(); i++)
		{
		
			if(matches(i)) 
			{
				noMatch = false;
				print("");
				print("Are you sure you want to remove this contact?");
				print("" + list.get(i));
				print("Enter y for yes or any other character for no: ");
				input(keyboard);
				
				if(temp.equals("y"))
				{
					list.remove(i);
					
				} 
				
				else{
					print("Contact [" + list.get(i) +"] has not been removed.");
					print("");
				
			}		
				
		if(noMatch) print("No contacts matched your search pattern " + temp);
		
		}
		}
	}
		
		
	}

	/**
	 * Prints all the contacts entered by the user.
	 */
	public void printList() {
		
		if (isEmpty());
		else{
			for(int i = 0; i < list.size() ; ++i)
			{
				print("\t" + list.get(i));
			}
		}
		
	}

	/**
	 * Displays the total number of contacts in the 
	 * list to the user.
	 */
	public void size() {
		
		print("Number of contacts: " + list.size());
		
	}

	/**
	 * Allows the user to search through the list
	 * for a specific contact. The search will be based 
	 * on the contact that matches the best the query
	 * entered by the user.
	 */
	public void search() {
		
		if(isEmpty());
		
		else{
			
			print("Enter a search query: ");
			input(keyboard);
			temp = "(?i:.*" + temp + ".*)";
		
		for(int i = 0; i < list.size(); i++){
			print("" + list.get(i));
			}	
		
		}
		
	}
	
	

	/**
	 * This method first offers the user the possibility
	 * to enter a desired query, which will then run through a
	 * search pattern (with the help of regular expressions) 
	 * and provide the user with the best matching contacts.
	 * Then, the user can edit the information of the contact.
	 * If the user leaves the strings empty, then the options
	 * remain unedited. 
	 */
	public void edit() {
		
		if(isEmpty());
		
		
		else{	
		System.out.print("What would you like to edit?: ");
		input(keyboard);
		
		boolean noMatch = true;

		for(int i = 0; i < list.size(); i++)
			{

				if(matches(i)) 
				{
					noMatch = false;
					print("");
					print("Are you sure you want to edit THIS contact?");
					print("" + list.get(i));
					print("Enter y for yes or any other character for no: ");
					input(keyboard);
					
					if(temp.equalsIgnoreCase("y"))
					{
						print("Enter first name: ");
						input(keyboard);
						if(!(temp.isEmpty() || temp.equals("")))		
							list.get(i).setfName(temp);
		
						print("Enter last name: ");
						input(keyboard); 
						if(!(temp.isEmpty() || temp.equals("")))		
							list.get(i).setlName(temp);
						
						print("Enter phone: ");
						input(keyboard);
						if(temp.matches("[0-9]+") && temp.length() > 6 && temp.length() < 16)
							list.get(i).setPhone(temp); 
						
						
					} else
						print("Contact (" + list.get(i) +") is NOT edited.");
						print("");
					
				}		
			
			}
		
			if(noMatch) print("No contacts matched your search pattern " + temp);
		}
		
		
		
	}

	/**
	 * Adds a new contact to the list.
	 * It does not allow empty strings, and the
	 * phone # can only be between 7 and 15 digits.
	 */
	
	public void add() {
		
		String fName, lName, phone;
		
		do{
			print("Enter first name: ");
			input(keyboard);
			}
		while(temp.isEmpty() || temp.equals(""));   
			fName = temp;
			
		do{
			print("Enter last name: ");
			input(keyboard);     
			}
		while(temp.isEmpty() || temp.equals(""));
			lName = temp;

		do{
			print("Enter a phone number: ");
			input(keyboard);
			
			if(!(temp.matches("[0-9]+") && temp.length() > 6 && temp.length() < 16)){
				print(temp + " is not a valid phone number.");
				print("A valid phone number is a number with at least 7 and at most 15 digits");
				print("");
			}
			
			}
		while(!(temp.matches("[0-9]+") && temp.length() > 6 && temp.length() < 16));
			phone = temp;
			
		list.add(new Contact(fName, lName, phone));
		
	}
		
	}
	
