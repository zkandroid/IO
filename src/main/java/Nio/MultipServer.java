package Nio;

import java.io.IOException;
import java.net.InetSocketAddress;

import java.nio.ByteBuffer;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class MultipServer implements Runnable {

	private Selector selector;
	
	private ServerSocketChannel sChannel;
	
	private volatile boolean stop;
	
	public MultipServer(int port) throws IOException {
		try {
			selector =Selector.open();
			sChannel = ServerSocketChannel.open();
			sChannel.configureBlocking(false);//设置成非阻塞
			sChannel.socket().bind(new InetSocketAddress(port),1024);
			sChannel.register(selector, SelectionKey.OP_ACCEPT);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void stop() {
		this.stop =true;
	}
	
	
	public void run() {
		while(!stop) {
		try {
			selector.select(100000);
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> it =selectionKeys.iterator();
			SelectionKey key= null;
			while(it.hasNext()) {
				key =it.next();
				it.remove();
				try {
					handleInput(key);//处理这个key
				} catch (Exception e) {
					e.printStackTrace();
					if(key !=null) {
						key.cancel();
						if(key.channel() !=null) {
							key.channel().close();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
	}
	private void handleInput(SelectionKey key) throws IOException {
		//System.out.println("---");
		if (key.isValid()) {
			//处理新连接请求
			if(key.isAcceptable()) {
				//System.out.println("++");
				ServerSocketChannel ssc =(ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				sc.register(selector, SelectionKey.OP_READ);
			}
			if(key.isReadable()) {
				//System.out.println("rrrr");
				//读数据
				SocketChannel sc=(SocketChannel) key.channel();
				ByteBuffer readBuffer =ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if(readBytes>0) {
					readBuffer.flip();
					byte [] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes, "UTF-8");
					System.out.println("recevive order: "+body);
					String cString ="i do kwon";
					doWrite(sc,cString);
				}else if (readBytes<0) {
					key.cancel();
					sc.close();
				}else {
					;
				}
			}
		}
		
	}
	private void doWrite(SocketChannel sc, String rsp) throws IOException {
		if(rsp !=null && rsp.trim().length()>0) {
			byte [] bytes = rsp.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			sc.write(writeBuffer);
			System.out.println("Send 2 client ");
		}
		
		
	}

}
