import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        MyScan in = new MyScan(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskD solver = new TaskD();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskD {
        public void solve(int testNumber, MyScan in, PrintWriter out) {
            String s = in.next();

            long[] res = new long[3];

            long ta = 1;
            for (char x : s.toCharArray()) {
                switch (x) {
                    case 'A':
                        res[0] = (res[0] + ta) % Util._m;
                        break;
                    case 'B':
                        res[1] = (res[1] + res[0]) % Util._m;
                        break;
                    case 'C':
                        res[2] = (res[1] + res[2]) % Util._m;
                        break;
                    case '?': {
                        res[2] = (3 * res[2] + res[1]) % Util._m;
                        res[1] = (3 * res[1] + res[0]) % Util._m;
                        res[0] = (3 * res[0] + ta) % Util._m;
                        ta = (ta * 3) % Util._m;
                        break;
                    }
                }

            }
//        out.println(Arrays.toString(res));
            out.println(res[2]);

        }

    }

    static class Util {
        public static final long M07 = 1000_000_007;
        public static final long _m = M07;

    }

    static class MyScan {
        private final InputStream in;
        private byte[] inbuf = new byte[1024];
        public int lenbuf = 0;
        public int ptrbuf = 0;

        public MyScan(InputStream in) {
            this.in = in;
        }

        private int readByte() {
            if (lenbuf == -1) throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = in.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0) return -1;
            }
            return inbuf[ptrbuf++];
        }

        public boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        public int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b)) ;
            return b;
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

    }
}

