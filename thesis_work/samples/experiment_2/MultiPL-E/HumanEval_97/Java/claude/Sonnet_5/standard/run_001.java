import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Complete the function that takes two integers and returns 
    // the product of their unit digits.
    // Assume the input is always valid.
    // Examples:
    // >>> multiply((148l), (412l))
    // (16l)
    // >>> multiply((19l), (28l))
    // (72l)
    // >>> multiply((2020l), (1851l))
    // (0l)
    // >>> multiply((14l), (-15l))
    // (20l)
    public static long multiply(long a, long b) {
        return (Math.abs(a) % 10) * (Math.abs(b) % 10);
    }

    public static void main(String[] args) {
        System.out.println(multiply(148,412));
        System.out.println(multiply(19,28));
        System.out.println(multiply(2020,1851));
        System.out.println(multiply(14,-15));
    }
}