import java.util.ArrayList;

public class NotationStack<T> implements StackInterface<T> {
	
	private Object[] elements;
	private int first, last, quantity, max;
	
	public NotationStack() {
		max = 100;
	    elements = new Object[max];
	}
	
	public NotationStack(int max) {
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

	public T pop() throws StackUnderflowException {
		
		if (isEmpty())
		{
			throw new StackUnderflowException();
		}
	    
	    @SuppressWarnings("unchecked")
		T firstElement = (T) elements[last];
	    
	    if (firstElement == null)
	    	return null;
	    else {
	    	elements[last] = null;
	    	last--;
	    	quantity--;
	    	return firstElement;
	    }
	}
	
	public T top() throws StackUnderflowException {
		
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		@SuppressWarnings("unchecked")
		T firstElement = (T) elements[last];
		return firstElement;
	}

	public boolean push(T e) throws StackOverflowException {
	    
		if (isFull()) {
			throw new StackOverflowException();
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
	    		push(t);
	    	} catch (StackOverflowException exception)
	    	{
	    		exception.getMessage();
	    	}});
	}

}