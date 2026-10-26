import java.io.*;
import java.util.*;

public class Main {
    static FastScanner in = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve() {
        // Read input, implement solution, write output.
        // Example:
        // int n = in.nextInt();
        // out.println(n);
    }

    // Fast scanner
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws RuntimeException {
            try {
                if (ptr >= len) {
                    len = in.read(buffer);
                    ptr = 0;
                    if (len <= 0) return -1;
                }
                return buffer[ptr++];
            } catch (IOException e) { throw new RuntimeException(e); }
        }
        String next() {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) <= ' ' && c != -1) {}
            if (c == -1) return null;
            do { sb.append((char)c); } while ((c = read()) > ' ');
            return sb.toString();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c != '\n' && c != '\r') sb.append((char)c);
            // consume \n after \r
            if (c == '\r') read();
            return sb.toString();
        }
    }
}