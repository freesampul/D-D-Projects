// Implements a singly-linked list.
import java.util.Objects;


public class SinglyLinkedList<E> {
	private ListNode<E> head;
	private ListNode<E> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public SinglyLinkedList() {
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public SinglyLinkedList(E[] values) {
		for(int i = 0; i < values.length; i++)
		{
			add(values[i]);
		}
	}
	
	public ListNode<E> getHead() {
		return head;
	}

	public ListNode<E> getTail() {
		return tail;
	}

	// Returns true if this list is empty; otherwise returns false.
	public boolean isEmpty() {
		return nodeCount == 0;
	}

	// Returns the number of elements in this list.
	public int size() {
		return nodeCount;
	}

	// Returns true if this list contains an element equal to obj;
	// otherwise returns false.
	public boolean contains(E obj) {
		for(ListNode<E> current = head; current.getNext() == null ; current = current.getNext()){
				if(current.getValue().equals(obj) || current.getValue() == obj)
				return true;
			}
			return false;
	}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(E obj) {
        int pos = 0;
        for (ListNode<E> node=head; node != null; node = node.getNext()) {
            if (Objects.equals(node.getValue(), obj)) {
                return pos;
            }
            pos++;
        }
        return -1;
    }

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(E obj) {
		ListNode<E> current = new ListNode<E>(obj);
		nodeCount++;
		if(nodeCount == 1){
			head = current;
			tail = current;
			return true;
		}
		tail.setNext(current);
		tail = current;
		return true;
	}

	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(E obj) {
		int position = indexOf(obj);
		if(position != -1){
			remove(position);
			return true;
		}
		return false;
	}

	// Returns the i-th element.               
	public E get(int i) {
		if (i > nodeCount || i < 0 ){
			throw new IndexOutOfBoundsException();
		}
		ListNode<E> current = head;
		for(int j = 0; j < i; j++)
		{
			current = current.getNext();
		}
		return current.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public E set(int i, E obj) {
        int counter = 0;
        E repl = null;
        for (ListNode<E> node=head; node != null; node = node.getNext()) {
            if (counter == i) {
                repl = node.getValue();
                node.setValue(obj);
                return repl;
            }
            else
                counter++;
        }
        throw new IndexOutOfBoundsException();
    }

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, E obj) {
	ListNode<E> current = head; 
	if(i == 0){
		ListNode<E> added = new ListNode(obj, current);
		added.setNext(current);
		head = added;
		nodeCount++;
		return;

	}
	for(int j = 0; j < i - 1; j++)
	{
		current = current.getNext();
	}
	ListNode<E> newer = new ListNode<E>(obj);
	newer.setNext(current.getNext());
	current.setNext(newer);
	nodeCount++;
	}



	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public E remove(int i) {
		if(i < 0 || i > nodeCount)
		{
			throw new IndexOutOfBoundsException();
		}
		nodeCount--;


		ListNode<E> current = head;
		if( i == 0)
		{
			head=head.getNext();
		} else if (i == nodeCount)
		{
			for(int j = 0; j < i -1; j++)
			{
			current = current.getNext();
			}
			tail = current;
			current.setNext();
		}
		for(int j = 0; j < i -1; j++)
		{
			current = current.getNext();
		}
		if(current.getNext().getNext() != null){
			current.setNext(current.getNext().getNext());
		}
		else{
			current.setNext(null);
		}
		return value;
	}




	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(ListNode<E> current = head; current.getNext() != null; current = current.getNext()){
				sb.append(current.getValue() + "");

			if(!current.getNext().equals(null))
		   sb.append(", ");
		}
		sb.append(tail.getValue() + "");
		sb.append("]");

		return sb.toString();
		//O(n)
	}
}