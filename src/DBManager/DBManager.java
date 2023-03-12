package DBManager;

import java.sql.*;

public class DBManager {

	private String dbDir;
	private String classForName;
	private String dbConnectionString;
	
	private Connection connection;
	
	
	
	public DBManager()
	{
		dbDir 				= "C:\\sqlite\\db\\rdp_holder.s3db";
		dbConnectionString 	= "jdbc:sqlite:";
		classForName 		= "org.sqlite.JDBC";
	}
	
	public void Connect() {
		String message;
		try {
			Class.forName(classForName);
			connection = DriverManager.getConnection(dbConnectionString + dbDir); 
			message = "DB connection success!";
		} catch (Exception e) {
			e.printStackTrace();
			message = "DB connection error!";
		}
		System.out.println(message);
	}
	
	public void Close() {
		String message;
		try {
			connection.close();
			message = "DB is closed!";
		} catch (SQLException e) {
			e.printStackTrace();
			message = "DB is not closed!";
		}
		System.out.println(message);
	}
	
	//public static void main(String[] args) {
	//
	//}

}
