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
    private static final int[] ROW_STEPS = {-1, 1, 0, 0};
    private static final int[] COLUMN_STEPS = {0, 0, -1, 1};

    public static ArrayList<Long> minPath(ArrayList<ArrayList<Long>> grid, long k) {
        int size = grid.size();

        // The smallest value on the board is 1, so the best path must sit on it
        // and then bounce between it and its cheapest neighbour.
        int oneRow = 0;
        int oneColumn = 0;
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (grid.get(row).get(column) == 1L) {
                    oneRow = row;
                    oneColumn = column;
                }
            }
        }

        long cheapestNeighbour = Long.MAX_VALUE;
        for (int move = 0; move < ROW_STEPS.length; move++) {
            int row = oneRow + ROW_STEPS[move];
            int column = oneColumn + COLUMN_STEPS[move];
            if (row >= 0 && row < size && column >= 0 && column < size) {
                cheapestNeighbour = Math.min(cheapestNeighbour, grid.get(row).get(column));
            }
        }

        ArrayList<Long> path = new ArrayList<>();
        for (long step = 0; step < k; step++) {
            path.add(step % 2 == 0 ? 1L : cheapestNeighbour);
        }
        return path;
    }
}