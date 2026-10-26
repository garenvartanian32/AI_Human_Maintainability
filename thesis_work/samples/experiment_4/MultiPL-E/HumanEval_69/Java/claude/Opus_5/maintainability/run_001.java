import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long search(ArrayList<Long> lst) {
        Map<Long, Long> frequencies = new HashMap<>();

        for (long value : lst) {
            frequencies.put(value, frequencies.getOrDefault(value, 0L) + 1);
        }

        long greatestValidValue = -1;

        for (Map.Entry<Long, Long> entry : frequencies.entrySet()) {
            long value = entry.getKey();
            long frequency = entry.getValue();

            if (frequency >= value && value > greatestValidValue) {
                greatestValidValue = value;
            }
        }

        return greatestValidValue;
    }
}