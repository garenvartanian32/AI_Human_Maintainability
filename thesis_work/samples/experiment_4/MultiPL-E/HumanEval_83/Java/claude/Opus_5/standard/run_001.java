import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long startsOneEnds(long n) {
        if (n == 1) return 1;
        return 18L * powerOfTen(n - 2);
    }

    private static long powerOfTen(long exponent) {
        long value = 1;
        while (exponent-- > 0) value *= 10;
        return value;
    }
}