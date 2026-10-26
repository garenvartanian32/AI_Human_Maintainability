import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // You are given a non-empty array list of positive integers. Return the greatest integer that is greater than 
    // zero, and has a frequency greater than or equal to the value of the integer itself. 
    // The frequency of an integer is the number of times it appears in the array list.
    // If no such a value exist, return -1.
    // Examples:
    // >>> search((new ArrayList<Long>(Arrays.asList((long)4l, (long)1l, (long)2l, (long)2l, (long)3l, (long)1l))))
    // (2l)
    // >>> search((new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)2l, (long)3l, (long)3l, (long)3l, (long)4l, (long)4l, (long)4l))))
    // (3l)
    // >>> search((new ArrayList<Long>(Arrays.asList((long)5l, (long)5l, (long)4l, (long)4l, (long)4l))))
    // (-1l)
    public static long search(ArrayList<Long> lst) {

        Map<Long, Long> freq = new HashMap<Long, Long>();
        for (long x : lst) freq.put(x, freq.getOrDefault(x, 0l) + 1);
        long best = -1;
        for (Map.Entry<Long, Long> e : freq.entrySet()) {
            if (e.getValue() >= e.getKey() && e.getKey() > best) {
                best = e.getKey();
            }
        }
        return best;
    }
}