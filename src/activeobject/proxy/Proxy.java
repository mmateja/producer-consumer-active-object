package activeobject.proxy;

import activeobject.future.Future;
import activeobject.future.GetResult;
import activeobject.future.PutResult;
import activeobject.request.GetRequest;
import activeobject.request.PutRequest;
import activeobject.scheduler.Scheduler;
import activeobject.scheduler.SchedulerImpl;
import activeobject.servant.Buffer;
import activeobject.servant.BufferImpl;

public class Proxy {
	
	private Buffer buffer;
	private Scheduler scheduler;
	
	public Proxy(int bufferCapacity) {
		buffer = new BufferImpl(bufferCapacity);
		scheduler = new SchedulerImpl();
	}

	public Future put(Integer[] elements) {
		PutResult result = new PutResult();
		scheduler.enqueue(new PutRequest(buffer, result, elements));
		return result;
	}

	public Future get(int numberOfElements) {
		GetResult result = new GetResult();
		scheduler.enqueue(new GetRequest(buffer, result, numberOfElements));
		return result;
	}

}
