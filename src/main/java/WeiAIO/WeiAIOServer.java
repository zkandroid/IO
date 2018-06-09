package WeiAIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 弊端：inputStream是一个阻塞的io，当对方发送或者应答消息，或者网络原因等，读取输入流一方的通信线程会被长时间阻塞，其它的接入消息只能在消息队列中排队
 * 同理outputStream也是一个阻塞操作，会导致大量输出消息流阻塞在消息队列。这会导致当消息的接入方处理缓慢的时候，不能及时从tcp缓冲区读取数据，而tcp为了提高消息的传输速率，增加了一个窗口控制，它的mud
 * 是保证一定程度上消息发送出去不必立马等待ask的到底才发下一条消息。而在这里，不能及时从tcp的缓冲区读取数据则会导致这个窗口越来越小，直到到0，发送阻塞
 * 
 */
import BIO.ServerHandle;

public class WeiAIOServer {

	private static void main(String [] args) throws IOException {
		int port =8080;
		
		ServerSocket server = null;
		try {
			server =new ServerSocket(port);
			Socket socket =null;
			WeiAIOHandlerExecutePool singleExecutor = new WeiAIOHandlerExecutePool(50, 10000);
			//创建io任务线程池,因为消息队列是有界的，所以无论客户端的连接数是多大，都不会导致线程个数过于膨胀和内存溢出
			while(true) {
				socket =server.accept();
				singleExecutor.execute(new ServerHandle(socket));
			}
		} finally {
			if(server !=null) {
				server.close();
				server =null;
			}
		}
	}
}
