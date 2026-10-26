import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        int n = (int) st.nval;
        int[] deg = new int[n + 1];
        for (int i = 0; i < n - 1; i++) {
            st.nextToken(); int u = (int) st.nval;
            st.nextToken(); int v = (int) st.nval;
            deg[u]++;
            deg[v]++;
        }
        for (int i = 1; i <= n; i++) {
            if (deg[i] == 2) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}