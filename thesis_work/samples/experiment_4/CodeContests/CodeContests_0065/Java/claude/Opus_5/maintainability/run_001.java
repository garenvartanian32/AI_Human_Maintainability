import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Decides the winner of the non-trivial-divisor game and a winning first move.
 */
public class Sol {

    private static final int FIRST_PLAYER_WINS = 1;
    private static final int SECOND_PLAYER_WINS = 2;
    private static final int NO_MOVE_NEEDED = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long number = Long.parseLong(reader.readLine().trim());

        if (number == 1) {
            // Already a losing position for the opponent; nothing to play.
            System.out.println(FIRST_PLAYER_WINS);
            System.out.println(NO_MOVE_NEEDED);
            return;
        }

        List<Long> factors = primeFactorsWithMultiplicity(number);

        if (factors.size() == 1) {
            // A prime has no non-trivial divisor, so the opponent is stuck.
            System.out.println(FIRST_PLAYER_WINS);
            System.out.println(NO_MOVE_NEEDED);
        } else if (factors.size() == 2) {
            // Any move hands a winning position to the opponent.
            System.out.println(SECOND_PLAYER_WINS);
        } else {
            // Move to the product of the two smallest primes, a losing position.
            Collections.sort(factors);
            System.out.println(FIRST_PLAYER_WINS);
            System.out.println(factors.get(0) * factors.get(1));
        }
    }

    /** Returns every prime factor of {@code value}, repeated by multiplicity. */
    private static List<Long> primeFactorsWithMultiplicity(long value) {
        List<Long> factors = new ArrayList<>();
        long remaining = value;

        for (long divisor = 2; divisor * divisor <= remaining; divisor++) {
            while (remaining % divisor == 0) {
                factors.add(divisor);
                remaining /= divisor;
            }
        }

        if (remaining > 1) {
            factors.add(remaining);
        }
        return factors;
    }
}