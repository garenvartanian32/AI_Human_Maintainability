import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * Counts roads that lie on no shortest path between their two towns.
 *
 * <p>A road is redundant exactly when some detour beats travelling along it.
 */
public class Sol {

    private static final long UNREACHABLE = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int townCount = (int) input.nval;
        input.nextToken();
        int roadCount = (int) input.nval;

        long[][] shortest = new long[townCount + 1][townCount + 1];
        for (long[] row : shortest) {
            Arrays.fill(row, UNREACHABLE);
        }
        for (int town = 1; town <= townCount; town++) {
            shortest[town][town] = 0;
        }

        int[] firstTown = new int[roadCount];
        int[] secondTown = new int[roadCount];
        long[] roadLength = new long[roadCount];

        for (int road = 0; road < roadCount; road++) {
            input.nextToken();
            firstTown[road] = (int) input.nval;
            input.nextToken();
            secondTown[road] = (int) input.nval;
            input.nextToken();
            roadLength[road] = (long) input.nval;

            // Parallel roads may exist; keep only the shortest as the link.
            if (roadLength[road] < shortest[firstTown[road]][secondTown[road]]) {
                shortest[firstTown[road]][secondTown[road]] = roadLength[road];
                shortest[secondTown[road]][firstTown[road]] = roadLength[road];
            }
        }

        runFloydWarshall(shortest, townCount);

        int redundant = 0;
        for (int road = 0; road < roadCount; road++) {
            if (shortest[firstTown[road]][secondTown[road]] < roadLength[road]) {
                redundant++;
            }
        }
        System.out.println(redundant);
    }

    private static void runFloydWarshall(long[][] shortest, int townCount) {
        for (int waypoint = 1; waypoint <= townCount; waypoint++) {
            for (int origin = 1; origin <= townCount; origin++) {
                long toWaypoint = shortest[origin][waypoint];
                if (toWaypoint >= UNREACHABLE) {
                    continue;
                }
                for (int destination = 1; destination <= townCount; destination++) {
                    long detour = toWaypoint + shortest[waypoint][destination];
                    if (detour < shortest[origin][destination]) {
                        shortest[origin][destination] = detour;
                    }
                }
            }
        }
    }
}