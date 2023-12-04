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
	
	
	
	public DBManager()
	{
		dbDir 				= "C:/sqlite/db";
		dbName				= "rdp_holder.s3db";
		dbConnectionString 	= "jdbc:sqlite:";
		classForName 		= "org.sqlite.JDBC";
	}
	
	public void Connect() {
		String message;
		try {
			Class.forName(classForName);
			File file = new File(dbDir);
			if(!file.exists()) {
				file.mkdir();
			}
			DriverManager.registerDriver(new JDBC());
			connection = DriverManager.getConnection(dbConnectionString + dbDir + "/" + dbName); 
			message = "DB connection success!";
		} catch (SQLException e) {
			e.printStackTrace();
			message = "DB connection error!";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			message = "ClassNotFoundException";
		}
		System.out.println(message);
		
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement(
					"CREATE TABLE IF NOT EXISTS RDP_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, PATH TEXT, STATUS INTEGER)"
					);

			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void AddNewRDPLine(DataLines.RDPLine RDPLine) {
		String queryStr = "INSERT Into RDP_TABLE " + DataLines.getDataLineFielsString(RDPLine) + " " + DataLines.getDataLineFieldsValueSampl(RDPLine);
		try {
			PreparedStatement statement = connection.prepareStatement(queryStr);
			statement = DataLines.setStatementObjectByDataLine(statement, RDPLine);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
