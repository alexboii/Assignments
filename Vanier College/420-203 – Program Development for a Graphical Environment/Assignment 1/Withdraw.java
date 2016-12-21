/**
*	For any withdraw transaction, this application will generate at random a transaction amount 
*	(or a number, which is seen as the input amount to withdraw) between 75% and 125% out of the
*	current balance amount. Withdraw transaction is valid if the transaction amount is not greater
*	than the current balance amount else it is invalid. If the transaction is valid, use the formula 
*
*		New balance = Balance amount - Withdraw amount
*
*	to compute the new balance amount. Thereafter, set the results and the text following these results
*	for displaying the output accordingly regardless whether the transaction was valid or not.
*
**/






public class Withdraw extends Operations implements Transactions {

	@Override
	/**
	*	This method returns true if the amount to withdraw is positive and
	*	less than or equal to the balance. Else, returns false.
	*	@param Account	 account	The account being used.
	*	@param float     amount		The amount to be withdrawn.
	*/
	public boolean validateTransaction(Account account, float amount) {

			if(amount >= 0 && amount <= (account.getBalance())){
				return true;
			}
			else{
				return false;
			}
			
	}

	@Override
	/**
	*	This method performs the withdraw. It is called if 
	*	validateTransaction returns true. 
	*	@param Account	account	 The account being used.
	*	@param float amount	The amount to be withdrawn.
	**/
	public void performOperation(Account account, float amount) {
		account.setBalance(account.getBalance() - amount); 
		
	}

	@Override
	/**
	*	This methods explains the result of validateTransaction. If the amount to withdraw
	*	is greater than the balance, the transaction is invalid. Otherwise, is valid.
	*
	*	@param Account	 account	 The account being used.
	*	@param float	 amount		 The amount to be withdrawn.	
	*/
	protected String endTransactionMessage(Account account, float amount) {
		String str;
		
		if (validateTransaction(account, amount))
		{
			str = "\nTransaction is valid because $" +  String.format("%.2f", amount) + " <= $" +  String.format("%.2f", account.getBalance());
			performOperation(account, amount);
		}
		else{
				str = "\nTransaction is invalid because $" + String.format("%.2f", amount) + " > $" +  String.format("%.2f", account.getBalance());

		}
		return ( str + "\n Have a nice day! \n");
	}

	@Override
	/**
	*	Prints the current balance, the amount to be withdrawn and the balance after the operation.
	*		
	*	@param	Account	 account	 The account being used.
	*	@param 	float	 amount		 The amount to be withdrawn.
	**/
	public String transactionInformation(Account account, float amount) {
				
		String str = " \nBalance amount: $" + String.format("%.2f", account.getBalance())
					+ "\nWithdraw amount: $" + String.format("%.2f", amount);
		
		if(validateTransaction(account, amount)){
			return ( str + "\nNew Balance: $" + String.format("%.2f",(account.getBalance() - amount)));
		} else {
			return ( str + "\nNew Balance: $" + String.format("%.2f",(account.getBalance())));
		}
	}
}
