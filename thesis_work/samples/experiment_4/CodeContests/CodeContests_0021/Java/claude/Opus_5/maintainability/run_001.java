import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Total candles burned when spent stubs can be recycled into new candles.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());

        long initialCandles = Long.parseLong(line.nextToken());
        long stubsPerCandle = Long.parseLong(line.nextToken());

        System.out.println(totalCandlesBurned(initialCandles, stubsPerCandle));
    }

    /** Returns how many candles burn in total, recycling stubs as we go. */
    private static long totalCandlesBurned(long initialCandles, long stubsPerCandle) {
        long burned = initialCandles;
        long stubsOnHand = initialCandles;

        while (stubsOnHand >= stubsPerCandle) {
            long recycled = stubsOnHand / stubsPerCandle;
            burned += recycled;
            // The recycled candles burn down into stubs of their own.
            stubsOnHand = stubsOnHand % stubsPerCandle + recycled;
        }

        return burned;
    }
}