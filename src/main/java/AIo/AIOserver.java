package AIo;



/**
 * 异步io不像nio需要一个selector去轮询注册到其上channel，如果channel处于就绪状态就被轮询出来进行io出来，
 * aio是告诉内核启动某个操作，处理完成之后通知我们。
 * 异步socket channel是被动执行的对象。不需要像nio一样创建一个独立的io线程来处理读写操作。对于AsynchronousServerSocketChannel
 * 和AsynchronousSocketChannel，他们都是由jdk底层的线程池负责回调并驱动读写操作。所以他的编程比nio要简单
 * @author zk
 *
 */
public class AIOserver {

	public static void main(String [] args) {
		int port =8081;
		AsyncServerHandler timeServer = new AsyncServerHandler(port);
		new Thread(timeServer).start();
	}
}
