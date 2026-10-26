import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Counts completed step-ups and step-downs from a sequence of foot movements.
 */
public class Sol {

    private static final char RAISE_SUFFIX = 'u';
    private static final int END_OF_INPUT = 0;

    // Where the pair of feet currently is.
    private static final int ON_FLOOR = 0;
    private static final int RISING = 1;
    private static final int ON_STEP = 2;
    private static final int FALLING = 3;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));
        input.wordChars('a', 'z');

        StringBuilder answers = new StringBuilder();
        while (true) {
            input.nextToken();
            int movementCount = (int) input.nval;
            if (movementCount == END_OF_INPUT) {
                break;
            }

            int position = ON_FLOOR;
            int completed = 0;

            for (int i = 0; i < movementCount; i++) {
                input.nextToken();
                boolean isRaise = input.sval.charAt(1) == RAISE_SUFFIX;

                switch (position) {
                    case ON_FLOOR:
                        position = RISING;
                        break;
                    case RISING:
                        if (isRaise) {
                            position = ON_STEP;
                            completed++;
                        } else {
                            position = ON_FLOOR;
                        }
                        break;
                    case ON_STEP:
                        position = FALLING;
                        break;
                    default: // FALLING
                        if (isRaise) {
                            position = ON_STEP;
                        } else {
                            position = ON_FLOOR;
                            completed++;
                        }
                        break;
                }
            }

            answers.append(completed).append('\n');
        }

        System.out.print(answers);
    }
}