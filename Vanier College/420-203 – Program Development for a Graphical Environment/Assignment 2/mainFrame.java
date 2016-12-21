

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainFrame extends JFrame implements Constants 
{
    static public int panelCounter = 0;
    static JFrame frame;
    
    JButton next = new JButton("Choose investors & companies");

    static boolean testCompanies1[] =  {false, true, true, false, true};

    public static JPanel panel = new Panel1();
    public JPanel nextButton = new JPanel();
    
    public mainFrame()
    {
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setTitle("Menu");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
                
        
        
        
        this.add(panel, BorderLayout.CENTER);
        
        
        next.addActionListener(new nextClass());

        
        nextButton.add(next);
        this.add(nextButton, BorderLayout.SOUTH);

        
        this.setVisible(true);
    }
  
    
    
    
        public static void main(String[] args) {
        
       frame = new mainFrame();
    }


    
    
       class nextClass implements ActionListener{
       public void actionPerformed(ActionEvent event)     {
       	              
       	       		int invCounter = 0;
       	       		int[] actInv = new int[3];
       	       		frame.setVisible(false);

       	               if(panelCounter == 0)
       	               {
       	               		nextButton.remove(next);
       	               		next = new JButton("Next");   
       	               		next.addActionListener(new nextClass());
       	               		nextButton.add(next); 
       	               }
       	      frame.remove(panel);
       	     // frame.remove(nextButton);
       	      
       	      	       	 
       	      		for(int i = 0; i < 5; ++i)
	       	       {
	       	       	   

	       	       		if(selectionPanel.investorsBoolean[i]==true)
	       	       		{
	       	       			System.out.println(selectionPanel.investorsBoolean[i]);
	       	           	  	actInv[invCounter] = i;
	       	           	  	++invCounter;
	       	       		}
	       	       }
       	       
       	++panelCounter;
		switch (panelCounter)
	       {
		
	       case 0:	panel = new Panel1();
		       System.out.println("Holasss");
			break;
		       
	       
	      case 1:	panel = new selectionPanel();
			System.out.println("Hola");
			break;
	   
	       
	       case 2:  

	       	       for(int i = 0; i<3; ++i)
	       	       {
	       	       	       System.out.println(actInv[i]);
	       	       }
	       	       panel = new transactionPanel(actInv[0]);
		
	       	       
	       	       
			break;
		   
	       
	       case 3:	
	       	       panel = new transactionPanel(actInv[1]);
			break;
		      
	       
	       case 4:	
	       	       if(invCounter==3){
	       	       panel = new transactionPanel(actInv[2]);
	       	       }
	       	       else
	       	       {
	       	       	       
	       	       	       panel = new transactionReportPanel();
	       	       	       frame.remove(nextButton);
	       	       }
			break;
	
		case 5:		
			
			panel = new transactionReportPanel();
			frame.remove(nextButton);
			break;
			
	       default: 	break;
	       
       }
      frame.add(panel, BorderLayout.CENTER);
    //   frame.add(nextButton, BorderLayout.SOUTH);
      frame.revalidate();
	/* 	     frame.repaint();*/
               frame.setVisible(true);

       	

}

}
}
      
    

    
 
	

