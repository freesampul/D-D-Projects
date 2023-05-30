import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class HuffmanCodeGenerator {
    
    private int[] letterFreq = new int[128];
    private Node root = null;
    private String charCodes[] = new String[128];


    public HuffmanCodeGenerator(String inputFile) throws IOException{

        File file = new File(inputFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while(reader.ready())
        {
            letterFreq[reader.read()]++;
        }
        generateTree();
        reader.close();
    }

    public int getFrequency(char c)
    {
        return letterFreq[(int) c];
    }

    public void generateTree() {
        PriorityQueue<Node> nodes = new PriorityQueue<Node>();
        for (int i = 0; i < letterFreq.length; i++) {
            if (letterFreq[i] != 0) {
                Node nodeAdd = new Node(i, letterFreq[i]);
                nodes.add(nodeAdd);
            }
        }
        if (nodes.isEmpty()) {
            throw new IllegalStateException("Empty file");
        }
        while (nodes.size() > 1) {
            Node comp1 = nodes.remove();
            Node comp2 = nodes.remove();
            Node parent = new Node(-1, comp1.getFreq() + comp2.getFreq());
            parent.setLeftChild(comp1);
            parent.setRightChild(comp2);
            nodes.add(parent);
        }
        root = nodes.remove(); 
        mapCodes();
    }
    


    public void mapCodes()
    {
        mapRecurse(root, "", charCodes);
    }


    private void mapRecurse(Node node, String code, String[] charCodes) {
        if (node.hasChildren()) {
            mapRecurse(node.getLeftChild(), code + "0", charCodes);
            mapRecurse(node.getRightChild(), code + "1", charCodes);
        } else {
            int cPos = node.getCharacter();
            if(letterFreq[cPos] != 0){
                charCodes[cPos] = code;
            }
            else {
                charCodes[cPos] = null;
            }

        }
    }
    
    
    

    public String getCode(char c) {
        int character = (int) c;
        if (character >= 0 && character < charCodes.length) {
            return charCodes[character];
        }
        return null;
    }


    public void makeCodeFile(String codeFile) throws IOException
    {
         PrintWriter writer = new PrintWriter(codeFile);
    
         for (int i = 0; i < letterFreq.length; i++) {
            Node node = new Node(i, letterFreq[i]);
            String code = charCodes[node.getCharacter()];
            if (code != null)
            writer.println(code);
            else
            writer.println("");
         }
         writer.close();
         }



         public void printTreeAndCodes() {
            System.out.println("Tree:");
            printTree();
            System.out.println("Character Codes:");
            for (int i = 0; i < charCodes.length; i++) {
                System.out.println((char)i + ": " + charCodes[i]);
            }
        }
        
        public void printTree() {
            printTreeRecursive(root, "");
        }
        
        private void printTreeRecursive(Node node, String prefix) {
            if (node == null) {
                return;
            }
            System.out.println(prefix + "└── " + node.getFreq() + " (" + (node.getCharacter() >= 0 ? (char) node.getCharacter() : "") + ")");
            printTreeRecursive(node.getLeftChild(), prefix + (node.getRightChild() != null ? "│   " : "    "));
            printTreeRecursive(node.getRightChild(), prefix + "    ");
        }



}