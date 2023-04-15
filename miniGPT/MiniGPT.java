import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MiniGPT {

    HashMap<String, ArrayList<String>> hash = new  HashMap<String, ArrayList<String>>(); 

    public MiniGPT(String fileName, int chainOrder) throws IOException{
        try {
            File file = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

ArrayList<String> words = new ArrayList<String>();

for (int i = 0; i < chainOrder; i++) {
    		int charCode = reader.read();
    		if (charCode == -1) {
        	break;
   		 }
    		char c = (char) charCode;
   			 words.add(Character.toString(c));
			}
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordsInLine = line.split("\\s+");
                for (String next : wordsInLine) {
                    String key = "";
                    for (int j = 0; j < words.size(); j++) {
                        key += words.get(j);
                    }
                    if (!hash.containsKey(key)) {
                        System.out.println(key);
                        hash.put(key, new ArrayList<String>());
                    }
                    hash.get(key).add(next);

                    words.remove(0);
                    ArrayList<String> temp = words;
                    words = new ArrayList<String>();
                    for (int k = 0; k < temp.size(); k++) {
                        words.add(temp.get(k));
                    }
                    words.add(next);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.print(e);
        }
    }

    public static void compile(String inputFile, String inputString, String outputFile, int numWords) throws IOException {
        String[] string = inputString.split(" ");
        int order = string.length;
        MiniGPT gpt = new MiniGPT(inputFile, order);
        gpt.generateText(outputFile, numWords, inputString);
    }

    
    public void generateText(String outputFileName, int numWords, String inputString) throws FileNotFoundException {
		if (inputString == null) {
			return;
		}
		String key = "";
		String currentString = inputString;
		
		String[] words = currentString.split(" ");
		ArrayList<Integer> lengths = new ArrayList<Integer>();
		for (int i = 0; i < words.length; i++) {
			key += words[i];
			lengths.add(words[i].length());
		}
		PrintWriter print = new PrintWriter(new File(outputFileName));
		print.print(inputString + " ");
	
		for (int k = 0; k < numWords; k++) {
			if (hash == null || !hash.containsKey(key)) {
				break;
			}
			int index = (int) (Math.random() * hash.get(key).size());
			String nextChar = hash.get(key).get(index);
			System.out.println("Key: " + key);
			System.out.println("Next word: " + nextChar);
			key = key.substring(lengths.get(0));
			lengths.remove(0);
			lengths.add(nextChar.length());
			key += nextChar;
			print.print(nextChar + " ");
		}
		print.close();
	}
	
    }
