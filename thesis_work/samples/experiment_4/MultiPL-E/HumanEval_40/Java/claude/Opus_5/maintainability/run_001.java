import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // triples_sum_to_zero takes an array array list of integers as an input.
    // it returns true if there are three distinct elements in the array list that
    // sum to zero, and false otherwise.
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)1l, (long)3l, (long)5l, (long)0l))))
    // (false)
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)1l, (long)3l, (long)-2l, (long)1l))))
    // (true)
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l, (long)7l))))
    // (false)
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)2l, (long)4l, (long)-5l, (long)3l, (long)9l, (long)7l))))
    // (true)
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)1l))))
    // (false)
    public static boolean triplesSumToZero(ArrayList<Long> l) {
        // Sorting lets each first element be paired off with a two-pointer
        // sweep, finding a matching pair in linear rather than quadratic time.
        List<Long> values = new ArrayList<>(l);
        Collections.sort(values);

        for (int first = 0; first < values.size(); first++) {
            int low = first + 1;
            int high = values.size() - 1;

            while (low < high) {
                long total = values.get(first) + values.get(low) + values.get(high);
                if (total == 0) {
                    return true;
                }
                if (total < 0) {
                    low++;
                } else {
                    high--;
                }
            }
        }

        return false;
    }
}