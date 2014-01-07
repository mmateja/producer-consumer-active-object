package activeobject;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import activeobject.future.Future;
import activeobject.proxy.Proxy;

public class Producer implements Runnable {

	private Proxy proxy;
	private int maxNumberOfElementsToPut;
	private int totalNumberOfElementsLeft;
	private List<Future> futures = new LinkedList<>();

	public Producer(Proxy proxy, int maxNumberOfElementsToPut, int totalNumberOfElements) {
		this.proxy = proxy;
		this.maxNumberOfElementsToPut = maxNumberOfElementsToPut;
		this.totalNumberOfElementsLeft = totalNumberOfElements;
	}

	@Override
	public void run() {
		int numberOfElements;
		Integer[] elements;
		ThreadLocalRandom random = ThreadLocalRandom.current();

		while(totalNumberOfElementsLeft > 0) {
			numberOfElements = totalNumberOfElementsLeft <= maxNumberOfElementsToPut ? totalNumberOfElementsLeft : random.nextInt(maxNumberOfElementsToPut)+1;
			totalNumberOfElementsLeft -= numberOfElements;

			elements = new Integer[numberOfElements];
			for(int i=0; i<numberOfElements; i++) {
				elements[i] = random.nextInt(10);
			}
			futures.add(proxy.put(elements));			
		}
		
		for(Future f : futures) {
			f.awaitDone();
		}
	}

}
