package Client;

import java.io.IOException;
import java.net.*;
import java.rmi.server.RMIClientSocketFactory;

public class ClientRoot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sHost		= "Localhost";
		int iPort			= 8000;
		boolean bClientIsOK	= true;
		Socket clientSocket = null;
		
		try {
			clientSocket = new Socket(sHost, iPort);
		} catch (IOException e) {
			e.printStackTrace();
			bClientIsOK = false;
		}
		
		//clientSocket
	}

}
