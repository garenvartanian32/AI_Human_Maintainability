import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); int n = (int) st.nval;
        st.nextToken(); long d = (long) st.nval;
        st.nextToken(); long a = (long) st.nval;
        long[][] monsters = new long[n][2];
        for (int i = 0; i < n; i++) {
            st.nextToken(); monsters[i][0] = (long) st.nval;
            st.nextToken(); monsters[i][1] = (long) st.nval;
        }
        Arrays.sort(monsters, (p, q) -> Long.compare(p[0], q[0]));
        long[] xs = new long[n];
        long[] hs = new long[n];
        for (int i = 0; i < n; i++) { xs[i] = monsters[i][0]; hs[i] = monsters[i][1]; }

        long[] diff = new long[n + 1];
        long current = 0;
        long totalBombs = 0;
        for (int i = 0; i < n; i++) {
            current += diff[i];
            long remaining = hs[i] - current;
            if (remaining > 0) {
                long bombs = (remaining + a - 1) / a;
                totalBombs += bombs;
                current += bombs * a;
                long limit = xs[i] + 2 * d;
                int r = upperBound(xs, limit) - 1;
                if (r + 1 <= n - 1 + 1) {
                    if (r + 1 < diff.length) diff[r+1] -= bombs * a;
                }
            }
        }
        System.out.println(totalBombs);
    }

    static int upperBound(long[] arr, long key) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= key) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}