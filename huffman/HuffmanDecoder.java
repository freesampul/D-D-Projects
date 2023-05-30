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

private String[] decodeBits(String binary) {
    String answer = "";
    String currentBinary = "";
    while (binary.length() > 0) {
        currentBinary += binary.charAt(0);
        binary = binary.substring(1);
        if (isCode(currentBinary)) {
            answer += decodeChar(currentBinary);
            currentBinary = "";
        }
    }
    String[] decoded = { answer, currentBinary };
    return decoded;
}




//This parts a real mess!

    public void decodeFile(String encodedFile) throws IOException {
    if (encodedFile.substring(encodedFile.length() - 3).equals(".huf")) { // check file "type" (why is type in quotes?)
        throw new IllegalArgumentException("File must end in .huf yo!");
    }

    String decodedFile = encodedFile.substring(0, encodedFile.length() - 4);

    BufferedReader reader = new BufferedReader(new FileReader(encodedFile));
    BufferedReader reader2 = new BufferedReader(new FileReader(encodedFile));

    String answer = "";
    String currentBinary = "";

    //Sets up propa varaibles m8!

    int fileLength = 0;
    int exit = 99999999;
    while (reader.ready()) {
        fileLength++;
        exit = reader.read() - 48;
    } //Don't wanna read parts that dont exist :D
    String charCode = "";
    for (int i = 0; i < fileLength; i++) {
        char character = (char) reader2.read();

        charCode = Integer.toBinaryString((int) character);
        while (charCode.length()<8)
        {
            charCode = "0" + charCode;
        } //Generate the decoding for codeing into de-coded codes

        if (i < fileLength-2) { 
            currentBinary += charCode;
            System.out.print(currentBinary);
            String[] data = decodeBits(currentBinary);

            answer += data[0];
            currentBinary = data[1];
        } 
        else if  (i < fileLength-1){
            for (int j = 0; j < exit; j++)
            {
                charCode = charCode.substring(0, charCode.length()-1);
            }
            currentBinary += charCode;
            System.out.print(currentBinary);
            String[] data = decodeBits(currentBinary);

            answer += data[0];
            currentBinary = data[1];
        }
        else {
            
        }
    }
    reader.close();
    reader2.close();
    PrintWriter writer = new PrintWriter(new FileWriter(decodedFile));
    writer.write(answer);
    writer.close();
}
//It works?

}
