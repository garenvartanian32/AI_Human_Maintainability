import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Checks if given string is a palindrome
    // >>> isPalindrome((""))
    // (true)
    // >>> isPalindrome(("aba"))
    // (true)
    // >>> isPalindrome(("aaaaa"))
    // (true)
    // >>> isPalindrome(("zbcd"))
    // (false)
    public static boolean isPalindrome(String text) {
        return text.equals(new StringBuilder(text).reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(""));
        System.out.println(isPalindrome("aba"));
        System.out.println(isPalindrome("aaaaa"));
        System.out.println(isPalindrome("zbcd"));
    }
}