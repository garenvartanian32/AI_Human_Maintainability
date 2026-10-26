import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Assigns each student to the nearest checkpoint by Manhattan distance.
 *
 * <p>Ties are resolved in favour of the smallest index, which falls out of
 * scanning left to right and only replacing on a strict improvement.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int studentCount = (int) input.nval;
        input.nextToken();
        int checkpointCount = (int) input.nval;

        long[] studentX = new long[studentCount];
        long[] studentY = new long[studentCount];
        for (int i = 0; i < studentCount; i++) {
            input.nextToken();
            studentX[i] = (long) input.nval;
            input.nextToken();
            studentY[i] = (long) input.nval;
        }

        long[] checkpointX = new long[checkpointCount];
        long[] checkpointY = new long[checkpointCount];
        for (int j = 0; j < checkpointCount; j++) {
            input.nextToken();
            checkpointX[j] = (long) input.nval;
            input.nextToken();
            checkpointY[j] = (long) input.nval;
        }

        StringBuilder answers = new StringBuilder();
        for (int i = 0; i < studentCount; i++) {
            int bestNumber = 1;
            long bestDistance = manhattanDistance(
                    studentX[i], studentY[i], checkpointX[0], checkpointY[0]);

            for (int j = 1; j < checkpointCount; j++) {
                long distance = manhattanDistance(
                        studentX[i], studentY[i], checkpointX[j], checkpointY[j]);
                if (distance < bestDistance) {
                    bestDistance = distance;
                    bestNumber = j + 1;
                }
            }
            answers.append(bestNumber).append('\n');
        }

        System.out.print(answers);
    }

    private static long manhattanDistance(long fromX, long fromY, long toX, long toY) {
        return Math.abs(fromX - toX) + Math.abs(fromY - toY);
    }
}