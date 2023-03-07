package Client;

import java.io.IOException;

public class ClientRoot {

	public static void main(String[] args) {
		OnRDPStart();
	}
	
	public static long OnRDPStart() {
		long result = -1;
		ProcessBuilder rdpProcessBuilder = new ProcessBuilder("mstsc.exe", "/v:217.147.22.90:34389");
		try {
			Process rdpProcess =  rdpProcessBuilder.start();
			if(rdpProcess.isAlive()) {
				result = rdpProcess.pid();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
