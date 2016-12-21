
import java.awt.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class transactionPanel extends JPanel implements Constants{
    
    
    String investorName;
    
    int investorCounter=0, companyCounter = 0;
    
    JLabel titles[] = new JLabel[6];
    
    
   public  static   boolean[] isSelling = new boolean[3];
    
 public static   JLabel transactionNumber[] = new JLabel[3];
    
    JLabel companyName[] = new JLabel[3];
    
  public static  JLabel totalPrice[] = new JLabel[3];
    
    JRadioButton buyType[] = new JRadioButton[3];
    
    JRadioButton sellType[] = new JRadioButton[3];
    
    ButtonGroup group[] = new ButtonGroup[3];
    
   public static JTextField quantity[] = new JTextField[3];
    
  public static   JTextField unitPrice[] = new JTextField[3];
    
    JPanel panel = new JPanel();
    
    JPanel[] panels = new JPanel[investorCounter];
    
    JPanel mainPanel= new JPanel(new GridLayout(1,6,5,5));
    JPanel titlesPanel = new JPanel(new GridLayout(1,6,5,5));
    JPanel TTpanel = new JPanel(new GridLayout(3,1,5,5));
    JPanel compPanel = new JPanel(new GridLayout(3,1,5,5));
    JPanel totalPanel = new JPanel(new GridLayout(3,1,5,5));
    JPanel qtyPanel = new JPanel(new GridLayout(3,1,5,5));
    JPanel pricePanel = new JPanel(new GridLayout(3,1,5,5));
    JPanel numbersPanel = new JPanel(new GridLayout(3,1,5,5));
    JPanel buttonPanel = new JPanel(new GridLayout(3,1,5,5));
    
   NextButtonListener textListener = new NextButtonListener();
   ButtonListener bl = new ButtonListener();
    
    
    int[] actInv = new int[3];
    int[] actComp = new int[9];
  
    
    final boolean testInvestor[] = {false, true, true, false, true};
    final boolean testCompanies1[] =  {false, true, true, false, true};
            							 ;
    
   
            							 
            							 
            							 
            							 
    public transactionPanel(int InvestorNumber)  //String nameInvestor, boolean[5] listCompanies
    {	
        panel.setLayout(new BorderLayout());
        
        for(int i = 0; i <=5; i++)
        {
            switch(i){
                case 0: titles[i] = new JLabel("Transaction #"); break;
                case 1: titles[i] = new JLabel("Company Name"); break;
                case 2: titles[i] = new JLabel("Type"); break;
                case 3: titles[i] = new JLabel("Quantity"); break;
                case 4: titles[i] = new JLabel("Unit Price"); break;
                case 5: titles[i] = new JLabel("Total"); break;
            }
            titlesPanel.add(titles[i]);
        }
        
        
     /**   for(int k = 0; k < testInvestor.length; k++){
        	
        
        	if(testInvestor[k] == true)
        	{	
        		investorLabels[investorCounter] = new JLabel(InvestorNames[k]);
        		System.out.println(InvestorNames[k]);
        		actInv[investorCounter] = k;
        		investorCounter++;    		
        	}	
        }
       
        */
       
        	
   
        		
        		for(int a = 0; a < 5; ++a)
        		{		
			if(selectionPanel.companiesBoolean[InvestorNumber][a]==true)
				{
					actComp[companyCounter]=a;
					companyName[companyCounter] = new JLabel(CompanyNames[a]);
					compPanel.add(companyName[companyCounter]);
					++companyCounter;

				}      	
			}
        
		
			
			
			for(int a = 1; a<=companyCounter;++a)
				{	
					numbersPanel.add(new JLabel(Integer.toString(a)));
				}
				

				
				JPanel[] botones = new JPanel[3];

			for(int a = 0; a < companyCounter; ++a)
			{
				buyType[a] = new JRadioButton("B", true);
				sellType[a] = new JRadioButton("S", false);
				
				ButtonGroup type = new ButtonGroup();
				
			//	group[a].add(buyType[a]);
			//	group[a].add(sellType[a]);
				 
				botones[a] = new JPanel(new GridLayout(1,2));
	            type.add(buyType[a]);
	            type.add(sellType[a]);
	           
	            if(a == 0)
	            {
	            sellType[a].addActionListener(new RadioButtonListener1());
		    buyType[a].addActionListener(new RadioButtonListener1());
		    }
		    
		    if(a == 1)
	            {
	            sellType[a].addActionListener(new RadioButtonListener2());
		    buyType[a].addActionListener(new RadioButtonListener2());
		    }
		    
		    
		   if(a == 2)
	            {
	            sellType[a].addActionListener(new RadioButtonListener3());
		    buyType[a].addActionListener(new RadioButtonListener3());
		    }
		    
		    
		    
				botones[a].add(buyType[a]);
				botones[a].add(sellType[a]);
				buttonPanel.add(botones[a]);
		
					
  					quantity[a] = new JTextField(10);
				    qtyPanel.add(quantity[a]);
				    unitPrice[a] = new JTextField(10);
				    pricePanel.add(unitPrice[a]);
				    totalPrice[a] = new JLabel("--");
				    
				    totalPanel.add(totalPrice[a]);
						
					   quantity[a].addKeyListener(textListener);
					   unitPrice[a].addKeyListener(textListener);
				
				
			}
				


			panel.add(titlesPanel, BorderLayout.NORTH);
			mainPanel.add(numbersPanel);
			mainPanel.add(compPanel);
			  mainPanel.add(buttonPanel);
			  mainPanel.add(qtyPanel);
			  mainPanel.add(pricePanel);
			  mainPanel.add(totalPanel);
			
		
					panel.add(mainPanel, BorderLayout.CENTER);

        add(panel);

        
        
     
        }
        
    }
     
        

    
    class NextButtonListener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            
            for(int i=0; i<=2; i++)
            {
                Transactions transaction = new Transactions();
                

                try
                {
                    
                    transaction.setQty(Integer.parseInt(transactionPanel.quantity[i].getText()));
                    transactionPanel.totalPrice[i].setText(Float.toString(transaction.getQty() * transaction.getPrice()));
                    System.out.println(Float.parseFloat(transactionPanel.unitPrice[i].getText()));
                    transaction.setPrice(Float.parseFloat(transactionPanel.unitPrice[i].getText()));
                    transactionPanel.totalPrice[i].setText(Float.toString(transaction.getQty() * transaction.getPrice()));
                }
                
                catch(NumberFormatException e)
                {
                    transactionPanel.totalPrice[i].setText("--");
                }
            }
        }
    }
    
    
    class RadioButtonListener1 implements ActionListener{
     
   
     
        public void actionPerformed(ActionEvent e) {
            JRadioButton btn = (JRadioButton) e.getSource();
           if(btn.getText() == "B")
           {
           	   transactionPanel.isSelling[0] = false; 
           }
           else
              transactionPanel.isSelling[0] = true;
        
        }
    }
 
     
       class RadioButtonListener2 implements ActionListener{
     
   
     
        public void actionPerformed(ActionEvent e) {
            JRadioButton btn = (JRadioButton) e.getSource();
           if(btn.getText() == "B")
           {
           	   transactionPanel.isSelling[1] = false;
           }
           else
           	transactionPanel.isSelling[1] = true;
        
        }
    }
 
     class RadioButtonListener3 implements ActionListener{
     
   
     
        public void actionPerformed(ActionEvent e) {
            JRadioButton btn = (JRadioButton) e.getSource();
           if(btn.getText() == "B")
           {
           	   transactionPanel.isSelling[2] = false;
           }
           else
           	   transactionPanel.isSelling[2] = true;
           	 

        
        }
    }
    
    
    

    
 

