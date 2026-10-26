import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Given a positive integer, obtain its roman numeral equivalent as a string,
    // and return it in lowercase.
    // Restrictions: 1 <= num <= 1000
    // Examples:
    // >>> intToMiniRoman((19l))
    // ("xix")
    // >>> intToMiniRoman((152l))
    // ("clii")
    // >>> intToMiniRoman((426l))
    // ("cdxxvi")

    // Values run largest to smallest and include the subtractive pairs
    // (cm, cd, xc, ...), so a plain greedy sweep never needs to backtrack.
    private static final long[] NUMERAL_VALUES =
            {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] NUMERAL_SYMBOLS =
            {"m", "cm", "d", "cd", "c", "xc", "l", "xl", "x", "ix", "v", "iv", "i"};

    public static String intToMiniRoman(long number) {
        StringBuilder roman = new StringBuilder();
        long remaining = number;

        for (int i = 0; i < NUMERAL_VALUES.length; i++) {
            long repeats = remaining / NUMERAL_VALUES[i];
            remaining %= NUMERAL_VALUES[i];
            for (long repeat = 0; repeat < repeats; repeat++) {
                roman.append(NUMERAL_SYMBOLS[i]);
            }
        }

        return roman.toString();
    }
}