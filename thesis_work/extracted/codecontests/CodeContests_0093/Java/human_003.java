import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B1 {
    static BufferedReader in;
    static StringTokenizer st;
    static PrintWriter out;

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        nextLine();
        int n = nextInt();
        long m = nextInt();
        long h = nextInt();
        nextLine();
        Graph g = new Graph(n);
        long time[] = new long[n];
        for (int i = 0; i < n; i++) {
            time[i] = nextInt();
        }
        for (int i = 0; i < m; i++) {
            nextLine();
            long x = nextInt() - 1;
            long y = nextInt() - 1;
            if ((time[(int) x] + 1) % h == time[(int) y]) {
                g.edge((int) x, (int) y);
            }
            if ((time[(int) y] + 1) % h == time[(int) x]) {
                g.edge((int) y, (int) x);
            }
        }
        Graph rev = reverse(g);
        long cur = 0;
        dfs = new long[n];
        marked = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!marked[i]) {
                cur = dfs(rev, i, (int) cur);
            }
        }
        for (int i = 0; i < n / 2; i++) {
            long swap = dfs[i];
            dfs[i] = dfs[n - 1 - i];
            dfs[n - 1 - i] = swap;
        }
        marked1 = new long[n];
        long count = 1;
        for (int i = 0; i < n; i++) {
            if (marked1[(int) dfs[i]] == 0) {
                dfs1((int) count, g, (int) dfs[i]);
                count++;
            }
        }
        Graph derevo = new Graph((int) count);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < g.adj[i].size(); j++) {
                if (marked1[i] - 1 != marked1[(int) g.adj[i].get(j)] - 1) {
                    derevo.edge((int) marked1[i] - 1, (int) marked1[(int) g.adj[i].get(j)] - 1);
                }
            }
        }
        count--;
        mas = new long[(int) count];
        for (long i = 0; i < n; i++) {
            mas[(int) marked1[(int) i] - 1]++;
        }
        dp = new long[(int) count];
        int min = 1000_0000_0;
        int koor = 0;
        for (int i = 0; i < count; i++) {
            if (derevo.adj[i].size() == 0) {
                if (mas[i] < min) {
                    min = (int) mas[i];
                    koor = i;
                }
            }
        }
        out.println(min);
        for (int i = 0; i < n; i++) {
            if (marked1[i] == koor+1) {
                out.println(i + 1 + " ");
            }
        }
        if (min == 0) {
            long wr[] = new long[2];
            wr[3] = 0;
        }
        out.close();
    }

    private static void dfs5(int tek, Graph g) {
        marked = new boolean[g.lenght];
        Stack s = new Stack();
        s.paste(tek);
        while (!s.empty()) {
            tek = s.take();
            out.print((tek + 1) + " ");
            marked[tek] = true;
            for (int j = 0; j < g.adj[tek].size(); j++) {
                if (!marked[(int) g.adj[tek].get(j)]) {
                    s.paste((int) g.adj[tek].get(j));
                    marked[(int) g.adj[tek].get(j)] = true;
                }
            }
        }
    }

    static boolean marked[];
    static long marked1[];
    static long dp[];
    static long mas[];
    static long[] dfs;
    static int mar[];

    public static void dfs2(int tek, Graph g) {
        dp[tek] = mas[tek];
        for (int i = 0; i < g.adj[tek].size(); i++) {
            if (dp[(int) g.adj[tek].get(i)] == 0) {
                dfs2((int) g.adj[tek].get(i), g);
            }
        }
        for (int i = 0; i < g.lenght; i++) {
            mar[i] = 0;
        }
        for (int i = 0; i < g.adj[tek].size(); i++) {
            if (mar[(int) g.adj[tek].get(i)] == 0) {
                dp[tek] += dp[(int) g.adj[tek].get(i)];
                mar[(int) g.adj[tek].get(i)]++;
            }
        }
    }

    public static void dfs1(int count, Graph g, int tek) {
        Stack s = new Stack();
        s.paste(tek);
        while (!s.empty()) {
            tek = s.take();
            marked1[tek] = count;
            for (int j = 0; j < g.adj[tek].size(); j++) {
                if (marked1[(int) g.adj[tek].get(j)] == 0) {
                    s.paste((int) g.adj[tek].get(j));
                    marked1[(int) g.adj[tek].get(j)] = count;
                }
            }
        }
    }

    public static long dfs(Graph g, int tek, long cur) {
        marked[tek] = true;
        for (int i = 0; i < g.adj[tek].size(); i++) {
            if (!marked[(int) g.adj[tek].get(i)]) {
                cur = dfs(g, (int) g.adj[tek].get(i), cur);
            }
        }
        dfs[(int) cur] = tek;
        return cur + 1;
    }

    public static class Stack {
        int tek;
        int mas[];

        public Stack() {
            tek = 0;
            mas = new int[2];
        }

        public void paste(int x) {
            if (tek == mas.length) {
                int th[] = new int[tek * 2];
                for (int i = 0; i < tek; i++) {
                    th[i] = mas[i];
                }
                mas = th;
            }
            mas[tek++] = x;
        }

        public int take() {
            return mas[--tek];
        }

        public boolean empty() {
            if (tek == 0) {
                return true;
            }
            return false;
        }
    }

    public static Graph reverse(Graph g) {
        Graph ans = new Graph(g.lenght);
        for (int i = 0; i < g.lenght; i++) {
            for (int j = 0; j < g.adj[i].size(); j++) {
                ans.edge((int) g.adj[i].get(j), i);
            }
        }
        return ans;
    }

    public static void nextLine() throws IOException {
        st = new StringTokenizer(in.readLine());
    }

    public static int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    public static class Graph {
        int lenght;
        ArrayList adj[];

        public Graph(int n) {
            lenght = n;
            adj = new ArrayList[lenght];
            for (int i = 0; i < lenght; i++) {
                adj[i] = new ArrayList();
            }
        }

        public void edge(int x, int y) {
            adj[(int) x].add(y);
        }
    }
}