import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds two Hamiltonian paths that avoid forbidden endpoint adjacencies.
 *
 * <p>Both paths share the same middle spine but enter and leave it in a
 * different order, which keeps each forbidden pair non-adjacent.
 */
public class Sol {

    private static final int IMPOSSIBLE = -1;
    private static final int MINIMUM_EXTRA_EDGES = 1;
    private static final int TOO_FEW_TOWNS = 4;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        int townCount = nextInt(input);
        int edgeBudget = nextInt(input);
        int a = nextInt(input);
        int b = nextInt(input);
        int c = nextInt(input);
        int d = nextInt(input);

        // With only four towns there is no room for a middle section, and the
        // construction always spends exactly townCount + 1 edges.
        if (townCount == TOO_FEW_TOWNS || edgeBudget < townCount + MINIMUM_EXTRA_EDGES) {
            System.out.println(IMPOSSIBLE);
            return;
        }

        List<Integer> middle = new ArrayList<>();
        for (int town = 1; town <= townCount; town++) {
            if (town != a && town != b && town != c && town != d) {
                middle.add(town);
            }
        }

        System.out.println(describePath(a, c, middle, d, b));
        System.out.println(describePath(c, a, middle, b, d));
    }

    private static String describePath(
            int firstHead, int secondHead, List<Integer> middle, int firstTail, int secondTail) {
        StringBuilder path = new StringBuilder();
        path.append(firstHead).append(' ').append(secondHead);
        for (int town : middle) {
            path.append(' ').append(town);
        }
        path.append(' ').append(firstTail).append(' ').append(secondTail);
        return path.toString();
    }

    private static int nextInt(StreamTokenizer input) throws IOException {
        input.nextToken();
        return (int) input.nval;
    }
}