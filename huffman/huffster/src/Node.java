public class Node implements Comparable<Node> {
    private int character;
    private int freq;

    private Node leftChild;
    private Node rightChild;

    public Node (int character, int freq)
    {
        this.character = character;
        this.freq = freq;
    }

   public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }
   
    public void setRightChild(Node rightChild){
        this.rightChild = rightChild;
    }

    public Node getLeftChild ()
    {
        return leftChild;
    }

    public Node getRightChild ()
    {
        return rightChild;
    }

    public void setCharacter (int character)
    {
        this.character = character;
    }

    public int getCharacter ()
    {
        return character;
    }

    public void setFreq (int freq)
    {
        this.freq = freq;
    }

    public int getFreq () {
        return freq;
    }



    public int compareTo (Node compare)
    {
        if (this.freq > compare.freq)
            return 1;
        else if (this.freq < compare.freq)
            return -1;
        return 0;
    }

    public boolean hasChildren()
    {
        if(getLeftChild() == null && getRightChild() == null)
        {
            return false;
        }
        return true;
    }


}
