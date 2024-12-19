import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
	Scanner scanner = new Scanner(System.in);

	private String clientOutMessageReader() {
		return scanner.nextLine();
	}

	public void singleMessage() throws IOException {

		System.out.println("Enter your message!");
		String message = clientOutMessageReader();

		Socket socket = new Socket("localhost", 8080);

		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(message);

		socket.close();
	}

	public void keepChatting() throws IOException {

	boolean keepRunning = true;
	Socket socket = new Socket("localhost", 8080);
	PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

	System.out.println("Enter your message until you write 'exit' !");

	while (keepRunning) {
		String message = clientOutMessageReader();
		out.println(message);

		if (message.equalsIgnoreCase("exit")) {
			keepRunning = false;
			System.out.println("You choose to exit chat!");
		}
	}

	//!comments line if i wanted to receive something from server
	//String response;


	//BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


	//response = in.readLine();
	//System.out.println(response);

	socket.close();
}
}
