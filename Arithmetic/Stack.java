import java.util.LinkedList;

public class Stack<E> {
	
	
	LinkedList<E> stack;
	
	public Stack () {
		stack = new LinkedList<>();
	}
	
	public E pop () {
		return stack.remove(stack.size()-1);
	}
	
	public E peek () {
		return stack.get(stack.size()-1);
	}

	public void push (E obj) {
	stack.add(obj);
	
	}
	public boolean isEmpty () {
		return stack.isEmpty();
	}
}
