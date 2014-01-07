package activeobject.future;

public interface Future {

	boolean done();

	Object get();
	
	void awaitDone();
	
}
