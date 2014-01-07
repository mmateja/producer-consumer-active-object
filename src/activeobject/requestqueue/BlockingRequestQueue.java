package activeobject.requestqueue;

import java.util.*;

import activeobject.request.*;

public class BlockingRequestQueue implements RequestQueue {
	
	private static final int QUEUE_INITIAL_CAPACITY = 1000;
	
	private Deque<Request> getRequests = new ArrayDeque<>(QUEUE_INITIAL_CAPACITY);
	private Deque<Request> putRequests = new ArrayDeque<>(QUEUE_INITIAL_CAPACITY);

	@Override
	public synchronized Request getNext() {
		Request request = getNextExecutableRequestOrNull();
		while(request == null) {
			try {
				wait();
			} catch(InterruptedException e) {
				// ignore
			}
			request = getNextExecutableRequestOrNull();
		}
		return request;
	}

	private Request getNextExecutableRequestOrNull() {
		if(!putRequests.isEmpty() && putRequests.getFirst().canBeExecuted()) {
			return putRequests.removeFirst();
		}
		if(!getRequests.isEmpty() && getRequests.getFirst().canBeExecuted()) {
			return getRequests.removeFirst();
		}
		return null;
	}

	@Override
	public synchronized void put(Request request) {
		if (request instanceof GetRequest) {
			getRequests.add(request);
		} else if (request instanceof PutRequest) {
			putRequests.add(request);
		} else {
			throw new IllegalArgumentException();
		}
		notify();
	}

}
