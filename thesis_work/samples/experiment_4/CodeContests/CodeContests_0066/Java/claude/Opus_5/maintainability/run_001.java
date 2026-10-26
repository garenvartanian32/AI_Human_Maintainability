import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Interleaves two partial edit logs into one consistent history.
 *
 * <p>An entry may be replayed only if it targets a position that already exists;
 * a placeholder entry appends a new position. Taking whichever log has a
 * currently-valid head is safe, because deferring a valid entry can never
 * unlock anything the other log needs.
 */
public class Sol {

    private static final long PLACEHOLDER = 0;
    private static final String IMPOSSIBLE = "-1";

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int testCount = (int) input.nval;

        StringBuilder answers = new StringBuilder();
        for (int test = 0; test < testCount; test++) {
            input.nextToken();
            long size = (long) input.nval;
            input.nextToken();
            int firstLength = (int) input.nval;
            input.nextToken();
            int secondLength = (int) input.nval;

            long[] first = readEntries(input, firstLength);
            long[] second = readEntries(input, secondLength);

            answers.append(mergeLogs(size, first, second)).append('\n');
        }

        System.out.print(answers);
    }

    private static long[] readEntries(StreamTokenizer input, int count) throws IOException {
        long[] entries = new long[count];
        for (int i = 0; i < count; i++) {
            input.nextToken();
            entries[i] = (long) input.nval;
        }
        return entries;
    }

    private static String mergeLogs(long size, long[] first, long[] second) {
        StringBuilder merged = new StringBuilder();
        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < first.length || secondIndex < second.length) {
            long entry;
            if (firstIndex < first.length && canReplay(first[firstIndex], size)) {
                entry = first[firstIndex++];
            } else if (secondIndex < second.length && canReplay(second[secondIndex], size)) {
                entry = second[secondIndex++];
            } else {
                return IMPOSSIBLE;
            }

            if (entry == PLACEHOLDER) {
                size++;
            }
            if (merged.length() > 0) {
                merged.append(' ');
            }
            merged.append(entry);
        }

        return merged.toString();
    }

    private static boolean canReplay(long entry, long size) {
        return entry == PLACEHOLDER || entry <= size;
    }
}