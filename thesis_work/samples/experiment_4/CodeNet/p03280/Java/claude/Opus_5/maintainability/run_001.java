import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Counts the interior squares of a grid — those not touching any edge.
 *
 * <p>Stripping the outermost row and column from each side leaves the squares
 * that have neighbours on all four sides.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());

        long rows = Long.parseLong(line.nextToken());
        long columns = Long.parseLong(line.nextToken());

        System.out.println((rows - 1) * (columns - 1));
    }
}