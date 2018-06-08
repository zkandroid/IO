package BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clientTest {
	
	private static int port=8081;
	private static String host = "127.0.0.1";
	
	public static void main(String [] args) {
		Socket socket =null;
		BufferedReader in =null;
		PrintWriter out =null;
		try {
			socket = new Socket(host, port);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("now time");
			System.out.println("Send order to server:");
			String rsp = in.readLine();
			System.out.println("from server get rsp:"+rsp);
			out.println("test");
			System.out.println("Send order2 to server:");
			String rsp1 = in.readLine();
			System.out.println("from server get rsp:"+rsp1);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) {
				out.close();
				out=null;
			}
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in =null;
			}
			if(socket !=null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				socket =null;
			}
		}
		
	}

}
