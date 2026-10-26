import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * For each query, finds the smallest positive multiple of d outside [l, r].
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int queryCount = (int) input.nval;

        StringBuilder answers = new StringBuilder();
        for (int query = 0; query < queryCount; query++) {
            input.nextToken();
            long l = (long) input.nval;
            input.nextToken();
            long r = (long) input.nval;
            input.nextToken();
            long d = (long) input.nval;

            answers.append(smallestMultipleOutside(l, r, d)).append('\n');
        }

        System.out.print(answers);
    }

    /**
     * Returns the least positive multiple of {@code d} not within [l, r].
     *
     * <p>If {@code d} itself already falls outside the forbidden range it is the
     * answer; otherwise the first free multiple is the next one after {@code r}.
     */
    private static long smallestMultipleOutside(long l, long r, long d) {
        if (d < l || d > r) {
            return d;
        }
        return (r / d + 1) * d;
    }
}