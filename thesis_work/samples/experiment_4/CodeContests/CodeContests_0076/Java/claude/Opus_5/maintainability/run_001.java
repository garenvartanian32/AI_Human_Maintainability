import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Decides whether apples of weight 100 or 200 can be split into equal halves.
 *
 * <p>Weights are multiples of {@code WEIGHT_UNIT}, so dividing through keeps the
 * subset-sum table small.
 */
public class Sol {

    private static final int WEIGHT_UNIT = 100;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int appleCount = (int) input.nval;

        int[] unitWeights = new int[appleCount];
        int total = 0;
        for (int i = 0; i < appleCount; i++) {
            input.nextToken();
            unitWeights[i] = (int) input.nval / WEIGHT_UNIT;
            total += unitWeights[i];
        }

        System.out.println(canSplitEvenly(unitWeights, total) ? "YES" : "NO");
    }

    /** True when some subset of the weights sums to exactly half the total. */
    private static boolean canSplitEvenly(int[] unitWeights, int total) {
        if (total % 2 != 0) {
            return false;
        }

        int half = total / 2;
        boolean[] reachable = new boolean[half + 1];
        reachable[0] = true;

        for (int weight : unitWeights) {
            // Walk downwards so each apple is used at most once.
            for (int subtotal = half; subtotal >= weight; subtotal--) {
                if (reachable[subtotal - weight]) {
                    reachable[subtotal] = true;
                }
            }
        }

        return reachable[half];
    }
}