package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public void connectToServer(int port) {
		try (ServerSocket serverSocket = new ServerSocket(port)){
			System.out.println("Server started");
			Socket connectionSocket = serverSocket.accept();
			
			InputStream input = connectionSocket.getInputStream();
			OutputStream output = connectionSocket.getOutputStream();
			
			Scanner scanner = new Scanner(input, "UTF-8");
			PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true);
			
			serverPrintOut.println("Hello World! Enter Peace to exit.");

            //Have the server take input from the client and echo it back
            //This should be placed in a loop that listens for a terminator text e.g. bye
            boolean done = false;

            while(!done && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                serverPrintOut.println("Echo from <Your Name Here> Server: " + line);

                if(line.toLowerCase().trim().equals("peace")) {
                    done = true;
                }
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
