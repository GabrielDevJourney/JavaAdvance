import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	public void receiveMultipleMessages() throws IOException {
		//!if wanted to send something to client just need to use PrintWriter as in Client "out"

		String message;
		ServerSocket serverSocket = new ServerSocket(8080);
		boolean keepRunning = true;

		//accept connection
		Socket clientSocket = serverSocket.accept();

		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		while (keepRunning) {
			message = in.readLine();

			if (message.equalsIgnoreCase("exit")) {
				keepRunning = false;
				System.out.println("Client as exit chat!");
			} else {
				System.out.println("Client message: " + message);

			}

		}

		clientSocket.close();
		serverSocket.close();
	}
}
