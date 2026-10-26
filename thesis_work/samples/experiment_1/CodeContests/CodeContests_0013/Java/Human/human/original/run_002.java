import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import java.io.OutputStream;
import java.nio.CharBuffer;
import java.io.IOException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.io.UncheckedIOException;
import java.util.Objects;
import java.util.List;
import java.io.Writer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author mikit
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        LightScanner2 in = new LightScanner2(inputStream);
        LightWriter2 out = new LightWriter2(outputStream);
        FEuclidsNightmare solver = new FEuclidsNightmare();
        solver.solve(1, in, out);
        out.close();
    }

    static class FEuclidsNightmare {
        private static final int MOD = (int) 1e9 + 7;

        public void solve(int testNumber, LightScanner2 in, LightWriter2 out) {
            int n = in.ints(), m = in.ints();
            ModMath mod = new ModMath(MOD);

            int[] transform = new int[m + 1];

            IntUnionFind uf = new IntUnionFind(m + 1);

            Arrays.fill(transform, -1);

            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int k = in.ints();

                if (k == 1) {
                    int p = in.ints();
                    p = uf.find(p);
                    // while (transform[p] != -1) p = transform[p];

                    if (p != uf.find(0)) {
                        uf.union(p, 0);
                        ans.add(i);
                    }
                } else {
                    int p1 = in.ints(), p2 = in.ints();
                    p1 = uf.find(p1);
                    p2 = uf.find(p2);
                    //while (transform[p1] != -1) p1 = transform[p1];
                    //while (transform[p2] != -1) p2 = transform[p2];

                    if (p1 == p2) continue;

                    if (p1 > p2) {
                        int t = p1;
                        p1 = p2;
                        p2 = t;
                    }

                    if (p1 != uf.find(0)) {
                        uf.union(p1, p2);
                        ans.add(i);
                    } else if (p2 != uf.find(0)) {
                        uf.union(0, p2);
                        ans.add(i);
                    }
                }
            }

            out.ans(mod.pow(2, ans.size())).ans(ans.size()).ln();
            for (int i : ans) out.ans(i + 1);
            out.ln();
        }

    }

    static class ModMath {
        private static final int DEFAULT_MOD = 1_000_000_007;
        private final long mod;

        public ModMath(long mod, boolean unsafe) {
        /*if (!unsafe && !IntMath.isPrime(mod)) {
            throw new RuntimeException("This class is designed for primes!");
        }*/
            this.mod = mod;
        }

        public ModMath(long mod) {
            this(mod, false);
        }

        public ModMath() {
            this(DEFAULT_MOD, true);
        }

        public long mod(long x) {
            x %= mod;
            return x < 0 ? x + mod : x;
        }

        public long inv(long x) {
            //return pow(x, mod - 2);
            return mod(LongEuclidSolver.solve(x, mod).x);
        }

        public long pow(long x, long y) {
            y %= (mod - 1);
            if (y < 0) {
                return pow(inv(x), -y);
            } else if (y == 0) {
                return 1;
            } else if (y % 2 == 0) {
                long z = pow(x, y / 2);
                return (z * z) % mod;
            } else {
                return (x % mod) * pow(x, y - 1) % mod;
            }
        }

    }

    static class LightScanner2 extends LightScannerAdapter {
        private static final int BUF_SIZE = 16 * 1024;
        private final InputStream stream;
        private final byte[] buf = new byte[BUF_SIZE];
        private int ptr;
        private int len;

        public LightScanner2(InputStream stream) {
            this.stream = stream;
        }

        private void reload() {
            try {
                ptr = 0;
                len = stream.read(buf);
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }

        private void load(int n) {
            if (ptr + n <= len) return;
            System.arraycopy(buf, ptr, buf, 0, len - ptr);
            len -= ptr;
            ptr = 0;
            try {
                int r = stream.read(buf, len, BUF_SIZE - len);
                if (r == -1) return;
                len += r;
                if (len != BUF_SIZE) buf[len] = '\n';
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }

        private void skip() {
            while (len != -1) {
                while (ptr < len && isTokenSeparator(buf[ptr])) ptr++;
                if (ptr < len) return;
                reload();
            }
            throw new NoSuchElementException("EOF");
        }

        public int ints() {
            skip();
            load(12);
            int b = buf[ptr++];
            boolean negate;
            if (b == '-') {
                negate = true;
                b = buf[ptr++];
            } else negate = false;
            int x = 0;
            for (; !isTokenSeparator(b); b = buf[ptr++]) {
                if ('0' <= b && b <= '9') x = x * 10 + b - '0';
                else throw new NumberFormatException("Unexpected character '" + ((char) b) + "'");
            }
            return negate ? -x : x;
        }

        public void close() {
            try {
                stream.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        private static boolean isTokenSeparator(int b) {
            return b < 33 || 126 < b;
        }

    }

    static class LightWriter2 implements AutoCloseable {
        private static final int BUF_SIZE = 16 * 1024;
        private final OutputStream out;
        private final byte[] buf = new byte[BUF_SIZE];
        private int ptr;
        private boolean autoflush = false;
        private boolean breaked = true;

        public LightWriter2(OutputStream out) {
            this.out = out;
        }

        public LightWriter2(Writer out) {
            this.out = new LightWriter2.WriterOutputStream(out);
        }

        private void allocate(int n) {
            if (ptr + n <= BUF_SIZE) return;
            try {
                out.write(buf, 0, ptr);
                ptr = 0;
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
            if (BUF_SIZE < n) throw new IllegalArgumentException("Internal buffer exceeded");
        }

        public void close() {
            try {
                out.write(buf, 0, ptr);
                ptr = 0;
                out.flush();
                out.close();
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }

        private static int countDigits(int l) {
            if (l >= 1000000000L) return 10;
            if (l >= 100000000L) return 9;
            if (l >= 10000000L) return 8;
            if (l >= 1000000L) return 7;
            if (l >= 100000L) return 6;
            if (l >= 10000L) return 5;
            if (l >= 1000L) return 4;
            if (l >= 100L) return 3;
            if (l >= 10L) return 2;
            return 1;
        }

        private static int countDigits(long l) {
            if (l >= 1000000000000000000L) return 19;
            if (l >= 100000000000000000L) return 18;
            if (l >= 10000000000000000L) return 17;
            if (l >= 1000000000000000L) return 16;
            if (l >= 100000000000000L) return 15;
            if (l >= 10000000000000L) return 14;
            if (l >= 1000000000000L) return 13;
            if (l >= 100000000000L) return 12;
            if (l >= 10000000000L) return 11;
            if (l >= 1000000000L) return 10;
            if (l >= 100000000L) return 9;
            if (l >= 10000000L) return 8;
            if (l >= 1000000L) return 7;
            if (l >= 100000L) return 6;
            if (l >= 10000L) return 5;
            if (l >= 1000L) return 4;
            if (l >= 100L) return 3;
            if (l >= 10L) return 2;
            return 1;
        }

        public LightWriter2 ans(long x) {
            allocate(24);
            if (!breaked) buf[ptr++] = ' ';
            breaked = false;

            if (x < 0) {
                buf[ptr++] = '-';
                x = -x;
            }
            int n = countDigits(x);
            for (int i = ptr + n - 1; ptr <= i; i--) {
                buf[i] = (byte) (x % 10 + '0');
                x /= 10;
            }
            ptr += n;

            return this;
        }

        public LightWriter2 ans(int x) {
            allocate(12);
            if (!breaked) buf[ptr++] = ' ';
            breaked = false;

            if (x < 0) {
                buf[ptr++] = '-';
                x = -x;
            }
            int n = countDigits(x);
            for (int i = ptr + n - 1; ptr <= i; i--) {
                buf[i] = (byte) (x % 10 + '0');
                x /= 10;
            }
            ptr += n;

            return this;
        }

        public LightWriter2 ln() {
            allocate(1);
            buf[ptr++] = '\n';
            breaked = true;
            if (autoflush) {
                try {
                    out.flush();
                } catch (IOException ex) {
                    throw new UncheckedIOException(ex);
                }
            }
            return this;
        }

        private static class WriterOutputStream extends OutputStream {
            final Writer writer;
            final CharsetDecoder decoder;

            WriterOutputStream(Writer writer) {
                this.writer = writer;
                this.decoder = StandardCharsets.UTF_8.newDecoder();
            }

            public void write(int b) throws IOException {
                writer.write(b);
            }

            public void write(byte[] b) throws IOException {
                writer.write(decoder.decode(ByteBuffer.wrap(b)).array());
            }

            public void write(byte[] b, int off, int len) throws IOException {
                writer.write(decoder.decode(ByteBuffer.wrap(b, off, len)).array());
            }

            public void flush() throws IOException {
                writer.flush();
            }

            public void close() throws IOException {
                writer.close();
            }

        }

    }

    static abstract class LightScannerAdapter implements AutoCloseable {
        public abstract void close();

    }

    static class Vec3l implements Comparable<Vec3l> {
        public long x;
        public long y;
        public long z;

        public Vec3l(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vec3i vec3i = (Vec3i) o;
            return x == vec3i.x &&
                    y == vec3i.y &&
                    z == vec3i.z;
        }

        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }

        public int compareTo(Vec3l o) {
            if (x == o.x) {
                if (y == o.y) {
                    return Long.compare(z, o.z);
                }
                return Long.compare(y, o.z);
            }
            return Long.compare(x, o.x);
        }

    }

    static class LongEuclidSolver {
        private LongEuclidSolver() {
        }

        public static Vec3l solve(long a, long b) {
            LongEuclidSolver.ReferenceLong p = new LongEuclidSolver.ReferenceLong(), q = new LongEuclidSolver.ReferenceLong();
            long d = solve(a, b, p, q);

            return new Vec3l(p.val, q.val, d);
        }

        private static long solve(long a, long b, LongEuclidSolver.ReferenceLong p, LongEuclidSolver.ReferenceLong q) {
            if (b == 0) {
                p.val = 1;
                q.val = 0;
                return a;
            } else {
                long d = solve(b, a % b, q, p);
                q.val -= (a / b) * p.val;
                return d;
            }
        }

        private static class ReferenceLong {
            private long val;

        }

    }

    static interface Verified {
    }

    static class Vec3i implements Comparable<Vec3i> {
        public int x;
        public int y;
        public int z;

        public Vec3i(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vec3i vec3i = (Vec3i) o;
            return x == vec3i.x &&
                    y == vec3i.y &&
                    z == vec3i.z;
        }

        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        public String toString() {
            return "(" + x + ", " + y + ", " + z + ")";
        }

        public int compareTo(Vec3i o) {
            if (x == o.x) {
                if (y == o.y) {
                    return Integer.compare(z, o.z);
                }
                return Integer.compare(y, o.z);
            }
            return Integer.compare(x, o.x);
        }

    }

    static final class IntUnionFind {
        private int groups;
        private final int[] nodes;
        private final int[] rank;

        public IntUnionFind(int n) {
            groups = n;
            nodes = new int[n];
            Arrays.fill(nodes, -1);
            rank = new int[n];
        }

        public int find(int i) {
            int ans = nodes[i];
            if (ans < 0) {
                return i;
            } else {
                return nodes[i] = find(ans);
            }
        }

        public int union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return -1;
            } else if (rank[x] < rank[y]) {
                nodes[y] += nodes[x];
                nodes[x] = y;
            } else if (rank[x] == rank[y]) {
                rank[x]++;
                nodes[x] += nodes[y];
                nodes[y] = x;
            } else {
                nodes[x] += nodes[y];
                nodes[y] = x;
            }
            groups--;
            return x;
        }

    }
}

