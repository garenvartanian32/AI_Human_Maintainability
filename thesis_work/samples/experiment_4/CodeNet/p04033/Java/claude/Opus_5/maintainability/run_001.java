import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Reports the sign of the product of every integer in [a, b].
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());

        long low = Long.parseLong(line.nextToken());
        long high = Long.parseLong(line.nextToken());

        if (low <= 0 && 0 <= high) {
            // Zero is in range, so it annihilates the product.
            System.out.println("Zero");
        } else if (low > 0) {
            // Every factor is positive.
            System.out.println("Positive");
        } else {
            // Every factor is negative; the sign follows the count's parity.
            long factorCount = high - low + 1;
            System.out.println(factorCount % 2 == 0 ? "Positive" : "Negative");
        }
    }
}