import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * Sum of minimum pairwise distances between points that eventually meet.
 *
 * <p>Two points converge exactly when the left one is not slower than the right
 * one, so we sweep by coordinate and query for smaller-or-equal speeds.
 */
public class Sol {

    /** Binary indexed tree tracking both a count and a sum of coordinates. */
    private static final class FenwickTree {
        private final int size;
        private final long[] counts;
        private final long[] coordinateSums;

        FenwickTree(int size) {
            this.size = size;
            this.counts = new long[size + 1];
            this.coordinateSums = new long[size + 1];
        }

        /** Records one point with the given coordinate at {@code position}. */
        void add(int position, long coordinate) {
            for (int i = position; i <= size; i += i & -i) {
                counts[i]++;
                coordinateSums[i] += coordinate;
            }
        }

        /** Returns {count, coordinate sum} over ranks 1..{@code position}. */
        long[] prefix(int position) {
            long count = 0;
            long coordinateSum = 0;
            for (int i = position; i > 0; i -= i & -i) {
                count += counts[i];
                coordinateSum += coordinateSums[i];
            }
            return new long[] {count, coordinateSum};
        }
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int pointCount = (int) input.nval;

        long[] coordinates = new long[pointCount];
        long[] velocities = new long[pointCount];
        for (int i = 0; i < pointCount; i++) {
            input.nextToken();
            coordinates[i] = (long) input.nval;
        }
        for (int i = 0; i < pointCount; i++) {
            input.nextToken();
            velocities[i] = (long) input.nval;
        }

        long[] distinctVelocities = distinctSorted(velocities);
        FenwickTree tree = new FenwickTree(distinctVelocities.length);

        Integer[] byCoordinate = new Integer[pointCount];
        Arrays.setAll(byCoordinate, i -> i);
        Arrays.sort(byCoordinate, (a, b) -> Long.compare(coordinates[a], coordinates[b]));

        long totalDistance = 0;
        for (int index : byCoordinate) {
            int rank = rankOf(distinctVelocities, velocities[index]) + 1;
            long[] earlier = tree.prefix(rank);
            totalDistance += earlier[0] * coordinates[index] - earlier[1];
            tree.add(rank, coordinates[index]);
        }

        System.out.println(totalDistance);
    }

    private static long[] distinctSorted(long[] values) {
        long[] sorted = values.clone();
        Arrays.sort(sorted);
        int distinctCount = 0;
        for (int i = 0; i < sorted.length; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                sorted[distinctCount++] = sorted[i];
            }
        }
        return Arrays.copyOf(sorted, distinctCount);
    }

    private static int rankOf(long[] sortedDistinct, long value) {
        int low = 0;
        int high = sortedDistinct.length;
        while (low < high) {
            int middle = (low + high) >>> 1;
            if (sortedDistinct[middle] < value) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }
}