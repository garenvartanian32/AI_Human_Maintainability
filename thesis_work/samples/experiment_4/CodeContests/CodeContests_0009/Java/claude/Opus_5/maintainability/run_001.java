import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Cheapest way to give Alice and Bob each at least k liked books.
 *
 * <p>Choosing some number of both-liked books covers that many likes for each
 * reader; any shortfall must be topped up from each single-reader pile.
 */
public class Sol {

    private static final long NOT_POSSIBLE = -1;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int bookCount = (int) input.nval;
        input.nextToken();
        int requiredLikes = (int) input.nval;

        List<Integer> likedByBoth = new ArrayList<>();
        List<Integer> likedByAliceOnly = new ArrayList<>();
        List<Integer> likedByBobOnly = new ArrayList<>();

        for (int book = 0; book < bookCount; book++) {
            input.nextToken();
            int readingTime = (int) input.nval;
            input.nextToken();
            boolean aliceLikes = input.nval == 1;
            input.nextToken();
            boolean bobLikes = input.nval == 1;

            if (aliceLikes && bobLikes) {
                likedByBoth.add(readingTime);
            } else if (aliceLikes) {
                likedByAliceOnly.add(readingTime);
            } else if (bobLikes) {
                likedByBobOnly.add(readingTime);
            }
        }

        long[] bothPrefix = prefixSums(likedByBoth);
        long[] alicePrefix = prefixSums(likedByAliceOnly);
        long[] bobPrefix = prefixSums(likedByBobOnly);

        long bestTime = NOT_POSSIBLE;
        for (int shared = 0; shared <= likedByBoth.size(); shared++) {
            int shortfall = Math.max(requiredLikes - shared, 0);
            if (shortfall > likedByAliceOnly.size() || shortfall > likedByBobOnly.size()) {
                continue;
            }

            long totalTime = bothPrefix[shared] + alicePrefix[shortfall] + bobPrefix[shortfall];
            if (bestTime == NOT_POSSIBLE || totalTime < bestTime) {
                bestTime = totalTime;
            }
        }

        System.out.println(bestTime);
    }

    /** Returns cumulative reading times after sorting ascending. */
    private static long[] prefixSums(List<Integer> times) {
        Collections.sort(times);
        long[] prefix = new long[times.size() + 1];
        for (int i = 0; i < times.size(); i++) {
            prefix[i + 1] = prefix[i] + times.get(i);
        }
        return prefix;
    }
}