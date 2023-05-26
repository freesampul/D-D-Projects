import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        if (!encodedFile.endsWith(".huf")) {
            throw new IllegalArgumentException("make it dot huf, yo!");
        }
        String decodedFile = encodedFile.substring(0, encodedFile.length() - 4);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(encodedFile));
            FileWriter writer = new FileWriter(decodedFile);

            StringBuilder binaryBuilder = new StringBuilder();
            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                String binary = String.format("%8s", Integer.toBinaryString(currentChar & 0xFF)).replace(' ', '0');
                binaryBuilder.append(binary);
                String binaryString = binaryBuilder.toString();
                if (isCode(binaryString)) {
                    char decodedChar = decodeChar(binaryString);
                    if (decodedChar != '\0') {
                        writer.write(decodedChar);
                        binaryBuilder.setLength(0);
                    }
                }
            }

            int paddingBits = reader.read();
            if (paddingBits >= 0 && paddingBits < 8) {
                binaryBuilder.setLength(binaryBuilder.length() - paddingBits);
                String lastCode = binaryBuilder.toString();
                char lastChar = decodeChar(lastCode);
                if (lastChar != '\0') {
                    writer.write(lastChar);
                }
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
