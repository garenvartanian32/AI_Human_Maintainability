import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Simulates the bozu-mekuri card game and reports each player's final hand.
 */
public class Sol {

    private static final int END_OF_INPUT = 0;
    private static final char MONK = 'S';      // sends the player's hand to the field
    private static final char PRINCESS = 'L';  // sweeps the field into the hand

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            int playerCount = Integer.parseInt(line);
            if (playerCount == END_OF_INPUT) {
                break;
            }

            String cards = reader.readLine().trim();
            output.append(play(playerCount, cards)).append('\n');
        }

        System.out.print(output);
    }

    /** Deals the cards in turn; returns sorted hand sizes then the field size. */
    private static String play(int playerCount, String cards) {
        int[] hands = new int[playerCount];
        int field = 0;

        for (int turn = 0; turn < cards.length(); turn++) {
            int player = turn % playerCount;
            char card = cards.charAt(turn);

            if (card == MONK) {
                field += hands[player] + 1;
                hands[player] = 0;
            } else if (card == PRINCESS) {
                hands[player] += field + 1;
                field = 0;
            } else {
                hands[player]++;
            }
        }

        Arrays.sort(hands);

        StringBuilder result = new StringBuilder();
        for (int hand : hands) {
            result.append(hand).append(' ');
        }
        result.append(field);
        return result.toString();
    }
}