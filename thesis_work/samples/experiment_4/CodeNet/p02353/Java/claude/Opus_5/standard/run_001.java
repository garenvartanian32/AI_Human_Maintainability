import java.io.*;
import java.util.*;

public class Sol {
    static long[] sumTree;
    static Long[] lazy;
    static int size;

    static void pushDown(int node, int nodeLen) {
        if (lazy[node] != null) {
            long val = lazy[node];
            int left = 2*node, right = 2*node+1;
            int half = nodeLen / 2;
            sumTree[left] = val * half;
            sumTree[right] = val * half;
            lazy[left] = val;
            lazy[right] = val;
            lazy[node] = null;
        }
    }

    static void update(int node, int nodeL, int nodeR, int s, int t, long x) {
        if (t < nodeL || nodeR < s) return;
        if (s <= nodeL && nodeR <= t) {
            sumTree[node] = x * (nodeR - nodeL + 1);
            lazy[node] = x;
            return;
        }
        pushDown(node, nodeR - nodeL + 1);
        int mid = (nodeL + nodeR) / 2;
        update(2*node, nodeL, mid, s, t, x);
        update(2*node+1, mid+1, nodeR, s, t, x);
        sumTree[node] = sumTree[2*node] + sumTree[2*node+1];
    }

    static long query(int node, int nodeL, int nodeR, int s, int t) {
        if (t < nodeL || nodeR < s) return 0;
        if (s <= nodeL && nodeR <= t) return sumTree[node];
        pushDown(node, nodeR - nodeL + 1);
        int mid = (nodeL + nodeR) / 2;
        return query(2*node, nodeL, mid, s, t) + query(2*node+1, mid+1, nodeR, s, t);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); int q = (int) st.nval;

        size = 1;
        while (size < n) size *= 2;
        sumTree = new long[2 * size];
        lazy = new Long[2 * size];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st.nextToken(); int typ = (int) st.nval;
            if (typ == 0) {
                st.nextToken(); int s = (int) st.nval;
                st.nextToken(); int t = (int) st.nval;
                st.nextToken(); int x = (int) st.nval;
                update(1, 0, size-1, s, t, x);
            } else {
                st.nextToken(); int s = (int) st.nval;
                st.nextToken(); int t = (int) st.nval;
                sb.append(query(1, 0, size-1, s, t)).append('\n');
            }
        }
        System.out.print(sb);
    }
}