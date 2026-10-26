import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Counts the squares that lie on a cycle of a circular sugoroku board.
 *
 * <p>Every square has exactly one successor, so the board is a functional
 * graph: each walk eventually runs into a cycle.
 */
public class Sol {

    private static final int UNVISITED = 0;
    private static final int ON_CURRENT_WALK = 1;
    private static final int FINISHED = 2;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int squareCount = (int) input.nval;

        int[] successor = new int[squareCount];
        for (int index = 0; index < squareCount; index++) {
            input.nextToken();
            successor[index] = (int) ((index + (long) input.nval) % squareCount);
        }

        int[] state = new int[squareCount];
        boolean[] liesOnCycle = new boolean[squareCount];

        for (int start = 0; start < squareCount; start++) {
            if (state[start] != UNVISITED) {
                continue;
            }

            List<Integer> walk = new ArrayList<>();
            int square = start;
            while (state[square] == UNVISITED) {
                state[square] = ON_CURRENT_WALK;
                walk.add(square);
                square = successor[square];
            }

            // Re-entering the current walk means we closed a fresh cycle.
            if (state[square] == ON_CURRENT_WALK) {
                int cycleStart = walk.indexOf(square);
                for (int i = cycleStart; i < walk.size(); i++) {
                    liesOnCycle[walk.get(i)] = true;
                }
            }

            for (int member : walk) {
                state[member] = FINISHED;
            }
        }

        int total = 0;
        for (boolean onCycle : liesOnCycle) {
            if (onCycle) {
                total++;
            }
        }
        System.out.println(total);
    }
}