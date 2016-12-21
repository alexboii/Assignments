

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class selectionPanel extends JPanel implements Constants{
	
    GridLayout grid = new GridLayout(1,3,5,5);
    GridLayout grid2 = new GridLayout(6,1,5,5);
    GridLayout grid3 = new GridLayout(6,2,5,5);
    GridLayout grid4 = new GridLayout(1,2,5,5);

    JPanel CheckBoxPanel = new JPanel();
    JPanel InvestorsPanel = new JPanel();
    JPanel CompaniesPanel = new JPanel();
    JPanel cp1 = new JPanel(grid2);
    JPanel cp2 = new JPanel(grid4); 
    
    JPanel NextButtonPanel = new JPanel();
    
    JLabel investorsLabel = new JLabel("Investors");
    JLabel companiesLabel = new JLabel("Companies");
   
    JPanel card = new JPanel(new CardLayout());
   
    JButton save = new JButton("Save");
    static JCheckBox investors[] = new JCheckBox[5];
    static JCheckBox companies[][] = new JCheckBox[5][5];
    
    	static boolean[] investorsBoolean = new boolean[5];
	static boolean[][] companiesBoolean = new boolean[5][5];
    
    
    int numberOfInvestors = 0;
    int numberOfCompanies = 0;
    
    
    public selectionPanel()
    {
    	this.setLayout(new BorderLayout());
    	CheckBoxPanel.setLayout(new BorderLayout(90,25));
    	CompaniesPanel.setLayout(new BorderLayout(10,25));
    	InvestorsPanel.setLayout(grid3);
    	
    	Listener listener = new Listener();
    	Listener2 listener2 = new Listener2();
    	
        
        
        cp2.add(investorsLabel);
        cp2.add(companiesLabel);


        for(int i = 0; i<investors.length; i++)
            {
                investors[i] = new JCheckBox(InvestorNames[i]);
                InvestorsPanel.add(investors[i]);
                investors[i].addItemListener(listener);
                CheckBoxPanel.add(InvestorsPanel, BorderLayout.WEST);
            }
        
            
            CheckBoxPanel.add(cp2,  BorderLayout.NORTH);
            CompaniesPanel.add(cp1,  BorderLayout.CENTER);

        for(int j = 0; j<companies.length; j++)
        {
            for(int k = 0; k<companies[j].length; k++)
            {
                companies[j][k] = new JCheckBox(CompanyNames[k]);
                companies[j][k].addItemListener(listener2);
                companies[j][k].setEnabled(false);
                cp1.add(companies[j][k]);
                CheckBoxPanel.add(CompaniesPanel);
            }
            
        }
        save.addActionListener(new ButtonListener());
        NextButtonPanel.add(save);
        card.add(CheckBoxPanel);
        
        this.add(card, BorderLayout.CENTER);
        this.add(NextButtonPanel, BorderLayout.EAST);
    
    }
    
    /** Allows the selection of a maximum of 3 investors **/
    
    static class Listener implements ItemListener {


        private int selectionCounter = 0;    
        private int selectionCounterC= 0; 

        @Override
        public void itemStateChanged(ItemEvent e) {
            JCheckBox source = (JCheckBox) e.getSource();
            
            	if (source.isSelected()) {
            		
            		for(int i = 0; i < investors.length; i++){	
            		
            			if (investors[i].isSelected() == true) {
            				selectionCounterC = 0;            			 
            				for(int k = 0; k < companies.length; k++){
            					if (companies[i][k].isSelected() == true)
            					{
            						selectionCounterC++;   						
            					}
            					if (selectionCounterC==3) {
            						companies[i][k].setEnabled(false);	
            					}
            					}
            				if (selectionCounterC<3) 
            					for(int k = 0; k < companies.length; k++)
            					{
        						companies[i][k].setEnabled(true);	
            					}
            				   		}     			
            			
            		}
                selectionCounter++;
                if (selectionCounter == MAX_SELECTIONS)
                    for (JCheckBox box: investors){
                        if (!box.isSelected()){
 	
                            box.setEnabled(false);
                        }
                    }
            }
            else {
                selectionCounter--;
                for(int i = 0; i < investors.length; i++){	
                	
                if (investors[i].isSelected() == false) {
        			
    				for(int k = 0; k < investors.length; k++){
            			companies[i][k].setEnabled(false);	
            			companies[i][k].setSelected(false);	
    				}

    				}
                }
                if (selectionCounter < MAX_SELECTIONS)
                    for (JCheckBox box: investors)
                        box.setEnabled(true);
            }
        }
        
    }
    
    /** Allows the selection of a maximum of 3 companies per investor**/
    
      static class Listener2 implements ItemListener{
			int investorsCounter = 0;
			int companiesCounter = 0;
			
		@Override
		public void itemStateChanged(ItemEvent e) {
			
	            investorsCounter = 0;

	            for (int i = 0; i < investors.length; i++) {
	                if (investors[i].isSelected()) {
	                    investorsCounter++;

	                    for (JCheckBox box : companies[i]) {
	                        box.setEnabled(true);
	                    }
	                } 
	                else {
	                    for (JCheckBox box : companies[i]) {
	                        box.setEnabled(false);
	                    }
	                }

	                if (investorsCounter == MAX_SELECTIONS) {
	                    for (int j = 0; j < investors.length; j++) {
	                        if (!investors[j].isSelected()) {
	                            investors[j].setEnabled(false);
	                            for (JCheckBox box : companies[j]) {
	                                box.setEnabled(false);
	                                box.setSelected(false);
	                            }
	                        }
	                    }
	                }
	            }

	            if (investorsCounter < MAX_SELECTIONS) {
	                for (JCheckBox box : investors) {
	                    box.setEnabled(true);
	                }

	            }

	            for (JCheckBox[] box : companies) {
	                companiesCounter = 0;

	                for (JCheckBox box1 : box) {
	                    if (box1.isSelected()) {
	                        companiesCounter++;
	                    }
	                }

	                if (companiesCounter == MAX_SELECTIONS) {
	                    for (JCheckBox box2 : box) {
	                        if (!box2.isSelected()) {
	                            box2.setEnabled(false);
	                        }
	                    }
	                }
	            }
	}
			
}


      /** Defines two arrays, one for investors, one for companies
       * to see where exactly a Checkbox is selected and where it is not**/
      
      static class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < investors.length; i++){
					
				investorsBoolean[i] = investors[i].isSelected();
				
				for(int k = 0; k < companies.length; k++){
					
					companiesBoolean[i][k] = companies[i][k].isSelected();
								
				}
			}
			
			for(int i = 0; i < investors.length; i++)
			System.out.println(investorsBoolean[i]);		
		}
}


}

