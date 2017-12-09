import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class DBA extends JPanel{
	//declaration of variables
	private JLabel menuLabel;
	private JLabel itemLabel, itemPriceLabel;
	private JTextField itemTextField, itemPriceTextField;
	private JButton viewButton;
	private JButton addButton;
	private JButton removeButton;
	static JComboBox menuComboBox;
	static JTextArea menuTextArea;
	
	static String itemName;
	static int itemPrice;
	DBAServer server;
	
	//constructor for DBA
	public  DBA(){
		initGUI();
		doTheLayout();
		viewButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				view_actionPerformed(e); 	} 
		});
		addButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				add_actionPerformed(e); 	} 
		});
		removeButton.addActionListener( new  java.awt.event.ActionListener(){
			public void actionPerformed(ActionEvent e){ 
				remove_actionPerformed(e); 	} 
		});
		
		
	}//end
	
	/*//main()
		public static void main(String args[]) {

			JFrame f = new JFrame("Welcome System Administrator");
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container contentPane = f.getContentPane();
			f.setResizable(false);
			contentPane.add( new DBA());
			f.pack();
			f.setSize(500, 300);
			f.setVisible(true);

		}//end of main()	
*/	
	private void initGUI(){
		//initialization of components
		
		menuLabel = new JLabel("Menu :");
		itemLabel = new JLabel("Item to Add/Remove:");
		itemPriceLabel = new JLabel("Price:");
		itemTextField = new JTextField();
		itemPriceTextField = new JTextField();
		viewButton = new JButton("View");
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		menuTextArea = new JTextArea();
		menuTextArea.setEditable(false);
		menuTextArea.setLineWrap(true);
		menuTextArea.setWrapStyleWord(true);
		
	}//end of initGUI()
	
	private void doTheLayout(){
		//designing the layout
		  JPanel panel = new JPanel();
	      panel.setLayout(new GridLayout(6,3));
	      panel.add(menuLabel);
	      panel.add(menuTextArea);
	      panel.add(new JLabel(""));
	      panel.add(new JLabel(""));
	      panel.add(new JLabel(""));
	      panel.add(new JLabel(""));
	      panel.add(itemLabel);
	      panel.add(itemTextField);
	      panel.add(new JLabel(""));
	      panel.add(itemPriceLabel);
	      panel.add(itemPriceTextField);
	      panel.add(new JLabel(""));
	      panel.add(new JLabel(""));
	      panel.add(new JLabel(""));
	      panel.add(new JLabel(""));
	      panel.add(viewButton);
	      panel.add(addButton);
	      panel.add(removeButton);
	     add(panel);
	}//end of doTheLayout()
	
	void view_actionPerformed(ActionEvent e){
		//clicking this button will fill the menuTextArea with existing menu
		try {
			server.view();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot view menu!");
		}
	}
	void add_actionPerformed(ActionEvent e){
		//clicking this button will update the menu
		
		try {//calling DBA server function add()
			itemName = itemTextField.getText().trim();
			itemPrice = Integer.parseInt(itemPriceTextField.getText().trim());
			//checking if any text field is left blank
			if(itemTextField.getText().equals("") || itemPriceTextField.getText().equals("")){
				 JOptionPane.showMessageDialog(null, "Please fill all the text fields!");
				 return;
			 }//end of if
			server.add();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot add Item into menu! Enter valid item name and price.");
		}
		
	}
	void remove_actionPerformed(ActionEvent e){
		//clicking this button will update the menu
		
		try {
			if(itemTextField.getText().equals("")){
				 JOptionPane.showMessageDialog(null, "Please fill the Item Name!");
				 return;
			 }//end of if
			
			itemName = itemTextField.getText().trim();
			//calling DBA server function add()
			server.remove();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Cannot delete Item from menu! Enter valid item name.");
		}
	}
	

}//end of class
