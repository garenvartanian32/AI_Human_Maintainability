import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        long[] a = new long[n], b = new long[n];
        for (int i = 0; i < n; i++) { st.nextToken(); a[i]=(long)st.nval; st.nextToken(); b[i]=(long)st.nval; }
        long[] c = new long[m], d = new long[m];
        for (int i = 0; i < m; i++) { st.nextToken(); c[i]=(long)st.nval; st.nextToken(); d[i]=(long)st.nval; }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int bestJ = 0;
            long bestD = Long.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                long dist = Math.abs(a[i]-c[j]) + Math.abs(b[i]-d[j]);
                if (dist < bestD) { bestD = dist; bestJ = j; }
            }
            out.append(bestJ+1).append("\n");
        }
        System.out.print(out);
    }
}