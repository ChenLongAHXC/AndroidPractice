package test.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestSocketForUDP {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket=new DatagramSocket(4567);
		InetAddress address=InetAddress.getByName("");
		byte[] bytes="abcd".getBytes();
		DatagramPacket packet=new DatagramPacket(bytes, bytes.length, address,4567);
		socket.send(packet);
	}
}
