import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // triples_sum_to_zero takes an array array list of integers as an input.
    // it returns true if there are three distinct elements in the array list that
    // sum to zero, and false otherwise.
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)1l, (long)3l, (long)5l, (long)0l))))
    // (false)
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)1l, (long)3l, (long)-2l, (long)1l))))
    // (true)
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l, (long)7l))))
    // (false)
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)2l, (long)4l, (long)-5l, (long)3l, (long)9l, (long)7l))))
    // (true)
    // >>> triplesSumToZero((new ArrayList<Long>(Arrays.asList((long)1l))))
    // (false)
    public static boolean triplesSumToZero(ArrayList<Long> l) {
        ArrayList<Long> s = new ArrayList<>(l);
        Collections.sort(s);
        int n = s.size();
        for (int i = 0; i < n; i++) {
            int lo = i + 1, hi = n - 1;
            while (lo < hi) {
                long total = s.get(i) + s.get(lo) + s.get(hi);
                if (total == 0) return true;
                else if (total < 0) lo++;
                else hi--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(triplesSumToZero(new ArrayList<>(Arrays.asList(1L,3L,5L,0L))));
        System.out.println(triplesSumToZero(new ArrayList<>(Arrays.asList(1L,3L,-2L,1L))));
        System.out.println(triplesSumToZero(new ArrayList<>(Arrays.asList(1L,2L,3L,7L))));
        System.out.println(triplesSumToZero(new ArrayList<>(Arrays.asList(2L,4L,-5L,3L,9L,7L))));
        System.out.println(triplesSumToZero(new ArrayList<>(Arrays.asList(1L))));
    }
}