import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long multiply(long a, long b) {
        long firstUnitDigit = Math.abs(a % 10);
        long secondUnitDigit = Math.abs(b % 10);

        return firstUnitDigit * secondUnitDigit;
    }
}