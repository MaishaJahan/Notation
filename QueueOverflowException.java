
public class QueueOverflowException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public QueueOverflowException() {
	    super("Enqueue method has been called on a full queue");
	  }

	}