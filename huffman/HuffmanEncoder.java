import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class HuffmanEncoder 
{
   private String charCodes[] = new String[128];
   private Map<Character, String> charCoder = new HashMap<>();


    public HuffmanEncoder (String codeFile)
    {
        charCodes = new String[128];
        try 
        {
            BufferedReader reaeder = new BufferedReader(new FileReader((codeFile)));
            String hold;
            int pos = 0;
            while((hold = reaeder.readLine()) !=null)
            {
                charCoder.put((char) pos, hold);
                charCodes[pos] = hold;
                pos++;
            }
            reaeder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String encodeChar(char input) {
        if (!charCoder.containsKey(input)) {
            return charCoder.get((char)0);
        }
        return charCoder.get(input);
    }



    public void encodeLong(String fileToCompress, String encodedFile)
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileToCompress));
            PrintWriter writer = new PrintWriter(encodedFile);
            int character = 0;
            while ((character = reader.read()) != -1)
             {
                char chara = (char) character;
                String code = encodeChar(chara);
                writer.write(code);
            }
            reader.close();
            writer.close();
        } catch (IOException e) 
        {
            e.printStackTrace();
    }
    }

    public void encodeFile(String filetoCompress) {
        String filename = filetoCompress + ".huf"; //adds huf ending 

        try { // main loop
            BufferedReader read = new BufferedReader(new FileReader(filetoCompress)); //creates a file reader
            BufferedWriter write = new BufferedWriter(new FileWriter(filename)); // creates a file writer

            int charac = 0; // creates a character counter 
            StringBuilder encoded = new StringBuilder(); // This is the main string that the encoded elements are stored on

            boolean hasMultiChar = false;
            char distinctChar = '\0';

            while ((charac = read.read()) != -1) { // while loop that goes through each line, sets character/charac to the each line
                if(!hasMultiChar)
                {
                    distinctChar = (char) charac;
                    hasMultiChar = true;
                }
                String code = encodeChar((char) charac); // makes each line/code into a string 
                encoded.append(code); //puts the string onto the block of encoded string. Will generate all encoded parts together 
            }
            read.close(); // closes reader ---> No more reading after this point 
           
            if (!hasMultiChar || encoded.length() == 0) {
                encoded.append(encodeChar(distinctChar));
            }
    
           
           
            int space = (8 - (encoded.length() % 8)); // calculates the number of "spaces" we need these are the 0s
            if (space == 8){ space = 0; } // if there are 8 spaces, that would be one full bit (duh) so there are no spaces (duh, again)

            for (int i = 0; i < space; i++) //loop that actually adds the correct number of spaces, why doesn't this work????? 
            {
                encoded.append('0');
            }


            int charCount = (encoded.length() + 7) / 8;  // so this is the total amount of characters, but like didn't we just add the spaces? Wait that last line was stupid, cause the spaces filled out the extra 


            for (int i = 0; i < charCount; i++)
            {
            int startIndex = i * 8;
            int endIndex = Math.min(startIndex + 8, encoded.length());
            String parts = encoded.substring(startIndex, endIndex);
            int value = Integer.parseInt(parts, 2);
            write.write((char) value);
            }
            write.write(String.valueOf(space)); // why doesn't this shit work? 


            write.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}