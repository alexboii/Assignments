/**
*	Deposit transaction is valid if the transaction amount is a positive number
*	less than or equals to 50% out of the current balance amount else the 
*	transaction is invalid. If the transaction is valid, use the formula 
*
*		New balance = Balance amount + Deposit amount
*
*	to compute the new balance amount.
*	Thereafter, set the results and the text following these results for displaying
*	the output accordingly regardless whether the transaction was valid or not.
*
*/


public class Deposit extends Operations implements Transactions {

	@Override
	/**
	*	This method returns true if the amount to deposit is positive and
	*	less than or equal to half the balance. Else, returns false.
	*	@param Account	 account	The account being used.
	*	@param float     amount		The amount to be deposit.
	*/
	
	public boolean validateTransaction(Account account, float amount) {

			if(amount >= 0 && amount <= (account.getBalance() * 0.50)){
				return true;
			}
			else{
				return false;
			}
			
	}

	@Override
	/**
	*	This method performs the deposit. It is called if 
	*	validateTransaction returns true. 
	*	@param Account	account	 The account being used.
	*	@param float amount	The amount to be deposit.
	**/
	
	public void performOperation(Account account, float amount) {
		account.setBalance(account.getBalance() + amount); 
		
	}

	@Override
	/**
	*	This methods explains the result of validateTransaction. If the amount to deposit
	*	is greater than half the balance, the transaction is invalid. Otherwise, is valid.
	*
	*	@param Account	 account	 The account being used.
	*	@param float	 amount		 The amount to be deposit.	
	*/
	
	protected String endTransactionMessage(Account account, float amount) {
		String str;
		if (validateTransaction(account, amount))
		{
			str = "\nTransaction is valid because   " +  String.format("%.2f", amount) + " <= $" +  String.format("%.2f", (account.getBalance() * 0.5));
			performOperation(account, amount);
		}
		else{
				str = "\nTransaction is invalid because $" +   String.format("%.2f", amount) + " > $" +  String.format("%.2f", (account.getBalance() * 0.5));

		}

		return ( str + "\n Have a nice day! \n");
		
	}

	@Override
	/**
	*	Prints the current balance, the amount to be deposit and the balance after the operation.
	*		
	*	@param	Account	 account	 The account being used.
	*	@param 	float	 amount		 The amount to be deposit.
	**/
	
	public String transactionInformation(Account account, float amount) {
		
		String str = " \nBalance amount: $" +  String.format("%.2f", account.getBalance())
					+ "\nDeposit amount: $" +  String.format("%.2f", amount) ;
		
		if(validateTransaction(account, amount)){
			return ( str + "\nNew Balance: $" +  String.format("%.2f", (account.getBalance() + amount)));
		} else {
			return ( str + "\nNew Balance: $" +  String.format("%.2f", (account.getBalance())));
		}
		}
	}

