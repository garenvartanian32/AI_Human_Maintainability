import java.util.*;
import java.io.*;

public class Main {
    static char solve(int n, long[] A, String S) {
        long[] basis = new long[64];
        char lastOwner = '0';
        for (int i = n-1; i >= 0; i--) {
            long cur = A[i];
            for (int b = 63; b >= 0; b--) {
                if (((cur >> b) & 1) == 0) continue;
                if (basis[b] == 0) {
                    basis[b] = cur;
                    lastOwner = S.charAt(i);
                    cur = 0;
                    break;
                } else {
                    cur ^= basis[b];
                }
            }
        }
        return lastOwner;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st = new StringTokenizer(br.readLine());
            long[] A = new long[n];
            for (int j = 0; j < n; j++) A[j] = Long.parseLong(st.nextToken());
            String S = br.readLine().trim();
            out.append(solve(n, A, S)).append("\n");
        }
        System.out.print(out);
    }
}