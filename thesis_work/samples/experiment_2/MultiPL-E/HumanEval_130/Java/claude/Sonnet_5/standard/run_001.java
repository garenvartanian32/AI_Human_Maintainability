import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Everyone knows Fibonacci sequence, it was studied deeply by mathematicians in 
    // the last couple centuries. However, what people don't know is Tribonacci sequence.
    // Tribonacci sequence is defined by the recurrence:
    // tri(1) = 3
    // tri(n) = 1 + n / 2, if n is even.
    // tri(n) =  tri(n - 1) + tri(n - 2) + tri(n + 1), if n is odd.
    // For example:
    // tri(2) = 1 + (2 / 2) = 2
    // tri(4) = 3
    // tri(3) = tri(2) + tri(1) + tri(4)
    // = 2 + 3 + 3 = 8 
    // You are given a non-negative integer number n, you have to a return an array array list of the 
    // first n + 1 numbers of the Tribonacci sequence.
    // Examples:
    // >>> tri((3l))
    // (new ArrayList<Long>(Arrays.asList((long)1l, (long)3l, (long)2l, (long)8l)))
    public static ArrayList<Long> tri(long n) {
        int nn = (int) n;
        long[] result = new long[nn + 1];
        if (nn >= 0) result[0] = 1;
        for (int i = 1; i <= nn; i++) {
            if (i == 1) {
                result[i] = 3;
            } else if (i % 2 == 0) {
                result[i] = 1 + i / 2;
            } else {
                long nextVal = 1 + (i + 1) / 2;
                result[i] = result[i - 1] + result[i - 2] + nextVal;
            }
        }
        ArrayList<Long> res = new ArrayList<>();
        for (long v : result) res.add(v);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(tri(3L));
    }
}