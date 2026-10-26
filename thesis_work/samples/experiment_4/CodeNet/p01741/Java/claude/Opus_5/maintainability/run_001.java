import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Longest road distance reachable within a straight-line limit.
 */
public class Sol {

    private static final double MINIMUM_LEG = 1.0;
    private static final double FLOOR_TOLERANCE = 1e-9;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double limit = Double.parseDouble(reader.readLine().trim());

        System.out.printf("%.12f%n", longestReachable(limit));
    }

    /**
     * Returns the greatest total road length within straight-line {@code limit}.
     *
     * <p>A route is a horizontal leg plus a vertical leg. For an integral number
     * of horizontal blocks the vertical leg stretches to whatever the
     * straight-line budget still allows, but never below one block. The
     * unconstrained diagonal is also a candidate, so it seeds the search.
     */
    private static double longestReachable(double limit) {
        double best = limit * Math.sqrt(2.0);

        int horizontalCeiling = (int) Math.floor(limit + FLOOR_TOLERANCE);
        for (int horizontal = 1; horizontal <= horizontalCeiling; horizontal++) {
            double remainingSquared =
                    Math.max(limit * limit - (double) horizontal * horizontal, 0.0);
            double vertical = Math.max(MINIMUM_LEG, Math.sqrt(remainingSquared));
            best = Math.max(best, horizontal + vertical);
        }

        return best;
    }
}