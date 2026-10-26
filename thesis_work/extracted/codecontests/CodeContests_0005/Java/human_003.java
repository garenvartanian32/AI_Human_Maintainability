import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public void solve(InputReader in, PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        int[] sorted = a.clone();
        Arrays.sort(sorted);
        for (int i = 0, j = n-1; i<j; i++, j--) {
            int t = sorted[i];
            sorted[i] = sorted[j];
            sorted[j] = t;
        }
        int m = in.nextInt();
        int[][] res = new int[n+1][];
        for (int t = 0; t < m; t++) {
            int k = in.nextInt();
            int pos = in.nextInt()-1;
            if (res[k] != null) {
                out.println(res[k][pos]);
                continue;
            }
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < k; i++) {
                int c = tm.getOrDefault(sorted[i], 0) + 1;
                tm.put(sorted[i], c);
                min = Math.min(min, sorted[i]);
            }
            int[] r1 = new int[k];
            int p = 0;
            int c = tm.get(min);
            for (int i = 0; i < n; i++) {
                if (a[i] > min) {
                    r1[p++] = a[i];
                } else if (a[i] == min) {
                    if (c > 0) {
                        r1[p++] = min;
                        c--;
                    }
                }
            }
            res[k] = r1;
            out.println(r1[pos]);
        }

    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Main obj = new Main();
        obj.solve(in, out);
        out.close();
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public float nextFloat() {
            return Float.parseFloat(next());
        }

    }
}
