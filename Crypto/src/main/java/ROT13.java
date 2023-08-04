
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ROT13  {
    private final String regularAlpha = "abcdefghijklmnopqrstuvwxyz";
    private final String vowels = "aeiouy";
    private String startingAlpha;
    private String encryptedAlpha;

    ROT13(Character cs, Character cf) {
        startingAlpha = rotate(regularAlpha, cs);
        encryptedAlpha = rotate(startingAlpha, cf);
    }

    ROT13() {
        this('a', 'm');
    }

    public String crypt(String text) throws UnsupportedOperationException {
        if(hasVowelsPerWord(text)) {
            return encrypt(text);
        }
        else{
            return decrypt(text);
        }
    }

    private boolean hasVowelsPerWord(String text) {
        String[] words = text.toLowerCase().split(" ");
        boolean containsVowel;
        for(String singleWord : words){
            containsVowel = false;
            for(char c : singleWord.toCharArray()){
                containsVowel = containsVowel || (vowels.indexOf(c) != -1);
            }
            if(!singleWord.equals("") && !containsVowel){
                return false;
            }
        }
        return true;
    }

    public String encrypt(String text) {
        return doTheSwap(text, startingAlpha, encryptedAlpha);
    }

    public String decrypt(String text) {
        return doTheSwap(text, encryptedAlpha, startingAlpha);
    }

    private String doTheSwap(String textToSwap, String srcAlpha, String destAlpha){
        StringBuilder sb = new StringBuilder();
        for(char c : textToSwap.toCharArray()){
            if(!Character.isLetter(c)){
                // first check if this character even IS a letter
                sb.append(c);
                continue;
            }
            char destChar = destAlpha.charAt(srcAlpha.indexOf(Character.toLowerCase(c)));;
            if(Character.isUpperCase(c)){
                // oh no! what if the character is upper case
                destChar = Character.toUpperCase(destChar);
            }
            sb.append(destChar);
        }
        return sb.toString();
    }

    public static String rotate(String s, Character c) {
        int indexOfChar = s.indexOf(c);
        if(Character.isUpperCase(c)){
            // index not found means its a uppercase letter
            indexOfChar = s.indexOf((c +"").toUpperCase());
        }
        return s.substring(indexOfChar, s.length()) + s.substring(0, indexOfChar);
    }

    // for reading from the file
    public static String[] readFromFile(String fileName){
        String directoryName = System.getProperty("user.dir");
        ArrayList<String> fileAsLines = new ArrayList<>();
        try {
            Scanner fileIn = new Scanner(new File(directoryName + "/" + fileName));
            while(fileIn.hasNext()){
                fileAsLines.add(fileIn.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
            throw new RuntimeException(e);
        }
        return fileAsLines.toArray(fileAsLines.toArray(new String[fileAsLines.size()]));
    }

    public static void writeToFile(String outputFileName, String[] arrayToWrite) {
        String directoryName = System.getProperty("user.dir");
        try {
            PrintWriter fileOut = new PrintWriter(directoryName + "/" + outputFileName);
            for(String s : arrayToWrite){
                fileOut.println(s);
            }
            fileOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!");
            throw new RuntimeException(e);
        }
    }
}
