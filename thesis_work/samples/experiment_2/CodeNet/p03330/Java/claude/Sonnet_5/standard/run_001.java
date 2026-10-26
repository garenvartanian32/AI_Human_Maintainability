import java.io.*;
import java.util.*;
public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int c = (int) st.nval;
        long[][] D = new long[c][c];
        for (int i = 0; i < c; i++)
            for (int j = 0; j < c; j++) { st.nextToken(); D[i][j] = (long) st.nval; }
        long[][] count = new long[3][c];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                st.nextToken(); int col = (int) st.nval;
                int g = (i + j) % 3;
                count[g][col-1]++;
            }
        }
        long[][] cost = new long[3][c];
        for (int g = 0; g < 3; g++) {
            for (int y = 0; y < c; y++) {
                long s = 0;
                for (int x = 0; x < c; x++) s += count[g][x] * D[x][y];
                cost[g][y] = s;
            }
        }
        long best = Long.MAX_VALUE;
        for (int y0 = 0; y0 < c; y0++) {
            for (int y1 = 0; y1 < c; y1++) {
                if (y1 == y0) continue;
                for (int y2 = 0; y2 < c; y2++) {
                    if (y2 == y0 || y2 == y1) continue;
                    long total = cost[0][y0] + cost[1][y1] + cost[2][y2];
                    if (total < best) best = total;
                }
            }
        }
        System.out.println(best);
    }
}