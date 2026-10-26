import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        long INF = Long.MAX_VALUE / 4;
        long[][] dist = new long[n+1][n+1];
        for (long[] row : dist) Arrays.fill(row, INF);
        for (int i = 1; i <= n; i++) dist[i][i] = 0;
        int[] ea = new int[m], eb = new int[m];
        long[] ec = new long[m];
        for (int i = 0; i < m; i++) {
            st.nextToken(); int a = (int) st.nval;
            st.nextToken(); int b = (int) st.nval;
            st.nextToken(); long c = (long) st.nval;
            ea[i] = a; eb[i] = b; ec[i] = c;
            if (c < dist[a][b]) { dist[a][b] = c; dist[b][a] = c; }
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] >= INF) continue;
                for (int j = 1; j <= n; j++) {
                    long nd = dist[i][k] + dist[k][j];
                    if (nd < dist[i][j]) dist[i][j] = nd;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (dist[ea[i]][eb[i]] < ec[i]) count++;
        }
        System.out.println(count);
    }
}