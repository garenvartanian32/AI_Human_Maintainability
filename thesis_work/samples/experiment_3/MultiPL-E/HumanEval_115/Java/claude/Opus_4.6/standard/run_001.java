import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // You are given a rectangular grid of wells. Each row represents a single well,
    // and each 1 in a row represents a single unit of water.
    // Each well has a corresponding bucket that can be used to extract water from it, 
    // and all buckets have the same capacity.
    // Your task is to use the buckets to empty the wells.
    // Output the number of times you need to lower the buckets.
    // Example 1:
    // >>> maxFill((new ArrayList<ArrayList<Long>>(Arrays.asList((ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)0l, (long)0l, (long)1l, (long)0l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)0l, (long)1l, (long)0l, (long)0l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)1l, (long)1l, (long)1l, (long)1l))))), (1l))
    // (6l)
    // Example 2:
    // >>> maxFill((new ArrayList<ArrayList<Long>>(Arrays.asList((ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)0l, (long)0l, (long)1l, (long)1l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)0l, (long)0l, (long)0l, (long)0l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)1l, (long)1l, (long)1l, (long)1l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)0l, (long)1l, (long)1l, (long)1l))))), (2l))
    // (5l)
    // Example 3:
    // >>> maxFill((new ArrayList<ArrayList<Long>>(Arrays.asList((ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)0l, (long)0l, (long)0l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)0l, (long)0l, (long)0l))))), (5l))
    // (0l)
    // Constraints:
    // * all wells have the same length
    // * 1 <= grid.length <= 10^2
    // * 1 <= grid[:,1].length <= 10^2
    // * grid[i][j] -> 0 | 1
    // * 1 <= capacity <= 10
    public static long maxFill(ArrayList<ArrayList<Long>> grid, long capacity) {
        long total = 0;
        for (ArrayList<Long> row : grid) {
            long sum = 0;
            for (long v : row) sum += v;
            total += (sum + capacity - 1) / capacity;
        }
        return total;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Long>> g1 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0L,0L,1L,0L)),
            new ArrayList<>(Arrays.asList(0L,1L,0L,0L)),
            new ArrayList<>(Arrays.asList(1L,1L,1L,1L))));
        System.out.println(maxFill(g1, 1L));
        ArrayList<ArrayList<Long>> g2 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(0L,0L,1L,1L)),
            new ArrayList<>(Arrays.asList(0L,0L,0L,0L)),
            new ArrayList<>(Arrays.asList(1L,1L,1L,1L)),
            new ArrayList<>(Arrays.asList(0L,1L,1L,1L))));
        System.out.println(maxFill(g2, 2L));
    }
}