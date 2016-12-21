
public class Transactions implements Constants {

	public float price, profit;
	public int qty, numberOfTransactions, TransactionID;
	public String companyName, InvestorName;
	public boolean isSelling;
	public int companyNumber;


	public Transactions()
	{
		InvestorName = "";
		companyName = "";
		price = 0;
		qty = 0;
		companyNumber = 0;
		isSelling = true;
		InvestorName = "";
		profit = 0;
	}
	
	
	public Transactions(String invName, String compName, float pr, int quant, boolean type, int compNum, float prof)
	{
		InvestorName = invName;
		companyName = compName;
		price = pr;
		qty = quant;
		companyNumber = compNum;
		isSelling = type;
		profit = prof; 
	}
	
	public String getCompanyName(){
		return companyName;
	}
	
	public float getPrice(){
		return price;
	}
	
	public boolean getIsSelling(){
		return isSelling;
	}
	
	public void setCompanyName(String name){
		companyName = name;
	}
	
	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}

	
	public void setIsSelling(boolean type){
		isSelling = type;
	}
	
	public void setTransactionID(int num){
		TransactionID = num;
	}
	
	public void setNumberOfTransactions(int num){
		numberOfTransactions = num;
	}


	public float getProfit() {
		return profit;
	}


	public void setProfit(float profit) {
		this.profit = profit;
	}


	public String getInvestorName() {
		return InvestorName;
	}


	public void setInvestorName(String investorName) {
		InvestorName = investorName;
	}


	public void setPrice(float price) {
		this.price = price;
	}
	

}
