import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            InputReader in;
            PrintWriter out;
            boolean useOutput = false;
            // if (System.getProperty("ONLINE_JUDGE") == null) useOutput = true;
            if (useOutput) {
                FileInputStream fin = new FileInputStream(new File("src/testdata.in"));
                in = new InputReader(fin);
                FileOutputStream fout = new FileOutputStream(new File("src/testData.out"));
                out = new PrintWriter(fout);
            } else {
                InputStream inputStream = System.in;
                in = new InputReader(inputStream);
                OutputStream outputStream = System.out;
                out = new PrintWriter(outputStream);
            }
            Solver solver = new Solver();
            solver.solve(in, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Solver {

        int allowed[][] = {{0, 1, 1}, {1, 0, 1}, {1, 1, 0}};

        public void solve(InputReader cin, PrintWriter cout) {
            try {
                int n = cin.nextInt();
                int dp[][] = new int[n][3];
                for (int i = 0; i < n; ++i) {
                    int a[] = new int[3];
                    for (int j = 0; j < 3; ++j) a[j] = cin.nextInt();
                    if (i == 0) {
                        for (int j = 0; j < 3; ++j) dp[i][j] = a[j];
                    } else {
                        dp[i][0] = dp[i][1] = dp[i][2] = Integer.MIN_VALUE;
                        for (int st = 0; st < 3; ++st) {
                            for (int nx = 0; nx < 3; ++nx) {
                                if(allowed[st][nx] == 1) {
                                    dp[i][nx] = Math.max(dp[i - 1][st] + a[nx], dp[i][nx]);
                                }
                            }
                        }
                    }
                }
                int res = Integer.MIN_VALUE;
                for (int i = 0; i < 3; ++i) res = Math.max(dp[n - 1][i], res);
                cout.println(res);
            } catch (RuntimeException e) {
                e.printStackTrace();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

        }
    }

    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

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

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

    }

}