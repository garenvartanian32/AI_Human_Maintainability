import java.io.*;
import java.util.*;

public class Sol {
    static int[] parent;

    static int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int m = (int) st.nval;
        parent = new int[m + 1];
        for (int i = 0; i <= m; i++) parent[i] = i;

        List<Integer> basis = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            st.nextToken(); int kk = (int) st.nval;
            int u, v;
            if (kk == 1) {
                u = 0;
                st.nextToken(); v = (int) st.nval;
            } else {
                st.nextToken(); u = (int) st.nval;
                st.nextToken(); v = (int) st.nval;
            }
            int ru = find(u), rv = find(v);
            if (ru != rv) {
                parent[ru] = rv;
                basis.add(i);
            }
        }

        final long MOD = 1_000_000_007L;
        int rank = basis.size();
        long total = 1;
        long base = 2;
        int e = rank;
        while (e > 0) {
            if ((e & 1) == 1) total = total * base % MOD;
            base = base * base % MOD;
            e >>= 1;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(total).append(' ').append(rank).append('\n');
        for (int i = 0; i < basis.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(basis.get(i));
        }
        System.out.println(sb);
    }
}