import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Sum of every m where floor(n/m) equals n mod m.
 *
 * <p>Writing q for the shared value, n = q*m + q = q*(m + 1), so m + 1 must
 * divide n. Enumerating divisors of n therefore enumerates all candidates.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(reader.readLine().trim());

        long total = 0;
        for (long divisor = 1; divisor * divisor <= n; divisor++) {
            if (n % divisor != 0) {
                continue;
            }

            // A divisor pairs with n / divisor; a set avoids double counting
            // when they coincide at a perfect square.
            Set<Long> candidates = new HashSet<>();
            candidates.add(divisor - 1);
            candidates.add(n / divisor - 1);

            for (long candidate : candidates) {
                if (candidate >= 1 && n / candidate == n % candidate) {
                    total += candidate;
                }
            }
        }

        System.out.println(total);
    }
}