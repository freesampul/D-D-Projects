import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class GenericTree {
	private TreeNode root;
	
	public GenericTree() {
		root = null;
	}
	
	public GenericTree(TreeNode root) {
		this.root = root;
	}
	
	public TreeNode getRoot() {
		return root;
	}
	
	public int size() {
		int count = 0; 
		
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty())
		{
			TreeNode save = stack.pop();
			count++;
			
			for(TreeNode children : save.getChildren())
			{
				stack.push(children);
			}
		}
		
		return count;
	}
	
	public boolean contains(Object obj) {
		if (root == null) return false;
		return root.contains(obj);
	}
	
	
	public String toString() {
		StringBuilder str  = new StringBuilder();
		str.append(root.getValue() + " ");
		for(TreeNode children : root.getChildren())
		{

		}
		return str.toString();
		
	}
	
	public String toStringStack() {
	  StringBuilder str  = new StringBuilder();
		
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty())
		{
			TreeNode save = stack.pop();
			str.append(save.getValue() + " ");
			
			for(TreeNode children : save.getChildren())
			{
				stack.push(children);
			}
		}
		
		return str.toString();
	}
	
	public String toStringQueue() {
		  StringBuilder str  = new StringBuilder();
			
			ArrayDeque<TreeNode> stack = new ArrayDeque<>();
			stack.add(root);
			
			while(!stack.isEmpty())
			{
				TreeNode save = stack.remove();
				str.append(save.getValue() + " ");
				
				for(TreeNode children : save.getChildren())
				{
					stack.add(children);
				}
			}
			
			return str.toString();
		}
	
	public String toStringRecursion() {
		return root.recursiveToString();
	}
}
