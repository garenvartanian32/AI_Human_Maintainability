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
        ArrayList<Long> sequence = new ArrayList<>();
        sequence.add(1L);

        for (int index = 1; index <= n; index++) {
            if (index == 1) {
                sequence.add(3L);
            } else if (index % 2 == 0) {
                sequence.add(evenTerm(index));
            } else {
                // The forward reference tri(index + 1) is even, hence known outright.
                sequence.add(sequence.get(index - 1)
                        + sequence.get(index - 2)
                        + evenTerm(index + 1));
            }
        }

        return sequence;
    }

    /** Even positions have a closed form, so no recursion is needed. */
    private static long evenTerm(long index) {
        return 1 + index / 2;
    }
}