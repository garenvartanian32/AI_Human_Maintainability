import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long digitSum(String s) {
        long asciiSum = 0;

        for (char character : s.toCharArray()) {
            if (isUppercaseAsciiLetter(character)) {
                asciiSum += character;
            }
        }

        return asciiSum;
    }

    private static boolean isUppercaseAsciiLetter(char character) {
        return character >= 'A' && character <= 'Z';
    }
}