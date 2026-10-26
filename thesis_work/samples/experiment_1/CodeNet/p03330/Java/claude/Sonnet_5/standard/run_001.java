import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int c = (int) st.nval;
        long[][] D = new long[c+1][c+1];
        for (int i = 1; i <= c; i++)
            for (int j = 1; j <= c; j++) { st.nextToken(); D[i][j] = (long) st.nval; }
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) { st.nextToken(); grid[i][j] = (int) st.nval; }
        long[][] hist = new long[3][c+1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int g = ((i+1)+(j+1)) % 3;
                hist[g][grid[i][j]]++;
            }
        long[][] cost = new long[3][c+1];
        for (int g = 0; g < 3; g++)
            for (int k = 1; k <= c; k++) {
                long s = 0;
                for (int col = 1; col <= c; col++) s += hist[g][col] * D[col][k];
                cost[g][k] = s;
            }
        long best = Long.MAX_VALUE;
        for (int k0 = 1; k0 <= c; k0++)
            for (int k1 = 1; k1 <= c; k1++) {
                if (k1 == k0) continue;
                for (int k2 = 1; k2 <= c; k2++) {
                    if (k2 == k0 || k2 == k1) continue;
                    long total = cost[0][k0] + cost[1][k1] + cost[2][k2];
                    best = Math.min(best, total);
                }
            }
        System.out.println(best);
    }
}