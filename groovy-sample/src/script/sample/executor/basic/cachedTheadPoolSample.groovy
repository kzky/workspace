import java.util.concurrent.Executors;

// CachedThreadPool のサンプル

service = Executors.newCachedThreadPool()

for (i in 0..<10) {
	service.execute(new Runnable(){
				public void run(){
					Thread.sleep(1 * 100);
					System.out.println("Hello! - " + Thread.currentThread().getId())
				}
			})
}

System.sleep(3000)
