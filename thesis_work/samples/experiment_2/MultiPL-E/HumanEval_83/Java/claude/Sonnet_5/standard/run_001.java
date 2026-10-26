import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Given a positive integer n, return the count of the numbers of n-digit
    // positive integers that start or end with 1.
    public static long startsOneEnds(long n) {
        if (n == 1) return 1;
        long pow = 1;
        for (long i = 0; i < n - 2; i++) pow *= 10;
        return 18L * pow;
    }

    public static void main(String[] args) {
        System.out.println(startsOneEnds(1));
        System.out.println(startsOneEnds(2));
        System.out.println(startsOneEnds(3));
        System.out.println(startsOneEnds(10));
    }
}