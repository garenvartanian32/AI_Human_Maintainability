import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Reports whether the product of two integers is odd or even.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line = new StringTokenizer(reader.readLine());

        long first = Long.parseLong(line.nextToken());
        long second = Long.parseLong(line.nextToken());

        // A product is odd only when neither factor contributes a factor of two.
        boolean productIsOdd = isOdd(first) && isOdd(second);
        System.out.println(productIsOdd ? "Odd" : "Even");
    }

    private static boolean isOdd(long value) {
        return value % 2 != 0;
    }
}