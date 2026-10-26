
import java.util.Arrays;

class DisjointSet {
  public int[] upper;

  public DisjointSet(int n) {
    upper = new int[n];
    Arrays.fill(upper, -1);
  }

  public DisjointSet(DisjointSet ds) {
    this.upper = Arrays.copyOf(ds.upper, ds.upper.length);
  }

  public int root(int x) {
    return upper[x] < 0 ? x : (upper[x] = root(upper[x]));
  }

  public boolean equiv(int x, int y) {
    return root(x) == root(y);
  }

  public boolean union(int x, int y) {
    x = root(x);
    y = root(y);
    if (x != y) {
      if (upper[y] < upper[x]) {
        int d = x;
        x = y;
        y = d;
      }
      upper[x] += upper[y];
      upper[y] = x;
    }
    return x == y;
  }

  public int count() {
    int ct = 0;
    for (int u : upper) {
      if (u < 0)
        ct++;
    }
    return ct;
  }
}


class SegmentTreeRMQPos {
  public int M, H, N;
  public long[] st;
  public int[] pos;

  public SegmentTreeRMQPos(long[] a) {
    N = a.length;
    M = Integer.highestOneBit(Math.max(N - 1, 1)) << 2;
    H = M >>> 1;
    st = new long[M];
    pos = new int[M];
    for (int i = 0; i < N; i++) {
      st[H + i] = a[i];
      pos[H + i] = i;
    }
    Arrays.fill(st, H + N, M, Long.MAX_VALUE);
    for (int i = H - 1; i >= 1; i--)
      propagate(i);
  }

  public void update(int pos, long x) {
    st[H + pos] = x;
    for (int i = (H + pos) >>> 1; i >= 1; i >>>= 1)
      propagate(i);
  }

  private void propagate(int i) {
    if (st[2 * i] <= st[2 * i + 1]) {
      st[i] = st[2 * i];
      pos[i] = pos[2 * i];
    } else {
      st[i] = st[2 * i + 1];
      pos[i] = pos[2 * i + 1];
    }
  }

  public int minpos;
  public long minval;

  public long min(int l, int r) {
    minpos = -1;
    minval = Long.MAX_VALUE;
    min(l, r, 0, H, 1);
    return minval;
  }

  private void min(int l, int r, int cl, int cr, int cur) {
    if (l <= cl && cr <= r) {
      if (st[cur] < minval) {
        minval = st[cur];
        minpos = pos[cur];
      }
    } else {
      int mid = cl + cr >>> 1;
      if (cl < r && l < mid)
        min(l, r, cl, mid, 2 * cur);
      if (mid < r && l < cr)
        min(l, r, mid, cr, 2 * cur + 1);
    }
  }
}


public class Main {
  private static void solve() {
    int n = ni();
    long d = ni();
    long[] a = nal(n);

    long[] left = new long[n];
    long[] right = new long[n];
    for (int i = 0; i < n; i++) {
      left[i] = -i * d + a[i];
      right[i] = i * d + a[i];
    }

    long[][] table = new long[n][3];
    for (int i = 0; i < n; i++) {
      table[i][0] = i;
      table[i][1] = a[i];
    }
    Arrays.sort(table, (o1, o2) -> Long.compare(o2[1], o1[1]));
    int[] idx = Arrays.stream(table).mapToInt(v -> (int) v[0]).toArray();

    SegmentTreeRMQPos stL = new SegmentTreeRMQPos(left);
    SegmentTreeRMQPos stR = new SegmentTreeRMQPos(right);

    int[] from = new int[n * 3];
    int[] to = new int[n * 3];
    long[] w = new long[n * 3];
    int ptr = 0;
    for (int i : idx) {
      stL.min(0, i);
      stR.min(i + 1, n);

      if (stL.minpos >= 0) {
        from[ptr] = i;
        to[ptr] = stL.minpos;
        w[ptr] = i * d + a[i] + stL.minval;
        ptr++;
      }
      if (stR.minpos >= 0) {
        from[ptr] = i;
        to[ptr] = stR.minpos;
        w[ptr] = -i * d + a[i] + stR.minval;
        ptr++;
      }
      stL.update(i, Long.MAX_VALUE);
      stR.update(i, Long.MAX_VALUE);
    }
    long ret = kruskal(from, to, w, ptr, n);
    System.out.println(ret);
  }

  public static long kruskal(int[] one, int[] other, long[] w, int m, int n) {
    DisjointSet ds = new DisjointSet(n);
    int p = m;
    long[][] ord = new long[p][2];
    for (int i = 0; i < p; i++) {
      ord[i][0] = w[i];
      ord[i][1] = i;
    }
    Arrays.sort(ord, (o1, o2) -> Long.compare(o1[0], o2[0]));

    long d = 0;
    for (int i = 0; i < m; i++) {
      int cur = (int) ord[i][1];
      if (!ds.equiv(one[cur], other[cur])) {
        ds.union(one[cur], other[cur]);
        d += w[cur];
      }
    }

    return d;
  }

  public static void main(String[] args) {
    new Thread(null, new Runnable() {
      @Override
      public void run() {
        long start = System.currentTimeMillis();
        String debug = args.length > 0 ? args[0] : null;
        if (debug != null) {
          try {
            is = java.nio.file.Files.newInputStream(java.nio.file.Paths.get(debug));
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
        reader = new java.io.BufferedReader(new java.io.InputStreamReader(is), 32768);
        solve();
        out.flush();
        tr((System.currentTimeMillis() - start) + "ms");
      }
    }, "", 64000000).start();
  }

  private static java.io.InputStream is = System.in;
  private static java.io.PrintWriter out = new java.io.PrintWriter(System.out);
  private static java.util.StringTokenizer tokenizer = null;
  private static java.io.BufferedReader reader;

  public static String next() {
    while (tokenizer == null || !tokenizer.hasMoreTokens()) {
      try {
        tokenizer = new java.util.StringTokenizer(reader.readLine());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return tokenizer.nextToken();
  }

  private static double nd() {
    return Double.parseDouble(next());
  }

  private static long nl() {
    return Long.parseLong(next());
  }

  private static int[] na(int n) {
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = ni();
    return a;
  }

  private static char[] ns() {
    return next().toCharArray();
  }

  private static long[] nal(int n) {
    long[] a = new long[n];
    for (int i = 0; i < n; i++)
      a[i] = nl();
    return a;
  }

  private static int[][] ntable(int n, int m) {
    int[][] table = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        table[i][j] = ni();
      }
    }
    return table;
  }

  private static int[][] nlist(int n, int m) {
    int[][] table = new int[m][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        table[j][i] = ni();
      }
    }
    return table;
  }

  private static int ni() {
    return Integer.parseInt(next());
  }

  private static void tr(Object... o) {
    if (is != System.in)
      System.out.println(java.util.Arrays.deepToString(o));
  }
}
