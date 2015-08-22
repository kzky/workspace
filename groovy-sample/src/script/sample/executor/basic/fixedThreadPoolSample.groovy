import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// FixedThreadPool のサンプルです．

service = Executors.newFixedThreadPool(3)

for(i in 0..<10) {
	service.execute(new Runnable(){
		public void run(){
				Thread.sleep(1 * 100);
				System.out.println("Hello! - " + Thread.currentThread().getId());
			}
	})
}

System.out.println("Sleeping..." + Thread.currentThread().getId());
Thread.sleep(1000);



