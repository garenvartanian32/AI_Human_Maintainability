import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long startsOneEnds(long n) {
        if (n == 1) {
            return 1;
        }

        long combinationsForMiddleDigits = powerOfTen(n - 2);
        long startsWithOne = 10L * combinationsForMiddleDigits;
        long endsWithOne = 9L * combinationsForMiddleDigits;
        long startsAndEndsWithOne = combinationsForMiddleDigits;

        return startsWithOne + endsWithOne - startsAndEndsWithOne;
    }

    private static long powerOfTen(long exponent) {
        long result = 1;

        for (long i = 0; i < exponent; i++) {
            result *= 10;
        }

        return result;
    }
}