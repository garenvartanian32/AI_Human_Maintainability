import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Tracks which cup hides the ball through a sequence of swaps.
 */
public class Sol {

    private static final char STARTING_CUP = 'A';
    private static final String SEPARATOR = ",";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char ballLocation = STARTING_CUP;
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            String[] swappedCups = line.split(SEPARATOR);
            char leftCup = swappedCups[0].charAt(0);
            char rightCup = swappedCups[1].charAt(0);

            // The ball only moves when its own cup is one of the two swapped.
            if (ballLocation == leftCup) {
                ballLocation = rightCup;
            } else if (ballLocation == rightCup) {
                ballLocation = leftCup;
            }
        }

        System.out.println(ballLocation);
    }
}