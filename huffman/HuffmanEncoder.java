import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class HuffmanEncoder 
{
   String charCodes[] = new String[128];

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
        String filename = filetoCompress + ".huf";

        try {
            BufferedReader read = new BufferedReader(new FileReader(filetoCompress));
            BufferedWriter write = new BufferedWriter(new FileWriter(filename));

            int pos1 = 0;
            int pos2 = 0;
            String line;
            while ((line = read.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    String code = charCodes[c];
                    for (char bit : code.toCharArray()) {
                        pos1 <<= 1;
                        if (bit == '1') {
                            pos1 |= 1;
                        }
                        pos2++;
                        if (pos2 == 8) {
                            write.write(String.valueOf(pos1));
                            pos1 = 0;
                            pos2 = 0;
                        }
                    }
                }
            }

            if (pos2 > 0) {
                pos1 <<= (8 - pos2);
                write.write(String.valueOf(pos1));
            }

            read.close();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}