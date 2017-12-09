import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class MainGUI extends JPanel{
	//declaration of variables
	private JLabel labelSelect;
	private JLabel labelCustomer;
	private JLabel labelChef;
	private JLabel labelDBA;
	private JRadioButton customerRadio;
	private JRadioButton chefRadio;
	private JRadioButton dbaRadio;
	private JLabel labelSelected;
	ButtonGroup groupButton;
	public JButton nextButton;
	
	//constructor for MainGUI
		public MainGUI(){
		initGUI();
		doTheLayout();
		nextButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				next_actionPerformed(e); 	} 
		});
		
		
		
	}
		//main() for MainGUI
	public static void main(String[] args) {
		JFrame f = new JFrame("Select  Role");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        contentPane.add( new MainGUI());
        f.pack();
        f.setSize(400, 200);
        f.setVisible(true);
	}//end of main()
	
	//initialising the GUI components
	private void initGUI(){
		
		labelSelect = new JLabel("Select Option: ");
		customerRadio = new JRadioButton("Customer", true); //customerRadio is set to true as a default
		chefRadio = new JRadioButton("Chef");
		dbaRadio = new JRadioButton("System Administrator");
		nextButton = new JButton("Next");
		groupButton = new ButtonGroup();
	    groupButton.add(customerRadio);
	    groupButton.add(chefRadio);
	    groupButton.add(dbaRadio);
	}//end of initGUI()
	
	//designing the layout
	private void doTheLayout(){
		
		JPanel panel = new JPanel();
		panel.add(labelSelect);
		panel.add(new JLabel(""));
		panel.add(customerRadio);
		panel.add(new JLabel(""));
		panel.add(chefRadio);
		panel.add(new JLabel(""));
		panel.add(dbaRadio);
		panel.add(new JLabel(""));
		panel.add(nextButton);
		panel.setLayout(new GridLayout(5,1) );
		add(panel);
	}//end of doTheLayout
	
	//action performed when next is clicked
	void next_actionPerformed(ActionEvent e){
		//when user selects customer, the login page for customer is displayed
		if(customerRadio.isSelected()){
			//login.java
			//if new user then, reggister.java
			//then back to login.java to go further
		}
		//when user selects chef, the login page for chef is displayed
		else if(chefRadio.isSelected()){
			//login.java
		}
		//when user selects, system administrator, the login page for dba is displayed
		else {
			//login.java
		}
	}
	
	 
}
