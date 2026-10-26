import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Collections.*;
// import static java.util.Comparator.*;

public class Main {
    FastScanner in;
    PrintWriter out;
    ArrayList<Integer>[] graph;
    ArrayList<GraphPair>[] weightedGraph;
    long mod = 998_244_353L; // (long) 1e9 + 7 || (long) 1e9 + 9
    boolean multitests = true;

    private void solve() throws IOException {
        // solveA();
        // solveB();
        // solveC();
        // solveD();
        solveE();
        // solveF();
    }

    private void solveA() throws IOException {
        long n = in.nextLong(), ans = 0;
        for (int i = 1; i < 10; i++) {
            long x = i;
            while (x <= n) {
                ans++;
                x = x * 10 + i;
            }
        }
        out.println(ans);
    }

    private void solveB() throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>(reverseOrder());
        for (int n = in.nextInt(); n-- > 0; )
            pq.add(in.nextInt());
        int ans = 0;
        while (!pq.isEmpty()) {
            int x = pq.poll();
            while (!pq.isEmpty() && pq.peek() == x)
                pq.poll();
            if (x % 2 == 0) {
                ans++;
                pq.add(x / 2);
            }
        }
        out.println(ans);
    }

    private void solveC() throws IOException {
        String s = in.next();

        boolean[] del = new boolean[s.length()];
        for (int i = 0; i + 4 < s.length(); i++) {
            if (s.substring(i, i + 5).equals("twone"))
                del[i + 2] = true;
        }

        for (int i = 0; i + 2 < s.length(); i++) {
            if (s.substring(i, i + 3).equals("two") && !del[i + 2])
                del[i + 1] = true;
            if (s.substring(i, i + 3).equals("one") && !del[i])
                del[i + 1] = true;
        }

        int cnt = 0;
        for (boolean b : del)
            if (b)
                cnt++;
        out.println(cnt);
        for (int i = 0; i < s.length(); i++) {
            if (del[i])
                out.print(i + 1 + " ");
        }
        out.println();
    }

    int cnt = 1, testId = 0, borRoot = 0;
    int alpha = '1' - '0' + 1, borSize = (int) 4e6;
    int[][] borNext = new int[alpha][borSize];
    int[] borCnt = new int[borSize];

    void borAdd(char[] s) {
        int v = borRoot;
        for (char c : s) {
            if (borNext[c - '0'][v] == 0)
                borNext[c - '0'][v] = cnt++;
            v = borNext[c - '0'][v];
        }
        borCnt[v] = testId;
    }

    boolean borFind(char[] s) {
        int v = borRoot;
        for (char c : s) {
            if (borNext[c - '0'][v] == 0)
                return false;
            v = borNext[c - '0'][v];
        }
        return borCnt[v] == testId;
    }

    private void solveD() throws IOException {
        testId++;

        int n = in.nextInt();
        char[][] s = new char[n][];
        int[][] cnt = new int[2][2];
        for (int i = 0; i < n; i++) {
            s[i] = in.next().toCharArray();
            cnt[s[i][0] - '0'][s[i][s[i].length - 1] - '0']++;
        }

        if (cnt[0][0] > 0 && cnt[1][1] > 0 && cnt[0][1] + cnt[1][0] == 0) {
            out.println(-1);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (s[i][0] != s[i][s[i].length - 1] &&
                    cnt[s[i][s[i].length - 1] - '0'][s[i][0] - '0'] - cnt[s[i][0] - '0'][s[i][s[i].length - 1] - '0'] > 1) {
                borAdd(s[i]);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (s[i][0] != s[i][s[i].length - 1] &&
                    cnt[s[i][0] - '0'][s[i][s[i].length - 1] - '0'] - cnt[s[i][s[i].length - 1] - '0'][s[i][0] - '0'] > 1) {
                if (!borFind(new StringBuilder(new String(s[i])).reverse().toString().toCharArray())) {
                    cnt[s[i][0] - '0'][s[i][s[i].length - 1] - '0']--;
                    cnt[s[i][s[i].length - 1] - '0'][s[i][0] - '0']++;
                    ans.add(i);
                }
            }
        }

        if (abs(cnt[0][1] - cnt[1][0]) > 1) {
            out.println(-1);
            return;
        }

        out.println(ans.size());
        for (int i : ans)
            out.print(i + 1 + " ");
        out.println();
    }

    private void solveE() throws IOException {
        int n = in.nextInt(), m = in.nextInt(), a = in.nextInt() - 1, b = in.nextInt() - 1;
        graph = in.nextGraph(n, m, false);

        boolean[][] used = new boolean[2][n];
        used[0][a] = used[0][b] = used[1][a] = used[1][b] = true;

        int[] q = new int[n];
        int ql, qr;
        for (int i = 0; i < 2; i++) {
            ql = qr = 0;
            q[qr++] = i == 0 ? a : b;
            while (ql < qr) {
                int v = q[ql++];
                for (int u : graph[v]) {
                    if (!used[i][u]) {
                        used[i][u] = true;
                        q[qr++] = u;
                    }
                }
            }
        }

        int[][] cnt = new int[2][2];
        for (int i = 0; i < n; i++)
            cnt[used[0][i] ? 1 : 0][used[1][i] ? 1 : 0]++;

        out.println((long) cnt[0][1] * cnt[1][0]);
    }

    private void solveF() throws IOException {

    }

    void shuffleInt(int[] a) {
        Random random = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int swap = a[j];
            a[j] = a[i];
            a[i] = swap;
        }
    }

    void shuffleLong(long[] a) {
        Random random = new Random();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            long swap = a[j];
            a[j] = a[i];
            a[i] = swap;
        }
    }

    void reverseInt(int[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            int swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
    }

    void reverseLong(long[] a) {
        for (int i = 0, j = a.length - 1; i < j; i++, j--) {
            long swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
    }

    int maxInt(int[] a) {
        int max = a[0];
        for (int i = 1; i < a.length; i++)
            if (max < a[i])
                max = a[i];
        return max;
    }

    long maxLong(long[] a) {
        long max = a[0];
        for (int i = 1; i < a.length; i++)
            if (max < a[i])
                max = a[i];
        return max;
    }

    int minInt(int[] a) {
        int min = a[0];
        for (int i = 1; i < a.length; i++)
            if (min > a[i])
                min = a[i];
        return min;
    }

    long minLong(long[] a) {
        long min = a[0];
        for (int i = 1; i < a.length; i++)
            if (min > a[i])
                min = a[i];
        return min;
    }

    long sumInt(int[] a) {
        long s = 0;
        for (int i = 0; i < a.length; i++)
            s += a[i];
        return s;
    }

    long sumLong(long[] a) {
        long s = 0;
        for (int i = 0; i < a.length; i++)
            s += a[i];
        return s;
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    long binpowmod(long a, long n) {
        long res = 1;
        a %= mod;
        n %= mod - 1;
        while (n > 0) {
            if (n % 2 == 1)
                res = (res * a) % mod;
            a = (a * a) % mod;
            n /= 2;
        }
        return res;
    }

    class GraphPair implements Comparable<GraphPair> {
        int v;
        long w;

        GraphPair(int v, long w) {
            this.v = v;
            this.w = w;
        }

        public int compareTo(GraphPair o) {
            return w != o.w ? Long.compare(w, o.w) : Integer.compare(v, o.v);
        }
    }

    ArrayList<Integer>[] createGraph(int n) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        return graph;
    }

    ArrayList<GraphPair>[] createWeightedGraph(int n) {
        ArrayList<GraphPair>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        return graph;
    }

    class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s), 32768);
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        boolean hasNext() throws IOException {
            return br.ready() || (st != null && st.hasMoreTokens());
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        int[] nextIntArray(int n) throws IOException {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = in.nextInt();
            return a;
        }

        int[][] nextIntTable(int n, int m) throws IOException {
            int[][] a = new int[n][m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    a[i][j] = in.nextInt();
            return a;
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        long[] nextLongArray(int n) throws IOException {
            long[] a = new long[n];
            for (int i = 0; i < n; i++)
                a[i] = in.nextLong();
            return a;
        }

        long[][] nextLongTable(int n, int m) throws IOException {
            long[][] a = new long[n][m];
            for (int i = 0; i < n; i++)
                for (int j = 0; j < m; j++)
                    a[i][j] = in.nextLong();
            return a;
        }

        ArrayList<Integer>[] nextGraph(int n, int m, boolean directed) throws IOException {
            ArrayList<Integer>[] graph = createGraph(n);
            for (int i = 0; i < m; i++) {
                int v = in.nextInt() - 1, u = in.nextInt() - 1;
                graph[v].add(u);
                if (!directed)
                    graph[u].add(v);
            }
            return graph;
        }

        ArrayList<GraphPair>[] nextWeightedGraph(int n, int m, boolean directed) throws IOException {
            ArrayList<GraphPair>[] graph = createWeightedGraph(n);
            for (int i = 0; i < m; i++) {
                int v = in.nextInt() - 1, u = in.nextInt() - 1;
                long w = in.nextLong();
                graph[v].add(new GraphPair(u, w));
                if (!directed)
                    graph[u].add(new GraphPair(v, w));
            }
            return graph;
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }

        boolean hasNextLine() throws IOException {
            return br.ready();
        }
    }

    private void run() throws IOException {
        in = new FastScanner(System.in); // new FastScanner(new FileInputStream(".in"));
        out = new PrintWriter(System.out); // new PrintWriter(new FileOutputStream(".out"));

        for (int t = multitests ? in.nextInt() : 1; t-- > 0; )
            solve();

        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}