import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Create a function that takes a value (string) representing a number
    // and returns the closest integer to it. If the number is equidistant
    // from two integers, round it away from zero.
    // Examples
    // >>> closestInteger(("10"))
    // (10l)
    // >>> closestInteger(("15.3"))
    // (15l)
    // Note:
    // Rounding away from zero means that if the given number is equidistant
    // from two integers, the one you should return is the one that is the
    // farthest from zero. For example closest_integer("14.5") should
    // return 15 and closest_integer("-14.5") should return -15.
    public static long closestInteger(String value) {
        double f = Double.parseDouble(value);
        if (f >= 0) {
            return (long) Math.floor(f + 0.5);
        } else {
            return (long) Math.ceil(f - 0.5);
        }
    }

    public static void main(String[] args) {
        System.out.println(closestInteger("10"));
        System.out.println(closestInteger("15.3"));
        System.out.println(closestInteger("14.5"));
        System.out.println(closestInteger("-14.5"));
    }
}