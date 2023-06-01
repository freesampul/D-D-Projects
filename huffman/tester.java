import java.util.*;
import java.io.*;

public class tester {
    public static void main (String[] args) throws IOException
    {
        HuffmanCodeGenerator gn = new HuffmanCodeGenerator("ABCDEFG.txt"); 
        gn.makeCodeFile("code.txt");
        System.out.println(gn.getFrequency('a'));

        HuffmanEncoder encoder = new HuffmanEncoder("code.txt");
        HuffmanDecoder decoder = new HuffmanDecoder("code.txt");
        decoder.decodeLong("encodedfile.txt", "decodedfile.txt");
        encoder.encodeFile("ABCDEFG.txt");
        decoder.decodeFile("ABCDEFG.txt.huf");
    }
}
