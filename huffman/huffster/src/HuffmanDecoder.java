import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class HuffmanDecoder {

    private HashMap<String, Character> mappedCodes;

    public HuffmanDecoder(String codeFile)
    {
        mappedCodes = new HashMap<>();
        try {
            BufferedReader reaeder = new BufferedReader(new FileReader((codeFile)));
            String hold;
            int pos = 0;
            while ((hold = reaeder.readLine()) != null) {
                mappedCodes.put(hold, (char) pos);
               pos++;
            }
            reaeder.close();

        } catch (IOException e) {

            e.printStackTrace();
            
        }
    }


    public boolean isCode(String binary)
    {
        return mappedCodes.containsKey(binary);
    }


    public char decodeChar(String binary)
    {
        return mappedCodes.get(binary);
    }

    public void decodeLong(String encodedFile, String decodedFile)
    {
        try{
            BufferedReader readd = new BufferedReader(new FileReader(encodedFile));
            FileWriter write = new FileWriter(decodedFile);
            StringBuilder stringBuild = new StringBuilder();
            int current = 0;
            while ((current = readd.read()) != -1) 
            {
                char num = (char) current;
                if (num == '0' || num == '1') {
                    stringBuild.append(num);
                    String code = stringBuild.toString();
                    if (mappedCodes.containsKey(code))
                    {
                        char decode = mappedCodes.get(code);
                        write.write(decode);
                        stringBuild.setLength(0);
                    }
                }
            }
            readd.close();
            write.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void decodeFile(String encodedFile) {
        if (!encodedFile.endsWith(".huf")) {  //If statement checks the file type/ending 
            throw new IllegalArgumentException("make it dot huf, yo!");
        }


        try {
            String decodedFile = encodedFile.substring(0, encodedFile.lastIndexOf(".")); //makes the decoded file
    
            BufferedReader reader = new BufferedReader(new FileReader(encodedFile)); //reader
            PrintWriter writer = new PrintWriter(decodedFile); //writer 
            StringBuilder stringBuilder = new StringBuilder(); // string storing our codes :D 
    
            int current; // what code the program is looking at 


            while ((current = reader.read()) != -1) { // loop 
                    String binaryString = String.format("%8s", Integer.toBinaryString(current)).replace(' ', '0'); // replaces the empty spaces with 0s
                    stringBuilder.append(binaryString); // starts putting the binary into the actual string for the file (good?)
            }
            reader.close(); // stops reading. My error is definietely (idk how to spell) not before this line (i pray to god that was true)
            int paddingBits = Integer.parseInt(stringBuilder.substring(stringBuilder.length() - 8), 2); //this calculates the amount of padding i put in the first part of this god awful project
            stringBuilder.setLength(stringBuilder.length() - 8 - paddingBits);
    
            int start = 0;
            for (int end = 1; end <= stringBuilder.length(); end++) {
                String code = stringBuilder.substring(start, end);
                if (mappedCodes.containsKey(code)) {
                    char decodedChar = mappedCodes.get(code);
                    writer.write(decodedChar);
                    start = end;
                }
            }
    
            writer.close();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
