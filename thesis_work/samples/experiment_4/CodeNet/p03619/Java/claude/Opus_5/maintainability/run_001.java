import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Shortest walk on a city grid, allowing corner cuts around fountains.
 *
 * <p>Cutting a corner around a fountain replaces two quarter-block legs with a
 * quarter arc; a fountain sitting on a straight stretch instead forces a
 * detour. Corner cuts chain only along a monotone staircase, so the number of
 * usable fountains is the length of a longest increasing subsequence.
 */
public class Sol {

    private static final double BLOCK_LENGTH = 100.0;
    private static final double FOUNTAIN_RADIUS = 10.0;
    private static final double CORNER_SAVING = FOUNTAIN_RADIUS * (2 - Math.PI / 2);
    private static final double STRAIGHT_DETOUR = FOUNTAIN_RADIUS * (Math.PI - 2);

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        long startX = nextLong(input);
        long startY = nextLong(input);
        long endX = nextLong(input);
        long endY = nextLong(input);
        int fountainCount = (int) nextLong(input);

        long[] fountainX = new long[fountainCount];
        long[] fountainY = new long[fountainCount];
        for (int i = 0; i < fountainCount; i++) {
            fountainX[i] = nextLong(input);
            fountainY[i] = nextLong(input);
        }

        double walk = (Math.abs(startX - endX) + Math.abs(startY - endY)) * BLOCK_LENGTH;

        // A purely horizontal or vertical trip has no corners to cut.
        if (startX == endX || startY == endY) {
            boolean blocked = straightPathBlocked(
                    startX, startY, endX, endY, fountainX, fountainY);
            System.out.printf("%.15f%n", walk + (blocked ? STRAIGHT_DETOUR : 0.0));
            return;
        }

        // Reflect coordinates so the trip always runs in the increasing
        // direction, turning "usable fountains" into an increasing subsequence.
        int xSign = (endX > startX) ? 1 : -1;
        int ySign = (endY > startY) ? 1 : -1;
        long lowX = Math.min(startX, endX);
        long highX = Math.max(startX, endX);
        long lowY = Math.min(startY, endY);
        long highY = Math.max(startY, endY);

        List<long[]> inside = new ArrayList<>();
        for (int i = 0; i < fountainCount; i++) {
            if (fountainX[i] > lowX && fountainX[i] < highX
                    && fountainY[i] > lowY && fountainY[i] < highY) {
                inside.add(new long[] {fountainX[i] * xSign, fountainY[i] * ySign});
            }
        }
        inside.sort((left, right) -> left[0] != right[0]
                ? Long.compare(left[0], right[0])
                : Long.compare(left[1], right[1]));

        System.out.printf("%.15f%n", walk - longestIncreasingRun(inside) * CORNER_SAVING);
    }

    /** Longest strictly increasing chain by the second coordinate. */
    private static int longestIncreasingRun(List<long[]> points) {
        long[] tails = new long[points.size()];
        int length = 0;

        for (long[] point : points) {
            int low = 0;
            int high = length;
            while (low < high) {
                int middle = (low + high) >>> 1;
                if (tails[middle] < point[1]) {
                    low = middle + 1;
                } else {
                    high = middle;
                }
            }
            tails[low] = point[1];
            if (low == length) {
                length++;
            }
        }
        return length;
    }

    private static boolean straightPathBlocked(
            long startX, long startY, long endX, long endY,
            long[] fountainX, long[] fountainY) {
        if (startY == endY) {
            long low = Math.min(startX, endX);
            long high = Math.max(startX, endX);
            for (int i = 0; i < fountainX.length; i++) {
                if (fountainY[i] == startY && fountainX[i] > low && fountainX[i] < high) {
                    return true;
                }
            }
            return false;
        }

        long low = Math.min(startY, endY);
        long high = Math.max(startY, endY);
        for (int i = 0; i < fountainX.length; i++) {
            if (fountainX[i] == startX && fountainY[i] > low && fountainY[i] < high) {
                return true;
            }
        }
        return false;
    }

    private static long nextLong(StreamTokenizer input) throws IOException {
        input.nextToken();
        return (long) input.nval;
    }
}