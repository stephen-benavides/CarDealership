package project_0.layers.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnection {

	private static Connection conn = null;

	public static Connection getConnection() {

		try {

			if (conn == null) {
				// Create connection
				//Hotfix to make sure the database connected
				Class.forName("oracle.jdbc.driver.OracleDriver");
				//read the file and place the bits in "input"
				FileInputStream input = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties").getFile());
				Properties p = new Properties();
				//Load the info in properties(which is a map for key, value where they are both strings) 
				p.load(input);	
				//Set the values that have been loaded and get them using their respective keys.
				String url = p.getProperty("url");
				String user = p.getProperty("user");
				String password = p.getProperty("password");
				
				conn = DriverManager.getConnection(url, user, password);
				
				
				return conn;
			}
			return conn;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
}
