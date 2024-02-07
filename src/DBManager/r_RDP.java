package DBManager;

import java.sql.Connection;

public class r_RDP extends r_BaseRecord {

	public r_RDP(Connection connection) {
		super(connection, r_RDP.class, DataLines.RDPLine.class);
	}

}
