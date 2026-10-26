import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Smallest candidate total that beats every rival and every existing score.
 *
 * <p>The answer must be at least the current maximum, and large enough that
 * {@code count} copies of it strictly exceed twice the current total.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int count = (int) input.nval;

        long total = 0;
        long largestScore = 0;
        for (int i = 0; i < count; i++) {
            input.nextToken();
            long score = (long) input.nval;
            total += score;
            largestScore = Math.max(largestScore, score);
        }

        long smallestWinning = largestScore;
        while (count * smallestWinning <= 2 * total) {
            smallestWinning++;
        }

        System.out.println(smallestWinning);
    }
}