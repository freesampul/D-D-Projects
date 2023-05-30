import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class HuffmanEncoder 
{
   private String charCodes[] = new String[128];

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
                charCodes[pos] = hold;
                pos++;
            }
            reaeder.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public String encodeChar(char input)
    {
        int character = (int) input;

        if(character < charCodes.length && character > -1) {
            if (charCodes[character] != null)
            {
                return charCodes[character];
            }
        }  
        return null;
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

            while ((charac = read.read()) != -1) { // while loop that goes through each line, sets character/charac to the each line
                String code = encodeChar((char) charac); // makes each line/code into a string 
                System.out.println(code);
                System.out.println((char) charac);
                encoded.append(code); //puts the string onto the block of encoded string. Will generate all encoded parts together 
            }
            read.close(); // closes reader ---> No more reading after this point 
            int space = (8 - (encoded.length() % 8)); // calculates the number of "spaces" we need these are the 0s
            if (space == 8){ space = 0; } // if there are 8 spaces, that would be one full bit (duh) so there are no spaces (duh, again)

            for (int i = 0; i < space; i++) //loop that actually adds the correct number of spaces, why doesn't this work????? 
            {
                encoded.append('0');
            }


            int charCount = encoded.length() / 8; // so this is the total amount of characters, but like didn't we just add the spaces? Wait that last line was stupid, cause the spaces filled out the extra 

            for (int i = 0; i < charCount; i++)
            {
                String parts = encoded.substring(i * 8, (i+1) * 8); // grabs each 8 bit line 
                int value = Integer.parseInt(parts, 2); // finds the associates value
                write.write((char) value); //writes the proppa nums into the file
            }
            write.write(String.valueOf(space)); // why doesn't this shit work? 


            write.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}