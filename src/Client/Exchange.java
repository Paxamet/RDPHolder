package Client;

import java.io.IOException;
import java.net.Socket;

public class Exchange {
	public static void Start() {
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
