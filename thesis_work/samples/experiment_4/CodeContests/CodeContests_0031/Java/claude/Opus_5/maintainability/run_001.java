import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * A toy memory manager supporting alloc, erase and defragment.
 */
public class Sol {

    private static final int FREE = 0;
    private static final String NULL_RESULT = "NULL";
    private static final String ILLEGAL_ERASE = "ILLEGAL_ERASE_ARGUMENT";
    private static final int NO_RUN_FOUND = -1;

    /** Fixed-size memory modelled as an array of owning block identifiers. */
    private static final class MemoryManager {
        private final int[] cells;
        private final Set<Integer> liveBlocks = new HashSet<>();
        private int nextIdentifier = 1;

        MemoryManager(int size) {
            this.cells = new int[size];
        }

        /** Reserves the first run of free cells that is long enough. */
        String allocate(int cellsNeeded) {
            int start = findFreeRun(cellsNeeded);
            if (start == NO_RUN_FOUND) {
                return NULL_RESULT;
            }

            int identifier = nextIdentifier++;
            Arrays.fill(cells, start, start + cellsNeeded, identifier);
            liveBlocks.add(identifier);
            return Integer.toString(identifier);
        }

        /** Frees a live block; returns an error string for unknown blocks. */
        String erase(int identifier) {
            if (!liveBlocks.remove(identifier)) {
                return ILLEGAL_ERASE;
            }
            for (int cell = 0; cell < cells.length; cell++) {
                if (cells[cell] == identifier) {
                    cells[cell] = FREE;
                }
            }
            return null;
        }

        /** Slides every allocated block towards the start, preserving order. */
        void defragment() {
            int writePosition = 0;
            for (int cell = 0; cell < cells.length; cell++) {
                if (cells[cell] != FREE) {
                    cells[writePosition++] = cells[cell];
                }
            }
            Arrays.fill(cells, writePosition, cells.length, FREE);
        }

        private int findFreeRun(int cellsNeeded) {
            int runLength = 0;
            for (int cell = 0; cell < cells.length; cell++) {
                runLength = (cells[cell] == FREE) ? runLength + 1 : 0;
                if (runLength == cellsNeeded) {
                    return cell - cellsNeeded + 1;
                }
            }
            return NO_RUN_FOUND;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer header = new StringTokenizer(reader.readLine());
        int operationCount = Integer.parseInt(header.nextToken());
        int memorySize = Integer.parseInt(header.nextToken());

        MemoryManager manager = new MemoryManager(memorySize);
        StringBuilder output = new StringBuilder();

        for (int operation = 0; operation < operationCount; operation++) {
            StringTokenizer line = new StringTokenizer(reader.readLine());
            String command = line.nextToken();

            switch (command) {
                case "alloc":
                    output.append(manager.allocate(Integer.parseInt(line.nextToken())))
                          .append('\n');
                    break;
                case "erase":
                    String error = manager.erase(Integer.parseInt(line.nextToken()));
                    if (error != null) {
                        output.append(error).append('\n');
                    }
                    break;
                case "defragment":
                    manager.defragment();
                    break;
                default:
                    break;
            }
        }

        System.out.print(output);
    }
}