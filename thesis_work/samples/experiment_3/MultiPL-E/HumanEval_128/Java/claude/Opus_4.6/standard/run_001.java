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
        if (arr.isEmpty()) return Optional.empty();
        long total = 0;
        int sign = 1;
        for (long x : arr) {
            total += Math.abs(x);
            if (x < 0) sign = -sign;
            else if (x == 0) sign = 0;
        }
        return Optional.of(total * sign);
    }

    public static void main(String[] args) {
        System.out.println(prodSigns(new ArrayList<>(Arrays.asList(1L,2L,2L,-4L))));
        System.out.println(prodSigns(new ArrayList<>(Arrays.asList(0L,1L))));
        System.out.println(prodSigns(new ArrayList<>()));
    }
}