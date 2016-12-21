

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


	
	public class transactionReportPanel extends JPanel{
	    
	    JButton Exit = new JButton("Exit");
	    JLabel vTransLabel = new JLabel("Valid Transactions");
	    JLabel invTransLabel = new JLabel("Invalid Transaction");
	    JPanel vTranPanel = new JPanel();
	    JPanel invTranPanel = new JPanel();
	    JPanel exitPanel = new JPanel();
	    JPanel labelPanelValidNest = new JPanel();
	    JPanel labelPanelInvalidNest = new JPanel();
	    JPanel labelPanelValid = new JPanel();
	    JPanel labelPanelInvalid = new JPanel();
	    JPanel legend = new JPanel();
	    JLabel titles[] = new JLabel[8];
	    JLabel titles2[] = new JLabel[8];
	    JLabel messages[] = new JLabel[4];
	    
	    public transactionReportPanel(){
	       /* this.setLocation(300, 300);
	        this.setVisible(true);
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        this.setLayout(new GridLayout(6, 1, 1, 1));
	        this.setTitle("Transaction Report");
	        this.setSize(800, 600);*/
	        
	        vTransLabel.setHorizontalAlignment(JLabel.CENTER);
	        invTransLabel.setHorizontalAlignment(JLabel.CENTER);
	        
	        labelPanelValid.add(vTransLabel);
	       
	        for(int i = 0; i <= 7; i++)
	        {
	            switch(i){
	                case 0: titles[i] = new JLabel("Transaction #"); break;
	                case 1: titles[i] = new JLabel("Investor"); break;
	                case 2: titles[i] = new JLabel("Company Name"); break;
	                case 3: titles[i] = new JLabel("Type"); break;
	                case 4: titles[i] = new JLabel("Quantity"); break;
	                case 5: titles[i] = new JLabel("Unit Price"); break;
	                case 6: titles[i] = new JLabel("Total"); break;
	                case 7: titles[i] = new JLabel("Profit"); break;
	            }
	            titles[i].setHorizontalAlignment(JLabel.CENTER);
	            labelPanelValidNest.add(titles[i]);

	        }
	        
	        labelPanelValidNest.setLayout(new GridLayout (1, 8));

	        labelPanelValid.setLayout(new GridLayout(2, 1));
            labelPanelValid.add(labelPanelValidNest);
	        
	        labelPanelInvalid.add(invTransLabel);
	        
	        for(int i = 0; i <= 7; i++)
	        {
	            switch(i){
	                case 0: titles2[i] = new JLabel("Transaction #"); break;
	                case 1: titles2[i] = new JLabel("Investor"); break;
	                case 2: titles2[i] = new JLabel("Company Name"); break;
	                case 3: titles2[i] = new JLabel("Type"); break;
	                case 4: titles2[i] = new JLabel("Quantity"); break;
	                case 5: titles2[i] = new JLabel("Unit Price"); break;
	                case 6: titles2[i] = new JLabel("Total"); break;
	                case 7: titles2[i] = new JLabel("Message"); break;
	            }
	            titles2[i].setHorizontalAlignment(JLabel.CENTER);
	            labelPanelInvalidNest.add(titles2[i]);
	        }
	        
	        labelPanelInvalidNest.setLayout(new GridLayout (1, 8));

	        labelPanelInvalid.setLayout(new GridLayout(2, 8));
            labelPanelInvalid.add(labelPanelInvalidNest);
	        
	        Font font = vTransLabel.getFont();
	        Map attributes = font.getAttributes();
	        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	        vTransLabel.setFont(font.deriveFont(attributes));
	        
	        Font font2 = invTransLabel.getFont();
	        invTransLabel.setFont(font2.deriveFont(attributes));
	        
	        for(int i = 0; i <= 3; i++)
	        {
	            switch(i){
	                case 0: messages[i] = new JLabel("Legend of Errors:"); break;
	                case 1: messages[i] = new JLabel("*1 = No past transaction defined / Quantity does not match"); break;
	                case 2: messages[i] = new JLabel("*2 = Quantity out of range"); break;
	                case 3: messages[i] = new JLabel("*3 = Price out of range"); break;
	            }
	            
	            messages[i].setHorizontalAlignment(JLabel.CENTER);
	            legend.add(messages[i]);
	        }
	        
	        legend.setLayout(new GridLayout(4, 1));
	        
	        vTranPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	        invTranPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	        
	        
	        this.add(labelPanelValid);
//	        setvTranPanel(vTransLabels);
	        this.add(vTranPanel);
//	        setInvTranPanel(invTransLabels);
	        this.add(labelPanelInvalid);
	        this.add(invTranPanel);
	        exitPanel.add(Exit);
	        this.add(exitPanel);
	        this.add(legend);
	        
	        ActionListener buttonListener = new ExitButtonListener();
	        Exit.addActionListener(buttonListener);
	    }
	    
	    public void setvTranPanel(JLabel vTransLabels[]){
	        if(vTransLabels.length == 0)
	            vTranPanel.add(new JLabel("No valid transaction"));
	        
	        else{
	        vTranPanel.setLayout(new GridLayout(8, vTransLabels.length));
	        }
	    }
	    
	    public void setInvTranPanel(JLabel invTransLabels[]){
	        if(invTransLabels.length == 0)
	            invTranPanel.add(new JLabel("No invalid transaction"));
	        
	        else{
	        invTranPanel.setLayout(new GridLayout(8, invTransLabels.length));
	        }
	    }
	    
	    private class ExitButtonListener implements ActionListener{
	        
	        public void actionPerformed(ActionEvent e){
	            System.exit(0);
	        }
	    }
	/**    
	    public static void main(String[] args) {
	    	JFrame frame = new transactionReportPanel();
	    }*/
	
	}
