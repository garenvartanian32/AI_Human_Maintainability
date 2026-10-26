import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Decides whether the first player can win the XOR-basis game.
 *
 * <p>Values are folded into a linear basis over GF(2) from the back of the list
 * forwards. The first value that survives reduction is the deciding one: if the
 * first player owns it, they can force the win.
 */
public class Sol {

    private static final int BIT_WIDTH = 64;
    private static final int FIRST_PLAYER_WINS = 1;
    private static final int FIRST_PLAYER_LOSES = 0;
    private static final char FIRST_PLAYER_MARK = '1';

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder raw = new StringBuilder();
        String chunk;
        while ((chunk = reader.readLine()) != null) {
            raw.append(chunk).append('\n');
        }
        StringTokenizer tokens = new StringTokenizer(raw.toString());

        int testCount = Integer.parseInt(tokens.nextToken());
        StringBuilder verdicts = new StringBuilder();

        for (int test = 0; test < testCount; test++) {
            int valueCount = Integer.parseInt(tokens.nextToken());
            long[] values = new long[valueCount];
            for (int i = 0; i < valueCount; i++) {
                values[i] = Long.parseLong(tokens.nextToken());
            }
            String owners = tokens.nextToken();

            verdicts.append(firstPlayerWins(values, owners)).append('\n');
        }

        System.out.print(verdicts);
    }

    private static int firstPlayerWins(long[] values, String owners) {
        long[] basis = new long[BIT_WIDTH];

        for (int index = values.length - 1; index >= 0; index--) {
            long residue = values[index];

            for (int bit = BIT_WIDTH - 1; bit >= 0; bit--) {
                if (((residue >>> bit) & 1L) == 0L) {
                    continue;
                }
                if (basis[bit] == 0) {
                    basis[bit] = residue;
                    return (owners.charAt(index) == FIRST_PLAYER_MARK)
                            ? FIRST_PLAYER_WINS
                            : FIRST_PLAYER_LOSES;
                }
                residue ^= basis[bit];
            }
        }

        return FIRST_PLAYER_LOSES;
    }
}