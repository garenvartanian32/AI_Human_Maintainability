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
        TaskE solver = new TaskE();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskE {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int N = in.nextInt();
            int M = in.nextInt();
            int K = in.nextInt();
            int[] line = new int[N + 1];
            for (int i = 0; i < M; i++) {
                line[in.nextInt()]++;
            }
            long[] costs = new long[K + 1];
            for (int i = 1; i <= K; i++) {
                costs[i] = in.nextInt();
            }
            int curSum = 0;
            int max = 1;
            for (int i = N; i >= 0; i--) {
                if (line[i] == 0) {
                    curSum = 0;
                } else {
                    curSum += line[i];
                    line[i] = curSum;
                    max = Math.max(line[i] + 1, max);
                }
            }
            if (line[0] > 0 || max > K) {
                out.println(-1);
            } else {
                long res = Long.MAX_VALUE;
                for (int k = K; k >= max; k--) {
                    int cnt = 0;
                    int curIndex = N;
                    while (curIndex > 0) {
                        cnt++;
                        curIndex = line[Math.max(0, curIndex - k)] + curIndex - k;
                    }
                    res = Math.min(cnt * costs[k], res);
                }
                out.println(res);
            }


        }

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

    }
}

