import javax.print.attribute.standard.RequestingUserName;
import javax.swing.event.InternalFrameEvent;

/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {
    
    /* Internal Object counter */
    protected int objectCount;
    
    /* Internal Object array */
    protected E [] internalArray;
    
    /* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
    public MyArrayList() {
        this.internalArray = (E[])new Object[100];
    }
    
    /* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
    public MyArrayList(int initialCapacity){
        this.internalArray = (E[])new Object[initialCapacity];
    }
    
    /* Return the number of active slots in the array list */
    public int size() {
        return objectCount;
        //O(1)
    }
    
    /* Are there zero objects in the array list? */
    public boolean isEmpty() {
        if(objectCount > 0 )
        return false;
       return true;
       //O(1)
    }
    
    /* Get the index-th object in the list. */
    public E get(int index) {
        if (internalArray[index] != null && index <=  objectCount)
        return internalArray[index];
        else if (index <=  objectCount && internalArray[index] == null)
        return null;
        throw new IndexOutOfBoundsException();

        //o(1)
    }
    
    /* Replace the object at index with obj.  returns object that was replaced. */
    public E set(int index, E obj) {
        if(index < 0 || index > objectCount){
            throw new IndexOutOfBoundsException();
        }
        E placeholder = internalArray[index];
        internalArray[index] = obj;
        return placeholder;
        //O(1)
    }

	/* Returns true if this list contains an element equal to obj;
	 otherwise returns false. */
    public boolean contains(E obj) {
    	for(int i = 0; i < internalArray.length; i++)
        {
            if(internalArray[i] == obj || internalArray[i].equals(obj))
            return true;
        }
        return false;

        //O(n)
    }
    
    /* Insert an object at index */
	@SuppressWarnings("unchecked")
    public void add(int index, E obj) {
        if(index < 0 || index > objectCount + 1){
            throw new IndexOutOfBoundsException();
        }
        objectCount++;
        if(objectCount > internalArray.length){resize(internalArray);}
        for(int i = internalArray.length-1; i > index; i--)
        {
            internalArray[i] = internalArray[i-1];
        }
        internalArray[index] = obj;
        //O(n) (could  be O(1))
    }

    /* Add an object to the end of the list; returns true */
	@SuppressWarnings("unchecked")
    public boolean add(E obj) {
        objectCount++;
        if(objectCount > internalArray.length -1){
       resize(internalArray);
        }
       internalArray[objectCount -1] = obj;
       return true;
       //O(n) (could  be O(1))
    }


    /* Remove the object at index and shift.  Returns removed object. */
    public E remove(int index) {
        if(index < 0 || index > objectCount){
            throw new IndexOutOfBoundsException();
        }
        E placeholder = internalArray[index]; 
        for(int i = index; i < objectCount -1; i++)
        {
            internalArray[i] = internalArray[i+1];
        }
        objectCount--;
        return placeholder;
        //Could be O(1), likely O(n)
    }
    

    
    /* Removes the first occurrence of the specified element from this list, 
     * if it is present. If the list does not contain the element, it is unchanged. 
     * More formally, removes the element with the lowest index i such that
     * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
     * Returns true if this list contained the specified element (or equivalently, 
     * if this list changed as a result of the call). */
    public boolean remove(E obj) {
         int location = indexOf(obj);

        if(location > -1) {
            remove(location);
            return true;
        }

        return false;
}
            //O(n)


	// This method will search list for all occurrences of obj and move them to the end
	// of the list without disrupting the order of the other elements.
	public void moveToBack(E obj){
        int count = 0;
        int arrayPos = 0;
        E[] newArray = (E[]) new Object[internalArray.length];
                  for(int i = 0; i < objectCount; i++) { 
                    if(internalArray[i].equals(obj)) {
                        count++;
                    }
                    else
                    {
                        newArray[arrayPos] = internalArray[i];
                        arrayPos++;
                    } 
                }


                for(int j = arrayPos; j < objectCount; j++)
                {
                    newArray[j] = obj;
                }
               internalArray = newArray;
    }


    public void moveToBack2(E obj){
        int objCount = 0; 
        for(int i = 0; i < objectCount; i++)
        {
            if(internalArray[i] == obj){
                objCount++;
            }
        }
        for(int j = 0; j < objCount; j++){
            remove(obj);
            add(obj);
        }
        //O(n)
    }




    
    /* For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the elements in the ArrayList.
     * If the array is empty, it should return "[]".  If there is one element, "[X]", etc.
     * Elements are separated by a comma and a space. */
    public String toString() {
           StringBuilder sb = new StringBuilder("[");
            for(int i = 0; i < objectCount; i++){
                if(internalArray[i] != null){
                    sb.append(internalArray[i] + "");
                } else if (internalArray[i] == null)
                sb.append( "" + "null");

                if(i != objectCount -1)
               sb.append(", ");
            }
            sb.append("]");
    
            return sb.toString();

            //O(n)
    }

    public void resize(E[] internalArray)
    {
        E[] newArray = (E[])new Object[internalArray.length*2];
        for(int i = 0; i < internalArray.length; i++){
            newArray[i] = internalArray[i];
        }
        this.internalArray =  newArray;
        
    }

    //Extra Practice Methods



    //Clears array, O(1)
    public void clear0(){
        E[] newArray = (E[])new Object[internalArray.length];
        this.internalArray = newArray;
        objectCount = 0;
    }

    //Clears array, O(n)
    public void clear1(){
        for(int i = 0; i < internalArray.length; i++){
            internalArray[i] = null;
        }
        objectCount = 0;
    }

    //Clears array, O(n^2????)
    public void clear2(){
        for(int i = 0; i < internalArray.length; i++)
        {
            remove(i);
        }
        objectCount = 0;
    }

    //Finds last index of object, O(n) (in worst case)
    public int lastIndexOf(E obj){
        for(int i = internalArray.length -1; i > 0; i-- ){
            if(internalArray[i] == obj && i < objectCount ){
                return i;
        }
    }
    return -1;
}


//Trims array to a specified size O(n)
public void trimToSize(){
    E[] newArray = (E[])new Object[objectCount];
    for(int i = 0; i < objectCount; i++)
    {
        newArray[i] = internalArray[i];
    }
    this.internalArray = newArray;
}


//Clones the array, O(n)
public MyArrayList<E> clone(){
    MyArrayList<E> secondArray = new MyArrayList<>();
    secondArray.internalArray = internalArray;
    secondArray.objectCount = objectCount;
    return secondArray;
    }

public int indexOf(E obj){
    for(int i = 0; i < objectCount; i++){
        if (internalArray[i] == obj || internalArray[i].equals(obj))
        {
            return i;
        }
    }
    return -1;
}
}