import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Given a positive integer, obtain its roman numeral equivalent as a string,
    // and return it in lowercase.
    // Restrictions: 1 <= num <= 1000
    // Examples:
    // >>> intToMiniRoman((19l))
    // ("xix")
    // >>> intToMiniRoman((152l))
    // ("clii")
    // >>> intToMiniRoman((426l))
    // ("cdxxvi")
    public static String intToMiniRoman(long number) {

        long[] vals = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] syms = {"m","cm","d","cd","c","xc","l","xl","x","ix","v","iv","i"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < vals.length; i++) {
            while (number >= vals[i]) {
                sb.append(syms[i]);
                number -= vals[i];
            }
        }
        return sb.toString();
    }
}