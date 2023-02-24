// Implements a singly-linked list.

public class MyHashSet {
	private ListNode[] buckets;
	private int objectCount;
	private double loadFactorLimit;

	// Constructor: creates an empty hash set with default parameters
	public MyHashSet()
	{
		this.buckets = new ListNode[10];
		this.objectCount = 0;
		this.loadFactorLimit = 0.75;
	}

	// Constructor: creates a hash set with the given initial bucket size and load factor limit
	public MyHashSet(int bucketCount, double loadFactorLimit)
	{
		this.buckets = new ListNode[bucketCount];
		this.objectCount = 0;
		this.loadFactorLimit = loadFactorLimit;
	}

	// Return a pointer to the bucket array
	public ListNode[] getBuckets() {
		return this.buckets;
	}

	// Returns true if this set is empty; otherwise returns false.
	public boolean isEmpty()
	{
		return (objectCount == 0);
	}

	// Returns the number of elements in this set.
	public int size()
	{
		return objectCount;
	}
	
	// Return the bucket index for the object
	public int whichBucket(Object obj) {
		return (0x7FFFFFFF & obj.hashCode()) % this.buckets.length;
	}

	// Returns the current load factor (objCount / buckets)
	public double currentLoadFactor() {
		return  (double) objectCount / buckets.length;
	}


	// Return true if the object exists in the set, otherwise false.
	// Use the .equals method to check equality.
	public boolean contains(Object obj) {
		int pos = whichBucket(obj);
		
		if(buckets[pos] == null) {
			return false;
		} else 
		{
			ListNode start = buckets[pos];
			while(start != null)
			{
				if(start.getValue().equals(obj))
				{
					return true;
				}
				start = start.getNext();
			}
			return false;
		}
	}

	// Add an object to the set.
	// If the object already exists in the set you should *not* add another.
	// Return true if the object was added; false if the object already exists.
	// If an item should be added, add it to the beginning of the bucket.
	// After adding the element, check if the load factor is greater than the limit.
	// - If so, you must call rehash with double the current bucket size.
	public boolean add(Object obj) {
		if(contains(obj))
		{
			return false;
		}
		
		
		int pos = whichBucket(obj);
		ListNode kevin = new ListNode(obj, null);
		
		if(buckets[pos] == null) {
			buckets[pos] = kevin;
			objectCount++;
			if(loadFactorLimit  <= currentLoadFactor()) {
				rehash(buckets.length *  2);
			}
			return true;
		} else 
		{
			ListNode start = buckets[pos];
			
			while(start != null)
			{
				if(start.getValue().equals(obj))
				{
					return false;
				}
				start = start.getNext();
			}
			kevin.setNext(buckets[pos]);
			buckets[pos] = kevin;
			objectCount++;
			if(loadFactorLimit  <= currentLoadFactor()) {
				rehash(buckets.length *  2);
			}
			return true;
		}
	}

	// Remove the object.  Return true if successful, false if the object did not exist
	public boolean remove(Object obj) {
		int pos = whichBucket(obj);
		
		if(buckets[pos] == null) {
			return false;
		} else 
		{
			ListNode before = null;
			ListNode start = buckets[pos];
			while(start != null)
			{
				if(start.getValue().equals(obj))
				{
					if(before == null) 
					{
						buckets[pos] = start.getNext();
					}else 
					{
						before.setNext(start.getNext());
					}
					objectCount--;
					return true;
				}
				before = start;
				start = start.getNext();
				
			}
			return false;
		}
	}

	// Rehash the set so that it contains the given number of buckets
	// Loop through all existing buckets, from 0 to length
	// rehash each object into the new bucket array in the order they appear on the original chain.
	public void rehash(int newBucketCount) {
		ListNode[] oldList = buckets;
		buckets = new ListNode[newBucketCount];
		for(int i = 0; i < oldList.length; i++) {
			ListNode temp = oldList[i];
			while(temp!=null)
			{
				int pos = this.whichBucket(temp.getValue());
				ListNode start = new ListNode(temp.getValue());
				start.setNext(buckets[pos]);
				buckets[pos] = start;
				temp = temp.getNext();
			}
			
		}
		
	}

	// The output should be in the following format:
	// [ #1, #2 | { b#: v1 v2 v3 } { b#: v1 v2 } ]
	// #1 is the objCount
	// #2 is the number of buckets
	// For each bucket that contains objects, create a substring that indicates the bucket index
	// And list all of the items in the bucket (in the order they appear)
	public String toString() 
	{

		
		String text = "[ " + objectCount + ", " + buckets.length + " | ";
		
		for(int k = 0; k < buckets.length; k++) {
			ListNode node = buckets[k];
			if(node != null) {
				text += "{ b" + k + ": ";
				ListNode start = buckets[k];
				while(start != null)
				{
				   text += "" + start.getValue() + " ";
				   start = start.getNext();
				}
				text += "} ";
				
			}
				
	}
		text += "]";
		return text;
	}

}
