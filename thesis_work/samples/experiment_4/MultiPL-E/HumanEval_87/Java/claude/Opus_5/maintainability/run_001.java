import java.util.*;
import java.lang.reflect.*;
import org.javatuples.*;
import java.security.*;
import java.math.*;
import java.io.*;
import java.util.stream.*;

class Problem {
    public static ArrayList<Pair<Long, Long>> getRow(ArrayList<ArrayList<Long>> lst, long x) {
        ArrayList<Pair<Long, Long>> coordinates = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < lst.size(); rowIndex++) {
            ArrayList<Long> row = lst.get(rowIndex);

            for (int columnIndex = row.size() - 1; columnIndex >= 0; columnIndex--) {
                if (row.get(columnIndex) == x) {
                    coordinates.add(
                        Pair.with((long) rowIndex, (long) columnIndex)
                    );
                }
            }
        }

        return coordinates;
    }
}