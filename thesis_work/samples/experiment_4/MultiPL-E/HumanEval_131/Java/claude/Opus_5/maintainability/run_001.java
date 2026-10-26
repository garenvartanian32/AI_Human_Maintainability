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
        long product = 1;
        boolean sawOddDigit = false;

        for (long remaining = n; remaining > 0; remaining /= 10) {
            long digit = remaining % 10;
            if (digit % 2 == 1) {
                product *= digit;
                sawOddDigit = true;
            }
        }

        // An empty product would be 1, but the specification asks for 0 instead.
        return sawOddDigit ? product : 0;
    }
}