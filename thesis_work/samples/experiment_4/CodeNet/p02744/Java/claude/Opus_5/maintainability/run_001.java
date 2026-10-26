import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Lists every length-n string in normal form, in lexicographic order.
 *
 * <p>Each letter may be at most one past the largest used so far. That rule
 * keeps exactly one representative of every way of partitioning the positions,
 * and generating letters in alphabetical order emits them already sorted.
 */
public class Sol {

    private static final char FIRST_LETTER = 'a';

    private static int length;
    private static char[] buffer;
    private static StringBuilder results;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        length = Integer.parseInt(reader.readLine().trim());

        buffer = new char[length];
        results = new StringBuilder();

        extend(0, 0);
        System.out.print(results);
    }

    private static void extend(int position, int lettersUsed) {
        if (position == length) {
            results.append(buffer, 0, length).append('\n');
            return;
        }

        for (int letterIndex = 0; letterIndex <= lettersUsed; letterIndex++) {
            buffer[position] = (char) (FIRST_LETTER + letterIndex);
            int nextUsed = (letterIndex == lettersUsed) ? lettersUsed + 1 : lettersUsed;
            extend(position + 1, nextUsed);
        }
    }
}