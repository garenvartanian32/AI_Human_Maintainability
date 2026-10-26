import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Size and minimal basis of the span of sparse GF(2) vectors.
 *
 * <p>Each vector has one or two set coordinates, so it behaves like an edge in a
 * graph. Independence over GF(2) is then exactly acyclicity, which union-find
 * decides directly.
 */
public class Sol {

    private static final long MODULUS = 1_000_000_007L;
    private static final int GROUND = 0;

    /** Union-find over coordinate nodes, with path compression. */
    private static final class DisjointSet {
        private final int[] parent;

        DisjointSet(int size) {
            parent = new int[size + 1];
            for (int node = 0; node <= size; node++) {
                parent[node] = node;
            }
        }

        int find(int node) {
            while (parent[node] != node) {
                parent[node] = parent[parent[node]];
                node = parent[node];
            }
            return node;
        }

        /** Merges the two components; returns true when they were distinct. */
        boolean union(int first, int second) {
            int firstRoot = find(first);
            int secondRoot = find(second);
            if (firstRoot == secondRoot) {
                return false;
            }
            parent[firstRoot] = secondRoot;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int vectorCount = (int) input.nval;
        input.nextToken();
        int coordinateCount = (int) input.nval;

        DisjointSet components = new DisjointSet(coordinateCount);
        List<Integer> basisIndices = new ArrayList<>();

        for (int vectorIndex = 1; vectorIndex <= vectorCount; vectorIndex++) {
            input.nextToken();
            int setCoordinates = (int) input.nval;

            int first;
            int second;
            if (setCoordinates == 1) {
                first = GROUND;
                input.nextToken();
                second = (int) input.nval;
            } else {
                input.nextToken();
                first = (int) input.nval;
                input.nextToken();
                second = (int) input.nval;
            }

            if (components.union(first, second)) {
                basisIndices.add(vectorIndex);
            }
        }

        StringBuilder output = new StringBuilder();
        output.append(modularPowerOfTwo(basisIndices.size()))
              .append(' ')
              .append(basisIndices.size())
              .append('\n');
        for (int i = 0; i < basisIndices.size(); i++) {
            if (i > 0) {
                output.append(' ');
            }
            output.append(basisIndices.get(i));
        }
        System.out.println(output);
    }

    private static long modularPowerOfTwo(int exponent) {
        long result = 1;
        long factor = 2;
        for (int remaining = exponent; remaining > 0; remaining >>= 1) {
            if ((remaining & 1) == 1) {
                result = result * factor % MODULUS;
            }
            factor = factor * factor % MODULUS;
        }
        return result;
    }
}