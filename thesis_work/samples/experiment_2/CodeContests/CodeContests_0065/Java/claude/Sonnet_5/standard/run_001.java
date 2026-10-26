import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long q = Long.parseLong(br.readLine().trim());
        if (q == 1) {
            System.out.println(1);
            System.out.println(0);
            return;
        }
        List<Long> factors = new ArrayList<>();
        long n = q;
        for (long d = 2; d * d <= n; d++) {
            while (n % d == 0) {
                factors.add(d);
                n /= d;
            }
        }
        if (n > 1) factors.add(n);
        int k = factors.size();
        if (k == 1) {
            System.out.println(1);
            System.out.println(0);
        } else if (k == 2) {
            System.out.println(2);
        } else {
            Collections.sort(factors);
            System.out.println(1);
            System.out.println(factors.get(0) * factors.get(1));
        }
    }
}