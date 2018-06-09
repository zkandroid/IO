package AIo;

public class AIOClient {
	
	public static void main(String [] args) {
		int port =8081;
		new Thread(new AsyncClientHandler("127.0.0.1", port)).start();
	}

}
