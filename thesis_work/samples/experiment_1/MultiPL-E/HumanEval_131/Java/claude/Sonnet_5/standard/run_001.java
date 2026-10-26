import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Given a positive integer n, return the product of the odd digits.
    // Return 0 if all digits are even.
    // For example:
    // >>> digits((1l))
    // (1l)
    // >>> digits((4l))
    // (0l)
    // >>> digits((235l))
    // (15l)
    public static long digits(long n) {

        String s = Long.toString(n);
        long product = 1;
        boolean any = false;
        for (char c : s.toCharArray()) {
            int d = c - '0';
            if (d % 2 == 1) {
                product *= d;
                any = true;
            }
        }
        return any ? product : 0;
    }
}