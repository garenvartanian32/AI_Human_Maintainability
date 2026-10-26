import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * Fewest bombs needed to clear a line of monsters.
 *
 * <p>Sweeping left to right, every monster must die at its own turn, so bombs
 * are always centred as far right as still covers it. An expiry array records
 * where each blast stops contributing, letting us carry a running total.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int monsterCount = (int) input.nval;
        input.nextToken();
        long blastRadius = (long) input.nval;
        input.nextToken();
        long blastDamage = (long) input.nval;

        long[][] monsters = new long[monsterCount][2];
        for (int i = 0; i < monsterCount; i++) {
            input.nextToken();
            monsters[i][0] = (long) input.nval;
            input.nextToken();
            monsters[i][1] = (long) input.nval;
        }
        Arrays.sort(monsters, (left, right) -> Long.compare(left[0], right[0]));

        long[] positions = new long[monsterCount];
        for (int i = 0; i < monsterCount; i++) {
            positions[i] = monsters[i][0];
        }

        long[] expiring = new long[monsterCount + 1];
        long damageInEffect = 0;
        long bombsUsed = 0;

        for (int index = 0; index < monsterCount; index++) {
            damageInEffect -= expiring[index];

            long remainingHealth = monsters[index][1] - damageInEffect;
            if (remainingHealth <= 0) {
                continue;
            }

            long bombs = (remainingHealth + blastDamage - 1) / blastDamage;
            bombsUsed += bombs;
            damageInEffect += bombs * blastDamage;

            int lastCovered = upperBound(positions, monsters[index][0] + 2 * blastRadius);
            if (lastCovered <= monsterCount) {
                expiring[lastCovered] += bombs * blastDamage;
            }
        }

        System.out.println(bombsUsed);
    }

    /** First index whose position exceeds {@code key}. */
    private static int upperBound(long[] positions, long key) {
        int low = 0;
        int high = positions.length;
        while (low < high) {
            int middle = (low + high) >>> 1;
            if (positions[middle] <= key) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low;
    }
}