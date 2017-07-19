package pl.rozkocha.szymon.jdbc_sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        DatabaseServer databaseServer = new DatabaseServer(
        		"localhost", "javadb", "user0", "pas123");
        
        Statement statement = null;
        try {
			databaseServer.connect();
			
			statement = databaseServer.createStatement();
			
			ResultSet resultSet = statement.executeQuery("select * from users;");
			while(resultSet.next()) {
				int id = resultSet.getInt(resultSet.findColumn("id"));
				String name = resultSet.getString(resultSet.findColumn("name"));
				String surname = resultSet.getString(resultSet.findColumn("surname"));
				
				System.out.println(id + " " + name + " " + surname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			databaseServer.close();
		}
        
//        String dbUser = "jdbc";
//        String dbPassword = "pass123";
//
//        Connection conn = null;
//        statement = null;
//        try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb?" +
//			        "user=" + dbUser + "&password=" + dbPassword);
//
//			statement = conn.createStatement();
//			ResultSet resultSet = statement.executeQuery("select * from users;");
//
//			while(resultSet.next()) {
//				int id = resultSet.getInt(resultSet.findColumn("id"));
//				String name = resultSet.getString(resultSet.findColumn("name"));
//				String surname = resultSet.getString(resultSet.findColumn("surname"));
//
//				System.out.println(id + " " + name + " " + surname);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			if(statement != null) {
//				try {
//					statement.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//				}
//			}
//		}
    }
}
