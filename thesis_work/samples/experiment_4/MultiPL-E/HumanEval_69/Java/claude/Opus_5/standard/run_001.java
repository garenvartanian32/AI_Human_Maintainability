import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static long search(ArrayList<Long> lst) {
        HashMap<Long, Integer> counts = new HashMap<>();
        for (long value : lst) counts.put(value, counts.getOrDefault(value, 0) + 1);

        long result = -1;
        for (Map.Entry<Long, Integer> entry : counts.entrySet()) {
            if (entry.getValue() >= entry.getKey()) result = Math.max(result, entry.getKey());
        }
        return result;
    }
}