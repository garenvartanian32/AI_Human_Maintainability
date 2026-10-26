//package codeforce.div2.r714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 4399
 * 42
 * <p>
 * L -> 42, 420, 4200, 42000
 * R -> 42, 429, 4299, 42999
 * <p>
 * ans max(4400, 43000) -> 4345
 */

/**
 * @author pribic (Priyank Doshi)
 * @since 11/04/21
 */
public class C {
  private static final int MOD = 1000000000 + 7;
  PrintWriter out;
  FastReader sc;

  public static void main(String[] args) {
    C main = new C();
    main.out = new PrintWriter(System.out);
    main.sc = new FastReader();

    long[] memo = new long[200000 + 1];
    Arrays.fill(memo, -1);
    for (int i = memo.length - 1; i >= 0; i--) {
      solve(i, memo);
    }


    int T = main.ni();
    for (int tt = 1; tt <= T; tt++) {
      int n = main.ni();
      int m = main.ni();
      long ans = 0;
      while (n > 0) {
        int d = n % 10;
        if (9 - d >= m) {
          ans = add(ans, 1L);
        } else {
          ans = add(ans, memo[m - 9 + d]);
        }
        n /= 10;
      }
      main.pl(ans);
      main.out.flush();
    }
  }

  private static long solve(int op, long[] memo) {
    if (memo[op] == -1) {
      if (op == 0) {
        memo[0] = 1L;
      } else if (op <= 9) {
        memo[op] = 2L;
      } else {
        memo[op] = add(solve(op - 9, memo), solve(op - 10, memo));
      }
    }
    return memo[op];
  }

  private static long add(long a, long b) {
    return (a % MOD + b % MOD) % MOD;
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new
        InputStreamReader(System.in));
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

  public int ni() {
    return sc.nextInt();
  }

  public long nl() {
    return sc.nextLong();
  }

  public double nd() {
    return sc.nextDouble();
  }

  public char[] rl() {
    return sc.nextLine().toCharArray();
  }

  public String rl1() {
    return sc.nextLine();
  }

  public void pl(Object s) {
    out.println(s);
  }

  public void ex() {
    out.println();
  }

  public void pr(Object s) {
    out.print(s);
  }

  public String next() {
    return sc.next();
  }

  public long abs(long x) {
    return Math.abs(x);
  }

  public int abs(int x) {
    return Math.abs(x);
  }

  public double abs(double x) {
    return Math.abs(x);
  }

  public long min(long x, long y) {
    return (long) Math.min(x, y);
  }
}