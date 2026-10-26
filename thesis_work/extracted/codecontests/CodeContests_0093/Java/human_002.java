import java.io.BufferedReader;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Arrays.fill;
import static java.util.Arrays.sort;
import static java.util.Collections.reverse;

public class Main {
    FastScanner in;
    PrintWriter out;

    ArrayList<Integer>[] graph, graphRev;
    ArrayList<Integer> order;
    boolean[] used;
    int[] comp, size;
    int compCnt;

    class Pair implements Comparable<Pair> {
        int v, u;

        Pair(int v, int u) {
            this.v = v;
            this.u = u;
        }

        public int compareTo(Pair o) {
            return v != o.v ? v - o.v : u - o.u;
        }
    }

    private void solve() throws IOException {
        int n = in.nextInt(), m = in.nextInt(), h = in.nextInt();
        graph = new ArrayList[n];
        graphRev = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            graphRev[i] = new ArrayList<>();
        }

        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
        }

        for (int i = 0, v, u; i < m; i++) {
            v = in.nextInt() - 1;
            u = in.nextInt() - 1;
            if ((x[v] + 1) % h == x[u]) {
                graph[v].add(u);
                graphRev[u].add(v);
            }
            if ((x[u] + 1) % h == x[v]) {
                graph[u].add(v);
                graphRev[v].add(u);
            }
        }

        order = new ArrayList<>();
        used = new boolean[n];
        for (int v = 0; v < n; v++)
            if (!used[v])
                dfs(v);

        reverse(order);
        comp = new int[n];
        fill(comp, -1);
        compCnt = 0;
        size = new int[n];
        for (int v : order)
            if (comp[v] == -1) {
                dfsRev(v);
                compCnt++;
            }

        fill(used, true);
        for (int v = 0; v < n; v++)
            for (int u : graph[v])
                if (comp[v] != comp[u])
                    used[comp[v]] = false;

        int ans = -1;
        for (int v = 0; v < compCnt; v++)
            if ((ans == -1 || size[ans] > size[v]) && used[v])
                ans = v;

        out.println(size[ans]);
        for (int i = 0; i < n; i++)
            if (comp[i] == ans)
                out.print(i + 1 + " ");
        out.println();
    }

    private void dfs(int v) {
        used[v] = true;
        for (int u : graph[v])
            if (!used[u])
                dfs(u);
        order.add(v);
    }

    private void dfsRev(int v) {
        size[compCnt]++;
        comp[v] = compCnt;
        for (int u : graphRev[v])
            if (comp[u] == -1)
                dfsRev(u);
    }

    class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
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

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next().replace(',', '.'));
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

        solve();

        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().run();
    }
}