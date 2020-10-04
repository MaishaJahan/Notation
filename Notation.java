
public class Notation {
	
	private static NotationQueue<String> queue;
	private static NotationStack<String> stack;
	private final static String operations = "+-*/";
	
	private static String stackTop() {
		try
		{
			return stack.top();
		} catch (StackUnderflowException exception)
		{
			exception.getMessage();
		}
		return null;
	}
	
	private static boolean stackPush(String element) {
		try
		{
			return stack.push(element);
		} catch (StackOverflowException exception)
		{
			exception.getMessage();
		}
		return false;
	}
	
	private static String stackPop() {
		try
		{
			return stack.pop();
		} catch (StackUnderflowException exception)
		{
			exception.getMessage();
		}
		return null;
	}
	
	private static boolean enqueue(String element) {
		try 
		{
			return queue.enqueue(element);
		} catch (QueueOverflowException exception)
		{
			exception.getMessage();
		}
		return false;
	}
	
	private static String dequeue() {
		try
		{
			return queue.dequeue();
		} catch (QueueUnderflowException exception)
		{
			exception.getMessage();
		}
		return null;
	}
	
	private static int operatorOrder(char t) {
		
		if ( t == '*')
			return 1;
		else if (t == '/')
			return 1;
		else if ( t == '+')
			return 0;
		else if (t == '-')
			return 0;
		else 
			return -1;
	}
	
	private static String useOperator(String element1, String element2, char operator) throws InvalidNotationFormatException
	{
		double x = Double.parseDouble(element1);
		double y = Double.parseDouble(element2);
			
		if(operator == '+')
			return Double.toString(x + y);
		else if (operator == '-')
			return Double.toString(x - y);
		else if (operator == '*')
			return Double.toString(x * y);
		else if (operator == '/')
		{
			if (y == 0)
				throw new InvalidNotationFormatException();
			return Double.toString(x / y); 
		}
		return null;
	}
	
	public static String convertInfixToPostfix(String infixToPostfix) throws InvalidNotationFormatException
	{
		queue = new NotationQueue<String>();
	    stack = new NotationStack<String>();
	    
	    for (int i = 0; i < infixToPostfix.length(); i++)
	    {
	    	char element = infixToPostfix.charAt(i);
	    	
	    	if (Character.isDigit(element))
	    	{
	    		enqueue(Character.toString(element));
	    	}
	    	else if (element == '(' )
	    	{
	    		stackPush(Character.toString(element));
	    	}
	    	else if (element == ')')
	    	{
	    		char top = stackPop().charAt(0);
		        while (top != '(') 
		        {
		        	enqueue(Character.toString(top));
		        	if (stack.isEmpty()) 
		        	{
		        		throw new InvalidNotationFormatException();
		        	} 
		        	else {
		            top = stackPop().charAt(0);
		          }
		        }
	    	}
	    	else if (operations.indexOf(element) >= 0)
	    	{
	    		while (!stack.isEmpty() && operatorOrder(stackTop().charAt(0)) >= operatorOrder(element))
	    		{
	    			enqueue(stackPop());
	    		}
	    		stackPush(Character.toString(element));
	    	}

	    	else if ( element == ' ') 
	    	{
	    		continue;
	    	}
	    	while (!stack.isEmpty()) {
	  	      enqueue(stackPop());
	    	}}
		return queue.toString();
	  }
	
	public static String convertPostfixToInfix(String postfixToInfix) throws InvalidNotationFormatException
	{
		stack = new NotationStack<String>();
		
		for (int i = 0; i < postfixToInfix.length(); i++)
		{
			char element = postfixToInfix.charAt(i);
			
			if ( Character.isDigit(element))
			{
				stackPush(Character.toString(element));
			}
			else if (operations.indexOf(element) >= 0)
			{
				String x, y, z;
				x = stackPop().toString();
				
				if (stack.isEmpty())
					throw new InvalidNotationFormatException();
				else {
			          y = stackPop().toString();
			          z = '(' + y + element + x + ')';
			          stackPush(z);
			    }
			}
			else if ( element == ' ')
				continue;
		}
		if (stack.size() != 1) {
			
			throw new InvalidNotationFormatException();
			}
		return stackPop();
		}
				
	public static double evaluatePostfixExpression(String evaluatePostfix) throws InvalidNotationFormatException
	{
	    stack = new NotationStack<String>();
	    
	    for (int i = 0; i < evaluatePostfix.length(); i++) 
	    {
	    	
	      char element = evaluatePostfix.charAt(i);
	      
	      if (Character.isDigit(element)) 
	      {
	        stackPush(Character.toString(element));
	      }
	      else if (element == '(')
	      {
	    	  stackPush(Character.toString(element));
	      }
	      
	      else if (operations.indexOf(element) >= 0)
	      {
	        String x = stackPop().toString();
	        String y, result;
	        
	        if (stack.isEmpty())
	        {
	          throw new InvalidNotationFormatException();
	        } 
	        else 
	        {
	          y = stackPop().toString();
	          result = useOperator(y, x, element);
	          stackPush(result);
	        }
	      }
	      else if (element == ' ')
	      {
		        continue;
		  } 
	    }
	    
	    if (stack.size() != 1)
	    {
	      throw new InvalidNotationFormatException();
	    }
	    return Double.parseDouble(stackPop());
	  }

	  public static double evaluateInfixExpression(String infixExpression)
	  {
	    String postfixExpression = convertInfixToPostfix(infixExpression);
	    return evaluatePostfixExpression(postfixExpression);
	  }

	}