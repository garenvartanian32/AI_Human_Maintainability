import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // You'll be given a string of words, and your task is to count the number
    // of boredoms. A boredom is a sentence that starts with the word "I".
    // Sentences are delimited by '.', '?' or '!'.
    // For example:
    // >>> isBored(("Hello world"))
    // (0l)
    // >>> isBored(("The sky is blue. The sun is shining. I love this weather"))
    // (1l)
    public static long isBored(String S) {
        String[] sentences = S.split("[.?!]");
        long count = 0;
        for (String s : sentences) {
            String trimmed = s.trim();
            if (trimmed.equals("I") || trimmed.startsWith("I ")) count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(isBored("Hello world"));
        System.out.println(isBored("The sky is blue. The sun is shining. I love this weather"));
    }
}