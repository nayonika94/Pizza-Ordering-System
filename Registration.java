import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class Registration extends JPanel{
	//declaration of variables
	private JLabel labelFirstName;
	private JLabel labelLastName;
	private JLabel labelAddress;
	private JLabel labelCity;
	private JLabel labelState;
	private JLabel labelZip;
	private JLabel labelEmail;
	private JLabel labelPhone;
	private JLabel labelUserName;
	private JLabel labelPassword;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textAddress;
	private JTextField textCity;
	private JTextField textState;
	private JTextField textZip;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textUserName;
	private JTextField textPassword;
	public JButton registerButton;
	public JButton cancelButton;
	
	//constructor for Registration
	public Registration(){
		initGUI();
		doTheLayout();
		cancelButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				cancel_actionPerformed(e); 	} 
		});
		
		registerButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				register_actionPerformed(e); 	}
		});
			
	}//end
	
	//main() 
	public static void main(String[] args) {
		JFrame f = new JFrame("Registration");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        contentPane.add( new Registration());
        f.pack();
        f.setSize(400, 500);
        f.setVisible(true);
	}//end of main()
	
	private void initGUI(){
		//initialization of components
		labelFirstName = new JLabel("First Name: ");
		labelLastName = new JLabel("Last Name: ");
		labelAddress = new JLabel("Address: ");
		labelCity = new JLabel("City: ");
		labelState = new JLabel("State: ");
		labelZip = new JLabel("Zip: ");
		labelPhone = new JLabel("Phone No.: ");
		labelEmail = new JLabel("Email: ");
		labelUserName = new JLabel("UserName: ");
		labelPassword = new JLabel("Password: ");
		textFirstName = new JTextField();
		textLastName = new JTextField();
		textAddress = new JTextField();
		textCity = new JTextField();
		textState = new JTextField();
		textZip = new JTextField();
		textPhone = new JTextField();
		textEmail = new JTextField();
		textUserName = new JTextField();
		textPassword = new JTextField();
		cancelButton = new JButton("Cancel");
		registerButton = new JButton("Register");
	}//end of initGUI()
	
	private void doTheLayout(){
		//design for gui
		JPanel panel = new JPanel();
		panel.add(labelFirstName);
		panel.add(textFirstName);
		panel.add(labelLastName);
		panel.add(textLastName);
		panel.add(labelAddress);
		panel.add(textAddress);
		panel.add(labelCity);
		panel.add(textCity);
		panel.add(labelState);
		panel.add(textState);
		panel.add(labelZip);
		panel.add(textZip);
		panel.add(labelPhone);
		panel.add(textPhone);
		panel.add(labelEmail);
		panel.add(textEmail);
		panel.add(labelUserName);
		panel.add(textUserName);
		panel.add(labelPassword);
		panel.add(textPassword);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(cancelButton);
		panel.add(registerButton);
		panel.setLayout(new GridLayout(12,2) );
		add(panel);
	}//end of doTheLayout()
	
	void cancel_actionPerformed(ActionEvent e){
		//if cancel button is clicked it goes back to login
	}
	void register_actionPerformed(ActionEvent e){
		//if register button is clicked it sends data to database
		//after storing data it goes to login page for further functions
	}
	

}//end of class
