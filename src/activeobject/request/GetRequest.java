package activeobject.request;

import activeobject.future.GetResult;
import activeobject.servant.Buffer;

public class GetRequest implements Request {

	private Buffer buffer;
	private GetResult result;
	private int numberOfElementsToGet;
	
	public GetRequest(Buffer buffer, GetResult result, int numberOfElements) {
		this.buffer = buffer;
		this.result = result;
		this.numberOfElementsToGet = numberOfElements;
	}
	
	@Override
	public boolean canBeExecuted() {
		if(buffer.size() >= numberOfElementsToGet) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		result.setResult(buffer.get(numberOfElementsToGet));
	}

}
