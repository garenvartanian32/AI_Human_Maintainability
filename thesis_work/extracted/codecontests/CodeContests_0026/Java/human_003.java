import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int[] x = new int[n], y = new int[n], r = new int[n];
            for (int i = 0; i < n; ++i) {
                x[i] = in.nextInt();
                y[i] = in.nextInt();
                r[i] = in.nextInt();
            }
            String ansX = "", ansY = "";
            for (int i = 0; i < n; ++i) {
                String a = "(1-abs((t-" + Integer.toString(i + 1) + ")))";
                String b = "(" + a + "+abs(" + a + "))";
                String fx = "(" + Integer.toString(x[i] / 2) + "*" + b + ")";
                String fy = "(" + Integer.toString(y[i] / 2) + "*" + b + ")";
                if (i == 0) {
                    ansX = fx;
                    ansY = fy;
                } else {
                    ansX = "(" + ansX + "+" + fx + ")";
                    ansY = "(" + ansY + "+" + fy + ")";
                }

            }
            out.println(ansX);
            out.println(ansY);
        }

    }

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
