
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class Main {
	InputStream is;

	int __t__ = 1;
	int __f__ = 0;
	int __FILE_DEBUG_FLAG__ = __f__;
	String __DEBUG_FILE_NAME__ = "src/T";

	FastScanner in;
	PrintWriter out;
	
	double EPS = 1e-3;
	boolean isZero(double a) {
		return a - EPS <= 0 && a + EPS >= 0;
	}
	
	double calc(int x, int rem1, int rem2, int upper) {
		double res = 1.0;
		for (int i = 0; i <= upper; i++) {
			if (i == rem1 || i == rem2) continue;
			res *= (x - i);
		}
		return res;
	}
	
	public void solve() {
		main : 
		while (true) {
			int d = in.nextInt();
			if (d == 0) break;
			double[] v = in.nextDoubleArray(d+3);
			
			double[] xx = new double[d+3];
			for (int next = 0; next <= d + 1; next++) {
				double res1 = 0, res2 = 0;
				for (int x = 0; x <= d + 1; x++) {
					if (x == next) continue;
					double sum = 0;
					for (int rem = 0; rem <= d + 1; rem++) {
						if (rem == next) continue;
						sum += calc(x, rem, next, d + 1);
					}
					xx[x] = v[x] / sum;
//					System.out.println(x + " " + sum + " " + v[x] + " " + xx[x]);
				}
				
				for (int rem = 0; rem <= d + 1; rem++) {
					if (rem == next) continue;
					res1 += xx[rem] * calc(next, rem, next, d + 1);
					res2 += xx[rem] * calc(d + 2, rem, next, d + 1);
				}
				
//				System.out.println(next + " : " + res1 + " " + v[next] + ", " + (d+2) + " : " + res2 + " " + v[d+2]);
				if (isZero(res1 - v[next]) && !isZero(res2 - v[d+2])) {
					System.out.println(d+2);
					continue main;
				} else if (!isZero(res1 - v[next]) && isZero(res2 - v[d+2])){
					System.out.println(next);
					continue main;
				}				
			}				
			System.out.println("dont find");
		}
	}

	public void run() {
		if (__FILE_DEBUG_FLAG__ == __t__) {
			try {
				is = new FileInputStream(__DEBUG_FILE_NAME__);
			} catch (FileNotFoundException e) {
				// TODO ????????????????????? catch ????????????
				e.printStackTrace();
			}
			System.out.println("FILE_INPUT!");
		} else {
			is = System.in;
		}
		in = new FastScanner(is);
		out = new PrintWriter(System.out);

		solve();
	}

	public static void main(String[] args) {
		new Main().run();
	}

	public void mapDebug(int[][] a) {
		System.out.println("--------map display---------");

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.printf("%3d ", a[i][j]);
			}
			System.out.println();
		}

		System.out.println("----------------------------");
		System.out.println();
	}

	public void debug(Object... obj) {
		System.out.println(Arrays.deepToString(obj));
	}

	class FastScanner {
		private InputStream stream;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public FastScanner(InputStream stream) {
			this.stream = stream;
			//stream = new FileInputStream(new File("dec.in"));

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
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		boolean isEndline(int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		int[] nextIntArray(int n) {
			int[] array = new int[n];
			for (int i = 0; i < n; i++)
				array[i] = nextInt();

			return array;
		}

		int[][] nextIntMap(int n, int m) {
			int[][] map = new int[n][m];
			for (int i = 0; i < n; i++) {
				map[i] = in.nextIntArray(m);
			}
			return map;
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		long[] nextLongArray(int n) {
			long[] array = new long[n];
			for (int i = 0; i < n; i++)
				array[i] = nextLong();

			return array;
		}

		long[][] nextLongMap(int n, int m) {
			long[][] map = new long[n][m];
			for (int i = 0; i < n; i++) {
				map[i] = in.nextLongArray(m);
			}
			return map;
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		double[] nextDoubleArray(int n) {
			double[] array = new double[n];
			for (int i = 0; i < n; i++)
				array[i] = nextDouble();

			return array;
		}

		double[][] nextDoubleMap(int n, int m) {
			double[][] map = new double[n][m];
			for (int i = 0; i < n; i++) {
				map[i] = in.nextDoubleArray(m);
			}
			return map;
		}

		String next() {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		String[] nextStringArray(int n) {
			String[] array = new String[n];
			for (int i = 0; i < n; i++)
				array[i] = next();

			return array;
		}

		String nextLine() {
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