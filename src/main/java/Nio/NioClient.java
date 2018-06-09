package Nio;

public class NioClient {
	
	public static void main(String [] args) {
		
		new Thread(new MultipClient("127.0.0.1",8081)).start();
	}

}
