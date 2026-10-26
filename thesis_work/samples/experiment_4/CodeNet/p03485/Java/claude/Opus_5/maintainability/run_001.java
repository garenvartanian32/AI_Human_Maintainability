import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Average of two integers, rounded up when it lands on a half.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());

        long first = Long.parseLong(line.nextToken());
        long second = Long.parseLong(line.nextToken());

        // Adding one before the integer division rounds .5 upwards.
        System.out.println((first + second + 1) / 2);
    }
}