package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.*;
import java.io.*;

public class Client {

	public void sendAndReceiveMessage(String message) throws IOException {

		DatagramSocket socket = new DatagramSocket();
		InetAddress address = InetAddress.getByName("127.0.0.1");

		byte[] sendBuffer = message.getBytes();
		byte[] receiveData = new byte[1024];

		DatagramPacket sendPacket = new DatagramPacket(sendBuffer,
				sendBuffer.length,
				address,
				9876);

		socket.send(sendPacket);


		//*RECEIVE SOCKET LOGIC

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		socket.receive(receivePacket);

		// Show response
		String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
		System.out.println("Server says: " + response);

		socket.close();
	}


}
