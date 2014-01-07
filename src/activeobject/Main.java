package activeobject;

import java.util.ArrayList;
import java.util.List;

import activeobject.proxy.Proxy;

public class Main implements Runnable {

	private static final int BUFFER_CAPACITY = 50;
	private static final int CONSUMER_THREADS = 1000;
	private static final int PRODUCER_THREADS = 1000;
	private static final int ELEMENTS_PER_THREAD = 1000;

	private List<Thread> threads = new ArrayList<>(CONSUMER_THREADS+PRODUCER_THREADS);
	private Proxy proxy = new Proxy(BUFFER_CAPACITY);

	public static void main(String[] args) {
		System.out.println("Initializing...");
		Main app = new Main();
		Thread thread = new Thread(app);

		System.out.println("Performing test...");
		long time = System.currentTimeMillis();
		thread.start();
		try {			
			thread.join();			
		} catch (InterruptedException e) {
			// ignore
		}
		time = System.currentTimeMillis() - time;
		System.out.println("Finished.");
		System.out.println("Execution time: " + time + "ms");
	}

	public Main() {
		createConsumers();
		createProducers();
	}

	private void startThreads() {
		for(Thread t : threads) {
			t.start();
		}
	}

	private void joinThreads() {
		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				// ignore
			}
		}
	}

	private void createProducers() {
		for(int i=0; i<PRODUCER_THREADS; i++) {
			threads.add(new Thread(new Producer(proxy, BUFFER_CAPACITY/2, ELEMENTS_PER_THREAD)));
		}
	}

	private void createConsumers() {
		for(int i=0; i<CONSUMER_THREADS; i++) {
			threads.add(new Thread(new Consumer(proxy, BUFFER_CAPACITY/2, ELEMENTS_PER_THREAD)));
		}
	}

	@Override
	public void run() {
		startThreads();
		joinThreads();
	}

}
