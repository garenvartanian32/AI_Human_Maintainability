import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // You are given an array array list arr of integers and you need to return
    // sum of magnitudes of integers multiplied by product of all signs
    // of each number in the array array list, represented by 1, -1 or 0.
    // Note: return null for empty arr.
    // Example:
    // >>> prodSigns((new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)2l, (long)-4l))))
    // Optional.of(9l)
    // >>> prodSigns((new ArrayList<Long>(Arrays.asList((long)0l, (long)1l))))
    // Optional.of(0l)
    // >>> prodSigns((new ArrayList<Long>(Arrays.asList())))
    // Optional.empty()
    public static Optional<Long> prodSigns(ArrayList<Long> arr) {
        if (arr.isEmpty()) {
            return Optional.empty();
        }

        long totalMagnitude = 0;
        int negativeCount = 0;

        for (long value : arr) {
            // A single zero makes the whole sign product zero.
            if (value == 0) {
                return Optional.of(0L);
            }
            if (value < 0) {
                negativeCount++;
            }
            totalMagnitude += Math.abs(value);
        }

        // The sign is negative exactly when an odd number of factors are.
        return Optional.of(negativeCount % 2 == 0 ? totalMagnitude : -totalMagnitude);
    }
}