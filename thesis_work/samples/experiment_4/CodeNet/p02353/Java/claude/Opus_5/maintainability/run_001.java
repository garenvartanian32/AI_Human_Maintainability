import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * Range Update Query plus Range Sum Query, backed by a lazy segment tree.
 */
public class Sol {

    private static final int UPDATE_COMMAND = 0;

    /** Segment tree supporting assign-on-range and sum-over-range. */
    private static final class LazySegmentTree {
        private final int width;
        private final long[] sums;
        private final long[] pending;
        private final boolean[] hasPending;

        LazySegmentTree(int length) {
            int size = 1;
            while (size < length) {
                size *= 2;
            }
            this.width = size;
            this.sums = new long[2 * size];
            this.pending = new long[2 * size];
            this.hasPending = new boolean[2 * size];
        }

        /** Sets every element in [first, last] to {@code value}. */
        void assign(int first, int last, long value) {
            assign(1, 0, width - 1, first, last, value);
        }

        /** Returns the sum over [first, last]. */
        long total(int first, int last) {
            return total(1, 0, width - 1, first, last);
        }

        /** Hands a pending assignment down to both children. */
        private void pushDown(int node, int span) {
            if (!hasPending[node]) {
                return;
            }

            long value = pending[node];
            int half = span / 2;
            for (int child = 2 * node; child <= 2 * node + 1; child++) {
                sums[child] = value * half;
                pending[child] = value;
                hasPending[child] = true;
            }
            hasPending[node] = false;
        }

        private void assign(int node, int low, int high, int first, int last, long value) {
            if (last < low || high < first) {
                return;
            }
            if (first <= low && high <= last) {
                sums[node] = value * (high - low + 1);
                pending[node] = value;
                hasPending[node] = true;
                return;
            }

            pushDown(node, high - low + 1);
            int middle = (low + high) >>> 1;
            assign(2 * node, low, middle, first, last, value);
            assign(2 * node + 1, middle + 1, high, first, last, value);
            sums[node] = sums[2 * node] + sums[2 * node + 1];
        }

        private long total(int node, int low, int high, int first, int last) {
            if (last < low || high < first) {
                return 0;
            }
            if (first <= low && high <= last) {
                return sums[node];
            }

            pushDown(node, high - low + 1);
            int middle = (low + high) >>> 1;
            return total(2 * node, low, middle, first, last)
                 + total(2 * node + 1, middle + 1, high, first, last);
        }
    }

    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(
                new BufferedReader(new InputStreamReader(System.in)));

        input.nextToken();
        int length = (int) input.nval;
        input.nextToken();
        int queryCount = (int) input.nval;

        LazySegmentTree tree = new LazySegmentTree(length);
        StringBuilder answers = new StringBuilder();

        for (int query = 0; query < queryCount; query++) {
            input.nextToken();
            int command = (int) input.nval;

            input.nextToken();
            int first = (int) input.nval;
            input.nextToken();
            int last = (int) input.nval;

            if (command == UPDATE_COMMAND) {
                input.nextToken();
                long value = (long) input.nval;
                tree.assign(first, last, value);
            } else {
                answers.append(tree.total(first, last)).append('\n');
            }
        }

        System.out.print(answers);
    }
}