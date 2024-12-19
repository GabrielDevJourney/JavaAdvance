import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class TcpServer {
	public ServerSocket serverSocket = new ServerSocket(1234);
	public Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	//create clone list for every update operation done later
	private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();

	public TcpServer() throws IOException {
	}


	public void receiveMultipleMessages() throws IOException {
		while (true) {

			clientSocket = serverSocket.accept();

			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new PrintWriter(clientSocket.getOutputStream(), true);

			ClientHandler clientHandler = new ClientHandler(in, out, clientSocket);
			clients.add(clientHandler);

			new Thread(clientHandler).start();
		}
	}

	public static void broadcast(String message, ClientHandler sender) {
		for (ClientHandler client : clients) {
			if (client != sender) {
				client.sendMessage(message);
			}
		}
	}

	private static class ClientHandler implements Runnable {
		private final BufferedReader in;
		private final PrintWriter out;
		private final Socket clientSocket;
		private String username;

		public ClientHandler(BufferedReader in, PrintWriter out, Socket clientSocket) {
			this.in = in;
			this.out = out;
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				String message;
				username = getUsername();

				out.println("Welcome to chat, " + username);
				out.println("Type your message");

				while ((message = in.readLine()) != null) {
					System.out.println("[ " + username + " ] " + message);
					broadcast(username + " says: " + message, this);
				}

				in.close();
				out.close();
				clientSocket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}


		private String getUsername() throws IOException {
			out.println("Enter your username: ");
			return in.readLine();
		}

		private void sendMessage(String message) {
			out.println(message);
			out.println("Type your message");
		}
	}
}
