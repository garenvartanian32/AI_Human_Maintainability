import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        byte[][] mat = new byte[n][n];
        for (int i = 0; i < n; i++) {
            String row = br.readLine().trim();
            int col = 0;
            for (int k = 0; k < row.length(); k++) {
                int v = Character.digit(row.charAt(k), 16);
                for (int shift = 3; shift >= 0; shift--) {
                    mat[i][col++] = (byte) ((v >> shift) & 1);
                }
            }
        }
        int[][] pre = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            int rowSum = 0;
            for (int j = 1; j <= n; j++) {
                rowSum += mat[i-1][j-1];
                pre[i][j] = pre[i-1][j] + rowSum;
            }
        }
        List<Integer> divisors = new ArrayList<>();
        for (int x = 1; x <= n; x++) {
            if (n % x == 0) divisors.add(x);
        }
        divisors.sort(Collections.reverseOrder());
        for (int x : divisors) {
            boolean ok = true;
            for (int bi = 0; bi < n && ok; bi += x) {
                for (int bj = 0; bj < n; bj += x) {
                    int r1 = bi+1, c1 = bj+1, r2 = bi+x, c2 = bj+x;
                    int s = pre[r2][c2] - pre[r1-1][c2] - pre[r2][c1-1] + pre[r1-1][c1-1];
                    if (s != 0 && s != x*x) { ok = false; break; }
                }
            }
            if (ok) { System.out.println(x); return; }
        }
    }
}