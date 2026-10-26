import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static ArrayList<Pair<Long, Long>> getRow(ArrayList<ArrayList<Long>> lst, long x) {
        ArrayList<Pair<Long, Long>> result = new ArrayList<>();

        for (int row = 0; row < lst.size(); row++) {
            ArrayList<Long> values = lst.get(row);
            for (int column = values.size() - 1; column >= 0; column--) {
                if (values.get(column) == x) {
                    result.add(Pair.with((long) row, (long) column));
                }
            }
        }
        return result;
    }
}