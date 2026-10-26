import java.util.*;
import java.io.*;

public class Main {
    static long[] segSum, lazy;
    static int[] segLen;
    static boolean[] hasLazy;
    static int size;

    static void applyVal(int node, long val) {
        segSum[node] = val * segLen[node];
        lazy[node] = val;
        hasLazy[node] = true;
    }
    static void pushDown(int node) {
        if (hasLazy[node]) {
            applyVal(2*node, lazy[node]);
            applyVal(2*node+1, lazy[node]);
            hasLazy[node] = false;
        }
    }
    static void update(int node, int nl, int nr, int l, int r, long val) {
        if (r <= nl || nr <= l) return;
        if (l <= nl && nr <= r) { applyVal(node, val); return; }
        pushDown(node);
        int mid = (nl+nr)/2;
        update(2*node, nl, mid, l, r, val);
        update(2*node+1, mid, nr, l, r, val);
        segSum[node] = segSum[2*node] + segSum[2*node+1];
    }
    static long query(int node, int nl, int nr, int l, int r) {
        if (r <= nl || nr <= l) return 0;
        if (l <= nl && nr <= r) return segSum[node];
        pushDown(node);
        int mid = (nl+nr)/2;
        return query(2*node, nl, mid, l, r) + query(2*node+1, mid, nr, l, r);
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        st.nextToken(); int n=(int)st.nval;
        st.nextToken(); int q=(int)st.nval;
        size = 1;
        while (size < n) size *= 2;
        segSum = new long[2*size];
        lazy = new long[2*size];
        hasLazy = new boolean[2*size];
        segLen = new int[2*size];
        for (int i = 0; i < size; i++) segLen[size+i] = 1;
        for (int i = size-1; i >= 1; i--) segLen[i] = segLen[2*i]+segLen[2*i+1];
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st.nextToken(); int t=(int)st.nval;
            if (t == 0) {
                st.nextToken(); int s=(int)st.nval;
                st.nextToken(); int e=(int)st.nval;
                st.nextToken(); long x=(long)st.nval;
                update(1, 0, size, s, e+1, x);
            } else {
                st.nextToken(); int s=(int)st.nval;
                st.nextToken(); int e=(int)st.nval;
                out.append(query(1, 0, size, s, e+1)).append("\n");
            }
        }
        System.out.print(out);
    }
}