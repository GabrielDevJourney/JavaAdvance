import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
	Scanner scanner = new Scanner(System.in);
	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;

	private String clientMessageReader() {
		return scanner.nextLine();
	}

	public void keepChatting() throws IOException {

		clientSocket = new Socket("localhost", 1234);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream(), true);

		System.out.println("Enter your message until you write 'exit' !");

		new Thread(() -> {
			try{
				String serverResponse;

				//initialize inside otherwise will be infinite
				while((serverResponse = in.readLine()) != null){
					System.out.println(serverResponse);
				}

			}catch (IOException e){
				e.printStackTrace();
			}
		}).start();

		while (true){
			String userMessage = clientMessageReader();
			out.println(userMessage);
		}

	}
}
