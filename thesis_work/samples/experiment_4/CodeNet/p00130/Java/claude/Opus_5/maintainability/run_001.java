import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Reconstructs a train's carriage order from a conductor's walk record.
 *
 * <p>The record is a chain of moves; each move links the current carriage to
 * the next one either behind it ({@code ->}) or in front of it. Recording both
 * links builds a doubly linked list, and the front carriage is the only one
 * with nothing ahead of it.
 */
public class Sol {

    private static final String BACKWARD_MARKER = "->";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int recordCount = Integer.parseInt(reader.readLine().trim());

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < recordCount; i++) {
            output.append(rebuildTrain(reader.readLine().trim())).append('\n');
        }
        System.out.print(output);
    }

    private static String rebuildTrain(String record) {
        Map<Character, Character> carriageBehind = new HashMap<>();
        Map<Character, Character> carriageAhead = new HashMap<>();
        List<Character> seen = new ArrayList<>();

        char current = record.charAt(0);
        seen.add(current);

        int position = 1;
        while (position < record.length()) {
            boolean movedBackward = record.startsWith(BACKWARD_MARKER, position);
            if (movedBackward) {
                position += BACKWARD_MARKER.length();
            }

            char nextCarriage = record.charAt(position);
            position++;
            if (!seen.contains(nextCarriage)) {
                seen.add(nextCarriage);
            }

            if (movedBackward) {
                carriageBehind.put(current, nextCarriage);
                carriageAhead.put(nextCarriage, current);
            } else {
                carriageAhead.put(current, nextCarriage);
                carriageBehind.put(nextCarriage, current);
            }

            current = nextCarriage;
        }

        char front = 0;
        for (char carriage : seen) {
            if (!carriageAhead.containsKey(carriage)) {
                front = carriage;
                break;
            }
        }

        StringBuilder order = new StringBuilder();
        Character carriage = front;
        while (carriage != null) {
            order.append((char) carriage);
            carriage = carriageBehind.get(carriage);
        }
        return order.toString();
    }
}