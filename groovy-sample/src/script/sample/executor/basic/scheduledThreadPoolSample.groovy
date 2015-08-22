import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit;

// scheduledThreadPoolサンプル

service = Executors.newScheduledThreadPool(3)

// fixed delay
for(i in 0..<10) {
	service.scheduleWithFixedDelay(
			new Runnable(){
				public void run() {
					println "current thread Id(fixed delay) = " + Thread.currentThread().getId()
				}
			}, 1 * 1000 , 3 * 1000, TimeUnit.MILLISECONDS)
}

// fixed rate
service.scheduleAtFixedRate(
		new Runnable(){
			public void run() {
				println "current thread Id(fixed rate) = " + Thread.currentThread().getId()
			}
		}, 1 * 1000 , 3 * 1000, TimeUnit.MILLISECONDS)


