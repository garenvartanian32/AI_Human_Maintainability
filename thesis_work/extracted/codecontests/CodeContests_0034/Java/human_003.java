import java.io.*;
import java.util.*;

/*
TASK: CFD
LANG: JAVA
 */
public class CFD {
    static int n , d , cur;
    static int[] a , max;

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        n = in.nextInt();
        d = in.nextInt();
        a = new int[n];
        max = new int[n];
        int cnt = 0;
        for(int i = 0;i < n;i++){
            a[i] = in.nextInt();
        }
        max[n-1] = d;
        for(int i = n-2; i >= 0; i--){
            max[i] = Math.min(d , max[i+1] - a[i+1]);
        }

        for(int i = 0;i < n; i++){
            if(a[i] == 0){
                if(cur < 0) {
                    if (max[i] < 0) {
                        System.out.println(-1);
                        return;
                    }
                    cur = Math.min(max[i], d);
                    cnt++;
                }
            }
            cur += a[i];
            if(cur > d){
                System.out.println(-1);
                return;
            }
        }
        System.out.println(cnt);
    }

    private static class FastScanner {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public FastScanner(InputStream stream) {
            this.stream = stream;
        }

        int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) return -1;
            }
            return buf[curChar++];
        }

        boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        boolean isEndline(int c) {
            return c == '\n' || c == '\r' || c == -1;
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

        public String next() {
            int c = read();
            while (isSpaceChar(c)) c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public String nextLine() {
            int c = read();
            while (isEndline(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isEndline(c));
            return res.toString();
        }
    }
}
