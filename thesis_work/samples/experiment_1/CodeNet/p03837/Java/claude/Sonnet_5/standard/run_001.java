import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        int[][] edges = new int[m][3];
        long INF = Long.MAX_VALUE/4;
        long[][] dist = new long[n+1][n+1];
        for (long[] row : dist) Arrays.fill(row, INF);
        for (int i = 1; i <= n; i++) dist[i][i] = 0;
        for (int i = 0; i < m; i++) {
            st.nextToken(); int a = (int) st.nval;
            st.nextToken(); int b = (int) st.nval;
            st.nextToken(); int c = (int) st.nval;
            edges[i] = new int[]{a,b,c};
            if (c < dist[a][b]) { dist[a][b] = c; dist[b][a] = c; }
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] >= INF) continue;
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        int count = 0;
        for (int[] e : edges) {
            if (dist[e[0]][e[1]] != e[2]) count++;
        }
        System.out.println(count);
    }
}