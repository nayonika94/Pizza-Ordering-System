import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

public class Customer extends JPanel{
	//declaration of variables
	
	//private Menu menu = new Menu(); //class to handle menu and database stuff
	//private Customer customer = new Customer(); //class to handle customer and database stuff 
	int dialogButton = JOptionPane.YES_NO_OPTION;
	
	private JLabel placeOrderLabel;
	private JLabel paymentLabel;
	private JLabel checkOrderLabel;
	private JLabel nameLabel;
	private JLabel menuLabel;
	private JLabel amountLabel;
	private JLabel descriptionLabel;
	private JLabel creditCardLabel;
	private JLabel expDateLabel;
	private JLabel orderNumberLabel;
	private JLabel statusLabel;
	static JComboBox menuComboBox;
	static JTextField amountTextField;
	static JTextField nameTextField;
	static JTextField creditCardTextField;
	static JTextField expDateTextField;
	static JTextField orderNumberTextField;
	static JTextField statusTextField;
	static JTextField modifyAmountTextField;
	static JTextArea descriptionTextArea;
	private JButton orderButton;
	private JButton makePaymentButton;
	private JButton backButton;
	private JButton cancelButton;
	private JButton modifyButton;
	
	public static String item;
	public static int price;
	public static String expDate;
	public static long cardNum;
	 public  static String name;
	CustomerServer server;
	static String[] menuList={};//temporary menu items

