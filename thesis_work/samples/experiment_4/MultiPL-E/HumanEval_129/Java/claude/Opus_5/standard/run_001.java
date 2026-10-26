import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // Given a grid with N rows and N columns (N >= 2) and a positive integer k, 
    // each cell of the grid contains a value. Every integer in the range [1, N * N]
    // inclusive appears exactly once on the cells of the grid.
    // You have to find the minimum path of length k in the grid. You can start
    // from any cell, and in each step you can move to any of the neighbor cells,
    // in other words, you can go to cells which share an edge with you current
    // cell.
    // Please note that a path of length k means visiting exactly k cells (not
    // necessarily distinct).
    // You CANNOT go off the grid.
    // A path A (of length k) is considered less than a path B (of length k) if
    // after making the ordered array lists of the values on the cells that A and B go
    // through (let's call them lst_A and lst_B), lst_A is lexicographically less
    // than lst_B, in other words, there exist an integer index i (1 <= i <= k)
    // such that lst_A[i] < lst_B[i] and for any j (1 <= j < i) we have
    // lst_A[j] = lst_B[j].
    // It is guaranteed that the answer is unique.
    // Return an ordered array list of the values on the cells that the minimum path go through.
    // Examples:    
    // >>> minPath((new ArrayList<ArrayList<Long>>(Arrays.asList((ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)4l, (long)5l, (long)6l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)7l, (long)8l, (long)9l))))), (3l))
    // (new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)1l)))
    // >>> minPath((new ArrayList<ArrayList<Long>>(Arrays.asList((ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)5l, (long)9l, (long)3l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)4l, (long)1l, (long)6l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)7l, (long)8l, (long)2l))))), (1l))
    // (new ArrayList<Long>(Arrays.asList((long)1l)))
    public static ArrayList<Long> minPath(ArrayList<ArrayList<Long>> grid, long k) {
        int n = grid.size();
        int pi = -1, pj = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1L) { pi = i; pj = j; }
            }
        }
        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};
        long m = Long.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            int ni = pi + di[d], nj = pj + dj[d];
            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                m = Math.min(m, grid.get(ni).get(nj));
            }
        }
        ArrayList<Long> result = new ArrayList<>();
        for (int idx = 0; idx < k; idx++) {
            result.add(idx % 2 == 0 ? 1L : m);
        }
        return result;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Long>> g1 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(1L,2L,3L)),
            new ArrayList<>(Arrays.asList(4L,5L,6L)),
            new ArrayList<>(Arrays.asList(7L,8L,9L))));
        System.out.println(minPath(g1, 3L));
        ArrayList<ArrayList<Long>> g2 = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(5L,9L,3L)),
            new ArrayList<>(Arrays.asList(4L,1L,6L)),
            new ArrayList<>(Arrays.asList(7L,8L,2L))));
        System.out.println(minPath(g2, 1L));
    }
}