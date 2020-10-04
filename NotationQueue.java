import java.util.ArrayList;

public class NotationQueue<T> implements QueueInterface<T>{
	
	private Object[] elements;
	private int first, last, quantity, max;

	public NotationQueue() {
		max = 100;
	    elements = new Object[max];
	    
	}

	public NotationQueue(int max) {
		this.max = max;
	    this.first = -1;
	    this.last = -1;
	    this.quantity = 0;
	    
	    elements = new Object[max];
	}

	public boolean isEmpty() {
		
		if (quantity == 0)
			return true;
		else
			return false;

	}

	public boolean isFull() {
		
		  if (max == quantity)
			  return true;
		  else 
			  return false;
		  
	 }
	
	public int size() {
		  return quantity;
	}

	public T dequeue() throws QueueUnderflowException {
		
		if (isEmpty())
		{
			throw new QueueUnderflowException();
		}
	    
	    @SuppressWarnings("unchecked")
		
	    T firstElement = (T) elements[first];
	    if (firstElement == null)
	    	return null;
	    else {
	    	elements[first] = null;
	    	first++;
	    	quantity--;
	    	return firstElement;
	    }
	}

	public boolean enqueue(T e) throws QueueOverflowException {
	    
		if (isFull()) {
			throw new QueueOverflowException();
	    }
		else if (isEmpty()) {
			first = 0;
			last = 0;
		}
		else {
			last++;
		}
		
	    quantity++;
	    elements[last] = e;
	    return true;
	    
	}

	public String toString() {
	   
		StringBuilder string = new StringBuilder();
	   
	    for (int i = first; i <= last; i++)
	    {
	      string.append(elements[i]);
	    }
	    return string.toString();
	}

	public String toString(String delimiter) {
	    
		StringBuilder string = new StringBuilder();
	    
	    for (int i = first; i < last; i++)
	    {
	      string.append(elements[i] + delimiter);
	    }
	    
	    string.append(elements[last]);
	    return string.toString();
	}

	public void fill(ArrayList<T> list) {
		
	    ArrayList<T> array = new ArrayList<>(list);
	    
	    array.forEach(t -> {
	    	try
	    	{
	    		enqueue(t);
	    	} catch (QueueOverflowException exception)
	    	{
	    		exception.getMessage();
	    	}});
	}


	}