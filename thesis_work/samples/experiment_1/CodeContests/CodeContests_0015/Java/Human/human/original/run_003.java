import java.util.*;
import java.io.*;

public class A2oj2 {

    static FastReader sc;
    static PrintWriter out;
    static int mod = 1000000007;

    public static void main(String[] args) throws IOException {
        if (System.getProperty("ONLINE_JUDGE") == null) {
            File f1 = new File("input.txt");
            File f2 = new File("output.txt");
            Reader r = new FileReader(f1);
            sc = new FastReader(r);
            out = new PrintWriter(f2);
            double prev = System.currentTimeMillis();
            solve();
            out.println("\n\nExecuted in : " + ((System.currentTimeMillis() - prev) / 1e3) + " sec");
        } else {
            sc = new FastReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            solve();
        }
        out.flush();
        out.close();
    }

    static void solve() {
        int t = sc.nextInt();
        StringBuilder ans = new StringBuilder("");
        int[][] dp = new int[(int) 2e5 + 1][10];
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < (int) 2e5 + 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (j != 9) {
                    dp[i][j] = dp[i - 1][j + 1];
                } else {
                    dp[i][j] = (dp[i - 1][1] + dp[i - 1][0]) % mod;
                }
            }
        }
        while (t-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt();
            long res = 0;
            while (n != 0) {
                res = (res + dp[m][n % 10]) % mod;
                n /= 10;
            }
            ans.append(res + "\n");
        }
        out.println(ans);
    }

    static boolean isPrime(long n) {
        if (n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    static long modInverse(long a) {
        long g = gcd(a, mod);
        if (g != 1)
            return -1;
        else {
            return modPower(a, mod - 2L);
        }
    }

    static long modPower(long x, long y) {
        long res = 1;
        x = x % mod;
        if (x == 0)
            return 0;
        while (y > 0) {
            if ((y & 1) != 0)
                res = (res * x) % mod;
            y = y >> 1;
            x = (x * x) % mod;
        }
        return res;
    }

    static long gcd(long a, long b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

    static boolean isPerfectSquare(long x) {
        long sqrt = (long) Math.sqrt(x);
        return (sqrt * sqrt) == x;
    }

    public static int digitsCount(long x) {
        return (int) Math.floor(Math.log10(x)) + 1;
    }

    public static boolean isPowerTwo(long n) {
        return (n & n - 1) == 0;
    }

    static void sieve(boolean[] prime, int n) { // Sieve Of Eratosthenes
        for (int i = 1; i <= n; i++) {
            prime[i] = true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = 2; i * j <= n; j++) {
                    prime[i * j] = false;
                }
            }
        }
    }

    static long nCr(int n, int r) { // Combinations
        if (n < r)
            return 0;
        if (r > n - r) { // because nCr(n, r) == nCr(n, n - r)
            r = n - r;
        }
        long ans = 1L;
        for (int i = 0; i < r; i++) {
            ans *= (n - i);
            ans /= (i + 1);
        }
        return ans;
    }

    static long catalan(int n) { // n-th Catalan Number
        long c = nCr(2 * n, n);
        return c / (n + 1);
    }

    static class Pair implements Comparable<Pair> { // Pair Class
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Pair)) {
                return false;
            }

            Pair pair = (Pair) o;

            if (x != pair.x) {
                return false;
            }
            if (y != pair.y) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            long result = x;
            result = 31 * result + y;
            return (int) result;
        }

        @Override
        public int compareTo(Pair o) {
            return (int) (this.x - o.x);
        }
    }

    static class Trip { // Triplet Class
        long x;
        long y;
        long z;

        Trip(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Trip)) {
                return false;
            }

            Trip trip = (Trip) o;

            if (x != trip.x) {
                return false;
            }
            if (y != trip.y) {
                return false;
            }
            if (z != trip.z) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            long result = 62 * x + 31 * y + z;
            return (int) result;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader(Reader r) {
            br = new BufferedReader(r);
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        int[] readArrayI(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            return arr;
        }

        long[] readArrayL(int n) {
            long[] arr = new long[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextLong();
            }
            return arr;
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    /*
     * ASCII Range--->(A-Z)--->[65,90]<<::>>(a-z)--->[97,122]
     */
    /******************************************************************************************************************/
    static void printArray(int[] arr) {
        out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1)
                out.print(arr[i] + ",");
            else
                out.print(arr[i]);
        }
        out.print("]");
        out.println();
    }

    static void printArray(long[] arr) {
        out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1)
                out.print(arr[i] + ",");
            else
                out.print(arr[i]);
        }
        out.print("]");
        out.println();
    }

    static void printArray(double[] arr) {
        out.print("[");
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1)
                out.print(arr[i] + ",");
            else
                out.print(arr[i]);
        }
        out.print("]");
        out.println();
    }
    /**********************************************************************************************************************/
}