import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Rearranges a string to hold as many disjoint copies of b and c as possible.
 *
 * <p>Copies of the two words compete for the same letters, so we try every
 * feasible count of the first word and keep the best total.
 */
public class Sol {

    private static final int ALPHABET_SIZE = 26;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String source = reader.readLine().trim();
        String firstWord = reader.readLine().trim();
        String secondWord = reader.readLine().trim();

        int[] available = letterCounts(source);
        int[] needFirst = letterCounts(firstWord);
        int[] needSecond = letterCounts(secondWord);

        int bestTotal = -1;
        int bestFirstCount = 0;
        int bestSecondCount = 0;

        int firstCeiling = maxCopies(available, needFirst, source.length());
        for (int firstCount = 0; firstCount <= firstCeiling; firstCount++) {
            int secondCount = source.length();
            for (int letter = 0; letter < ALPHABET_SIZE; letter++) {
                if (needSecond[letter] > 0) {
                    int leftover = available[letter] - firstCount * needFirst[letter];
                    secondCount = Math.min(secondCount, leftover / needSecond[letter]);
                }
            }

            if (firstCount + secondCount > bestTotal) {
                bestTotal = firstCount + secondCount;
                bestFirstCount = firstCount;
                bestSecondCount = secondCount;
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(firstWord.repeat(bestFirstCount));
        answer.append(secondWord.repeat(bestSecondCount));

        // Any letters not consumed by a copy can be appended in any order.
        for (int letter = 0; letter < ALPHABET_SIZE; letter++) {
            int leftover = available[letter]
                    - bestFirstCount * needFirst[letter]
                    - bestSecondCount * needSecond[letter];
            for (int i = 0; i < leftover; i++) {
                answer.append((char) ('a' + letter));
            }
        }

        System.out.println(answer);
    }

    /** How many whole copies of {@code needed} fit inside {@code available}. */
    private static int maxCopies(int[] available, int[] needed, int ceiling) {
        int copies = ceiling;
        for (int letter = 0; letter < ALPHABET_SIZE; letter++) {
            if (needed[letter] > 0) {
                copies = Math.min(copies, available[letter] / needed[letter]);
            }
        }
        return copies;
    }

    private static int[] letterCounts(String word) {
        int[] counts = new int[ALPHABET_SIZE];
        for (int i = 0; i < word.length(); i++) {
            counts[word.charAt(i) - 'a']++;
        }
        return counts;
    }
}