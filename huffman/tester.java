import java.io.IOException;

public class tester {
    public static void main(String[] args) {
        String inputFile = "ABCDEFG.txt";
        String codeFile = "codes.txt";
        HuffmanCodeGenerator hcg = null;
        HuffmanCodeGenerator sol = null;
        HuffmanCodeGenerator student = null;
        try {
            hcg = new HuffmanCodeGenerator(inputFile);
            hcg.mapCodes();
            hcg.makeCodeFile(codeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(hcg.getCode('a')); 
        System.out.println(hcg.getCode('b')); 
        System.out.println(hcg.getCode('c')); 
        System.out.println(hcg.getCode('d')); 
        System.out.println(hcg.getCode('e')); 
        System.out.println(hcg.getCode('f')); 
        System.out.println(hcg.getCode('g')); 
        System.out.println(hcg.getCode('h')); 
        
        System.out.println(hcg.getFrequency('e')); 


      

    }

}
