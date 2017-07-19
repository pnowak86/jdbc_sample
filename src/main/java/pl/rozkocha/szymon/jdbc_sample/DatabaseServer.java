package pl.rozkocha.szymon.jdbc_sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseServer {
	private String address;
	private String database;
	private String user;
	private String password;
	
	private Connection connection;
	
	public DatabaseServer(String address, String database, String user, String password) {
		this.address = address;
		this.database = database;
		this.user = user;
		this.password = password;
	}
	
	public void connect() throws SQLException {
		connection = DriverManager.getConnection(
				"jdbc:mysql://" + address + "/" + database + "?" +
		        "user=" + user + "&password=" + password);
	}
	
	public Statement createStatement() throws SQLException {
		if(connection == null) {
			throw new IllegalStateException("Call connect() first!!!");
		}
		
		return connection.createStatement();
	}
	
	public void close() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();//TODO
			}
		}
		
	}
}
