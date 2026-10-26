import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Set;

/**
 * Decides whether pressing every button can switch off all the bulbs.
 *
 * <p>Pressing every button is always allowed, so a bulb can be switched off
 * exactly when at least one button controls it.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int buttonCount = (int) input.nval;
        input.nextToken();
        int bulbCount = (int) input.nval;

        Set<Integer> controlledBulbs = new HashSet<>();
        for (int button = 0; button < buttonCount; button++) {
            input.nextToken();
            int bulbsOnThisButton = (int) input.nval;
            for (int i = 0; i < bulbsOnThisButton; i++) {
                input.nextToken();
                controlledBulbs.add((int) input.nval);
            }
        }

        boolean everyBulbIsCovered = controlledBulbs.size() == bulbCount;
        System.out.println(everyBulbIsCovered ? "YES" : "NO");
    }
}