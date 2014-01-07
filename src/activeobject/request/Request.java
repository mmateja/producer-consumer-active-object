package activeobject.request;

public interface Request {
	
	boolean canBeExecuted();

	void execute();

}
