package Server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;

public class ServerRoot {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean bServerIsOK 		= true;
		ServerSocket serverSocket	= null;
		Socket socketMain 			= null;
		OutputStreamWriter writer	= null;
		int iPort 					= 8000;
		int iCounter 				= 0;
		
		try
		{
			serverSocket = new ServerSocket(iPort);
		} catch (IOException e) {
			e.printStackTrace();
			bServerIsOK = false;
		}
		
		System.out.println(bServerIsOK ? "Server started." : "Starting faild!");
		
		//#IPM основной цикл
		while(bServerIsOK) {
			try {
				socketMain =  serverSocket.accept();
				writer = new OutputStreamWriter(socketMain.getOutputStream());
				System.out.println("Connecions: " + iCounter);
				String MessageHeader = "HTTP/1.1 200 OK\r\n"
									+ "Content-Type: text/html\r\n\r\n";
				String MessageBody = "<h2>Some number: " + iCounter + "</h2>";
				writer.write(MessageHeader + MessageBody);
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
				bServerIsOK = false;
			}
			
			iCounter++;
		}
		
		//#IPM выключаем сервер
		if(serverSocket != null)
		{
			bServerIsOK = false;
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
