import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Rewrites a sum of 1s, 2s and 3s so its summands appear in ascending order.
 *
 * <p>Only the multiset of summands matters, so sorting them is enough.
 */
public class Sol {

    private static final String PLUS = "+";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String expression = reader.readLine().trim();

        String[] summands = expression.split("\\" + PLUS);
        Arrays.sort(summands);

        System.out.println(String.join(PLUS, summands));
    }
}