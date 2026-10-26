import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            String row = br.readLine().trim();
            int col = 0;
            for (char ch : row.toCharArray()) {
                int v = Character.digit(ch, 16);
                String bin = String.format("%4s", Integer.toBinaryString(v)).replace(' ', '0');
                for (char b : bin.toCharArray()) grid[i][col++] = b - '0';
            }
        }
        List<Integer> divisors = new ArrayList<Integer>();
        for (int x = 1; x <= n; x++) if (n % x == 0) divisors.add(x);
        Collections.sort(divisors, Collections.reverseOrder());
        for (int x : divisors) {
            boolean ok = true;
            outer:
            for (int bi = 0; bi < n && ok; bi += x) {
                for (int bj = 0; bj < n; bj += x) {
                    int val = grid[bi][bj];
                    for (int i = bi; i < bi+x; i++) {
                        for (int j = bj; j < bj+x; j++) {
                            if (grid[i][j] != val) { ok = false; break outer; }
                        }
                    }
                }
            }
            if (ok) { System.out.println(x); return; }
        }
        System.out.println(1);
    }
}