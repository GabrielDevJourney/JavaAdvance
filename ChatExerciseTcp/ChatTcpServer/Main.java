import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		TcpServer server = new TcpServer();
		server.receiveMultipleMessages();
	}
}
