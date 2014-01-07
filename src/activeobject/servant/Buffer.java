package activeobject.servant;

public interface Buffer {
	
	void put(Integer[] elements);

	Integer[] get(int numberOfElements);
	
	int size();
	
	int capacity();

}
