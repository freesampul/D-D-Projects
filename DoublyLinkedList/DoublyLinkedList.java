import java.util.Objects;


public class DoublyLinkedList {
	// Implements a circular doubly-linked list.

	private ListNode2<Nucleotide> head;
	private ListNode2<Nucleotide> tail;
	private int nodeCount;

	// Constructor: creates an empty list
	public DoublyLinkedList() {
		head = null;
		tail = null;
		nodeCount =0;
	}

	// Constructor: creates a list that contains
	// all elements from the array values, in the same order
	public DoublyLinkedList(Nucleotide[] values) {
		for(int i = 0; i < values.length; i++)
		{
			add(values[i]);
		}
	}
	
	public ListNode2<Nucleotide> getHead() {
		return head;
	}
	
	public ListNode2<Nucleotide> getTail() {
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
	public boolean contains(Nucleotide obj) {
		for(ListNode2<Nucleotide> current = head; current.getNext() == null ; current = current.getNext()){
			if(current.getValue().equals(obj) || current.getValue() == obj)
			return true;
		}
		return false;
}

	// Returns the index of the first element in equal to obj;
	// if not found, returns -1.
	public int indexOf(Nucleotide obj) {
		int pos = 0;
		for(ListNode2<Nucleotide> node = head; node != null; node.getNext()){
			if(Objects.equals(node.getValue(), obj)) {
				return pos;
			}
			pos++;
		}	
		return -1;
	}

	// Adds obj to this collection.  Returns true if successful;
	// otherwise returns false.
	public boolean add(Nucleotide obj) {
		ListNode2<Nucleotide> added = new ListNode2<Nucleotide>(obj);
		nodeCount++;
		if(nodeCount == 1){
			head = added;
			tail = added;
			return true;
		}
		added.setPrevious(tail);
		tail.setNext(added);
		tail = added;
		return true;
	}


	// Removes the first element that is equal to obj, if any.
	// Returns true if successful; otherwise returns false.
	public boolean remove(Nucleotide obj) {
		int position = indexOf(obj);
		if(position == -1) {
			return false;
		}
		ListNode2<Nucleotide> node = head;
		if(position == 0) {
			head = node.getNext();
			head.setPrevious(null);
			nodeCount--;
			return true;
		} if (position == nodeCount - 1) {
			tail = tail.getPrevious();
			tail.setNext(null);
			nodeCount--;
			return true;
		} if (nodeCount == 1) {
			tail = null;
			head = null;
			nodeCount--;
		}
		
		ListNode2<Nucleotide> removed = new ListNode2(getNodeAt(position));
        removed.getPrevious().setNext(removed.getNext().getNext());
        nodeCount--;
        removed.getNext().setPrevious(removed.getPrevious().getPrevious());
        return true;
    }


	// Returns the i-th element.               
	public Nucleotide get(int i) {
		if (i > nodeCount - 1 || i < 0 ){
			throw new IndexOutOfBoundsException();
		}
		ListNode2<Nucleotide> current = head;
		for(int j = 0; j < i; j++)
		{
			current = current.getNext();
		}
		return current.getValue();
	}

	// Replaces the i-th element with obj and returns the old value.
	public Nucleotide set(int i, Nucleotide obj) {
		int counter = 0;
        for (ListNode2<Nucleotide> node=head; node != null; node = node.getNext()) {
            if (counter == i) {
                node.setValue(obj);
                return node.getValue();
            }
            else
                counter++;
        }
        throw new IndexOutOfBoundsException();
    }
	

	// Inserts obj to become the i-th element. Increments the size
	// of the list by one.
	public void add(int i, Nucleotide obj) {
		ListNode2<Nucleotide> current = head;
		if(i == 0){
			ListNode2<Nucleotide> added = new ListNode2<Nucleotide>(obj, current, null);
			added.setNext(current);
			current.setPrevious(added);
			head = added;
			nodeCount++;
			return;
		}
		for(int j = 0; j < i - 1; j++)
		{
			current = current.getNext();
		}
		ListNode2<Nucleotide> newer = new ListNode2<Nucleotide>(obj);
		newer.setNext(current.getNext());
		newer.setPrevious(current);
		newer.getNext().setNext(newer);
		current.setNext(newer);
		nodeCount++;
		}
 
	// Removes the i-th element and returns its value.
	// Decrements the size of the list by one.
	public Nucleotide remove(int i) {
			if(i < 0 || i > nodeCount)
			{
				throw new IndexOutOfBoundsException();
			}
			ListNode2<Nucleotide> removed = head;
			
			if( i == 0)
			{
				head=head.getNext();
				return removed.getValue(); 
			} else if (i == nodeCount - 1)
			{
				removed = tail;
				tail = tail.getPrevious();
			} else {
				ListNode2<Nucleotide> current = head;
				for(int j = 0; j < i; j++){
					current = current.getNext();
				}
				removed = current;
				current = current.getPrevious();
				current.setNext(current.getNext().getNext());
			}
			return removed.getValue(); 

		}

	// Returns a string representation of this list exactly like that for MyArrayList.
	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(ListNode2<Nucleotide> current = head; current.getNext() != null; current = current.getNext()){
				sb.append(current.getValue() + "");

			if(!current.getNext().equals(null))
		   sb.append(", ");
		}
		sb.append(tail.getValue() + "");
		sb.append("]");

		return sb.toString();
		//O(n)
	}


	public String toStringBackwards() {
		StringBuilder sb = new StringBuilder("[");
		for(ListNode2<Nucleotide> current = tail; current.getPrevious() != null; current = current.getPrevious()){
				sb.append(current.getValue() + "");

			if(!current.getNext().equals(null))
		   sb.append(", ");
		}
		sb.append(head.getValue() + "");
		sb.append("]");

		return sb.toString();
		//O(n)
	}
	
	// Like question 7 on the SinglyLinkedList test:
	// Add a "segment" (another list) onto the end of this list
	public void addSegmentToEnd(DoublyLinkedList seg) {
		for (int i = 0; i < seg.size(); i++)
		{
			ListNode2<Nucleotide> node = seg.getHead();
			tail.setNext(node);
			tail = seg.getTail();
			nodeCount += seg.size();
		}
	}
	
	// Like question 8 on the SinglyLinkedList test:
	// You are to remove the next 16 nodes from the list, after the node nodeBefore.
	// (on the test these nodes were assumed to contain CCCCCCCCGGGGGGGG, but here
	// you do not need to assume or check for that)
	public void removeCCCCCCCCGGGGGGGG(ListNode2<Nucleotide> nodeBefore) {
		ListNode2<Nucleotide> node = nodeBefore;
		for(int i = 0; i <16; i++)
		{
			node = node.getNext();
		}
		if(node.getNext() == null)
		{
			tail = nodeBefore;
		}
		nodeBefore.setNext(node);
	}
	
	// Like question 9 on the SinglyLinkedList test:
	// You are to find and delete the first instance of seg in the list.
	// If seg is not in the list, return false, otherwise return true.
	public boolean deleteSegment(DoublyLinkedList seg) {
		return true;
	}
	
	// Like question 10 on the SinglyLinkedList test:
	// Delete the last three nodes in the list
	// If there are not enough nodes, return false
	public boolean deleteLastThree() {
		return true;
	}

	// Like question 11 on the SinglyLinkedList test:
	// Replaces every node containing "A" with three nodes containing "T" "A" "C"
	
	
	
	public ListNode2<Nucleotide> getNodeAt(int i) {
		ListNode2<Nucleotide> current = head;
		for(int j = 0; j < i-1; j++) {
			current = current.getNext();
		}
		return current;
	}
}