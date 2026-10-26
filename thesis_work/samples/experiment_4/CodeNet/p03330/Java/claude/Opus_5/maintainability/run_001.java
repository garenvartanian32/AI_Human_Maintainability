import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Cheapest recolouring of a grid so that colours depend on (row+col) mod 3.
 *
 * <p>Cells sharing {@code (row + column) % 3} must end up the same colour, so
 * only the tally of current colours per group matters. The three groups must
 * then receive three distinct colours.
 */
public class Sol {

    private static final int GROUP_COUNT = 3;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int gridSize = (int) input.nval;
        input.nextToken();
        int colourCount = (int) input.nval;

        long[][] repaintCost = new long[colourCount][colourCount];
        for (int source = 0; source < colourCount; source++) {
            for (int target = 0; target < colourCount; target++) {
                input.nextToken();
                repaintCost[source][target] = (long) input.nval;
            }
        }

        long[][] tally = new long[GROUP_COUNT][colourCount];
        for (int row = 0; row < gridSize; row++) {
            for (int column = 0; column < gridSize; column++) {
                input.nextToken();
                int currentColour = (int) input.nval - 1;
                tally[(row + column) % GROUP_COUNT][currentColour]++;
            }
        }

        long[][] groupCost = new long[GROUP_COUNT][colourCount];
        for (int group = 0; group < GROUP_COUNT; group++) {
            for (int target = 0; target < colourCount; target++) {
                long cost = 0;
                for (int source = 0; source < colourCount; source++) {
                    cost += tally[group][source] * repaintCost[source][target];
                }
                groupCost[group][target] = cost;
            }
        }

        long best = Long.MAX_VALUE;
        for (int first = 0; first < colourCount; first++) {
            for (int second = 0; second < colourCount; second++) {
                if (second == first) {
                    continue;
                }
                for (int third = 0; third < colourCount; third++) {
                    if (third == first || third == second) {
                        continue;
                    }
                    best = Math.min(best,
                            groupCost[0][first] + groupCost[1][second] + groupCost[2][third]);
                }
            }
        }

        System.out.println(best);
    }
}