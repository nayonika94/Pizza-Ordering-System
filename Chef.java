 import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class Chef extends JPanel{
	//declaring the variables
	/*String[] getPendingOrders ={"Get Current Pending Orders"};
	String[] getRecievedOrders ={"Get Current Recieved Orders"};*/
	private JLabel pendingOrdersLabel;
	private JLabel currentOrdersLabel;
	private JButton markAsRecievedButton;
	private JButton markAsCompletedButton;
	private JButton getPendingOrdersButton;
	private JButton getRecievedOrdersButton;
	private JButton closeButton;
	static JTextArea pendingOrdersDescTextArea;
	static JTextArea recievedOrdersDescTextArea;
	
	static int orderNumber;
	static String itemName;
	static int itemPrice;
	static String status;
	ChefServer server;
	
	public  Chef(){
		initGUI();
		doTheLayout();
		closeButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				close_actionPerformed(e); 	} 
		});
		markAsRecievedButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				markAsReceived_actionPerformed(e); 	} 
		});
		markAsCompletedButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				markAsCompleted_actionPerformed(e); 	} 
		});
		getPendingOrdersButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				getPendingOrder_actionPerformed(e); 	} 
		});
		getRecievedOrdersButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				getReceivedOrder_actionPerformed(e); 	} 
		});
		
	}
	/*//main()
		public static void main(String args[]) {

			JFrame f = new JFrame("Welcome Chef");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container contentPane = f.getContentPane();
			f.setResizable(false);
			contentPane.add( new Chef());
			f.pack();
			f.setSize(500, 600);
			f.setVisible(true);
		}//end of main()
*/		
	private void initGUI(){
		//initializing the components
		pendingOrdersLabel = new JLabel("Pending Orders :");
		currentOrdersLabel = new JLabel("Recieved Orders : ");
		markAsRecievedButton = new JButton("Mark as Recieved");
		markAsCompletedButton = new JButton("Mark as Completed");
		getPendingOrdersButton = new JButton("Get Current Pending Orders");
		getRecievedOrdersButton  = new JButton("Get Current Recieved Orders");
		closeButton = new JButton("Close");
		pendingOrdersDescTextArea = new JTextArea();
		pendingOrdersDescTextArea.setEditable(false);
		pendingOrdersDescTextArea.setLineWrap(true);
		pendingOrdersDescTextArea.setWrapStyleWord(true);
		recievedOrdersDescTextArea = new JTextArea();
		recievedOrdersDescTextArea.setEditable(false);
		recievedOrdersDescTextArea.setLineWrap(true);
		recievedOrdersDescTextArea.setWrapStyleWord(true);
		
	}//end of initGUI()
	
	private void doTheLayout(){
		//designing the gui
		  JPanel top = new JPanel();
	      JPanel center = new JPanel();
	      JPanel bottom = new JPanel();
	      
	      top.setLayout(new GridLayout(3,1));	      
	      top.add(pendingOrdersLabel);
	      top.add(getPendingOrdersButton);
	      top.add(pendingOrdersDescTextArea);
	      top.add(markAsRecievedButton);
	      
	      center.setLayout(new GridLayout(3,1));
	      center.add(currentOrdersLabel);
	      center.add(getRecievedOrdersButton);
	      center.add(recievedOrdersDescTextArea);
	      center.add(markAsCompletedButton);
	      
	      bottom.add(closeButton);
	      setLayout( new GridLayout(3,1));
		 	add(top);
		 	add(center);
		 	add(bottom);
	      
	}//end of doThelayout()
	
	void getPendingOrder_actionPerformed(ActionEvent e){
		//clicking this button displays the order details which are unmarked
		try {
			server.pendingOrder();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot view pending order!");
		}
	}
	void getReceivedOrder_actionPerformed(ActionEvent e){
		//clicking this button gets the order details which are marked as received
		try {
			server.receivedOrder();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot view received order!");
		}
	}
	void markAsReceived_actionPerformed(ActionEvent e){
		//clicking this button changes the order status to 'received'
		try {
			server.markReceived();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot mark order as received!");
		}
	}
	void markAsCompleted_actionPerformed(ActionEvent e){
		//clicking this button changes the order status to 'completed'
		try {
			server.markCompleted();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot mark order as completed!");
		}
	}
	void close_actionPerformed(ActionEvent e){
		System.exit(0);
	}
	
	

}//end of class
