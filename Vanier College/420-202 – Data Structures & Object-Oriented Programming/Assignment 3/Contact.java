
/**
 * Assignment #3 - 24/03/2013 
 * 
 * A class which allows you to create a contact. 
 * A contact is defined by a first name, a last name and a phone number. 
 * 
 * @author Alexander Bratyshkin
 *
 */
public class Contact {

	String fName, lName, phone;
	
	public Contact(String fName, String lName, String phone) {
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
	}
	
	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return  fName + ", " + lName + ", "
				+ phone;
	}
		
}
