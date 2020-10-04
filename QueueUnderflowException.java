
public class QueueUnderflowException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QueueUnderflowException() {
	    super("Dequeue method has been called on an empty queue");
	  }

	}