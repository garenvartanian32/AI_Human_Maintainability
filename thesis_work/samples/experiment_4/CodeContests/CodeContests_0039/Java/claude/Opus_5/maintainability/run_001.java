import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Enumerates every length-n string in normal form, in lexicographic order.
 *
 * <p>A string is in normal form when each letter is at most one past the largest
 * letter used before it. Building left to right in alphabetical order yields
 * the strings already sorted.
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

    private static void extend(int position, int highestUsed) {
        if (position == length) {
            results.append(buffer, 0, length).append('\n');
            return;
        }

        for (int letterIndex = 0; letterIndex <= highestUsed; letterIndex++) {
            buffer[position] = (char) (FIRST_LETTER + letterIndex);
            int nextHighest = (letterIndex == highestUsed) ? highestUsed + 1 : highestUsed;
            extend(position + 1, nextHighest);
        }
    }
}