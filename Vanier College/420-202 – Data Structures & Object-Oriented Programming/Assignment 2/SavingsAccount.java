/**
 * Assignment #2 - Due date: 04/03/2014 - Savings Account Class
 * The Checking Account class is extended by the super-class Account, which is abstract.
 * An account of type savings offers free deposit and withdraw transactions and earns 
 * interest from the lowest amount that is record in the account balance during a month. 
 * The annual interest rate is assigned when a savings account is constructed.
 * 
 * @author Alexander Bratyshkin
 *
 */

public class SavingsAccount extends Account {
	
	private double interestRate, // The interest rate, which will be provided by driver.
				   lowestBalance; // Variable that stores the lowest balance recorded over the course of a month. 

	private static String type = "Savings";
	
	/**
	 * Creates an account of type savings for a given customer, with assigned
	 * initial balance and an annual interest rate. If the interest rate is negative, 
	 * it is automatically set to zero. 
	 * Also automatically equates the initial balance to the lowest balance. 
	 * @param owner Customer's information.
	 * @param interestRate Given interest rate.
	 * @param initialBalance Given initial balance. 
	 */
	public SavingsAccount(Customer owner, double interestRate, double initialBalance) {
		
		super(owner, initialBalance);

		if(interestRate < 0.0)
		{
			interestRate = 0.0;
		}
		
		else
		{
			this.interestRate = interestRate;
		}
		
		lowestBalance = initialBalance;
		

	}
	
	/**
	 * Creates an account of type savings for a customer that does not
	 * provide an initial balance nor an interest rate. 
	 * @param owner Customer's information.
	 */
	public SavingsAccount(Customer owner)
	{
		this(owner, 0.0, 0.0);
	}

	/**
	 * Creates an account of type savings for a customer that does not
	 * provide an initial balance. 
	 * @param owner Customer's information.
	 * @param interestRate Given interest rate. 
	 */
	public SavingsAccount(Customer owner, double interestRate)
	{
		this(owner, interestRate, 0.0);
	}

	
	/**
	 * This method is used to record the lowest balance of an account during
	 * a given month, and is summoned everytime a transaction is made.
	 */
	private void recordLowestBalance()
	{
		if(this.lowestBalance > this.getBalance() || this.lowestBalance <= 0)
		{
			this.lowestBalance = this.getBalance();
		}
	
		
	}
	
	/**
	 * Calculates the interest that is applied to the lowest balance recorded
	 * during the month.  
	 * @return The interest. 
	 */
	public double calculateInterest()
	{
		return getLowestBalance() * (interestRate/12/100);
	}

	/* (non-Javadoc)
	 * @see Account#setBalance(double)
	 */
	@Override
	public boolean setBalance(double balance) {
		
		if (!super.setBalance(balance)) return false;
		
		recordLowestBalance();
		return true;
		
	}

	/* (non-Javadoc)
	 * @see Account#deposit(double)
	 */
	@Override
	public boolean deposit(double transferAmt) {
		
		if (!super.deposit(transferAmt)) return false;
		
		recordLowestBalance();
		return true;
	}

	/* (non-Javadoc)
	 * @see Account#withdraw(double)
	 */
	@Override
	public boolean withdraw(double withdrawAmt) {
		
		if (!super.withdraw(withdrawAmt)) return false;
		
		recordLowestBalance();
		return true;
		
	}

	/* (non-Javadoc)
	 * @see Account#transferTo(double, Account)
	 */
	@Override
	public boolean transferTo(double transferAmt, Account acc) {
		if(!super.transferTo(transferAmt, acc)) return false; 
		
		recordLowestBalance();
		return true; 
	}

	/**
	 * Gets the interest rate for a given account. 
	 * @return The interest rate. 
	 */
	public double getInterestRate() {
		return interestRate;
	}
	
	/**
	 * Gets the lowest balance recorded in the account for a month.
	 * @return The lowest balance. 
	 */
	public double getLowestBalance() {
		
		return lowestBalance;
	}

	/**
	 * Sets the lowest balance recorded in the account for a month.
	 * @param lowestBalance The lowest balance provided.
	 */
	public void setLowestBalance(double lowestBalance) {
		this.lowestBalance = lowestBalance;
	}
	
	/* 
	 * Displays the full profile of the customer 
	 * (name, phone, email, account number, balance, account type and interest rate).
	 * Overrides from class Object. 
	 * @return Customer's profile.
	 */
	public String toString(){
		return super.toString() + "\n" + "Account type: " + type + "\n" + "Interest rate: " + (getInterestRate()/12) + "%";
	
	}

	/* 
	 * Applies the interest on the lowest recorded balance of the month.
	 */
	public void PerformMonthEndProcessing() {
		this.setBalance(this.getBalance() + calculateInterest()); 
		
	}

}
