import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Flips a single bit: reports 1 for input 0, and 0 for input 1.
 */
public class Sol {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int bit = Integer.parseInt(reader.readLine().trim());
        System.out.println(1 - bit);
    }
}