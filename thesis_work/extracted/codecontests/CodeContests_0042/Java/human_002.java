import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        MyInput in = new MyInput(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        EConnectingCities solver = new EConnectingCities();
        solver.solve(1, in, out);
        out.close();
    }

    static class EConnectingCities {
        public void solve(int testNumber, MyInput in, PrintWriter out) {
            int n = in.nextInt();
            long d = in.nextLong();
            long[] a = in.nextLongArray(n);

            UnionFind uf = new UnionFind(n);
            long ans = 0;

            while (uf.size(0) != n) {
                long[] val = new long[n + 2];
                long[] val2 = new long[n + 2];
                long[] valL = new long[n + 2];
                long[] valR = new long[n + 2];
                Arrays.fill(valL, Long.MAX_VALUE / 2);
                Arrays.fill(valR, Long.MAX_VALUE / 2);
                Arrays.fill(val, Long.MAX_VALUE / 2);
                Arrays.fill(val2, Long.MAX_VALUE / 2);
                int[] indexL = new int[n + 2];
                int[] indexR = new int[n + 2];
                for (int i = 0; i < n; i++) {
                    if (valL[uf.root(i) + 1] > a[i] - i * d) {
                        valL[uf.root(i) + 1] = a[i] - i * d;
                        indexL[uf.root(i) + 1] = i;
                    }
                    if (valR[uf.root(i) + 1] > a[i] + i * d) {
                        valR[uf.root(i) + 1] = a[i] + i * d;
                        indexR[uf.root(i) + 1] = i;
                    }
                    val[uf.root(i) + 1] = Math.min(val[uf.root(i) + 1], a[i] + i * d);
                    val2[uf.root(i) + 1] = Math.min(val2[uf.root(i) + 1], a[i] - i * d);
                }

                int[] minL = new int[n + 2];
                int[] minR = new int[n + 2];

                for (int i = 1; i <= n; i++) {
                    minL[i] = valL[minL[i - 1]] >= valL[i] ? i : minL[i - 1];
                }

                minR[n + 1] = n + 1;
                for (int i = n; i >= 1; i--) {
                    minR[i] = valR[minR[i + 1]] >= valR[i] ? i : minR[i + 1];
                }

                for (int i = 1; i <= n; i++) {
                    if (uf.root(i - 1) != i - 1) continue;
                    long cl = valL[minL[i - 1]] + val[i];
                    long cr = valR[minR[i + 1]] + val2[i];
//                dump(i, cl, cr);
                    if (cl <= cr) {
                        if (!uf.same(i - 1, minL[i - 1] - 1)) {
                            ans += cl;
                            uf.union(i - 1, minL[i - 1] - 1);
                        }
                    } else {
//                    dump(i, cl, cr, valR, minR, val2);
//                    dump(i, cl, cr, minL, valL, val);
                        if (!uf.same(i - 1, minR[i + 1] - 1)) {
                            ans += cr;
                            uf.union(i - 1, minR[i + 1] - 1);
                        }

                    }
                }
            }
            out.println(ans);
        }

        class UnionFind {
            private int[] data;

            public UnionFind(int size) {
                data = new int[size];
                clear();
            }

            public void clear() {
                Arrays.fill(data, -1);
            }

            public int root(int x) {
                return data[x] < 0 ? x : (data[x] = root(data[x]));
            }

            public void union(int x, int y) {
                if ((x = root(x)) != (y = root(y))) {
                    if (data[y] < data[x]) {
                        final int t = x;
                        x = y;
                        y = t;
                    }
                    data[x] += data[y];
                    data[y] = x;
                }
            }

            public boolean same(int x, int y) {
                return root(x) == root(y);
            }

            public int size(int x) {
                return -data[root(x)];
            }

        }

    }

    static class MyInput {
        private final BufferedReader in;
        private static int pos;
        private static int readLen;
        private static final char[] buffer = new char[1024 * 8];
        private static char[] str = new char[500 * 8 * 2];
        private static boolean[] isDigit = new boolean[256];
        private static boolean[] isSpace = new boolean[256];
        private static boolean[] isLineSep = new boolean[256];

        static {
            for (int i = 0; i < 10; i++) {
                isDigit['0' + i] = true;
            }
            isDigit['-'] = true;
            isSpace[' '] = isSpace['\r'] = isSpace['\n'] = isSpace['\t'] = true;
            isLineSep['\r'] = isLineSep['\n'] = true;
        }

        public MyInput(InputStream is) {
            in = new BufferedReader(new InputStreamReader(is));
        }

        public int read() {
            if (pos >= readLen) {
                pos = 0;
                try {
                    readLen = in.read(buffer);
                } catch (IOException e) {
                    throw new RuntimeException();
                }
                if (readLen <= 0) {
                    throw new MyInput.EndOfFileRuntimeException();
                }
            }
            return buffer[pos++];
        }

        public int nextInt() {
            int len = 0;
            str[len++] = nextChar();
            len = reads(len, isSpace);
            int i = 0;
            int ret = 0;
            if (str[0] == '-') {
                i = 1;
            }
            for (; i < len; i++) ret = ret * 10 + str[i] - '0';
            if (str[0] == '-') {
                ret = -ret;
            }
            return ret;
        }

        public long nextLong() {
            int len = 0;
            str[len++] = nextChar();
            len = reads(len, isSpace);
            int i = 0;
            long ret = 0;
            if (str[0] == '-') {
                i = 1;
            }
            for (; i < len; i++) ret = ret * 10 + str[i] - '0';
            if (str[0] == '-') {
                ret = -ret;
            }
            return ret;
        }

        public char nextChar() {
            while (true) {
                final int c = read();
                if (!isSpace[c]) {
                    return (char) c;
                }
            }
        }

        int reads(int len, boolean[] accept) {
            try {
                while (true) {
                    final int c = read();
                    if (accept[c]) {
                        break;
                    }
                    if (str.length == len) {
                        char[] rep = new char[str.length * 3 / 2];
                        System.arraycopy(str, 0, rep, 0, str.length);
                        str = rep;
                    }
                    str[len++] = (char) c;
                }
            } catch (MyInput.EndOfFileRuntimeException e) {
            }
            return len;
        }

        public long[] nextLongArray(final int n) {
            final long[] res = new long[n];
            for (int i = 0; i < n; i++) {
                res[i] = nextLong();
            }
            return res;
        }

        static class EndOfFileRuntimeException extends RuntimeException {
        }

    }
}

