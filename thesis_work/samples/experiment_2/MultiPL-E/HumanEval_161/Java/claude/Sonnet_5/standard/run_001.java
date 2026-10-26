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
        boolean hasLetter = false;
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) { hasLetter = true; break; }
        }
        if (hasLetter) {
            StringBuilder sb = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isUpperCase(c)) sb.append(Character.toLowerCase(c));
                else if (Character.isLowerCase(c)) sb.append(Character.toUpperCase(c));
                else sb.append(c);
            }
            return sb.toString();
        } else {
            return new StringBuilder(s).reverse().toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(solve("1234"));
        System.out.println(solve("ab"));
        System.out.println(solve("#a@C"));
    }
}