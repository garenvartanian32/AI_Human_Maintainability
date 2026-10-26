I don't see the problem statement — please paste it (include input/output format, constraints, and samples). Meanwhile, here's a ready Java template to drop your solution into:

public class Main {
    static class FastScanner {
        private final java.io.InputStream in = System.in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private int read() throws java.io.IOException {
            if (ptr >= len) { len = in.read(buffer); ptr = 0; if (len <= 0) return -1; }
            return buffer[ptr++];
        }
        int nextInt() throws java.io.IOException {
            int c, s = 1, x = 0;
            while ((c = read()) <= ' ') if (c == -1) return Integer.MIN_VALUE;
            if (c == '-') { s = -1; c = read(); }
            for (; c > ' '; c = read()) x = x * 10 + (c - '0');
            return x * s;
        }
        String next() throws java.io.IOException {
            int c; StringBuilder sb = new StringBuilder();
            while ((c = read()) <= ' ') if (c == -1) return null;
            for (; c > ' '; c = read()) sb.append((char)c);
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        // Example usage:
        // int n = fs.nextInt();
        // String s = fs.next();
        // Implement solution below and print results to System.out
    }
}

Paste the problem and I'll implement the full solution in Java.