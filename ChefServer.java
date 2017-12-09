import java.awt.Container;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ChefServer {
	//declaring all the variables
	Socket	socket;
	ServerSocket serverSocket;
	Chef chef;
	public Connection connection = null;
	ObjectInputStream serverInputStream;
	ObjectOutputStream serverOutputStream; 
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public int orderNum;
	public String item;
	public int price;
	public String status;
	
		
	public ChefServer(int port) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{
		//constructor for ChefServer
		viewGUI();
		initializeDB(port);	
	}//end of ChefServer constructor
	
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
	
	public void pendingOrder() throws SQLException{
		
		//creating a query statement
		statement = connection.createStatement();
		//creating a result set to view the results
		resultSet = statement.executeQuery("SELECT * from Order WHERE status = 'pending';");
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
				
				Chef.pendingOrdersDescTextArea.append(order.toString());
			
		}//end of if
			else{
				JOptionPane.showMessageDialog(null, "No pending order!");
			}
	} //end of pendingOrder() function
	
	public void receivedOrder() throws SQLException{
		
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
						
						Chef.recievedOrdersDescTextArea.append(order.toString());
					
				}//end of if
					else{
						JOptionPane.showMessageDialog(null, "No received order!");
					}
	} //end of receivedOrder() function
	
	
		
	public void markReceived() throws SQLException{
		
		//creating a query statement
		statement = connection.createStatement();
		//creating a result set to view the results
		resultSet = statement.executeQuery("SELECT * from Order WHERE status = 'pending';");
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
				status = "received";
				//JOptionPane.showConfirmDialog(null, "Do you wish to modify the price for Item= " +item +"?");
				preparedStatement = connection.prepareStatement("UPDATE Order SET status = ? WHERE orderNum = ?");
				preparedStatement.setInt(1, orderNum);		
				preparedStatement.setString(2, status);
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
		JFrame f = new JFrame("Welcome Chef");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = f.getContentPane();
		f.setResizable(false);
		contentPane.add( new Chef());
		f.pack();
		f.setSize(500, 600);
		f.setVisible(true);
	} //end of viewGUI() function

	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		
		int port = 8000;
		ChefServer server = new ChefServer(port);
	} // End of main	
} //End of ChefServer