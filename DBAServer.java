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

public class DBAServer {
	//declaring all the variables
	Socket	socket;
	ServerSocket serverSocket;
	DBA dba;
	public Connection connection = null;
	ObjectInputStream serverInputStream;
	ObjectOutputStream serverOutputStream; 
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	
	public String item;
	public int price;
	public String[] menu;
		
	public DBAServer(int port) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException{
		//constructor for DBAServer
		viewGUI();
		initializeDB(port);	
	}//end of DBAServer constructor
	
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
	
	public void view() throws SQLException{
		
		//creating a query statement
		statement = connection.createStatement();
		//creating a result set to view the results
		resultSet = statement.executeQuery("SELECT * from Menu;");
		ArrayList<DBA> menu= new ArrayList<DBA>();
		//to check if the query result is valid
		
		if(resultSet.next()){
			//if resultset is valid, displaying the menu 
			DBA m = new DBA();
			m.itemName = resultSet.getString(1);//if being static creates an issue, rectify by creating separate non-static variables
			m.itemPrice = resultSet.getInt(2);
			menu.add(m);
			DBA.menuTextArea.append(menu.toString());
		}//end of if
	} //end of view() function
	
	public void add() throws SQLException{
		//getting all the data from the gui text fields		
		item = DBA.itemName;	
		price = DBA.itemPrice;
		//creating a query statement
		statement = connection.createStatement();
		//creating a result set to store the result
		resultSet = statement.executeQuery("SELECT * from Menu WHERE item = " + item + ";");
		//checking if the result set is valid
		if(!resultSet.next()){
			//creating a query statement to 'insert' a menu entry
			preparedStatement = connection.prepareStatement("INSERT into Menu VALUES (?,?)");
			preparedStatement.setString(1, item);		
			preparedStatement.setInt(2, price);		
			//executing the statement
			preparedStatement.executeUpdate();
			//displaying the result in the display area
			System.out.println("Menu data inserted: Item= " + item + ", Price= " + price + "\n");
		}//end of if
		else{
			//if the menu item already exists updating the price
			JOptionPane.showConfirmDialog(null, "Do you wish to modify the price for Item= " +item +"?");
			preparedStatement = connection.prepareStatement("UPDATE Staff SET price = ? WHERE item = ?");
			preparedStatement.setString(1, item);		
			preparedStatement.setInt(2, price);
			System.out.println("Menu data inserted: Item= " + item + ", Price= " + price + "\n");
			
		}//end of else		
	} //end of add() function
	
	
		
	public void remove() throws SQLException{
		//getting the item name from the gui text field
		item = DBA.itemName;
		//creating a query statement
		statement = connection.createStatement();
		//creating a result set to store the result
		resultSet = statement.executeQuery("SELECT * from Menu WHERE item = " + item + ";");
		//checking if the result set is valid
		if(!resultSet.next()){
			//displays error if menu item doesn't exist
			JOptionPane.showMessageDialog(null, "Menu Item: " + item + " does not exist!");
		}//end of if
		else{
			//creating a query statement to delete entry
			preparedStatement = connection.prepareStatement("DELETE from Menu WHERE item = ?");
			preparedStatement.setString(1, item);
			//executing the query
			preparedStatement.executeUpdate();
			System.out.println("Menu data deleted: Item= " + item + ", Price= " + price + "\n");
		}//end of else
	}//end of remove() function
	
	void viewGUI(){
		//displaying the frame of the gui
		JFrame f = new JFrame("Welcome System Administrator");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = f.getContentPane();
		contentPane.add( new DBA());
		f.pack();
		f.setSize(500, 300);
		f.setVisible(true);
	} //end of viewGUI() function

	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, SQLException {
		
		int port = 8000;
		DBAServer server = new DBAServer(port);
	} // End of main	
} //End of DBAServer