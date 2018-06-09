package Nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultipClient implements Runnable {
	
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	
	public MultipClient(String host,int port) {
		this.host=host;
		this.port=port;
		try {
			selector =Selector.open();
			socketChannel =SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void run() {
		try {
			doConnect();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> it =selectionKeys.iterator();
				SelectionKey key= null;
				while(it.hasNext()) {
					key =it.next();
					it.remove();
					try {
						handleInput(key);//处理这个key
					} catch (Exception e) {
						if(key !=null) {
							key.cancel();
							if(key.channel() !=null) 
								key.channel().close();
							
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			
		}
		//如果多路复用器没有关闭，则关闭
		if(selector !=null) 
			try {
				selector.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}

	}

	private void handleInput(SelectionKey key) throws IOException {
		
		if(!key.isValid()) {
			//如果连接不成功
			System.out.println(" no key.isValid");
			return;
		}
		SocketChannel sc = (SocketChannel) key.channel();
		if(key.isConnectable()) {
			System.out.println("---");
			if(sc.finishConnect()) {
				sc.register(selector, SelectionKey.OP_READ);
				doWrite(sc);
			}else {
				return;
			}
			if(key.isReadable()) {
				//读数据
				ByteBuffer readBuffer =ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if(readBytes>0) {
					readBuffer.flip();
					byte [] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("client recevive order: "+body);
					this.stop=true;
				}else if (readBytes<0) {
					key.cancel();
					sc.close();
				}else 
					;
				
			}
		}
		
	}

	private void doWrite(SocketChannel sc) throws ClosedChannelException, IOException {
		byte [] req = " hello server".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		sc.write(writeBuffer);
		if(!writeBuffer.hasRemaining())
			System.out.println("Send order 2 server succeed");
		
	}

	private void doConnect() throws ClosedChannelException, IOException {
		//如果连接成功，则注册到多路复用器上，发送请求消息，读应答
				if(socketChannel.connect(new InetSocketAddress(host, port))) {
					socketChannel.register(selector, SelectionKey.OP_READ);
					doWrite(socketChannel);
				}else {
					socketChannel.register(selector, SelectionKey.OP_CONNECT);
				}
		
	}

}
