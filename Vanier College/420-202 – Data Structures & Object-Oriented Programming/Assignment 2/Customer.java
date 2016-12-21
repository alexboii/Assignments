
public class Customer {
	
	private String name, phone, email;

	/**
	 * Assigns a name, a phone number and an email to a customer. 
	 * @param name Customer's name.
	 * @param phone Customer's phone.
	 * @param email Customer's e-mail. 
	 */
	public Customer(String name, String phone, String email){
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	/**
	 * Copy constructor. 
	 * @param cust Customer. 
	 */
	public Customer(Customer cust){
		this(cust.name, cust.phone, cust.email);

	}

	/**
	 * Gets the name of the customer.
	 * @return Customer's name.
	 */
	public String getName(){
		return this.name;

	}

	/**
	 * Gets the phone of the custumer.
	 * @return Customer's phone.
	 */
	public String getPhone(){
		return this.phone;

	}

	/**
	 * Gets the email of the customer. 
	 * @return Customer's email. 
	 */
	public String getEmail(){
		return this.email;

	}

	/** 
	 * Sets the name of the customer.
	 * @param name Customer's name. 
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Sets the name of the phone.
	 * @param phone Customer's phone.
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}

	/**
	 * Sets the e-mail of the customer.
	 * @param email
	 */
	public void setEmail(String email){
		this.email = email;
	}

	/* Partially displays the profile of the customer.
	 * (name, phone, email)
	 * Overrides the toString() method from class Object.
	 * @return Customer's profile.
	 */
	public String toString(){
		return "\nName: " + getName() + "\n" + "Phone: " + getPhone() + "\n" + "Email: " + getEmail();
		
	}

	/* 
	 * Checks the account numbers to see if two customers are equal.
	 * Two customers are considered equal if they have the same name and same phone.
	 * @return Equal or not.  
	 */
	
	public boolean equals(Object obj){
		if(obj == null) return false; 
		if(obj instanceof Customer) return false; 
		
		Customer c = (Customer)obj; 
		
		return c.name.equals(this.name) && c.phone.equals(this.phone);
 
	}

}
