import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.io.IOException;
import java.util.Random;
import java.io.UncheckedIOException;
import java.io.Closeable;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(null, new TaskAdapter(), "", 1 << 27);
        thread.start();
        thread.join();
    }

    static class TaskAdapter implements Runnable {
        @Override
        public void run() {
            InputStream inputStream = System.in;
            OutputStream outputStream = System.out;
            FastInput in = new FastInput(inputStream);
            FastOutput out = new FastOutput(outputStream);
            TaskF solver = new TaskF();
            solver.solve(1, in, out);
            out.close();
        }
    }
    static class TaskF {
        long inf = (int) 1e8;
        Modular mod = new Modular(1e9 + 7);
        DigitBase base = new DigitBase(10);
        Power power = new Power(mod);

        public void solve(int testNumber, FastInput in, FastOutput out) {
            int s = in.readInt();
            ExpressionSolver expressionSolver = new ExpressionSolver();
            int ans = 0;

            // differ for zero
            IntList list = new IntList();
            for (int i = 1; i * i <= s; i++) {
                if (s % i != 0) {
                    continue;
                }
                list.add(i);
                list.add(s / i);
            }
            list.unique();
            for (int i = 0; i < list.size(); i++) {
                int factor = list.get(i);
                int req = s / factor;
                int count = mod.subtract(power.pow(10, factor), power.pow(10, factor - 1));
                if (factor <= 9 && count < req) {
                    continue;
                }
                count = mod.subtract(count, req - 1);
                ans = mod.plus(ans, count);
            }

            // differ for one
            for (int i = 1; i + i + 1 <= s; i++) {
                long atLeast = s % i;
                if (i + atLeast * (i + 1) > s) {
                    continue;
                }
                long xr = inf;
                long yr = inf;
                if (i <= 10) {
                    xr = base.valueOfBit(i) - base.valueOfBit(i - 1);
                    yr = base.valueOfBit(i + 1) - base.valueOfBit(i);
                }

                long way = expressionSolver.findWaysToAssignXYSatisfyEquation(i, i + 1, s, 1, xr, 1, yr);
                ans = mod.plus(ans, way);
            }

            // differ for more
            for (int i = 1; i <= 9; i++) {
                for (int j = i + 2; j <= 9; j++) {
                    long remain = s;
                    for (int k = i + 1; k < j; k++) {
                        remain -= k * (base.valueOfBit(k) - base.valueOfBit(k - 1));
                    }
                    if (remain < 0) {
                        continue;
                    }
                    long way = expressionSolver.findWaysToAssignXYSatisfyEquation(i, j, remain, 1,
                                    base.valueOfBit(i) - base.valueOfBit(i - 1), 1,
                                    base.valueOfBit(j) - base.valueOfBit(j - 1));
                    ans = mod.plus(ans, way);
                }
            }

            out.println(ans);
        }

    }
    static class SequenceUtils {
        public static boolean equal(int[] a, int al, int ar, int[] b, int bl, int br) {
            if ((ar - al) != (br - bl)) {
                return false;
            }
            for (int i = al, j = bl; i <= ar; i++, j++) {
                if (a[i] != b[j]) {
                    return false;
                }
            }
            return true;
        }

    }
    static class Modular {
        int m;

        public Modular(int m) {
            this.m = m;
        }

        public Modular(long m) {
            this.m = (int) m;
            if (this.m != m) {
                throw new IllegalArgumentException();
            }
        }

        public Modular(double m) {
            this.m = (int) m;
            if (this.m != m) {
                throw new IllegalArgumentException();
            }
        }

        public int valueOf(int x) {
            x %= m;
            if (x < 0) {
                x += m;
            }
            return x;
        }

        public int valueOf(long x) {
            x %= m;
            if (x < 0) {
                x += m;
            }
            return (int) x;
        }

        public int plus(int x, int y) {
            return valueOf(x + y);
        }

        public int plus(long x, long y) {
            x = valueOf(x);
            y = valueOf(y);
            return valueOf(x + y);
        }

        public int subtract(int x, int y) {
            return valueOf(x - y);
        }

        public String toString() {
            return "mod " + m;
        }

    }
    static class ExtGCD {
        private long x;
        private long y;
        private long g;

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }

        public long extgcd(long a, long b) {
            if (a >= b) {
                g = extgcd0(a, b);
            } else {
                g = extgcd0(b, a);
                long tmp = x;
                x = y;
                y = tmp;
            }
            return g;
        }

        private long extgcd0(long a, long b) {
            if (b == 0) {
                x = 1;
                y = 0;
                return a;
            }
            long g = extgcd0(b, a % b);
            long n = x;
            long m = y;
            x = m;
            y = n - m * (a / b);
            return g;
        }

    }
    static class Randomized {
        static Random random = new Random();

        public static void randomizedArray(int[] data, int from, int to) {
            to--;
            for (int i = from; i <= to; i++) {
                int s = nextInt(i, to);
                int tmp = data[i];
                data[i] = data[s];
                data[s] = tmp;
            }
        }

        public static int nextInt(int l, int r) {
            return random.nextInt(r - l + 1) + l;
        }

    }
    static class CompareUtils {
        private static final int[] BUF8 = new int[1 << 8];
        private static final IntList INT_LIST = new IntList();

        private CompareUtils() {}

        public static void radixSort(int[] data, int l, int r) {
            INT_LIST.clear();
            INT_LIST.expandWith(0, r - l + 1);
            int[] output = INT_LIST.getData();
            for (int i = 0; i < 4; i++) {
                int rightShift = i * 8;
                radixSort0(data, output, BUF8, l, r, rightShift);
                System.arraycopy(output, 0, data, l, r - l + 1);
            }
        }

        private static void radixSort0(int[] data, int[] output, int[] buf, int l, int r, int rightShift) {
            Arrays.fill(buf, 0);
            int mask = buf.length - 1;
            for (int i = l; i <= r; i++) {
                buf[(data[i] >>> rightShift) & mask]++;
            }
            for (int i = 1; i < buf.length; i++) {
                buf[i] += buf[i - 1];
            }
            for (int i = r; i >= l; i--) {
                output[--buf[(data[i] >>> rightShift) & mask]] = data[i];
            }
        }

    }
    static class LongList {
        private int size;
        private int cap;
        private long[] data;
        private static final long[] EMPTY = new long[0];

        public LongList(int cap) {
            this.cap = cap;
            if (cap == 0) {
                data = EMPTY;
            } else {
                data = new long[cap];
            }
        }

        public LongList(LongList list) {
            this.size = list.size;
            this.cap = list.cap;
            this.data = Arrays.copyOf(list.data, size);
        }

        public LongList() {
            this(0);
        }

        public void ensureSpace(int req) {
            if (req > cap) {
                while (cap < req) {
                    cap = Math.max(cap + 10, 2 * cap);
                }
                data = Arrays.copyOf(data, cap);
            }
        }

        private void checkRange(int i) {
            if (i < 0 || i >= size) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        public long tail() {
            checkRange(0);
            return data[size - 1];
        }

        public void add(long x) {
            ensureSpace(size + 1);
            data[size++] = x;
        }

        public long[] toArray() {
            return Arrays.copyOf(data, size);
        }

        public String toString() {
            return Arrays.toString(Arrays.copyOf(data, size));
        }

    }
    static class IntList {
        private int size;
        private int cap;
        private int[] data;
        private static final int[] EMPTY = new int[0];

        public int[] getData() {
            return data;
        }

        public IntList(int cap) {
            this.cap = cap;
            if (cap == 0) {
                data = EMPTY;
            } else {
                data = new int[cap];
            }
        }

        public IntList(IntList list) {
            this.size = list.size;
            this.cap = list.cap;
            this.data = Arrays.copyOf(list.data, size);
        }

        public IntList() {
            this(0);
        }

        public void ensureSpace(int req) {
            if (req > cap) {
                while (cap < req) {
                    cap = Math.max(cap + 10, 2 * cap);
                }
                data = Arrays.copyOf(data, cap);
            }
        }

        private void checkRange(int i) {
            if (i < 0 || i >= size) {
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        public int get(int i) {
            checkRange(i);
            return data[i];
        }

        public void add(int x) {
            ensureSpace(size + 1);
            data[size++] = x;
        }

        public void expandWith(int x, int len) {
            ensureSpace(len);
            while (size < len) {
                data[size++] = x;
            }
        }

        public void sort() {
            if (size <= 1) {
                return;
            }
            if (size < 256) {
                Randomized.randomizedArray(data, 0, size);
                Arrays.sort(data, 0, size);
            } else {
                CompareUtils.radixSort(data, 0, size - 1);
            }
        }

        public void unique() {
            if (size <= 1) {
                return;
            }

            sort();
            int wpos = 1;
            for (int i = 1; i < size; i++) {
                if (data[i] != data[wpos - 1]) {
                    data[wpos++] = data[i];
                }
            }
            size = wpos;
        }

        public int size() {
            return size;
        }

        public int[] toArray() {
            return Arrays.copyOf(data, size);
        }

        public void clear() {
            size = 0;
        }

        public String toString() {
            return Arrays.toString(toArray());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof IntList)) {
                return false;
            }
            IntList other = (IntList) obj;
            return SequenceUtils.equal(data, 0, size - 1, other.data, 0, other.size - 1);
        }

    }
    static class FastInput {
        private final InputStream is;
        private byte[] buf = new byte[1 << 13];
        private int bufLen;
        private int bufOffset;
        private int next;

        public FastInput(InputStream is) {
            this.is = is;
        }

        private int read() {
            while (bufLen == bufOffset) {
                bufOffset = 0;
                try {
                    bufLen = is.read(buf);
                } catch (IOException e) {
                    bufLen = -1;
                }
                if (bufLen == -1) {
                    return -1;
                }
            }
            return buf[bufOffset++];
        }

        public void skipBlank() {
            while (next >= 0 && next <= 32) {
                next = read();
            }
        }

        public int readInt() {
            int sign = 1;

            skipBlank();
            if (next == '+' || next == '-') {
                sign = next == '+' ? 1 : -1;
                next = read();
            }

            int val = 0;
            if (sign == 1) {
                while (next >= '0' && next <= '9') {
                    val = val * 10 + next - '0';
                    next = read();
                }
            } else {
                while (next >= '0' && next <= '9') {
                    val = val * 10 - next + '0';
                    next = read();
                }
            }

            return val;
        }

    }
    static class Power {
        final Modular modular;

        public Power(Modular modular) {
            this.modular = modular;
        }

        public int pow(int x, long n) {
            if (n == 0) {
                return modular.valueOf(1);
            }
            long r = pow(x, n >> 1);
            r = modular.valueOf(r * r);
            if ((n & 1) == 1) {
                r = modular.valueOf(r * x);
            }
            return (int) r;
        }

    }
    static class ExpressionSolver {
        private static ExtGCD extGCD = new ExtGCD();

        public long findWaysToAssignXYSatisfyEquation(long a, long b, long c, long xl, long xr, long yl, long yr) {
            if (c < 0) {
                a = -a;
                b = -b;
                c = -c;
            }
            if (a < 0) {
                a = -a;
                long l = xl;
                long r = xr;
                xl = -r;
                xr = -l;
            }
            if (b < 0) {
                b = -b;
                long l = yl;
                long r = yr;
                yl = -r;
                yr = -l;
            }
            if (xl > xr || yl > yr) {
                return 0;
            }
            if (a == 0 && b == 0) {
                if (c != 0) {
                    return 0;
                }
                return (xr - xl + 1) * (yr - yl + 1);
            }
            if (a == 0) {
                if (c % b != 0) {
                    return 0;
                }
                long y = c / b;
                if (y < yl || y > yr) {
                    return 0;
                }
                return xr - xl + 1;
            }
            if (b == 0) {
                if (c % a != 0) {
                    return 0;
                }
                long x = c / a;
                if (x < xl || x > xr) {
                    return 0;
                }
                return yr - yl + 1;
            }
            long g = extGCD.extgcd(a, b);
            if (c % g != 0) {
                return 0;
            }
            a /= g;
            b /= g;
            c /= g;
            long x = extGCD.getX() * c;
            long y = extGCD.getY() * c;
            if (x < xl) {
                // x + kb >= xl
                // k >= (xl - x)/b
                long k = DigitUtils.ceilDiv(xl - x, b);
                x += k * b;
                y -= k * a;
            } else {
                // x - kb >= xl
                // (x - xl)/b>=k
                long k = DigitUtils.floorDiv(x - xl, b);
                x -= k * b;
                y += k * a;
            }

            if (y > yr) {
                // y - ka <= yr
                // k >= (y - yr)/a
                long k = DigitUtils.ceilDiv(y - yr, a);
                x += k * b;
                y -= k * a;
            }

            if (x > xr || y < yl) {
                return 0;
            }

            long xSpare = Math.max(0, (xr - x) / b);
            long ySpare = Math.max(0, (y - yl) / a);
            return 1 + Math.min(xSpare, ySpare);
        }

    }
    static class FastOutput implements AutoCloseable, Closeable {
        private StringBuilder cache = new StringBuilder(10 << 20);
        private final Writer os;

        public FastOutput(Writer os) {
            this.os = os;
        }

        public FastOutput(OutputStream os) {
            this(new OutputStreamWriter(os));
        }

        public FastOutput println(int c) {
            cache.append(c).append('\n');
            return this;
        }

        public FastOutput flush() {
            try {
                os.append(cache);
                os.flush();
                cache.setLength(0);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
            return this;
        }

        public void close() {
            flush();
            try {
                os.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }

        public String toString() {
            return cache.toString();
        }

    }
    static class DigitUtils {
        private DigitUtils() {}

        public static long floorDiv(long a, long b) {
            return a < 0 ? -ceilDiv(-a, b) : a / b;
        }

        public static long ceilDiv(long a, long b) {
            if (a < 0) {
                return -floorDiv(-a, b);
            }
            long c = a / b;
            if (c * b < a) {
                return c + 1;
            }
            return c;
        }

        public static boolean isMultiplicationOverflow(long a, long b, long limit) {
            if (limit < 0) {
                limit = -limit;
            }
            if (a < 0) {
                a = -a;
            }
            if (b < 0) {
                b = -b;
            }
            if (a == 0 || b == 0) {
                return false;
            }
            return a > limit / b;
        }

    }
    static class DigitBase {
        private long[] pow;
        private long base;

        public DigitBase(long base) {
            if (base <= 1) {
                throw new IllegalArgumentException();
            }
            this.base = base;
            LongList ll = new LongList(64);
            ll.add(1);
            while (!DigitUtils.isMultiplicationOverflow(ll.tail(), base, Long.MAX_VALUE)) {
                ll.add(ll.tail() * base);
            }
            pow = ll.toArray();
        }

        public long valueOfBit(int i) {
            return pow[i];
        }

    }
}

