
public class ROT13  {
    private final String regularAlpha = "abcdefghijklmnopqrstuvwxyz";
    private String startingAlpha;
    private String encryptedAlpha;

    ROT13(Character cs, Character cf) {
        startingAlpha = rotate(regularAlpha, cs);
        encryptedAlpha = rotate(startingAlpha, cf);
    }

    ROT13() {
        this('a', 'd');
    }

    public String crypt(String text) throws UnsupportedOperationException {
        return encrypt(text);
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

}
