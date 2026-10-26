import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;
class Problem {
    // You are given a 2 dimensional data, as a nested array lists,
    // which is similar to matrix, however, unlike matrices,
    // each row may contain a different number of columns.
    // Given lst, and integer x, find integers x in the array list,
    // and return array list of pairs, [(x1, y1), (x2, y2) ...] such that
    // each pair is a coordinate - (row, columns), starting with 0.
    // Sort coordinates initially by rows in ascending order.
    // Also, sort coordinates of the row by columns in descending order.
    // Examples:
    // >>> getRow((new ArrayList<ArrayList<Long>>(Arrays.asList((ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l, (long)4l, (long)5l, (long)6l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l, (long)4l, (long)1l, (long)6l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l, (long)4l, (long)5l, (long)1l))))), (1l))
    // (new ArrayList<Pair<Long, Long>>(Arrays.asList((Pair<Long, Long>)Pair.with(0l, 0l), (Pair<Long, Long>)Pair.with(1l, 4l), (Pair<Long, Long>)Pair.with(1l, 0l), (Pair<Long, Long>)Pair.with(2l, 5l), (Pair<Long, Long>)Pair.with(2l, 0l))))
    // >>> getRow((new ArrayList<ArrayList<Long>>(Arrays.asList())), (1l))
    // (new ArrayList<Pair<Long, Long>>(Arrays.asList()))
    // >>> getRow((new ArrayList<ArrayList<Long>>(Arrays.asList((ArrayList<Long>)new ArrayList<Long>(Arrays.asList()), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)1l)), (ArrayList<Long>)new ArrayList<Long>(Arrays.asList((long)1l, (long)2l, (long)3l))))), (3l))
    // (new ArrayList<Pair<Long, Long>>(Arrays.asList((Pair<Long, Long>)Pair.with(2l, 2l))))
    public static ArrayList<Pair<Long, Long>> getRow(ArrayList<ArrayList<Long>> lst, long x) {

        ArrayList<Pair<Long, Long>> result = new ArrayList<Pair<Long, Long>>();
        for (int r = 0; r < lst.size(); r++) {
            ArrayList<Long> row = lst.get(r);
            ArrayList<Integer> cols = new ArrayList<Integer>();
            for (int c = 0; c < row.size(); c++) {
                if (row.get(c) == x) cols.add(c);
            }
            Collections.sort(cols, Collections.reverseOrder());
            for (int c : cols) {
                result.add(Pair.with((long) r, (long) c));
            }
        }
        return result;
    }
}