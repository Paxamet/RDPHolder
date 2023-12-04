package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientRoot {

	public static void main(String[] args) {
		System.out.println("Client::BEGIN");
		long processID = OnRDPStart();
		
		CheckRDPProcess(processID);
		
		System.out.println("Client::END");
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
	
	public static void CheckRDPProcess(long processID) {
		boolean isRDPProcessFinded = true;
		while(isRDPProcessFinded) {
			isRDPProcessFinded = false;
			Process process = null;
			try {
				process = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
						
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				for(String processStr = input.readLine(); processStr != null; processStr = input.readLine()) {
					String[] processArr = processStr.split(",");
					if(processArr.length < 2) {
						System.out.println("ERROR processStr: " + processStr);
						continue;
					}
					
					if(Integer.valueOf(processArr[1].replace("\"", "")) == processID) {
						isRDPProcessFinded = true;
						System.out.println("Process " + processID + " is running");
						Thread.sleep(3 * 1000);
						break;
					}
				}
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Process " + processID + " is finished");
	}

}
