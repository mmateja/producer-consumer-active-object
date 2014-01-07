package activeobject.request;

import activeobject.future.PutResult;
import activeobject.servant.Buffer;

public class PutRequest implements Request {
	
	private Buffer buffer;
	private PutResult result;
	private Integer[] elementsToAdd;
	
	public PutRequest(Buffer buffer, PutResult result, Integer[] elements) {
		this.buffer = buffer;
		this.result = result;
		this.elementsToAdd = elements;
	}

	@Override
	public boolean canBeExecuted() {
		if(buffer.size() + elementsToAdd.length <= buffer.capacity()) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		buffer.put(elementsToAdd);
		result.markAsDone();
	}

}
