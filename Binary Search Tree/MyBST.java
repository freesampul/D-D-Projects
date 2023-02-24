// Implements a BST with BinaryNode nodes

public class MyBST<E extends Comparable<E>> {
	
	private BinaryNode<E> root;  // holds the root of this BST

	// Constructor: creates an empty BST.
	public MyBST() {
		root = null;
	}
	
	//bloop!

	public BinaryNode getRoot() {
		return root;
	}

	// Returns true if this BST contains value; otherwise returns false.
	public boolean contains(E value)
	{
		return containsHelper(value, root);
	}
	
	public boolean containsHelper(E value, BinaryNode<E> current)
	{
		if(root == null)
			return false;
		
		if(current.getValue() == value)
		{
			return true;
		}
		
		if (current.getValue().compareTo(value) < 0) {
			if (!current.hasRight()) {
				return false;
			} else {
				return containsHelper(value, current.getRight());
			}
		} else if (current.getValue().compareTo(value) > 0) {
			if (!current.hasLeft()) {
				return false;
			} else {
				return containsHelper(value, current.getLeft());
			}
		}
		return false;
	}
	// Adds value to this BST, unless this tree already holds value.
	// Returns true if value has been added; otherwise returns false.
	public boolean add(E value)
	{
		if(contains(value))
			return false;
		
		BinaryNode<E> temp =  new BinaryNode<E> (value);
		if (root == null)
		{
			root = temp;
			return true;
		}
		else
		{
			return addMethod(temp, root);
		}
	}
	
	
	public boolean addMethod(BinaryNode<E> temp, BinaryNode<E> current)
	{
		if(current.getValue() == temp.getValue())
		{
			return false;
		}
		
		if(temp.getValue().compareTo(current.getValue()) > 0)
		{
			if (!current.hasRight())
			{
				current.setRight(temp);
				return true;
			}
			
				return addMethod(temp, current.getRight());
				
		} 
		
		else if(temp.getValue().compareTo(current.getValue())  < 0)
			{
				if (!current.hasLeft())
				{
					current.setLeft(temp);
					return true;
				}
					return addMethod(temp, current.getLeft());
			}
		return false;
	}
	

	// Removes value from this BST.  Returns true if value has been
	// found and removed; otherwise returns false.
	public boolean remove(E value)
	{
		if(!contains(value))
			return false;
		if(root == null)
		{
			return false;
		}
		if(root.getValue().equals(value)) {
			if(!root.hasChildren())
			{
				root = null;
				return true;
			}
			swapROOT(root);
			return true;
		}
		
		BinaryNode<E> toRemove = recursiveSearch(root, value);
		BinaryNode<E> parent = getParent(root, toRemove);
		
		if (!toRemove.hasChildren()) 
		{
			if (parent.getLeft() == null) {
				parent.setRight(null);
				return true;
			} 
			else if (parent.getLeft().getValue().equals(value)) {
				parent.setLeft(null);
				return true;
			} 
			else if (parent.getRight() == null) {
				parent.setLeft(null);
				return true;
			} 
			else if (parent.getRight().getValue().equals(value)) {
				parent.setRight(null);
				return true;
			}
		}
		if (toRemove.getLeft() == null && toRemove.getRight() != null) {
			if (parent.getValue().compareTo(toRemove.getValue()) > 0) {
				parent.setLeft(toRemove.getRight());
				return true;
			}
			else
			{
			parent.setRight(toRemove.getRight());
			}
			return true;
		}
		
		
		switcharoo(toRemove);
		return true;
		
		
	}


	
	
	public void swapROOT(BinaryNode<E> current){
		BinaryNode<E> top = current.getLeft();
		BinaryNode<E> parent = current;
		if(top != null) {
			while (top.hasRight()) {
				parent = top;
				top = top.getRight();
			}
		} else {
			top = current.getRight();
			while (top.hasLeft()) {
				parent = top;
				top = top.getLeft();
			}
		}
		
		
		current.setValue(top.getValue());
		if (!current.hasLeft()) {
			this.root = current.getRight();
			return;
		}
		if (parent == current)
			parent.setLeft(top.getLeft());
		else
			parent.setRight(top.getLeft());
	}
	
	
	
	public BinaryNode<E> getParent(BinaryNode<E> current, BinaryNode<E> value){
		if(current.equals(value))
			return null;
		if(current.getLeft() != null && current.getLeft().equals(value))
			return current;
		else if (current.getRight() != null && current.getRight().equals(value))
			return current;
		else {
			BinaryNode<E> parent = null;
			if(current.hasLeft())
				parent = getParent(current.getLeft(), value);
			if(parent == null && current.getRight() != null)
				parent = getParent(current.getRight(), value);
			return parent;
		}
		}
	
	public BinaryNode<E> recursiveSearch(BinaryNode<E> current, E value){
		
		if (current.getValue() == value) {
			return current;
		} else 
		{
			if (current.getValue().compareTo(value) < 0) 
			{
				return recursiveSearch(current.getRight(), value);
			}
			if (current.getValue().compareTo(value) > 0)
			{
				return recursiveSearch(current.getLeft(), value);
			}
		}
		return null;
	}

	
	
	
	public void switcharoo(BinaryNode<E> swap){
		BinaryNode<E> top = swap;
		if(swap.hasLeft()) {
			top = swap.getLeft();
		}
		BinaryNode<E> aboveTop = swap;
		while(top.hasRight()) {
			aboveTop = top;
			top = top.getRight();  //Wow these variable names do not make sense!~
			
			
		}
		swap.setValue(top.getValue());
		if(top.equals(swap))
			aboveTop.setLeft(top.getLeft());
		else
			aboveTop.setRight(top.getLeft());
	}
	
	
	
	// Returns a bracket-surrounded, comma separated list of the contents of the nodes, in order
	// e.g. ["Apple", "Cranberry", "Durian", "Mango"]
	public String toString()
	{
		if(root == null)
		{
			return "[]";
		}
		else {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		recursivetoString(sb, root);
		sb.delete(sb.length()-2, sb.length());
		sb.append("]");
		return sb.toString();
		}
	}
	
	public void recursivetoString(StringBuilder sb, BinaryNode<E> current)
	{
		if(current == null)
		{
			return;
		}
		if(current.hasLeft())
		recursivetoString(sb, current.getLeft());
		sb.append(current.getValue());
		sb.append(", ");
		if(current.hasRight())
		recursivetoString(sb, current.getRight());
	}

	
}
