import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author Don Li
 */
public class Cheese {

    static final int INF = (int) 1e9;

    Map<Integer, String> map = new HashMap<>();

    {
        map.put(1, "x^y^z");
        map.put(2, "x^z^y");
        map.put(3, "(x^y)^z");
        map.put(4, "(x^z)^y");
        map.put(5, "y^x^z");
        map.put(6, "y^z^x");
        map.put(7, "(y^x)^z");
        map.put(8, "(y^z)^x");
        map.put(9, "z^x^y");
        map.put(10, "z^y^x");
        map.put(11, "(z^x)^y");
        map.put(12, "(z^y)^x");
    }

    void solve() {
        double x = in.nextDouble(), y = in.nextDouble(), z = in.nextDouble();
        int id = calc(x, y, z);
        out.println(map.get(id));
    }

    int calc(double x, double y, double z) {
        double[] A = new double[13];

        if (x <= 1 && y <= 1 && z <= 1) {
            if (x == 1) return 1;
            if (y == 1) return 5;
            if (z == 1) return 9;

            A[1] = calc1(1 / x, y, z);
            A[2] = calc1(1 / x, z, y);
            A[3] = calc2(1 / x, y, z);
            A[4] = calc2(1 / x, z, y);
            A[5] = calc1(1 / y, x, z);
            A[6] = calc1(1 / y, z, x);
            A[7] = calc2(1 / y, x, z);
            A[8] = calc2(1 / y, z, x);
            A[9] = calc1(1 / z, x, y);
            A[10] = calc1(1 / z, y, x);
            A[11] = calc2(1 / z, x, y);
            A[12] = calc2(1 / z, y, x);

            double min = INF;
            int idx = -1;
            for (int i = 1; i <= 12; i++) {
                if (min > A[i]) {
                    min = A[i];
                    idx = i;
                }
            }
            return idx;
        }

        Arrays.fill(A, -INF);
        if (x > 1) {
            A[1] = calc1(x, y, z);
            A[2] = calc1(x, z, y);
            A[3] = calc2(x, y, z);
            A[4] = calc2(x, z, y);
        }
        if (y > 1) {
            A[5] = calc1(y, x, z);
            A[6] = calc1(y, z, x);
            A[7] = calc2(y, x, z);
            A[8] = calc2(y, z, x);
        }
        if (z > 1) {
            A[9] = calc1(z, x, y);
            A[10] = calc1(z, y, x);
            A[11] = calc2(z, x, y);
            A[12] = calc2(z, y, x);
        }

        double max = -INF;
        int idx = -1;
        for (int i = 1; i <= 12; i++) {
            if (max < A[i]) {
                max = A[i];
                idx = i;
            }
        }
        return idx;
    }

    private double calc1(double a, double b, double c) {
        return c * Math.log(b) + Math.log(Math.log(a));
    }

    private double calc2(double a, double b, double c) {
        return Math.log(c) + Math.log(b) + Math.log(Math.log(a));
    }

    public static void main(String[] args) {
        in = new FastScanner(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(System.out);
        new Cheese().solve();
        out.close();
    }

    static FastScanner in;
    static PrintWriter out;

    static class FastScanner {
        BufferedReader in;
        StringTokenizer st;

        public FastScanner(BufferedReader in) {
            this.in = in;
        }

        public String nextToken() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(in.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(nextToken());
        }

        public long nextLong() {
            return Long.parseLong(nextToken());
        }

        public double nextDouble() {
            return Double.parseDouble(nextToken());
        }
    }
}
