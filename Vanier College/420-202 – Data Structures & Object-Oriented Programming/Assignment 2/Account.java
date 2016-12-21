/**
 * Assignment #2 - Due date: 04/03/2014 - Account Class
 * The Account class creates a bank account for costumers.
 * A bank account is associated to a unique owner with a corresponding account number and balance.
 * Each customer has their own account number, starting from 1001. 
 * A bank account provides each user with a set of possible operations that can be performed,
 * from withdrawals to transfers from or to other accounts. 
 * Finally, the class provides an abstract PerformMonthEndProcessing() method which has to be implemented
 * in its sub-classes.
 * 
 * @author Alexander Bratyshkin
 *
 */

public abstract class Account {

	private static int counter=1; // Counter initialized to 0.
	private int accNumber; // Unique account number assigned to each customer.
	private Customer owner; // Name, phone and email of the customer.
	protected double balance; // Balance available in the account. 
	
	/**
	 * Constructor which accepts the costumer's information provided by the driver as well 
	 * as the account's initial balance, which then is set to be the balance of the actual account.
	 * A unique account number is also assigned to each account.
	 * @param owner The customer's information.
	 * @param initialBalance The initial balance provided. 
	 */
	public Account(Customer owner, double initialBalance){
		
		this.owner = owner; 
		accNumber = counter + 1000;
		++counter;
		this.balance = initialBalance; 
	
	}
	
	/**
	 * Constructor which only accepts the customer's information.
	 * @param owner The customer's information.
	 */
	public Account(Customer owner){
		this(owner, 0.0);
	
	}
	
	/**
	 * Copy constructor. 
	 * @param acc Account. 
	 */
	public Account(Account acc){
		this(acc.owner, acc.balance);
	
	}
	
	/**
	 * Gets the information of the owner (name, phone, email). 
	 * @return The customer's information. 
	 */
	public Customer getOwner(){
		return owner;
	}
	
	/**
	 * Sets the information of the owner (name, phone, email). 
	 * @param owner The customer's information. 
	 */
	public void setOwner(Customer owner){
		this.owner = owner;
	}
	
	/**
	 * Gets the current balance of given owner.
	 * @return Balance.
	 */
	public double getBalance(){
		return balance;
	}
	
	/**
	 * Sets a balance for a given owner.
	 * @param balance New balance. 
	 * @return Operation performed or not. 
	 */
	public boolean setBalance(double balance){
		if(balance < 0){
			System.out.print("You have entered an invalid amount!\n" + getAccNumber() + " remains unchanged\n");
			return false;
		}
		
		this.balance = balance;
		return true; 
		
	}
	
	/**
	 * Gets the account number for given customer. 
	 * @return Customer's account number. 
	 */
	public int getAccNumber(){
		return accNumber;
	}
	
	/**
	 * Deposits a provided amount to a given account.  
	 * @param transferAmt Amount provided. 
	 * @return Operation performed or not. 
	 */
	public boolean deposit(double transferAmt){
		if (transferAmt < 0){
			System.out.print("You have entered an invalid amount!\n" + getAccNumber() + " remains unchanged\n");
			return false;
		}
		
			balance += transferAmt; 
			return true;
	}
	
	/**
	 * Withdraws a provided amount from a given account.  
	 * @param withdrawAmt
	 * @return Operation performed or not. 
	 */
	public boolean withdraw(double withdrawAmt){
		if (withdrawAmt < 0 || withdrawAmt > balance){
			System.out.print("You have entered an invalid amount!\n" + getAccNumber() + " remains unchanged\n");
			return false;
		}
		
			balance -= withdrawAmt; 
			return true;	
	}
	
	/**
	 * Transfers a provided amount to a given account. 
	 * @param transferAmt Amount provided. 
	 * @param acc Copy account. 
	 * @return Operation performed or not. 
	 */
	public boolean transferTo(double transferAmt, Account acc){
		if (transferAmt < 0 || transferAmt > this.balance ){
			System.out.println("You have entered an invalid amount! \nAccount " + getAccNumber() + " remains unchanged\n");
			return false;
		}
		
			double i = transferAmt;
			this.balance -= i;
			acc.balance += i;
			return true;
	}
	
	/**
	 * Transfers a provided amount from a given account. 
	 * @param transferAmt Amount provided.
	 * @param acc Copy account. 
	 * @return Operation performed or not. 
	 */
	public boolean transferFrom(double transferAmt, Account acc){
		if (transferAmt < 0 || transferAmt > acc.balance ){
			System.out.println("You have entered an invalid amount! \nAccount " + getAccNumber() + " remains unchanged\n");
			return false;
		}
		
			double i = transferAmt;
			this.balance += i;
			acc.balance -= i;
			return true;
	}
	
	/* Partially displays the profile of the customer.
	 * (name, phone, email, account number and balance)
	 * Overrides the toString() method from class Object.
	 * @return Customer's profile.
	 */
	public String toString(){
		return this.owner.toString() + "\n" + "Account No.: " + getAccNumber() + "\n" + "Balance: " + getBalance();
	}

	/* 
	 * Checks the account to see if they are equal. 
	 * Two accounts are considered equal of they have the same account number. 
	 * @return Equal or not.  
	 */
	public boolean equals(Object obj){
		if(obj == null) return false; 
		if(obj instanceof Account) return false; 
		
		Account a = (Account)obj; 
		
		return a.accNumber == this.accNumber;
	}
	
	/**
	 * Since the method is abstract, its behavior must be defined
	 * in the subclasses CheckingAccount and Savings account. 
	 * @see CheckingAccount#PerformMonthEndProcessing()
	 * @see SavingsAccount#PerformMonthEndProcessing()
	 */
	abstract public void PerformMonthEndProcessing();

}
	
	
