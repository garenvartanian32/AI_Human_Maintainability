import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Selection sort, reporting the sorted array and the number of swaps made.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int count = (int) input.nval;
        int[] values = new int[count];
        for (int i = 0; i < count; i++) {
            input.nextToken();
            values[i] = (int) input.nval;
        }

        int swapCount = selectionSort(values);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(values[i]);
        }
        output.append('\n').append(swapCount);
        System.out.println(output);
    }

    /** Sorts {@code values} in place; returns how many real swaps happened. */
    private static int selectionSort(int[] values) {
        int swapCount = 0;

        for (int boundary = 0; boundary < values.length; boundary++) {
            int smallest = boundary;
            for (int candidate = boundary + 1; candidate < values.length; candidate++) {
                if (values[candidate] < values[smallest]) {
                    smallest = candidate;
                }
            }

            // Only count a swap when the element actually moves.
            if (smallest != boundary) {
                int held = values[boundary];
                values[boundary] = values[smallest];
                values[smallest] = held;
                swapCount++;
            }
        }

        return swapCount;
    }
}