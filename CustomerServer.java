import java.awt.Container;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerServer {
	//declaring all the variables
	Socket	socket;
	ServerSocket serverSocket;
	Customer customer;
	public Connection connection = null;
	ObjectInputStream serverInputStream;
	ObjectOutputStream serverOutputStream; 
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatement1 = null;
	private ResultSet resultSet = null;
	
	public int orderNum;
	public String item;
	public int price;
	public String status;
	public String name;
	public String expDate;
	public long cardNumber;
	
		
	public CustomerServer(int port) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{
		//constructor for CustomerServer
		viewGUI();
		initializeDB(port);	
	}//end of CustomerServer constructor
	
	public void initializeDB(int listeningPort) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		//initializing the database
	try {
		
			serverSocket = new ServerSocket(listeningPort);
			//Load the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");					
			//Establishing connection
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDatabase","root","");
			System.out.println("Database connected... \n");			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("It couldn't listen on the port "+listeningPort + "\n");			
		}
	}//end of initializeDB
	public String[] viewMenu() throws SQLException{
		String[] items = null;
		//creating a query statement
				statement = connection.createStatement();
				//creating a result set to view the results
				resultSet = statement.executeQuery("SELECT * from Menu;");
				ArrayList<Customer> menu= new ArrayList<Customer>();
				if(resultSet.next()){
				
					Customer m = new Customer();
					m.item = resultSet.getString(2);//if being static creates an issue, rectify by creating separate non-static variables
					m.price = resultSet.getInt(3);
					menu.add(m);
					int size = menu.size();
					items = new String[size]; 
					for (int i=0; i<menu.size(); i++){
					items[i] = menu.get(i).item;
					}
					
				}
				
				return items;
	}
	
	public void order() throws SQLException{
		item = Customer.item;
		//creating a query statement
		statement = connection.createStatement();
		//creating a result set to view the results
		resultSet = statement.executeQuery("SELECT * from Order WHERE item = " +item+";");
		//to check if the query result is valid
			if(resultSet.next()){
				//if resultset is valid, displaying the menu 
				price = resultSet.getInt("price");
				String price1 = Integer.toString(price);
				customer.amountTextField.setText(price1);
				preparedStatement = connection.prepareStatement("INSERT into Order VALUES (?,?)");//we are not inserting order number for customer, it is auto generated
				preparedStatement.setString(1, item);		
				preparedStatement.setInt(2, price);	
				preparedStatement1 = connection.prepareStatement("SELECT orderNumber from Order WHERE item = "+item+";");
				preparedStatement1.setString(1, item);
				//executing the statement
				preparedStatement.executeUpdate();
				preparedStatement1.executeUpdate();
			}
			else{
				JOptionPane.showMessageDialog(null, "Cannot place order!");
			}
	} //end of order() function
	
	public void payment() throws SQLException{
		name = Customer.name;
		item = Customer.item;
		price = Customer.price;
		orderNum = Customer.orderNum;
		String payStatus = "paid";
				//creating a query statement
				statement = connection.createStatement();
				//creating a result set to view the results
				resultSet = statement.executeQuery("SELECT * from Payment;");
				//to check if the query result is valid
					if(!resultSet.next()){
							//if resultset is valid, displaying the menu 
							preparedStatement = connection.prepareStatement("INSERT into Payment VALUES (?,?,?,?,?)");
							preparedStatement.setInt(1, orderNum);
							preparedStatement.setString(2, name);		
							preparedStatement.setString(3, item);
							preparedStatement.setInt(4, price);
							preparedStatement.setString(5, payStatus);
							//executing the statement
							preparedStatement.executeUpdate();
						
							JOptionPane.showMessageDialog(null, "Payment Successful! Order Number:" +orderNum);
					
				}//end of if
					else{
						JOptionPane.showMessageDialog(null, "No received order!");
					}
	} //end of receivedOrder() function
	
	
		
	public void cancel() throws SQLException{
		orderNum = Customer.orderNum;
		//creating a query statement
		statement = connection.createStatement();
		//creating a result set to view the results
		resultSet = statement.executeQuery("SELECT * from Order WHERE orderNum=" + orderNum+";");
		//to check if the query result is valid
			if(resultSet.next()){
				//if resultset is valid, displaying the menu 
				preparedStatement = connection.prepareStatement("DELETE from Order WHERE orderNum=" +orderNum+";");//we are not inserting order number for customer, it is auto generated
				preparedStatement.setInt(1, orderNum);	
				preparedStatement.executeUpdate();
				preparedStatement1 = connection.prepareStatement("DELETE from Payment WHERE orderNum=" +orderNum+";");	
				preparedStatement1.setInt(1, orderNum);	
				//executing the statement
				preparedStatement1.executeUpdate();
				/*Customer o = new Customer();
				o.orderNumber = resultSet.getInt(1);//if being static creates an issue, rectify by creating separate non-static variables
				o.itemName = resultSet.getString(2);
				o.itemPrice = resultSet.getInt(3);
				order.add(o);
				orderNum = o.orderNumber;*/
				JOptionPane.showMessageDialog(null, "Order Number: " + orderNum + " marked as Received!");
			
		}//end of if
			else{
				JOptionPane.showMessageDialog(null, "No order to mark!");
			}
	}//end of markReceived() function
	
	public void markCompleted() throws SQLException{
		
		//creating a query statement
		statement = connection.createStatement();
		//creating a result set to view the results
		resultSet = statement.executeQuery("SELECT * from Order WHERE status = 'received';");
		//to check if the query result is valid
			ArrayList<Chef> order= new ArrayList<Chef>();
			//to check if the query result is valid
			
			if(resultSet.next()){
				//if resultset is valid, displaying the menu 
				Chef o = new Chef();
				o.orderNumber = resultSet.getInt(1);//if being static creates an issue, rectify by creating separate non-static variables
				o.itemName = resultSet.getString(2);
				o.itemPrice = resultSet.getInt(3);
				order.add(o);
				orderNum = o.orderNumber;
				status = "completed";
				//JOptionPane.showConfirmDialog(null, "Do you wish to modify the price for Item= " +item +"?");
				preparedStatement = connection.prepareStatement("UPDATE Order SET status = ? WHERE orderNum = ?");
				preparedStatement.setInt(1, orderNum);		
				preparedStatement.setString(2, status);
				JOptionPane.showMessageDialog(null, "Order Number: " + orderNum + " marked as Completed!");
			
		}//end of if
			else{
				JOptionPane.showMessageDialog(null, "No order to mark!");
			}
	}//end of markCompleted() function
	
	void viewGUI(){
		//displaying the frame of the gui
		JFrame f = new JFrame("Welcome Customer");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = f.getContentPane();
		f.setResizable(false);
		contentPane.add( new Customer());
		f.pack();
		f.setSize(500, 600);
		f.setVisible(true);
	} //end of viewGUI() function

	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		
		int port = 8000;
		//CustomerServer server = new CustomerServer(port);
	} // End of main	

	

	
} //End of CustomerServer