	public static int orderNum;
	//constructor for user
        public Customer(){
    	init();
    	doTheLayout();
    	orderButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				order_actionPerformed(e); 	} 
		});
    	makePaymentButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				makePayment_actionPerformed(e); 	} 
		});
    	backButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				back_actionPerformed(e); 	} 
		});
    	cancelButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				cancel_actionPerformed(e); 	} 
		});
    	modifyButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				modify_actionPerformed(e); 	} 
		});
    }
        //main()
	public static void main(String[] args) {
		JFrame f = new JFrame("Welcome Customer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = f.getContentPane();
		f.setResizable(true);
		contentPane.add( new Customer());
		f.pack();
		f.setSize(600, 600);
		f.setVisible(true);
	}//end of main()
	
    public void init() {
    	//initializing the components
    	placeOrderLabel = new JLabel("Place your Order . . .");
		menuLabel = new JLabel("Menu:");
		amountLabel = new JLabel("Amount:");
		descriptionLabel = new JLabel("Order Details:");
		paymentLabel = new JLabel("Make Payment . . .");
		nameLabel = new JLabel("Name on Card:");
		creditCardLabel = new JLabel("Card Number:");
		expDateLabel = new JLabel("Expiration Date:");
		checkOrderLabel = new JLabel("Check Order Status . . .");
		orderNumberLabel = new JLabel("Order Number:");
		statusLabel = new JLabel("Status: ");
		menuComboBox = new JComboBox(menuList);//menulist is a string that stores the menu items from the database
		nameTextField = new JTextField();
		nameTextField.setEditable(false);
		amountTextField = new JTextField();
		amountTextField.setEditable(false);
		creditCardTextField = new JTextField();
		creditCardTextField.setEditable(false);
		expDateTextField = new JTextField();	
		expDateTextField.setEditable(false);
		orderNumberTextField = new JTextField();		
		statusTextField = new JTextField();
		statusTextField.setEditable(false);	
		descriptionTextArea = new JTextArea();//it will display order number, order item, amount
		descriptionTextArea.setEditable(false);
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);		
		orderButton = new JButton("Order");
		makePaymentButton = new JButton("Pay");
		makePaymentButton.setEnabled(false);
		backButton = new JButton("Back");
		backButton.setEnabled(false);
		cancelButton = new JButton("Cancel Order");		
		modifyButton = new JButton("Modify Order");		
    } //end of init()
    
         public void doTheLayout() {
        	 //designing the gui layout
        	 JPanel top = new JPanel();
        	 JPanel center = new JPanel();
   	      	 JPanel bottom = new JPanel();
   	      	 
   	      	 top.setLayout(new GridLayout(6,2));
   	      	 top.add(placeOrderLabel);
   	      	 top.add(new JLabel(""));
     		 top.add(menuLabel);
     		 top.add(menuComboBox);
     		 top.add(descriptionLabel);
     		 top.add(descriptionTextArea);
     		 top.add(amountLabel);
    		 top.add(amountTextField);
    		 top.add(new JLabel (""));
    		 top.add(new JLabel (""));
    		 top.add(new JLabel (""));
    		 top.add(orderButton);
     		 
     		 center.setLayout(new GridLayout(6,2));
     		 center.add(paymentLabel);
     		 center.add(new JLabel(""));
     		 center.add(nameLabel);
     		 center.add(nameTextField);
     		 center.add(creditCardLabel);
     		 center.add(creditCardTextField);
     		 center.add(expDateLabel);
     		 center.add(expDateTextField);
     		 center.add(new JLabel (""));
     		 center.add(new JLabel (""));
     		 center.add(makePaymentButton);
     		 center.add(backButton);
     		 
     		 bottom.setLayout(new GridLayout(6,2));
     		 bottom.add(checkOrderLabel);
     		 bottom.add(new JLabel(""));
     		 bottom.add(orderNumberLabel);
     		 bottom.add(orderNumberTextField);
     		 bottom.add(statusLabel);
     		 bottom.add(statusTextField);
     		 bottom.add(new JLabel (""));
     		 bottom.add(new JLabel (""));
     		 bottom.add(modifyButton);
     		 bottom.add(cancelButton);
     		 
     		
     		setLayout( new GridLayout(3,1));
		 	add(top);
		 	add(center);
		 	add(bottom);
    	   }//end of doTheLayout()
 
         void order_actionPerformed(ActionEvent e){
        	 //when order button is clicked check whether amount textbox is filled and make payment visible
        	 
        	 try {
        		 item = menuComboBox.getSelectedItem().toString();
        		 
				server.order();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Cannot order item!");;
			}
        	 nameTextField.setEditable(true);
        	 creditCardTextField.setEditable(true);
        	 expDateTextField.setEditable(true);
        	 makePaymentButton.setEnabled(true);
        	 backButton.setEnabled(true);
        	 menuComboBox.setEnabled(false);
         }
         void makePayment_actionPerformed(ActionEvent e){
        	 //check for credit card validation
        	 try{
        		 if(nameTextField.getText().equals("") || creditCardTextField.getText().equals("") || expDateTextField.getText().equals("")){
        			 JOptionPane.showMessageDialog(null, "Credit card details cannot be left blank!");
        		 }
        		 setName(nameTextField.getText());
        		 //card number validation
        		 cardNum = Long.parseLong(creditCardTextField.getText());
        		 if(creditCardTextField.getText().length()>16){
        			 JOptionPane.showMessageDialog(null,"Please enter a valid Credit card number.");
        		 }
        		 String[] splitDate  = expDateTextField.getText().split("/");
     	        int month=0, year=0;
     	        //expiry date validation
     	        if(splitDate.length==2){
     	        	month =Integer.parseInt(splitDate[0]);
     	        	year = Integer.parseInt(splitDate[1]);
     	        	if(month>12 && year<2016){
     	        		JOptionPane.showMessageDialog(null,"Invalid Date! Enter as mm/yyyy.");
     	        		
     	        	}
     	        	else {
     	        		expDate =expDateTextField.getText();
     	        	}
     	         }
       	       		else{
     	        	JOptionPane.showMessageDialog(null,"Invalid Date! Enter as mm/yyyy.");
     	        	
       	       		} 
     	        
     	        server.payment();
     	        
        	 }catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Payment Failed! Please check your credit card details.");
				}
        	 //if payment success print following statement
        	 JOptionPane.showMessageDialog(null, "Payment Successful! Order Id:");
        	
         }
         void back_actionPerformed(ActionEvent e){
        	 //go back to order
        	 //making all the order fields visible
        	 nameTextField.setEditable(false);
        	 creditCardTextField.setEditable(false);
        	 expDateTextField.setEditable(false);
        	 makePaymentButton.setEnabled(false);
        	 backButton.setEnabled(false);
        	 menuComboBox.setEnabled(true);
         }
         void modify_actionPerformed(ActionEvent e){
        	 //this function is used to modify the order based on order number and status
        	//check if order number is valid
        	 //check if status is not received or completed
        	
        	 
         }
         void cancel_actionPerformed(ActionEvent e){
        	 //this function is used to delete a particular order
        	 JOptionPane.showConfirmDialog(null, "Do you wish to cancel your Order?");
        	 //Delete the order
         }
		
 
  
   
  
}