import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Length of a number after repeatedly replacing each digit d with d+1.
 *
 * <p>A digit only matters through how many operations remain once it first
 * overflows past 9, so one shared table answers every digit of every query.
 */
public class Sol {

    private static final long MODULUS = 1_000_000_007L;
    private static final int MAX_OPERATIONS = 200_001;
    private static final int SPLIT_DELAY_ONE = 9;
    private static final int SPLIT_DELAY_TWO = 10;

    public static void main(String[] args) throws IOException {
        long[] lengthAfter = buildLengthTable();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCount = Integer.parseInt(reader.readLine().trim());

        StringBuilder answers = new StringBuilder();
        for (int test = 0; test < testCount; test++) {
            StringTokenizer line = new StringTokenizer(reader.readLine());
            String digits = line.nextToken();
            int operations = Integer.parseInt(line.nextToken());

            long total = 0;
            for (int i = 0; i < digits.length(); i++) {
                total += grownLength(lengthAfter, digits.charAt(i) - '0', operations);
                total %= MODULUS;
            }
            answers.append(total).append('\n');
        }

        System.out.print(answers);
    }

    /** Length a freshly created digit 0 grows to after t further operations. */
    private static long[] buildLengthTable() {
        long[] lengths = new long[MAX_OPERATIONS + 1];
        lengths[0] = 1;
        for (int steps = 1; steps <= MAX_OPERATIONS; steps++) {
            long first = (steps <= SPLIT_DELAY_ONE - 1) ? 1 : lengths[steps - SPLIT_DELAY_ONE];
            long second = (steps <= SPLIT_DELAY_TWO - 1) ? 1 : lengths[steps - SPLIT_DELAY_TWO];
            lengths[steps] = (first + second) % MODULUS;
        }
        return lengths;
    }

    /** Length that a single {@code digit} reaches after {@code operations} steps. */
    private static long grownLength(long[] lengthAfter, int digit, int operations) {
        int stepsUntilOverflow = 9 - digit;
        if (operations < stepsUntilOverflow) {
            return 1;
        }
        return lengthAfter[operations - stepsUntilOverflow];
    }
}