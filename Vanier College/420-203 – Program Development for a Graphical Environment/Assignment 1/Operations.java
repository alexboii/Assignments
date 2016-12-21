
/**
*	Abstract class which is the parent class of Deposit, Withdraw & Balance.
*	
**/

public abstract class Operations {
	
	Operations(){

	}
	
	protected abstract String endTransactionMessage(Account balance, float amount);
	protected abstract String transactionInformation(Account balance, float amount);
	
}
