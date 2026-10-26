import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine().trim());
        long total = 0;
        for (long d = 1; d * d <= n; d++) {
            if (n % d == 0) {
                Set<Long> divisors = new HashSet<>();
                divisors.add(d);
                divisors.add(n / d);
                for (long dd : divisors) {
                    long m = dd - 1;
                    if (m >= 1) {
                        long q = n / dd;
                        if (q < m) total += m;
                    }
                }
            }
        }
        System.out.println(total);
    }
}