package DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBManager.DataLines.DataLine;

public class r_BaseRecord {
	
	private Connection connection;
	private Class<?>  recordClass;
	private Class<?> dataLineClass;
	
	public r_BaseRecord(Connection connection, Class<?> recordClass, Class<?> dataLineClass) {
		this.connection = connection;
		this.recordClass = recordClass;
		this.dataLineClass = dataLineClass;
	}
	
	private String getTableName() {
		return new StringBuilder().append(recordClass.getSimpleName().substring(2)).append("_TABLE").toString().toUpperCase();
	}
	
	void createTable() {
		String queryStr = new StringBuilder()
				.append("CREATE TABLE IF NOT EXISTS ")
				.append(getTableName()).append(" ")
				.append(DataLines.getDataLineFieldAndTypeString(dataLineClass))
				.toString();
		
		try {
			PreparedStatement statement = connection.prepareStatement(queryStr);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addNewLine(DataLine newData) {
		String queryStr = new StringBuilder()
				.append("INSERT INTO ")
				.append(getTableName()).append(" ")
				.append(DataLines.getDataLineFieldsString(newData)).append(" ")
				.append(DataLines.getDataLineFieldsValueSampl(newData))
				.toString();
		try {
			PreparedStatement statement = connection.prepareStatement(queryStr);
			statement = DataLines.setStatementObjectByDataLine(statement, newData);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
