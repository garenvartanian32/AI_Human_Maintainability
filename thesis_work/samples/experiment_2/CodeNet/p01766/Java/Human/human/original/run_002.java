import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
	void run() {
		MyScanner sc = new MyScanner();

		int n = sc.nextInt();
		int pf = sc.nextInt();
		int pa = sc.nextInt();
		int pt = sc.nextInt();
		int px = sc.nextInt();
		int py = sc.nextInt();

		double[] max = new double[2];
		int[] time = new int[2];
		max[0] = max[1] = 0;
		time[0] = time[1] = 1 << 20;
		for (int i = 1; i < n; i++) {
			int f = sc.nextInt();
			int a = sc.nextInt();
			int t = sc.nextInt();
			int x = sc.nextInt();
			int y = sc.nextInt();

			if (pt == t && pa != a) {
				double len = Math.hypot(Math.abs(px - x), Math.abs(py - y));
				if (max[t] < len) {
					max[t] = len;
					time[t] = f - pf;
				} else if (max[t] == len && time[t] > f - pf) {
					time[t] = f - pf;
				}
			}
			pf = f;
			pa = a;
			pt = t;
			px = x;
			py = y;
		}

		if (max[0] > 0) {
			System.out.println(max[0] + " " + 1. * time[0] / 60);
		} else {
			System.out.println(-1 + " " + -1);
		}
		if (max[1] > 0) {
			System.out.println(max[1] + " " + 1. * time[1] / 60);
		} else {
			System.out.println(-1 + " " + -1);
		}
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
		System.out.println("----------------------------" + '\n');
	}

	class MyScanner {
		int read() {
			try {
				return System.in.read();
			} catch (IOException e) {
				throw new InputMismatchException();
			}
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

		long nextLong() {
			return Long.parseLong(next());
		}

		long[] nextLongArray(int n) {
			long[] array = new long[n];
			for (int i = 0; i < n; i++)
				array[i] = nextLong();
			return array;
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