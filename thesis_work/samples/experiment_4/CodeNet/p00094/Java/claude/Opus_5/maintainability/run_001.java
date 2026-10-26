import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Converts a room's area from square metres to tsubo.
 */
public class Sol {

    private static final double SQUARE_METRES_PER_TSUBO = 3.305785;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());

        double width = Double.parseDouble(line.nextToken());
        double depth = Double.parseDouble(line.nextToken());

        double areaInTsubo = (width * depth) / SQUARE_METRES_PER_TSUBO;
        System.out.printf("%.6f%n", areaInTsubo);
    }
}