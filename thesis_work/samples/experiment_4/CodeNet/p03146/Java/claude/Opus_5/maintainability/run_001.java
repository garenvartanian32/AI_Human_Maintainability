import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * First index at which a Collatz-style sequence revisits an earlier term.
 *
 * <p>The sequence is deterministic, so the first repeated value is the point at
 * which it has entered a cycle.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long start = Long.parseLong(reader.readLine().trim());

        Set<Long> seen = new HashSet<>();
        seen.add(start);

        long value = start;
        int index = 1;

        while (true) {
            value = nextTerm(value);
            index++;
            if (!seen.add(value)) {
                System.out.println(index);
                return;
            }
        }
    }

    /** Halves an even term; otherwise triples it and adds one. */
    private static long nextTerm(long value) {
        return (value % 2 == 0) ? value / 2 : 3 * value + 1;
    }
}