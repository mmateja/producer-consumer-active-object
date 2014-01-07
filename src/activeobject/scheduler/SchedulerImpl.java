package activeobject.scheduler;

import activeobject.request.Request;
import activeobject.requestqueue.BlockingRequestQueue;
import activeobject.requestqueue.RequestQueue;

public class SchedulerImpl implements Scheduler {
	
	private RequestQueue queue = new BlockingRequestQueue();
	
	public SchedulerImpl() {
		startExecutingThread();
	}
	
	private void startExecutingThread() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				while(true) {
					Request request = queue.getNext();
					request.execute();
				}
			}
			
		});
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void enqueue(Request request) {
		queue.put(request);
	}

}
