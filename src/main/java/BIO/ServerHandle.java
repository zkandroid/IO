package BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerHandle implements Runnable {
	private Socket socket;
	public ServerHandle(Socket socket) {
		this.socket =socket;
	}

	public void run() {
		BufferedReader in =null;
		PrintWriter out =null;
		try {
			in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(),true);
			String currentTime = null;
			String body =null;
			while(true) {
				body=in.readLine();
				if(body ==null) {
					break;
				}
				System.out.println("recevie client order :"+body);
				if(body.equals("now time")) {
					currentTime =Long.toString(System.currentTimeMillis());
					out.println(currentTime);
				}else {
					currentTime = "i dont kwon you mean";
					out.println(currentTime);
				}
			}
		} catch (Exception e) {
			if(in!=null) { 
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(out!=null) {
				out.close();
				out =null;
			}
			if(this.socket !=null) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.socket =null;
			}
				
				
		}

	}

}
