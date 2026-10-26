import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Decodes a message enciphered with a repeating sequence of shift keys.
 *
 * <p>Lower and upper case form one continuous 52-letter ring, so a shift can
 * carry a letter from one case into the other.
 */
public class Sol {

    private static final String ALPHABET =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int END_OF_INPUT = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder messages = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }

            int keyCount = Integer.parseInt(line);
            if (keyCount == END_OF_INPUT) {
                break;
            }

            StringTokenizer keyTokens = new StringTokenizer(reader.readLine());
            int[] keys = new int[keyCount];
            for (int i = 0; i < keyCount; i++) {
                keys[i] = Integer.parseInt(keyTokens.nextToken());
            }

            String ciphertext = reader.readLine().trim();
            messages.append(decode(ciphertext, keys)).append('\n');
        }

        System.out.print(messages);
    }

    /** Undoes the encryption by shifting each letter back by its key. */
    private static String decode(String ciphertext, int[] keys) {
        StringBuilder plaintext = new StringBuilder(ciphertext.length());
        for (int position = 0; position < ciphertext.length(); position++) {
            int shift = keys[position % keys.length];
            int enciphered = ALPHABET.indexOf(ciphertext.charAt(position));
            int original = ((enciphered - shift) % ALPHABET.length() + ALPHABET.length())
                    % ALPHABET.length();
            plaintext.append(ALPHABET.charAt(original));
        }
        return plaintext.toString();
    }
}