import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // You are given a string s.
    // if s[i] is a letter, reverse its case from lower to upper or vise versa, 
    // otherwise keep it as it is.
    // If the string contains no letters, reverse the string.
    // The function should return the resulted string.
    // Examples
    // >>> solve(("1234"))
    // ("4321")
    // >>> solve(("ab"))
    // ("AB")
    // >>> solve(("#a@C"))
    // ("#A@c")
    public static String solve(String s) {
        // With no letters to flip there is nothing for the case rule to do, so
        // the specification falls back to reversing the whole string instead.
        if (!containsLetter(s)) {
            return new StringBuilder(s).reverse().toString();
        }

        return swapCase(s);
    }

    private static boolean containsLetter(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /** Flips the case of every letter, leaving other characters untouched. */
    private static String swapCase(String text) {
        char[] flipped = text.toCharArray();
        for (int i = 0; i < flipped.length; i++) {
            if (Character.isUpperCase(flipped[i])) {
                flipped[i] = Character.toLowerCase(flipped[i]);
            } else if (Character.isLowerCase(flipped[i])) {
                flipped[i] = Character.toUpperCase(flipped[i]);
            }
        }
        return new String(flipped);
    }
}