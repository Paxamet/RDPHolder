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
		public String NAME;
		public String PATH;
		public int STATUS;
		public RDPLine() {
			ID = -1;
			NAME = "";
			PATH = "";
			STATUS = -1;
		};
		public RDPLine(String NAME, String PATH, int STATUS) {
			super();
			this.NAME = NAME;
			this.PATH = PATH;
			this.STATUS = STATUS;
		};
		/*public RDPLine(int ID, String NAME, String PATH, int STATUS) {
			this.ID = ID;
			this.NAME = NAME;
			this.PATH = PATH;
			this.STATUS = STATUS;
		};*/
	}
	
	static String getDataLineFieldsString(DataLine dataLineObject) {
		StringBuilder fieldsSB = new StringBuilder();
		fieldsSB.append("(");
		for(Field field : dataLineObject.getClass().getDeclaredFields())
		{
			if(field.getName().equals("ID")) {
				continue;
			}
			fieldsSB.append("'").append(field.getName()).append("'");
			if(!field.equals(dataLineObject.getClass().getDeclaredFields()[dataLineObject.getClass().getDeclaredFields().length - 1]))
				fieldsSB.append(", ");
		}
		fieldsSB.append(")");
		return fieldsSB.toString();
	}
		
	static String getDataLineFieldsValueSampl(DataLine dataLineObject) {
		StringBuilder valuesSB = new StringBuilder();
		valuesSB.append("VALUES(");
		for(Field field : dataLineObject.getClass().getDeclaredFields())
		{ 
			if(field.getName().equals("ID")) {
				continue;
			}
			valuesSB.append("?");
			if(!field.equals(dataLineObject.getClass().getDeclaredFields()[dataLineObject.getClass().getDeclaredFields().length - 1]))
				valuesSB.append(", ");
		}
		valuesSB.append(")");
		return valuesSB.toString();
	}
	
	static String getDataLineFieldAndTypeString(Class<?> dataLineClass) {
		StringBuilder resultSB = new StringBuilder().append("(");
		for(Field field : dataLineClass.getDeclaredFields())
		{ 
			resultSB.append(field.getName()).append(" ").append(convertTypeJavaToSQLite(field.getType().getName()));
			if(field.getName().equals("ID")) {
				resultSB.append(" PRIMARY KEY AUTOINCREMENT");
			}
			if(!field.equals(dataLineClass.getDeclaredFields()[dataLineClass.getDeclaredFields().length - 1]))
				resultSB.append(", ");
		}
		resultSB.append(")");
		return resultSB.toString();
	}
	
	private static String convertTypeJavaToSQLite(String javaType) {
		String result = "";
		switch (javaType) {
		case "int":
		case "long":
		case "Integer":
			result = "INTEGER";
			break;
			
		case "Float":
		case "float":
			result = "REAL";
			break;
			
		case "String":
			result = "TEXT";
			break;
					
		default:
			result = "NUMERIC";
			break;
		}
		return result;
	}
	
	static PreparedStatement setStatementObjectByDataLine(PreparedStatement statement, DataLine dataLineObject) {
		int fieldCounter = 1;
		for(Field field : dataLineObject.getClass().getDeclaredFields()) {
			if(field.getName().equals("ID")) {
				continue;
			}
			try {
				statement.setObject(fieldCounter, field.get(dataLineObject));
				fieldCounter++;
			} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return statement;
	}
}
