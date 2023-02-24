import java.util.ArrayList;

public class TreeNode {
	private ArrayList<TreeNode> children;
	private Object value;
	

	public TreeNode(Object value) {
		this.value = value;
		children = new ArrayList<TreeNode>();
	}
	public ArrayList<TreeNode> getChildren() {
		return children;
	}
	
	public void addChild(Object value) {
		children.add(new TreeNode(value));
	}
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	//returns true if the subTree rooted at this node contains obj
	//needs to be recursive to earn style points
	public boolean contains(Object obj) {
		if(value.equals(obj))
		{
			return true;
		}
		
		for(TreeNode children : children)
		{
			if(children.contains(obj)) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return value.toString();
	}
	
	//returns the toString of the subTree rooted at this node
	//needs to be recursive to earn style points
	//order is not important
	public String recursiveToString() {
		 StringBuilder str  = new StringBuilder();
		 return recurseString (this, str);
	}
	
	
	public String recurseString(TreeNode node, StringBuilder str)
	{
		str.append(node.getValue() + " ");
		for(TreeNode children : node.getChildren())
		{
			recurseString(children, str);
		}
		return str.toString();
		
	}
}
