import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static Pair<Long, Long> sumProduct(ArrayList<Long> numbers) {
        long totalSum = 0;
        long totalProduct = 1;

        for (long number : numbers) {
            totalSum += number;
            totalProduct *= number;
        }

        return Pair.with(totalSum, totalProduct);
    }
}