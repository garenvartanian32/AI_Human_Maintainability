import java.io.*; import java.util.*;
public class Main {
  static class FastScanner {
    BufferedInputStream in = new BufferedInputStream(System.in);
    byte[] buffer = new byte[1<<16]; int ptr=0, len=0;
    int read() throws IOException {
      if (ptr>=len) { len = in.read(buffer); ptr=0; if (len<=0) return -1; }
      return buffer[ptr++];
    }
    int nextInt() throws IOException {
      int c; while ((c=read())<=32 && c!=-1);
      int s = 1, x = 0;
      if (c=='-'){ s = -1; c = read(); }
      while (c>32){ x = x*10 + (c-'0'); c = read(); }
      return x*s;
    }
    String next() throws IOException {
      int c; while ((c=read())<=32 && c!=-1);
      StringBuilder sb = new StringBuilder();
      while (c>32){ sb.append((char)c); c = read(); }
      return sb.toString();
    }
  }
  public static void main(String[] args) throws Exception {
    FastScanner fs = new FastScanner();
    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // Implement solution here using fs.nextInt()/fs.next()
    out.flush();
  }
}