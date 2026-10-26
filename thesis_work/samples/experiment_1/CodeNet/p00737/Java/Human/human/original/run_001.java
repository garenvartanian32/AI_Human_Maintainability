import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Main {

	static PrintWriter out;
	static InputReader ir;
	static boolean debug = true;
	static final int[] dd = { 0, 1, 0, -1 };

	static void solve() {
		for (;;) {
			int w = ir.nextInt();
			int h = ir.nextInt();
			if (h == 0 && w == 0)
				return;
			int[][] a = new int[h][];
			for (int i = 0; i < h; i++)
				a[i] = ir.nextIntArray(w);
			int[] c = ir.nextIntArray(4);
			int[][] d = new int[h * w][4];
			for (int i = 0; i < h * w; i++)
				Arrays.fill(d[i], 1 << 30);
			d[0][0] = 0;
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					return a[3] - b[3];
				}
			});
			pq.add(new int[] { 0, 0, 0, 0 });
			while (!pq.isEmpty()) {
				int[] from = pq.poll();
				int cur = from[0] * w + from[1];
				int cmd = a[from[0]][from[1]];
				for (int i = 0; i < 4; i++) {
					int nx = from[0] + dd[dir(from[2], i)], ny = from[1] + dd[dir(from[2], i) ^ 1];
					int cost = cmd == i ? 0 : c[i];
					if (nx >= 0 && ny >= 0 && nx < h && ny < w
							&& d[cur][from[2]] + cost < d[nx * w + ny][dir(from[2], i)]) {
						d[nx * w + ny][dir(from[2], i)] = d[cur][from[2]] + cost;
						pq.add(new int[] { nx, ny, dir(from[2], i), d[nx * w + ny][dir(from[2], i)] });
					}
				}
			}
			out.println(Math.min(d[h * w - 1][0], d[h * w - 1][1]));
		}
	}

	static int dir(int cur, int cmd) {
		return (cur + cmd) % 4;
	}

	public static void main(String[] args) throws Exception {
		ir = new InputReader(System.in);
		out = new PrintWriter(System.out);
		solve();
		out.flush();
	}

	static class InputReader {

		private InputStream in;
		private byte[] buffer = new byte[1024];
		private int curbuf;
		private int lenbuf;

		public InputReader(InputStream in) {
			this.in = in;
			this.curbuf = this.lenbuf = 0;
		}

		public boolean hasNextByte() {
			if (curbuf >= lenbuf) {
				curbuf = 0;
				try {
					lenbuf = in.read(buffer);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (lenbuf <= 0)
					return false;
			}
			return true;
		}

		private int readByte() {
			if (hasNextByte())
				return buffer[curbuf++];
			else
				return -1;
		}

		private boolean isSpaceChar(int c) {
			return !(c >= 33 && c <= 126);
		}

		private void skip() {
			while (hasNextByte() && isSpaceChar(buffer[curbuf]))
				curbuf++;
		}

		public boolean hasNext() {
			skip();
			return hasNextByte();
		}

		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while (!isSpaceChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		public int nextInt() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}

		public long nextLong() {
			if (!hasNext())
				throw new NoSuchElementException();
			int c = readByte();
			while (isSpaceChar(c))
				c = readByte();
			boolean minus = false;
			if (c == '-') {
				minus = true;
				c = readByte();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res = res * 10 + c - '0';
				c = readByte();
			} while (!isSpaceChar(c));
			return (minus) ? -res : res;
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public int[] nextIntArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}

		public long[] nextLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}

		public char[][] nextCharMap(int n, int m) {
			char[][] map = new char[n][m];
			for (int i = 0; i < n; i++)
				map[i] = next().toCharArray();
			return map;
		}
	}

	static void tr(Object... o) {
		if (debug)
			out.println(Arrays.deepToString(o));
	}
}

