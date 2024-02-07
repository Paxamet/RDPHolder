package DBManager;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;

import javax.sound.sampled.DataLine;

import org.sqlite.JDBC;

public class DBManager {

	private String dbDir;
	private String dbName;
	private String classForName;
	private String dbConnectionString;
	
	private Connection connection;
	
	//TABLES
	private r_RDP rec_RDP;
	//TABLES
	
	public DBManager()
	{
		dbDir 				= "C:/sqlite/db";
		dbName				= "rdp_holder.s3db";
		dbConnectionString 	= "jdbc:sqlite:";
		classForName 		= "org.sqlite.JDBC";
	}
	
	public boolean connect() {
		String message = "";
		try {
			Class.forName(classForName);
			File file = new File(dbDir);
			if(!file.exists()) {
				file.mkdir();
			}
			DriverManager.registerDriver(new JDBC());
			connection = DriverManager.getConnection(dbConnectionString + dbDir + "/" + dbName); 
		} catch (SQLException e) {
			e.printStackTrace();
			message = "DB connection error!";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			message = "ClassNotFoundException";
		}
		
		if(message.isEmpty()) {
			System.out.println("DB connection success!");
			return true;
		}
		System.out.println(message);
		return false;
	}
	
	public void close() {
		String message;
		if(connection == null) {
			System.out.println("DB is missing!");
			return;
		}
		try {
			connection.close();
			message = "DB is closed!";
		} catch (SQLException e) {
			e.printStackTrace();
			message = "DB is not closed!";
		}
		System.out.println(message);
	}
	
	public void initTables() {
		rec_RDP = new r_RDP(connection);
	}
	
	///
	public r_RDP getRDPRec() {
		return rec_RDP;
	}
	///
	
	public void checkTables() {
		getRDPRec().createTable();
	}
}
