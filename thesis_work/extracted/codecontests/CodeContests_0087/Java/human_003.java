

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		new Solver().solve().end();
	}
}

class Solver {
	PrintWriter out;
	FastReader cd;
	@SuppressWarnings("unused")
	public static final int INF = 0x3f3f3f3f;
	@SuppressWarnings("unused")
	public static final double MINI = 1e-6, ATOM = 1e-8;

	Solver() {
		out = new PrintWriter(System.out, true);
		cd = new FastReader();
	}

	class Car {
		int price;
		int cap;

		public Car() {
			super();
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		public int getCap() {
			return cap;
		}

		public void setCap(int cap) {
			this.cap = cap;
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return super.equals(obj);
		}

		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		@Override
		public String toString() {
			return price + " " + cap;
		}

		@Override
		protected void finalize() throws Throwable {
			super.finalize();
		}

		public Car(int price, int cap) {
			this.price = price;
			this.cap = cap;
		}


	}

	static boolean valid(final long cap, int[] lens, long mostTime) {
		long time = 0;
		for (long len : lens) {
			long max = cap * 2;
			if (max < len) return false;
			else time += Math.max(len, len * 3 - cap);
			if (time > mostTime) return false;
		}
		return true;
	}

	Solver solve() {
		//             s   t   g
		// normal mode 1 : 2 : 1, fast mode 1 : 1 : 2
		//  cars   stations  length  the most time
		int n, k, s, t;
		n = cd.nextInt();
		k = cd.nextInt();
		s = cd.nextInt();
		t = cd.nextInt();
		Car[] cars = new Car[n];
		for (int i = 0; i < n; i++) {
			cars[i] = new Car(cd.nextInt(), cd.nextInt());
		}
		Arrays.sort(cars, Comparator.comparingInt(Car::getCap));
		int maxLen = -INF;
		int[] lens = new int[k + 1];
		int[] stations = cd.nextInts(k);
		Arrays.sort(stations);
		lens[0] = stations[0];
		for (int i = 1; i < k; i++) {
			lens[i] = stations[i] - stations[i - 1];
			maxLen = Math.max(lens[i], maxLen);
		}
		lens[k] = s - stations[k - 1];
		//
		if (s > t) {
			out.println(-1);
			return this;
		}
		maxLen = Math.max(lens[k], maxLen);
		int mostGas;
		int left = maxLen, right = maxLen * 2;
		while (left <= right) {
			int mid = (right + left) >>> 1;
			if (valid(mid, lens, t)) right = mid - 1;
			else left = mid + 1;
		}
		mostGas = left;
		int rightPos = Arrays.binarySearch(Arrays.stream(cars).mapToInt(Car::getCap).toArray(), mostGas);
		//
		int minRent = INF;
		for (
				int i = rightPos < 0 ? -rightPos - 1 : rightPos;
				i < cars.length;
				i++
		) {
			minRent = Math.min(minRent, cars[i].price);
		}
		out.println(minRent == INF ? -1 : minRent);
		return this;
	}

	void end() {
		out.close();
		cd.close();
	}

	@SuppressWarnings("unused")
	class FastReader {
		private BufferedReader in;
		private StringTokenizer tokenizer;

		FastReader() {
			in = new BufferedReader(new InputStreamReader(System.in), 23333);
			tokenizer = null;
		}

		String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(in.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		int[] nextInts(int len) {
			int[] a = new int[len];
			for (int i = 0; i < a.length; i++) {
				a[i] = cd.nextInt();
			}
			return a;
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		void close() {
			try {
				in.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

