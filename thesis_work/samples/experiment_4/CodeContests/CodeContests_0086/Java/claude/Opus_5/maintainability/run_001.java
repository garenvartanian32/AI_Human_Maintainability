import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Largest permutation reachable using an allowed set of position swaps.
 *
 * <p>Swaps are freely composable, so within one connected component the values
 * can be permuted arbitrarily. Placing them largest-first at the smallest
 * positions therefore gives the lexicographic maximum.
 */
public class Sol {

    /** Union-find over positions, with path compression. */
    private static final class DisjointSet {
        private final int[] parent;

        DisjointSet(int size) {
            parent = new int[size + 1];
            for (int position = 0; position <= size; position++) {
                parent[position] = position;
            }
        }

        int find(int position) {
            while (parent[position] != position) {
                parent[position] = parent[parent[position]];
                position = parent[position];
            }
            return position;
        }

        void union(int first, int second) {
            int firstRoot = find(first);
            int secondRoot = find(second);
            if (firstRoot != secondRoot) {
                parent[firstRoot] = secondRoot;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DataInputStream input = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));

        int size = nextInt(input);
        int swapCount = nextInt(input);

        int[] values = new int[size + 1];
        for (int position = 1; position <= size; position++) {
            values[position] = nextInt(input);
        }

        DisjointSet positions = new DisjointSet(size);
        for (int swap = 0; swap < swapCount; swap++) {
            positions.union(nextInt(input), nextInt(input));
        }

        List<List<Integer>> components = groupByComponent(positions, size);

        int[] answer = new int[size + 1];
        for (List<Integer> memberPositions : components) {
            List<Integer> available = new ArrayList<>();
            for (int position : memberPositions) {
                available.add(values[position]);
            }
            available.sort(Collections.reverseOrder());
            for (int i = 0; i < memberPositions.size(); i++) {
                answer[memberPositions.get(i)] = available.get(i);
            }
        }

        StringBuilder output = new StringBuilder();
        for (int position = 1; position <= size; position++) {
            if (position > 1) {
                output.append(' ');
            }
            output.append(answer[position]);
        }
        System.out.println(output);
    }

    private static List<List<Integer>> groupByComponent(DisjointSet positions, int size) {
        int[] componentIndex = new int[size + 1];
        Arrays.fill(componentIndex, -1);

        List<List<Integer>> components = new ArrayList<>();
        for (int position = 1; position <= size; position++) {
            int root = positions.find(position);
            if (componentIndex[root] < 0) {
                componentIndex[root] = components.size();
                components.add(new ArrayList<>());
            }
            components.get(componentIndex[root]).add(position);
        }
        return components;
    }

    private static int nextInt(DataInputStream input) throws IOException {
        int value = 0;
        int character = input.read();
        while (character < '0' || character > '9') {
            character = input.read();
        }
        while (character >= '0' && character <= '9') {
            value = value * 10 + (character - '0');
            character = input.read();
        }
        return value;
    }
}