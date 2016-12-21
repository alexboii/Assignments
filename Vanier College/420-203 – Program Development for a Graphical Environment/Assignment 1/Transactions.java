/**
*	Transactions interface linked to classes Deposit & Withdraw
*
*/


public interface Transactions {

	boolean validateTransaction(Account balance, float amount);
	void performOperation(Account balance, float amount);
	String transactionInformation(Account balance, float depositAmount);
	
}
