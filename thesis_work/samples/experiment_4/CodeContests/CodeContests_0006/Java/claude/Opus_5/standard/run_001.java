import java.io.*;
import java.util.*;

public class Sol {
    static int[] cnt;
    static long[] sumx;
    static int m;

    static void update(int pos, int c, long s) {
        while (pos <= m) {
            cnt[pos] += c;
            sumx[pos] += s;
            pos += pos & (-pos);
        }
    }

    static long[] query(int pos) {
        long c = 0, s = 0;
        while (pos > 0) {
            c += cnt[pos];
            s += sumx[pos];
            pos -= pos & (-pos);
        }
        return new long[]{c, s};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken();
        int n = (int) st.nval;
        long[] xs = new long[n];
        long[] vs = new long[n];
        for (int i = 0; i < n; i++) { st.nextToken(); xs[i] = (long) st.nval; }
        for (int i = 0; i < n; i++) { st.nextToken(); vs[i] = (long) st.nval; }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Long.compare(xs[a], xs[b]));

        long[] sortedV = vs.clone();
        Arrays.sort(sortedV);
        int uniq = 0;
        long[] compact = new long[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || sortedV[i] != sortedV[i - 1]) {
                compact[uniq++] = sortedV[i];
            }
        }
        m = uniq;
        cnt = new int[m + 1];
        sumx = new long[m + 1];

        long total = 0;
        for (int idx : order) {
            long v = vs[idx];
            long x = xs[idx];
            int r = lowerBound(compact, uniq, v) + 1; // 1-indexed rank
            long[] cs = query(r);
            total += cs[0] * x - cs[1];
            update(r, 1, x);
        }
        System.out.println(total);
    }

    static int lowerBound(long[] arr, int len, long key) {
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < key) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}