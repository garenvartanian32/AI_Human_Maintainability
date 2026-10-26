import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static Pair<Long, Long> sumProduct(ArrayList<Long> numbers) {
        long sum = 0;
        long product = 1;
        for (long number : numbers) {
            sum += number;
            product *= number;
        }
        return Pair.with(sum, product);
    }
}