package activeobject.servant;

import java.nio.BufferOverflowException;
import java.util.*;

public class BufferImpl implements Buffer {
	
	private Deque<Integer> buffer;
	private int capacity;
	
	public BufferImpl(int capacity) {
		this.buffer = new ArrayDeque<>(capacity);
		this.capacity = capacity;
	}

	@Override
	public void put(Integer[] elements) {
		if(buffer.size() + elements.length > capacity) {
			throw new BufferOverflowException();
		}
		
		for(Integer i : elements) {
			buffer.add(i);
		}
	}

	@Override
	public Integer[] get(int numberOfElements) {
		if(buffer.size() < numberOfElements) {
			throw new NullPointerException();
		}
		
		Integer[] returnedValues = new Integer[numberOfElements];
		for(int i=0; i<returnedValues.length; i++) {
			returnedValues[i] = buffer.pollFirst();
		}
		
		return returnedValues;
	}

	@Override
	public int size() {
		return buffer.size();
	}

	@Override
	public int capacity() {
		return capacity;
	}	

}
