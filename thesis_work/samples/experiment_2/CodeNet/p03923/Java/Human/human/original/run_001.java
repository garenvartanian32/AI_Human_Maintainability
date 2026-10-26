import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;

    void solve() throws IOException {
        long n = nl();
        long a = nl();

        long ans = n;

        for (int i = 1; i <= 40; i++) {
            if (a * i > n) break;

            long x = (long) Math.pow(n, 1.0 / (i + 1));

            long high = (i + 1) * (x + 1);
            long low = (i + 1) * x - 1; // 誤差を考慮
            while (high - low > 1) {
                long mid = (high + low) / 2;
                long p = mid / (i + 1);
                long q = mid % (i + 1);

                long t = 1;
                for (int j = 0; j <= i; j++) {
                    if (j < q) {
                        t *= (p + 1);
                    } else {
                        t *= p;
                    }
                    if (t >= n) break;
                }

                if (t >= n) {
                    high = mid;
                } else {
                    low = mid;
                }
            }

            ans = Math.min(ans, a * i + high);
        }

        out.println(ans);
    }

    String ns() throws IOException {
        while (!tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine(), " ");
        }
        return tok.nextToken();
    }

    int ni() throws IOException {
        return Integer.parseInt(ns());
    }

    long nl() throws IOException {
        return Long.parseLong(ns());
    }

    double nd() throws IOException {
        return Double.parseDouble(ns());
    }

    String[] nsa(int n) throws IOException {
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            res[i] = ns();
        }
        return res;
    }

    int[] nia(int n) throws IOException {
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = ni();
        }
        return res;
    }

    long[] nla(int n) throws IOException {
        long[] res = new long[n];
        for (int i = 0; i < n; i++) {
            res[i] = nl();
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        tok = new StringTokenizer("");
        Main main = new Main();
        main.solve();
        out.close();
    }
}