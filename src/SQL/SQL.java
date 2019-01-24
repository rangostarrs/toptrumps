package SQL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SQL {
	

	//Written by Reiko
	
		public static void main(String[] args) {
		/// load the JDBC Driver 
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println("Could not find JBDC Driver");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Found!");
		
		Connection c = null;
		
		try {
			//Toby's database information
			c = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2420282r", "2420282r");
			
			
		}catch(SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return;
		}//Connection is completed
		
		if(c!=null) {
			
		}
		
	}
	}

