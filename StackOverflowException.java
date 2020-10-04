
public class StackOverflowException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public StackOverflowException() {
	    super("Push method has been called on a full stack");
	  }
}
