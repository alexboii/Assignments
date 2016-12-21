/**
*
* 	For any balance transaction, this application will retrieve and display to
*	the screen the current balance amount using the output format.
*
**/


public class Balance extends Operations{

	@Override
	/**
	*	This methods wishes the user a nice day.
	*	
	*	@param	Account	 account 	The account being used.
	*	@param 	float 	 amount		This parameter is not used.
	*/
	protected String endTransactionMessage(Account account, float amount) {
		return "Have a nice day! \n";
	}

	@Override
	/**
	*	Prints the balance of the account.
	*
	*	@param	Account	 account 	The account being used.
	*	@param 	float 	 amount		This parameter is not used.
	**/
	protected String transactionInformation(Account account, float amount) {
		return ("Balance: $" +  String.format("%.2f", account.getBalance())) ;
	}

	
}
