package BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	private static int port =8081;
	private String host = "127.0.0.1";
	public static void main(String [] args) throws IOException {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			Socket socket = null;
			while(true) {
				socket = server.accept();
				new Thread(new ServerHandle(socket)).start();
			}
		} finally {
			// TODO: handle finally clause
		}
	}

}
