
public class Portfolio {

	public float price;
	public int qty, numberOfTransactions, TransactionID;
	public String companyName;
	public boolean type;
	public boolean isSelling;
	public int companyNumber;


		public Portfolio()
		{
			companyName = "";
			price = 0;
			qty = 0;
			companyNumber = 0;
			isSelling = true;
		}
		
		
		public Portfolio(String compName, float pr, int quant, boolean type, int compNum)
		{
			companyName = compName;
			price = pr;
			qty = quant;
			companyNumber = compNum;
			isSelling = type;
		}
		
		
		public String getCompanyName(){
				return companyName;
		}
		
		public int getQuantity(){
			return qty;
		}
		
		public float getPrice(){
			return price;
		}
		
		public boolean isSelling(){
			return isSelling;
		}
		
		public void setCompanyName(String name){
			companyName = name;
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
}
