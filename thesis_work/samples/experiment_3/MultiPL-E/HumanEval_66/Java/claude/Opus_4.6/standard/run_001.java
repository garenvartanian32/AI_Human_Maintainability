import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Task
    // Write a function that takes a string as input and returns the sum of the upper characters only'
    // ASCII codes.
    // Examples:
    // >>> digitSum((""))
    // (0l)
    // >>> digitSum(("abAB"))
    // (131l)
    // >>> digitSum(("abcCd"))
    // (67l)
    // >>> digitSum(("helloE"))
    // (69l)
    // >>> digitSum(("woArBld"))
    // (131l)
    // >>> digitSum(("aAaaaXa"))
    // (153l)
    public static long digitSum(String s) {
        long total = 0;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) total += (int) c;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(digitSum(""));
        System.out.println(digitSum("abAB"));
        System.out.println(digitSum("abcCd"));
        System.out.println(digitSum("helloE"));
        System.out.println(digitSum("woArBld"));
        System.out.println(digitSum("aAaaaXa"));
    }
}