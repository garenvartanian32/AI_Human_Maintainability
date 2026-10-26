import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Capitalises every letter whose alphabet position is at most k.
 *
 * <p>The original casing carries no information, so each character is first
 * normalised to lower case and then re-cased purely from its rank.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String text = reader.readLine().trim();
        int threshold = Integer.parseInt(reader.readLine().trim());

        System.out.println(capitaliseLowRanked(text, threshold));
    }

    private static String capitaliseLowRanked(String text, int threshold) {
        char[] result = new char[text.length()];
        for (int i = 0; i < text.length(); i++) {
            char lowered = Character.toLowerCase(text.charAt(i));
            int rank = lowered - 'a' + 1;
            result[i] = (rank <= threshold) ? Character.toUpperCase(lowered) : lowered;
        }
        return new String(result);
    }
}