import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long s = Long.parseLong(br.readLine().trim());
        Map<Long, Integer> seen = new HashMap<>();
        long cur = s;
        int i = 1;
        seen.put(cur, 1);
        while (true) {
            if (cur % 2 == 0) cur = cur / 2;
            else cur = 3 * cur + 1;
            i++;
            if (seen.containsKey(cur)) {
                System.out.println(i);
                return;
            }
            seen.put(cur, i);
        }
    }
}