package activeobject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import activeobject.future.Future;
import activeobject.proxy.Proxy;

public class Consumer implements Runnable {

	private Proxy proxy;
	private int maxNumberOfElementsToGet;
	private int totalNumberOfElementsLeft;
	private List<Future> futures = new LinkedList<>();
	
	public Consumer(Proxy proxy, int maxNumberOfElementsToGet, int totalNumberOfElements) {
		this.proxy = proxy;
		this.maxNumberOfElementsToGet = maxNumberOfElementsToGet;
		this.totalNumberOfElementsLeft = totalNumberOfElements;
	}

	@Override
	public void run() {
		int numberOfElements;
		ThreadLocalRandom random = ThreadLocalRandom.current();
		
		while(totalNumberOfElementsLeft > 0) {
			numberOfElements = totalNumberOfElementsLeft <= maxNumberOfElementsToGet ? totalNumberOfElementsLeft : random.nextInt(maxNumberOfElementsToGet)+1;
			totalNumberOfElementsLeft -= numberOfElements;
			
			futures.add(proxy.get(numberOfElements));
		}
		
		for(Future f : futures) {
			f.awaitDone();
		}
	}

}
