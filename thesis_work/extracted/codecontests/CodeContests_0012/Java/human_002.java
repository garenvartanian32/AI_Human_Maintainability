import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A143 {
  static FastScanner in;
  static FastWriter out;
  
  public static void main(String[] args) throws IOException {
    in = new FastScanner(System.in);
    out = new FastWriter(System.out);
    
    int[] r = new int[2];
    int[] c = new int[2];
    int[] d = new int[2];
    
    for (int i = 0; i < 2; i++)
      r[i] = in.nextInt();
    for (int i = 0; i < 2; i++)
      c[i] = in.nextInt();
    for (int i = 0; i < 2; i++)
      d[i] = in.nextInt();
    
    boolean found = false;
    for (int i = 1; i <= 9 && !found; i++) {
      for (int j = 1; j <= 9 && !found; j++) {
        if (j == i) continue;
        for (int k = 1; k <= 9 && !found; k++) {
          if (k == i || k == j) continue;
          for (int l = 1; l <= 9 && !found; l++) {
            if (l == i || l == j || l == k) continue;
            if (i + j != r[0]) continue;
            if (k + l != r[1]) continue;
            if (i + k != c[0]) continue;
            if (j + l != c[1]) continue;
            if (i + l != d[0]) continue;
            if (k + j != d[1]) continue;
            out.printf("%d %d\n", i, j);
            out.printf("%d %d\n", k, l);
            found = true;
          }
        }
      }
    }
    
    if (!found) {
      out.println(-1);
    }
    
    out.close();
  }
  
  static class FastScanner {
    BufferedReader br;
    StringTokenizer st;
    
    public FastScanner(InputStream in) {
      br = new BufferedReader(new InputStreamReader(in));
      st = new StringTokenizer("");
    }
    
    public FastScanner(File f) throws IOException {
      br = new BufferedReader(new FileReader(f));
      st = new StringTokenizer("");
    }
    
    public int nextInt() throws IOException {
      return Integer.parseInt(next());
    }
    
    public long nextLong() throws IOException {
      return Long.parseLong(next());
    }
    
    public double nextDouble() throws IOException {
      return Double.parseDouble(next());
    }
    
    public String next() throws IOException {
      if (st.hasMoreTokens()) return st.nextToken();
      st = new StringTokenizer(br.readLine());
      return next();
    }
  }
  
  static class FastWriter extends PrintWriter {
    public FastWriter(OutputStream out) throws IOException {
      super(new BufferedWriter(new OutputStreamWriter(out)));
    }
    
    public FastWriter(File f) throws IOException {
      super(new BufferedWriter(new FileWriter(f)));
    }
  }
}
