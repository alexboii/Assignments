

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;



public class Panel1 extends JPanel implements Constants {
	
public Panel1() {
        setLayout(new FlowLayout());

       JButton chooseButton = new JButton("Choose investors & companies");
       JButton exitButton = new JButton("Exit");
              
       add(exitButton);


       ActionListener exitListener = new exitClass();

       exitButton.addActionListener(exitListener); 
	

	}
	
	
	
	 
      class exitClass implements ActionListener{
       public void actionPerformed(ActionEvent event)     {            
            System.exit(0);
       		}
      }
      
      
  
          
      
      



}
