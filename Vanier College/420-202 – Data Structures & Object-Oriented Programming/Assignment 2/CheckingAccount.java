/**
 * Assignment #2 - Due date: 04/03/2014 - Checking Account Class
 * The Checking Account class is extended by the super-class Account, which is abstract.
 * Therefore, the implementation of the method PerformMonthEndProcessing() is necessary. 
 * In this case, the savings account does not earn an interest, and allows a maximum of four free withdrawals.
 * Afterwards, a fee of 1$ is applied after the end of the month for each additional withdrawal.
 * 
 * @author Alexander Bratyshkin
 *
 */
public class CheckingAccount extends Account {
	
	private static String type = "Checking"; // A static field which specifies the account type in the toString() method.
	private static int fee = 1, // Fee applied after free transactions expire, which is equal to 1$
					   maxFreeTrans = 4; // The maximum amount of free transactions that a user is allowed to have per month.
	private int numTransactions, // A counter which keeps track of the number of transactions done since the creation of the account.
				monthlyWithdraws; // A counter which keeps track of the number of transactions done since the creation of the account.
	
	/**
	 * Constructor that sends the information of the client to the superclass.
	 * Sets the value of the counters to 0 for each new account. 
	 * @param owner	Name, phone and email of the customer.
	 * @param initialBalance The initial balance set by the client.
	 */
	public CheckingAccount(Customer owner, double initialBalance){
		
		super(owner, initialBalance);
		
		setNumTransactions(0);
		setMonthlyWithdraws(0);
		
	}
	
	/**
	 * Copy constructor, which accepts the information of the client only.
	 * @param owner Name, phone and email of the customer.
	 */
	public CheckingAccount(Customer owner){
		
		super(owner, 0.0);
		
	}

	/* 
	 * This method performs the end-of-month processing of the class.
	 * The method checks if the number of withdrawals that have been made in a month exceeds the amount
	 * of free transactions available to the user. 
	 * If it does, then a fee is applied. 
	 * Otherwise, the method reminds the user to use his free transactions next time.
	 * 
	 */
	public void PerformMonthEndProcessing(){
		
		if(monthlyWithdraws > maxFreeTrans)
		{
			// We must ensure that a withdrawal is indeed being performed, otherwise there is no point in resetting the counter
			// of withdraws performed in a month
			if(this.withdraw((monthlyWithdraws - maxFreeTrans)*fee)){ 
				
				monthlyWithdraws = 0;
				
				}
			
			else{
				
				System.out.println("Cannot perform end of month processing! No money in account #" + getAccNumber());
			
			}
		}
		else{
			
			System.out.println("You still had " + (maxFreeTrans - monthlyWithdraws) + "free withdrawls remaining this month.\nMake sure to use them next time, it's on the house!");
			monthlyWithdraws = 0;
			
		}

	}
	
	/* (non-Javadoc)
	 * @see Account#withdraw(double)
	 */
	@Override
	public boolean withdraw(double dAmt){
		if (!super.withdraw(dAmt)) return false;
		
		++numTransactions; 
		++monthlyWithdraws;

		return true;
	}
	
	/* 
	 * Displays the full profile of the customer 
	 * (name, phone, email, account number, balance, account type and number 
	 * of transactions made by the customer).
	 * Overrides from class Object. 
	 * @return Customer's profile.
	 */
	public String toString(){
		return new String(super.toString() + "\n" + "Account type: " + type + "\n" 
						  + "Transactions since last end-of-month processing: " + getMonthlyWithdraws() 
						  + "\nTransactions since creation of the account: " + getNumTransactions());
	
	}


	/**
	 * Gets the total number of transactions done since creation of account.
	 * @return The total number of transactions.
	 */
	public int getNumTransactions() {
		
		return numTransactions;
		
	}

	/**
	 * Sets the total number of transactions done since creation of account.
	 * @param numTransactions The number of transactions effectuated. 
	 */
	public void setNumTransactions(int numTransactions){

		this.numTransactions = numTransactions;
	
	}

	/**
	 * Gets the total number of transactions done since the last end-of-month processing.
	 * @return The number of monthly withdraws effectuated.
	 */
	public int getMonthlyWithdraws() {
		
		return monthlyWithdraws;
	}

	/**
	 * Sets the total number of transactions done since the last end-of-month processing.
	 * @param monthlyWithdraws The number of monthly withdraws effectuated.
	 */
	public void setMonthlyWithdraws(int monthlyWithdraws) {
		
		this.monthlyWithdraws = monthlyWithdraws;
		
	} 
 
}
