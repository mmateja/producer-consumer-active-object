package activeobject.future;

public class GetResult implements Future {
	
	private Integer[] result;

	@Override
	public synchronized boolean done() {
		return result != null;			
	}

	@Override
	public synchronized Integer[] get() {
		if(result == null) {
			throw new IllegalStateException();
		}
		return result;
	}

	public synchronized void setResult(Integer[] result) {
		this.result = result;
		notify();
	}

	@Override
	public synchronized void awaitDone() {
		while(!done()) {
			try {
				wait();
			} catch(InterruptedException e) {
				// ignore
			}
		}
	}

}
