package SQL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SQL {
	

	//Written by Reiko
	
		public static void main(String[] args) {
		/// load the JDBC Driver 
			try {
				   Class.forName("com.mysql.jdbc.Driver");
				}
				catch(ClassNotFoundException ex) {
				   System.out.println("Error: unable to load driver class!");
				   System.exit(1);
				}
		System.out.println("PostgreSQL JDBC Driver Found!");
		
		Connection c = null;
		
		try {
			
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/postgres", "postgres", "Postimees55");
			System.out.println("Connection succeeded");
			
			
		}catch(SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return;
		}//Connection is completed
		
		if(c!=null) {
			
		}
		
	}
	}

