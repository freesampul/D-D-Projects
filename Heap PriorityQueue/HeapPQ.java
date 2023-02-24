
public class HeapPQ<E extends Comparable<E>> implements MyPriorityQueue<E> {

	private E[] heap;
	private int objectCount;
	
	public HeapPQ()
    {
        this.heap = (E[])new Comparable[3];
        this.objectCount = 0;
    }

	//Adds obj to the Priority Queue
	public void add(E obj)
	{
		if(heap.length-1 <= objectCount)
		{
			increaseCapacity();
		}
		heap[objectCount] = obj;
		bubbleUp(objectCount);
		objectCount++;
	}
	
	//Removes and returns the MINIMUM element from the Priority Queue
	public E removeMin()
	{
		E temp = heap[0];	
		swap(0, objectCount-1);
		heap[objectCount-1] = null;
		objectCount--;
		bubbleDown(0);
		return temp;
	}
	
	//Returns the MINIMUM element from the Priority Queue without removing it
	public E peek()
	{
		return heap[0];
		
	}
	
	// Returns true if the priority queue is empty
	public boolean isEmpty()
	{
		return (objectCount == 0);
	}
	
	//Returns the number of elements in the priority queue
	public int size()
	{
		return objectCount;
	}
	
	public String toString()
	{
		StringBuffer stringbuf = new StringBuffer("[");
		for (int i = 0; i < objectCount; i++)
		{
			stringbuf.append(heap[i]);
			if (i < objectCount - 1)
				stringbuf.append(", ");
		}
		stringbuf.append("]\nor alternatively,\n");

		for(int rowLength = 1, j = 0; j < objectCount; rowLength *= 2)
		{
			for (int i = 0; i < rowLength && j < objectCount; i++, j++)
			{
				stringbuf.append(heap[j] + " ");
			}
			stringbuf.append("\n");
			if (j < objectCount)
			{
				for (int i = 0; i < Math.min(objectCount - j, rowLength*2); i++)
				{
					if (i%2 == 0)
						stringbuf.append("/");
					else
						stringbuf.append("\\ ");
				}
				stringbuf.append("\n");
			}
		}
		return stringbuf.toString();
	}
	
	//Doubles the size of the heap array
	private void increaseCapacity()
	{

		E[] beap = (E[])new Comparable[heap.length*2];
		for(int i = 0; i < heap.length; i++) {
			beap[i] = heap[i];
		}
		
		this.heap =  beap;
		
	}

	//Returns the index of the "parent" of index i
	private int parent(int i)
	{
		return ((i -1)/ 2);
	}
	//Returns the index of the *smaller child* of index i
	private int smallerChild(int i)
	{
		int leftChild = 2*i + 1;
	    int rightChild = 2*i + 2;
	    if (leftChild >= heap.length || heap[leftChild] == null)
	    	return -1;
	    if (rightChild >= heap.length  || heap[rightChild] == null)
	    return leftChild;
	    if (heap[rightChild].compareTo(heap[leftChild]) < 0) 
	        return rightChild;
	        return leftChild;
		
	}
	//Swaps the contents of indices i and j
	private void swap(int i, int j)
	{
		E temp = heap[i];
		heap [i] = heap[j];
		heap[j] = temp;
	}

	// Bubbles the element at index i upwards until the heap properties hold again.
	private void bubbleUp(int i)
	{
		while( heap[i].compareTo(heap[parent(i)]) < 0)
		{
			swap(i, parent(i));
			i = parent(i);
		}
		
		
	}
	
	// Bubbles the element at index i downwards until the heap properties hold again.
	private void bubbleDown(int i)
	{
		while(smallerChild(i) != -1 && heap[i].compareTo(heap[smallerChild(i)]) >= 0)
		{
			swap(i, smallerChild(i));
			i = smallerChild(i);
		}
		
	}
	
	private E rightChild(int i) {
		int pos = i*2 + 2;
		return heap[pos];
	}
	
	private E leftChild(int i) {
		int pos = i*2 + 1;
		return heap[pos];
	}

}
