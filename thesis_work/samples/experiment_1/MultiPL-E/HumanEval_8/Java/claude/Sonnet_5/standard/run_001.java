import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // For a given array list of integers, return a pair consisting of a sum and a product of all the integers in an array array list.
    // Empty sum should be equal to 0 and empty product should be equal to 1.
    // >>> sumProduct((new ArrayList<Long>(Arrays.asList())))
    // (Pair.with(0l, 1l))
    // >>> sumProduct((new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l, (long)4l))))
    // (Pair.with(10l, 24l))
    public static Pair<Long, Long> sumProduct(ArrayList<Long> numbers) {

        long sum = 0;
        long product = 1;
        for (long n : numbers) {
            sum += n;
            product *= n;
        }
        return Pair.with(sum, product);
    }
}