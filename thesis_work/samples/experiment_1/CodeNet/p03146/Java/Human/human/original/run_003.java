import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) {
    InputReader in = new InputReader(System.in);
    PrintWriter out = new PrintWriter(System.out);

    int n = in.nextInt();

    HashSet<Integer> ns = new HashSet<>();

    int res = 1;

    while (!ns.contains(n)) {
      ns.add(n);
      if (n % 2 == 0)
        n /= 2;
      else
        n = 3 * n + 1;
      res++;
    }

    out.println(res);

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
  }

  static void print(int[] arr) {
    Arrays.stream(arr).forEach(i -> System.out.print(i + " "));
    System.out.println();
  }

  static class Pair implements Comparable<Pair> {
    int x;
    int y;

    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public int compareTo(Pair o) {
      int compareX = Integer.compare(x, o.x);
      return (compareX == 0) ? Integer.compare(y, o.y) : compareX;
    }

    @Override
    public int hashCode() {
      int res = 7;
      res = 31 * res + x;
      res = 31 * res + y;
      return res;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Pair) {
        return hashCode() == o.hashCode();
      }
      return false;
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ")";
    }

  }

}

