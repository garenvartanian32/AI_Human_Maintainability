import java.io.*;
import java.util.*;

public class Sol {
    static final long MOD = 1_000_000_007L;
    static final int MAXM = 200001;

    public static void main(String[] args) throws IOException {
        long[] L = new long[MAXM + 1];
        L[0] = 1;
        for (int t = 1; t <= MAXM; t++) {
            long term1 = (t <= 8) ? 1 : L[t - 9];
            long term2 = (t <= 9) ? 1 : L[t - 10];
            L[t] = (term1 + term2) % MOD;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            String n = stk.nextToken();
            int m = Integer.parseInt(stk.nextToken());
            long total = 0;
            for (int j = 0; j < n.length(); j++) {
                int d = n.charAt(j) - '0';
                int rem = 9 - d;
                if (m < rem) total += 1;
                else total += L[m - rem];
                total %= MOD;
            }
            sb.append(total).append('\n');
        }
        System.out.print(sb);
    }
}