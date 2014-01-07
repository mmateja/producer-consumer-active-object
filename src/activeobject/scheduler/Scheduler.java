package activeobject.scheduler;

import activeobject.request.Request;

public interface Scheduler {

	void enqueue(Request request);
	
}
