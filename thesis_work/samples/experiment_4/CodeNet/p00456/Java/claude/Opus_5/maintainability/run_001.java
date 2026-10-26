import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * Scores two teams by summing each one's three best results.
 */
public class Sol {

    private static final int TEAM_SIZE = 10;
    private static final int COUNTED_RESULTS = 3;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        int[] allResults = new int[2 * TEAM_SIZE];
        for (int i = 0; i < allResults.length; i++) {
            input.nextToken();
            allResults[i] = (int) input.nval;
        }

        int westScore = teamScore(allResults, 0);
        int eastScore = teamScore(allResults, TEAM_SIZE);

        System.out.println(westScore + " " + eastScore);
    }

    /** Sum of the three highest results for the team starting at {@code offset}. */
    private static int teamScore(int[] allResults, int offset) {
        int[] results = Arrays.copyOfRange(allResults, offset, offset + TEAM_SIZE);
        Arrays.sort(results);

        int score = 0;
        for (int i = 0; i < COUNTED_RESULTS; i++) {
            score += results[TEAM_SIZE - 1 - i];
        }
        return score;
    }
}