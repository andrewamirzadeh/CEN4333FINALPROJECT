import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Connectors {

	
	
	public static Connection getConnection() throws Exception{
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/newToDoList?useSSL=false";
			String user = "root";
			String pass = "Root1234!";
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, user, pass);
		//	System.out.println("Connection estabalished");
			return con;
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		return null;
	}
}
