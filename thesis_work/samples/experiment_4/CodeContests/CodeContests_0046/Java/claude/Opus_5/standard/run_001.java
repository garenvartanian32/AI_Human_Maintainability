import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        long[] a = new long[n], b = new long[n];
        for (int i = 0; i < n; i++) {
            st.nextToken(); a[i] = (long) st.nval;
            st.nextToken(); b[i] = (long) st.nval;
        }
        long[] c = new long[m], d = new long[m];
        for (int j = 0; j < m; j++) {
            st.nextToken(); c[j] = (long) st.nval;
            st.nextToken(); d[j] = (long) st.nval;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int bestJ = -1;
            long bestDist = Long.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                long dist = Math.abs(a[i] - c[j]) + Math.abs(b[i] - d[j]);
                if (dist < bestDist) {
                    bestDist = dist;
                    bestJ = j + 1;
                }
            }
            sb.append(bestJ).append('\n');
        }
        System.out.print(sb);
    }
}