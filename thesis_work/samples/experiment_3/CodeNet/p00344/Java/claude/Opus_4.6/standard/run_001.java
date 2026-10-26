import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        long[] a = new long[n];
        for (int i = 0; i < n; i++) { st.nextToken(); a[i] = (long) st.nval; }
        int[] nxt = new int[n];
        for (int i = 0; i < n; i++) nxt[i] = (int) ((i + a[i]) % n);
        int[] color = new int[n];
        boolean[] onCycle = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (color[i] != 0) continue;
            List<Integer> path = new ArrayList<>();
            int cur = i;
            while (color[cur] == 0) {
                color[cur] = 1;
                path.add(cur);
                cur = nxt[cur];
            }
            if (color[cur] == 1) {
                int idx = path.indexOf(cur);
                for (int k = idx; k < path.size(); k++) onCycle[path.get(k)] = true;
            }
            for (int node : path) color[node] = 2;
        }
        int count = 0;
        for (boolean b : onCycle) if (b) count++;
        System.out.println(count);
    }
}