import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Decides whether a tree admits the required edge configuration.
 *
 * <p>A vertex whose degree is exactly two cannot be satisfied, so the presence
 * of any such vertex rules the whole configuration out.
 */
public class Sol {

    private static final int DEGREE_THAT_BLOCKS = 2;

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int vertexCount = (int) input.nval;

        int[] degrees = new int[vertexCount + 1];
        for (int edge = 0; edge < vertexCount - 1; edge++) {
            input.nextToken();
            degrees[(int) input.nval]++;
            input.nextToken();
            degrees[(int) input.nval]++;
        }

        System.out.println(hasBlockingVertex(degrees, vertexCount) ? "NO" : "YES");
    }

    private static boolean hasBlockingVertex(int[] degrees, int vertexCount) {
        for (int vertex = 1; vertex <= vertexCount; vertex++) {
            if (degrees[vertex] == DEGREE_THAT_BLOCKS) {
                return true;
            }
        }
        return false;
    }
}