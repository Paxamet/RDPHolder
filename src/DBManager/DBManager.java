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
		try {
			Class.forName(classForName);
			connection = DriverManager.getConnection(dbConnectionString + dbDir); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void Close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//public static void main(String[] args) {
	//
	//}

}
