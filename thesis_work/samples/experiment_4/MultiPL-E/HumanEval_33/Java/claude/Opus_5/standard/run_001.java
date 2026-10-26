import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // This function takes an array array list l and returns an array array list l' such that
    // l' is identical to l in the indicies that are not divisible by three, while its values at the indicies that are divisible by three are equal
    // to the values of the corresponding indicies of l, but sorted.
    // >>> sortThird((new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l))))
    // (new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l)))
    // >>> sortThird((new ArrayList<Long>(Arrays.asList((long)5l, (long)6l, (long)3l, (long)4l, (long)8l, (long)9l, (long)2l))))
    // (new ArrayList<Long>(Arrays.asList((long)2l, (long)6l, (long)3l, (long)4l, (long)8l, (long)9l, (long)5l)))
    public static ArrayList<Long> sortThird(ArrayList<Long> l) {
        List<Integer> indices = new ArrayList<>();
        List<Long> values = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            if (i % 3 == 0) {
                indices.add(i);
                values.add(l.get(i));
            }
        }
        Collections.sort(values);
        ArrayList<Long> result = new ArrayList<>(l);
        for (int k = 0; k < indices.size(); k++) {
            result.set(indices.get(k), values.get(k));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(sortThird(new ArrayList<>(Arrays.asList(1L,2L,3L))));
        System.out.println(sortThird(new ArrayList<>(Arrays.asList(5L,6L,3L,4L,8L,9L,2L))));
    }
}