import java.io.*;
import java.util.*;

public class Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer st = new StreamTokenizer(br);
        st.nextToken(); long x1 = (long) st.nval;
        st.nextToken(); long y1 = (long) st.nval;
        st.nextToken(); long x2 = (long) st.nval;
        st.nextToken(); long y2 = (long) st.nval;
        st.nextToken(); int n = (int) st.nval;
        long[] X = new long[n], Y = new long[n];
        for (int i = 0; i < n; i++) {
            st.nextToken(); X[i] = (long) st.nval;
            st.nextToken(); Y[i] = (long) st.nval;
        }

        double r = 10.0;
        long dx = Math.abs(x1 - x2);
        long dy = Math.abs(y1 - y2);
        double manhattan = (dx + dy) * 100.0;

        if (x1 == x2 || y1 == y2) {
            double penalty = 0.0;
            if (y1 == y2) {
                long lo = Math.min(x1, x2), hi = Math.max(x1, x2);
                for (int i = 0; i < n; i++) {
                    if (Y[i] == y1 && lo < X[i] && X[i] < hi) {
                        penalty = r * (Math.PI - 2);
                        break;
                    }
                }
            } else {
                long lo = Math.min(y1, y2), hi = Math.max(y1, y2);
                for (int i = 0; i < n; i++) {
                    if (X[i] == x1 && lo < Y[i] && Y[i] < hi) {
                        penalty = r * (Math.PI - 2);
                        break;
                    }
                }
            }
            System.out.printf("%.15f%n", manhattan + penalty);
            return;
        }

        long xlo = Math.min(x1, x2), xhi = Math.max(x1, x2);
        long ylo = Math.min(y1, y2), yhi = Math.max(y1, y2);
        int xdir = x2 > x1 ? 1 : -1;
        int ydir = y2 > y1 ? 1 : -1;

        List<long[]> candidates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (xlo < X[i] && X[i] < xhi && ylo < Y[i] && Y[i] < yhi) {
                candidates.add(new long[]{X[i] * xdir, Y[i] * ydir});
            }
        }
        candidates.sort((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]);
        });

        long[] tails = new long[candidates.size()];
        int len = 0;
        for (long[] cand : candidates) {
            long ty = cand[1];
            int lo = 0, hi = len;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (tails[mid] < ty) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = ty;
            if (lo == len) len++;
        }

        double bonus = r * (2 - Math.PI / 2);
        double answer = manhattan - len * bonus;
        System.out.printf("%.15f%n", answer);
    }
}