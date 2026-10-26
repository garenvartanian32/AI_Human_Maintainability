import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n = (int) st.nval;
        int[] deg = new int[n+1];
        for (int i = 0; i < n-1; i++) {
            st.nextToken(); int u = (int) st.nval;
            st.nextToken(); int v = (int) st.nval;
            deg[u]++; deg[v]++;
        }
        boolean ok = true;
        for (int i = 1; i <= n; i++) if (deg[i] == 2) { ok = false; break; }
        System.out.println(ok ? "YES" : "NO");
    }
}