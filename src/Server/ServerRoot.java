package Server;

import DBManager.DBManager;

public class ServerRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBManager db = new DBManager();
		db.connect();
		
		db.close();
	}

}
