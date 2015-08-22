import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

// Callable/Future のサンプル．

public class MyTask implements Callable<Date> {
	public Date call() throws Exception {
		Thread.sleep(1)

		println Thread.currentThread().getId()
		return new Date()
	}
}



list = new ArrayList<Future<Date>>()
for(i in 0..<100) {
	list.push(new MyTask())
}
def service = Executors.newFixedThreadPool(3)
def futures = service.invokeAll(list)

futures.each {
	println it.get()
}






