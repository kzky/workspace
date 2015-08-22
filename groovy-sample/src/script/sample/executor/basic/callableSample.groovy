import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit


public class MyTask implements Callable<Date> {
	
	public Date call() throws Exception {
		println Thread.currentThread().getId()
		return new Date()
	}
}

// これは単一．
// submitで実行．
// 値をもってくるのはget()
def service = Executors.newFixedThreadPool(3)
Future<Date> future = service.submit(new MyTask())

System.sleep(1000); 
date = future.get(1000, TimeUnit.MILLISECONDS)
println date
service.shutdown()
