import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Shortest king's walk across a sparse set of allowed board squares.
 *
 * <p>The board arrives as horizontal segments, far smaller than the full
 * coordinate range, so squares are held in a hash set keyed by a packed
 * row/column pair rather than a dense grid.
 */
public class Sol {

    private static final int UNREACHABLE = -1;
    private static final long COLUMN_RANGE = 2_000_000_000L;

    private static final int[] ROW_DELTAS = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] COLUMN_DELTAS = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        long startRow = nextLong(input);
        long startColumn = nextLong(input);
        long goalRow = nextLong(input);
        long goalColumn = nextLong(input);
        int segmentCount = (int) nextLong(input);

        Set<Long> allowed = new HashSet<>();
        for (int segment = 0; segment < segmentCount; segment++) {
            long row = nextLong(input);
            long firstColumn = nextLong(input);
            long lastColumn = nextLong(input);
            for (long column = firstColumn; column <= lastColumn; column++) {
                allowed.add(pack(row, column));
            }
        }

        System.out.println(shortestWalk(
                pack(startRow, startColumn), pack(goalRow, goalColumn), allowed));
    }

    /** Breadth-first search over the allowed squares only. */
    private static int shortestWalk(long start, long goal, Set<Long> allowed) {
        if (!allowed.contains(start) || !allowed.contains(goal)) {
            return UNREACHABLE;
        }

        Map<Long, Integer> stepsTo = new HashMap<>();
        stepsTo.put(start, 0);
        ArrayDeque<Long> frontier = new ArrayDeque<>();
        frontier.add(start);

        while (!frontier.isEmpty()) {
            long square = frontier.poll();
            if (square == goal) {
                return stepsTo.get(square);
            }

            long row = Math.floorDiv(square, COLUMN_RANGE);
            long column = square - row * COLUMN_RANGE;
            int nextSteps = stepsTo.get(square) + 1;

            for (int move = 0; move < ROW_DELTAS.length; move++) {
                long neighbour = pack(row + ROW_DELTAS[move], column + COLUMN_DELTAS[move]);
                if (allowed.contains(neighbour) && !stepsTo.containsKey(neighbour)) {
                    stepsTo.put(neighbour, nextSteps);
                    frontier.add(neighbour);
                }
            }
        }

        return UNREACHABLE;
    }

    private static long pack(long row, long column) {
        return row * COLUMN_RANGE + column;
    }

    private static long nextLong(StreamTokenizer input) throws IOException {
        input.nextToken();
        return (long) input.nval;
    }
}