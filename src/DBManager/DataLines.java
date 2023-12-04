package DBManager;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sound.sampled.DataLine;

public class DataLines {
	
	public static class DataLine {
		
	}
	
	public static class RDPLine extends DataLine{
		public int ID;
		public String PATH;
		public int STATUS;
		public RDPLine() {
			ID = -1;
			PATH = "";
			STATUS = -1;
		};
		public RDPLine(int ID, String PATH, int STATUS) {
			this.ID = ID;
			this.PATH = PATH;
			this.STATUS = STATUS;
		};
	}
	
	public static String getDataLineFielsString(DataLine dataLineObject) {
		StringBuilder fieldsSB = new StringBuilder();
		fieldsSB.append("(");
		for(Field field : dataLineObject.getClass().getDeclaredFields())
		{
			fieldsSB.append("'").append(field.getName()).append("'");
			if(!field.equals(dataLineObject.getClass().getDeclaredFields()[dataLineObject.getClass().getDeclaredFields().length - 1]))
				fieldsSB.append(", ");
		}
		fieldsSB.append(")");
		return fieldsSB.toString();
	}
		
	public static String getDataLineFieldsValueSampl(DataLine dataLineObject) {
		StringBuilder valuesSB = new StringBuilder();
		valuesSB.append("VALUES(");
		for(Field field : dataLineObject.getClass().getDeclaredFields())
		{ 
			valuesSB.append("?");
			if(!field.equals(dataLineObject.getClass().getDeclaredFields()[dataLineObject.getClass().getDeclaredFields().length - 1]))
				valuesSB.append(", ");
		}
		valuesSB.append(")");
		return valuesSB.toString();
	}
	
	public static PreparedStatement setStatementObjectByDataLine(PreparedStatement statement, DataLine dataLineObject) {
		//for(Field field : dataLineObject.getClass().getDeclaredFields())
		for(int fieldNumber = 0; fieldNumber < dataLineObject.getClass().getDeclaredFields().length; fieldNumber++)
		{ 
			try {
				statement.setObject(fieldNumber + 1, dataLineObject.getClass().getDeclaredFields()[fieldNumber].get(dataLineObject));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return statement;
	}
}
