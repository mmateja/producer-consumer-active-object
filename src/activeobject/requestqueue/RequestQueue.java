package activeobject.requestqueue;

import activeobject.request.Request;

public interface RequestQueue {
	
	Request getNext();
	
	void put(Request request);

}
