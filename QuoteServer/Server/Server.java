package Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {

	public void receiveAndSendMessage() throws IOException {
		DatagramSocket socket = new DatagramSocket(9876);

		byte[] receiveData = new byte[1024];
		byte[] sendData;
		String message;

		while (true) {

			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);

			message = new String(receivePacket.getData(), 0, receivePacket.getLength());

			if (message.equals("hit me")) {
				message = "Keep showing up take what is there to be taken!";
			} else {
				message = "Unsupported message";
			}


			//*SEND DATA

			sendData = message.getBytes();

			InetAddress clientAddress = receivePacket.getAddress();
			int clientPort = receivePacket.getPort();

			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length,
					clientAddress,
					clientPort);

			socket.send(sendPacket);
		}
	}
}
