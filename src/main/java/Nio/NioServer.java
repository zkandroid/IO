package Nio;

import java.io.IOException;

/**
 * 因为前面的未异步io是阻塞模式的，所以nio就针对它做了相关改进，改了非阻塞模式。
 * nio的三个基本概率
 * 1，缓冲区Buffer,Buffer是一个对象，包含了写入或者要读出的数据，在nio库中，所有的数据都是用缓冲区处理的，读入和写入都是通过buffer
 * 2，通道channel，通道和strean（流）的区别在于流只是一个方向流动（所以有input和output），而通道可以用于读和写二者同时进行
 * 3，多路复用器Selector,Selector会不断轮询注册在其上的Channel,如果某个Channel上面发生读或者写事件，
 * 这个通道就处于就绪状态，会被selector提前出来，然后通过selectorkey可以获取就绪Channeld的集合
 * （jdk使用了epoll代替了select的实现，所以没有1024/2014d的限制，所以可以处理成千上万的客户端）
 * @author zk
 *
 */
public class NioServer {
	
	public static void main(String [] args) throws IOException {
		int port =8081;
		MultipServer server = new MultipServer(port);
		new Thread(server).start();
	}

}
