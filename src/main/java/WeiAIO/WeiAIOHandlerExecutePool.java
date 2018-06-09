package WeiAIO;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WeiAIOHandlerExecutePool {

	private ExecutorService eService;
	
	public WeiAIOHandlerExecutePool(int maxPoolSize,int queueSize) {
		eService =new 	ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(queueSize));//初始化线程池
}
	public void execute(java.lang.Runnable task) {
		eService.execute(task);//在将来的某个时候执行给定的命令
	}
}
