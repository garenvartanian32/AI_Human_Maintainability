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
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            long x1 = in.nextInt(), y1 = in.nextInt();
            long x2 = in.nextInt(), y2 = in.nextInt();
            long x3 = in.nextInt(), y3 = in.nextInt();
            if ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) == (x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3) && (y1 - y3) * (x1 - x2) != (y1 - y2) * (x1 - x3))
                out.print("Yes");
            else out.print("No");
        }

    }

    static class InputReader {
        private int lenbuf = 0;
        private int ptrbuf = 0;
        private InputStream in;
        private byte[] inbuf = new byte[1024];

        public InputReader(InputStream in) {
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

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }

            while (true) {
                if (b >= '0' && b <= '9') {
                    num = num * 10 + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

    }
}

