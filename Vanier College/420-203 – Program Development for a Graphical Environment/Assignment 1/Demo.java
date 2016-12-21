/**
*	This class generates at random a number between 250 and 500 to 
*	initialize the balance amount of this given bank account. This balance 
*	amount is set only once and it is on for the entire session.the application 
*	will generate at random a number between 250 and 500 to initialize the balance
*	amount of this given bank account. This balance amount is set only once and it is 
*	on for the entire session.
*
*
**/


import java.util.Random;

public class Demo {
	
		 public static void main(String[] args) {
	

		 float withdrawAmount, depositAmount;
		 int  numberOfTransactions = 0, typeOfTransaction;
		 Random r = new Random();
		 Account account = new Account(r.nextInt(250) + 250);
		 
		 
	  
		 
		while(numberOfTransactions<5)
		{
			typeOfTransaction = r.nextInt(4) + 1; 
			System.out.println("\n" + typeOfTransaction);
			switch(typeOfTransaction) 
			{
			case 1: //balance
				Balance balance = new Balance();
				System.out.println(balance.transactionInformation(account, 0));
				System.out.println(balance.endTransactionMessage(account, 0));
				break;
				
			case 2: // deposit
				depositAmount = account.getBalance() * ( (float)(r.nextInt(50) + 25) /100) ;
				Deposit deposit = new Deposit();
				System.out.print(deposit.transactionInformation(account, depositAmount));       	
				System.out.print(deposit.endTransactionMessage(account, depositAmount));
				break;
				
			case 3: // withdraw
				
				withdrawAmount = account.getBalance() * ( (float)(r.nextInt(50) + 75) /100);
				Withdraw withdraw  = new Withdraw();
				System.out.print(withdraw.transactionInformation(account, withdrawAmount));       	
				System.out.print(withdraw.endTransactionMessage(account, withdrawAmount));
				break;
				
			case 4: //salir
				numberOfTransactions=7;
				System.out.println("You chose to exit the program. ");
				break;
				
			}
			++numberOfTransactions;
			if(numberOfTransactions == 5 )
				{
					System.out.println("The maximum number of transactions has been reached");
				}
	
		
		}
		
	} 
}
