import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class Login extends JPanel{
	//declaration of variables
	private JLabel labelUser;
	private JLabel labelPassword;
	public JTextField textUser;
	public JTextField textPassword;
	public JButton loginButton;
	public JButton registerButton;
	
	//constructor for Login
	public Login(){
		initGUI();
		doTheLayout();
		
		loginButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				login_actionPerformed(e); 	} 
		});
		
		registerButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				register_actionPerformed(e); 	}
		});
			
	}
	//main() for Login
	public static void main(String[] args) {
		JFrame f = new JFrame("Login");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();
        contentPane.add( new Login());
        f.pack();
        f.setSize(300, 200);
        f.setVisible(true);
	}//end of main()
	
	private void initGUI(){
		//initialization of the components
		labelUser = new JLabel("UserName: ");
		labelPassword = new JLabel("Password: ");
		textUser = new JTextField();
		textPassword = new JTextField();
		loginButton = new JButton("Log In");
		registerButton = new JButton("Register");
	}//end of initGUI
	
	
	private void doTheLayout(){
		//designing the gui for login page
		JPanel panel = new JPanel();
		panel.add(labelUser);
		panel.add(textUser);
		panel.add(labelPassword);
		panel.add(textPassword);
		panel.add(new JLabel(""));
		panel.add(new JLabel(""));
		panel.add(loginButton);
		panel.add(registerButton);
		panel.setLayout(new GridLayout(4,2) );
		add(panel);
	}//end of doTheLayout()
	
	void login_actionPerformed(ActionEvent e){
		//when login button is clicked, user name and password are validated 
		//depending on the role the further class is called
	}
	void register_actionPerformed(ActionEvent e){
		//if it is a new user then, register.java is called
	}
	

}//end of class
