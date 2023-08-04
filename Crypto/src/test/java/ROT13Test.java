import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ROT13Test {
    @Test
    public void testFileEncryptAndDecrypt(){
        // Given the input
        String inputFileName = "sonnet18.txt";
        String[] expectedInput = ROT13.readFromFile(inputFileName);
        // Given the cipher
        ROT13 cipher = new ROT13();
        String[] output = cryptHelp(cipher, expectedInput);
        //Then write to a .enc file
        String outputFileName = "sonnet18.enc";
        ROT13.writeToFile(outputFileName, output);
        // NOW WE DO THE DECRYPT
        String[] encryptedInput = ROT13.readFromFile(outputFileName);
        String[] actualOutput = cryptHelp(cipher, encryptedInput);
        // Then we can test for equivalence
        Assert.assertEquals(true, Arrays.equals(expectedInput, actualOutput));
    }

    public String[] cryptHelp(ROT13 cipher, String[] inputArray){
        String[] outputArray = new String[inputArray.length];
        for(int i = 0; i < inputArray.length; i++){
            outputArray[i] = cipher.crypt(inputArray[i]);
        }
        return outputArray;
    }

    @Test
    public void rotateStringTest0() {
        // Given
        String s1 = "ABCDEF";
        String s2 = "ABCDEF";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'A');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void rotateStringTest1() {
        // Given
        String s1 = "ABCDEF";
        String s2 = "DEFABC";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'D');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void rotateStringTest2() {
        // Given
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s2 = "NOPQRSTUVWXYZABCDEFGHIJKLM";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'N');
        System.out.println(s1);
        System.out.println(actual);
        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void cryptTest1() {
        // Given
        ROT13 cipher = new ROT13('a', 'n');

        String Q1 = "Why did the chicken cross the road?";
        String A1 = "Jul qvq gur puvpxra pebff gur ebnq?";

        String Q2 = "Gb trg gb gur bgure fvqr!";
        String A2 = "To get to the other side!";

        // When
        String actual = cipher.encrypt(Q1);
        System.out.println(Q1);
        System.out.println(A1);
        // Then
        assertTrue(actual.equals(A1));

        // When
        String actual2 = cipher.decrypt(Q2);
        System.out.println(Q2);
        System.out.println(A2);
        // Then
        assertTrue(actual2.equals(A2));
    }
    @Test
    public void cryptTest2() {
        // Given
        ROT13 cipher = new ROT13('a', 'n');

        String Q1 = "Why did the chicken cross the road?";
        System.out.println(Q1);

        // When
        String actual = cipher.crypt(cipher.crypt(Q1));
        System.out.println(actual);
        // Then
        assertTrue(actual.equals(Q1));
    }

}