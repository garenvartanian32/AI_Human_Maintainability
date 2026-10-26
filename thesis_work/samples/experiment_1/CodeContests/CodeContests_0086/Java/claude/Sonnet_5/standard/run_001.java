import java.util.*;
import java.io.*;

public class Main {
    static int[] parent;
    static int find(int x) {
        while (parent[x] != x) { parent[x] = parent[parent[x]]; x = parent[x]; }
        return x;
    }
    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        int[] p = new int[n];
        for (int i = 0; i < n; i++) { st.nextToken(); p[i] = (int) st.nval; }
        parent = new int[n+1];
        for (int i = 0; i <= n; i++) parent[i] = i;
        for (int i = 0; i < m; i++) {
            st.nextToken(); int a = (int) st.nval;
            st.nextToken(); int b = (int) st.nval;
            int ra = find(a), rb = find(b);
            if (ra != rb) parent[ra] = rb;
        }
        Map<Integer, List<Integer>> groups = new HashMap<Integer, List<Integer>>();
        for (int i = 1; i <= n; i++) groups.computeIfAbsent(find(i), k -> new ArrayList<Integer>()).add(i);
        int[] result = p.clone();
        for (List<Integer> idxs : groups.values()) {
            List<Integer> vals = new ArrayList<Integer>();
            for (int i : idxs) vals.add(p[i-1]);
            vals.sort(Collections.reverseOrder());
            Collections.sort(idxs);
            for (int k = 0; k < idxs.size(); k++) result[idxs.get(k)-1] = vals.get(k);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) { if (i>0) sb.append(' '); sb.append(result[i]); }
        System.out.println(sb);
    }
}