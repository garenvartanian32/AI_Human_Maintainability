import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int t = (int) st.nval;
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < t; tc++) {
            st.nextToken(); long k = (long) st.nval;
            st.nextToken(); int n = (int) st.nval;
            st.nextToken(); int m = (int) st.nval;
            long[] a = new long[n];
            long[] b = new long[m];
            for (int i = 0; i < n; i++) { st.nextToken(); a[i] = (long) st.nval; }
            for (int j = 0; j < m; j++) { st.nextToken(); b[j] = (long) st.nval; }
            long length = k;
            int i = 0, j = 0;
            List<Long> res = new ArrayList<>();
            boolean ok = true;
            while (i < n || j < m) {
                if (i < n && (a[i] == 0 || a[i] <= length)) {
                    if (a[i] == 0) length++;
                    res.add(a[i]);
                    i++;
                } else if (j < m && (b[j] == 0 || b[j] <= length)) {
                    if (b[j] == 0) length++;
                    res.add(b[j]);
                    j++;
                } else {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                StringBuilder line = new StringBuilder();
                for (int x = 0; x < res.size(); x++) {
                    if (x > 0) line.append(' ');
                    line.append(res.get(x));
                }
                sb.append(line).append('\n');
            } else {
                sb.append(-1).append('\n');
            }
        }
        System.out.print(sb);
    }
}