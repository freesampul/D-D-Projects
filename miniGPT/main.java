import java.util.*;
import java.io.*;

public class main {
    public static void main (String [] args) throws IOException
    {

        Scanner myObj = new Scanner(System.in); 
    	System.out.println("Enter input");

        String word = myObj.nextLine();  
        MiniGPT.compile("As I Lay Dying.txt", word, "dye.txt", 10000);
    }
}