import java.io.*;
import java.math.BigInteger;
import java.util.*;


public class D {

  void solve() throws IOException {
    int n=nextInt();
    int m=nextInt();
    int[] p=new int[n];
    for(int i=0;i<n;i++)p[i]=nextInt() - 1;
    HashSet<Integer>[] s=new HashSet[n];
    HashSet<Integer> q=new HashSet<>();
    for(int i=0;i<n;i++)s[i]=new HashSet<>();
    for(int i=0;i<m;i++) {
      int u=nextInt()-1;
      int v=nextInt()-1;
      s[u].add(v);
      if (v==p[n-1])q.add(u);
    }
    int ans=0;
    int i=n-1;
    HashSet<Integer> w=new HashSet<>();
    while (i>=0) {
      if (q.contains(p[i])) {
        boolean good = true;
        for (Integer x:w) {
          if (!s[p[i]].contains(x)) {
            good=false;
            break;
          }
        }
        if (good) {
          ans++;
        } else {
          w.add(p[i]);
        }
      } else {
        w.add(p[i]);
      }
      i--;
    }
    out.println(ans);
  }

  public static void main(String[] args) throws IOException {
    new D().run();
  }

  void run() throws IOException {
    reader = new BufferedReader(new InputStreamReader(System.in));
//		reader = new BufferedReader(new FileReader("input.txt"));
    tokenizer = null;
    out = new PrintWriter(new OutputStreamWriter(System.out));
//		out = new PrintWriter(new FileWriter("output.txt"));
    solve();
    reader.close();
    out.flush();

  }

  BufferedReader reader;
  StringTokenizer tokenizer;
  PrintWriter out;

  int nextInt() throws IOException {
    return Integer.parseInt(nextToken());
  }

  long nextLong() throws IOException {
    return Long.parseLong(nextToken());
  }

  double nextDouble() throws IOException {
    return Double.parseDouble(nextToken());
  }

  String nextToken() throws IOException {
    while (tokenizer == null || !tokenizer.hasMoreTokens()) {
      tokenizer = new StringTokenizer(reader.readLine());
    }
    return tokenizer.nextToken();
  }

  class BitSet {
    int[] set = new int[10000];

  }
}
