import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static String S;

    public static void main(String[] args) {
        FastScanner sc = new FastScanner(System.in);
        S = sc.next();

        System.out.println(solve());
    }

    static int MOD = 1_000_000_007;

    static long solve() {
        int [] A = new int[S.length()];
        int [] B = new int[S.length()];
        int [] C = new int[S.length()];
        int [] Q = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            if( i != 0 ) {
                A[i] = A[i-1];
                B[i] = B[i-1];
                C[i] = C[i-1];
                Q[i] = Q[i-1];
            }

            char c = S.charAt(i);
            if( c == 'A' ) {
                A[i]++;
            } else if( c == 'B' ) {
                B[i]++;
            } else if( c == 'C' ) {
                C[i]++;
            } else {
                Q[i]++;
            }
        }

        long ans = 0;

        for (int j = 1; j < S.length()-1; j++) {
            if( S.charAt(j) == 'B' || S.charAt(j) == '?' ) {
                int a = A[j-1];
                int q1 = Q[j-1];

                long prev = a * pow3(q1) % MOD;
                prev += pow3(q1-1) * q1 % MOD;
                prev %= MOD;

                int c = C[S.length()-1] - C[j];
                int q2 = Q[S.length()-1] - Q[j];

                long post = c * pow3(q2) % MOD;
                post += pow3(q2 -1) * q2 % MOD;
                post %= MOD;

                ans += prev * post % MOD;
                ans %= MOD;
            }
        }

        return ans;
    }

    static Map<Integer, Integer> POW3 = new HashMap<>();

    static long pow3(int n) {
        if( POW3.containsKey(n) ) {
            return POW3.get(n);
        }

        int r = pow(3, n);
        POW3.put(n, r);
        return r;
    }

    static int pow(int base, int exp) {
        if( exp == 0 ) return 1;

        int ans = 1;
        base %= MOD;
        while( exp > 0 ) {
            if( (exp & 1) == 1 ) {
                ans = mul(ans, base);
            }

            base = mul(base, base);
            exp = exp >> 1;
        }
        return ans;
    }

    static int mul(int a, int b) {
        return (int)((long)a * b % MOD);
    }

    @SuppressWarnings("unused")
    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
            tokenizer = null;
        }

        String next() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        String nextLine() {
            if (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    return reader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken("\n");
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] nextIntArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long[] nextLongArray(int n) {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = nextLong();
            return a;
        }
    }
}
