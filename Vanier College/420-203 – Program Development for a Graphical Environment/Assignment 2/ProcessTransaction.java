
import java.util.ArrayList;

public class ProcessTransaction implements Constants {

    protected ArrayList<Transactions> validList;
    protected ArrayList<Transactions> invalidList;
    protected int InvalidTransactionType;
    
	public int getInvalidTransactionType() {
		return InvalidTransactionType;
	}

	public void setInvalidTransactionType(int invalidTransactionType) {
		InvalidTransactionType = invalidTransactionType;
	}

	public ArrayList<Transactions> getValidList() {
		return validList;
	}
	
	public void setValidList(ArrayList<Transactions> validList) {
		this.validList = validList;
	}
	
	public ArrayList<Transactions> getInvalidList() {
		return invalidList;
	}
	
	public void setInvalidList(ArrayList<Transactions> invalidList) {
		this.invalidList = invalidList;
	}
	
	public void evaluteTransaction(Transactions trans, int InvestorNumber, int CompanyNumber){
		
		if(trans.getIsSelling())
		{
			if(mainPortfolio[InvestorNumber][CompanyNumber].getQuantity() != 0 )
			{
				if(trans.getQty() > 10 || trans.getQty() < 100 || 
			       mainPortfolio[InvestorNumber][CompanyNumber].getQuantity() < trans.getQty()){
				
					if(trans.getPrice() > 10 || trans.getPrice() < 100 || 
					   mainPortfolio[InvestorNumber][CompanyNumber].getQuantity() < trans.getPrice()){
						validList.add(trans);
					}else{
						setInvalidTransactionType(3);
						invalidList.add(trans);
					}
				}else{
				setInvalidTransactionType(2);
				invalidList.add(trans);
				}
			}else{
				setInvalidTransactionType(1);
				invalidList.add(trans);
			}
		
		}else{
			
				if(trans.getQty() > 10 || 
			       (mainPortfolio[InvestorNumber][CompanyNumber].getQuantity() + trans.getQty()) < 100){
				
					if(trans.getPrice() > 10 || trans.getPrice() < 100 || 
					   mainPortfolio[InvestorNumber][CompanyNumber].getQuantity() < trans.getPrice()){
						validList.add(trans);
					}else{
						setInvalidTransactionType(3);
						invalidList.add(trans);
					}
				}else{
				setInvalidTransactionType(2);
				invalidList.add(trans);
				}
			}		
		}
    }
	

