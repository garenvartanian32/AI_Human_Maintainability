import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		FastReader in = new FastReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		long a = in.nextLong();
		long b = in.nextLong();
		long k = in.nextLong();
		for (int i = 1; i <= k; i++) {
			if (i % 2 != 0) {
				if (a % 2 != 0) {
					a -= 1;
					a = a / 2;
					b += a;
				} else {
					a = a / 2;
					b += a;
				}
			} else {
				if (b % 2 != 0) {
					b -= 1;
					b = b / 2;
					a += b;
				} else {
					b = b / 2;
					a += b;
				}
			}
		}
		out.println(a + " " + b);
		out.flush();
		out.close();

	}
	// 70376445703718

	static class FastReader {
		StringTokenizer st;
		BufferedReader br;

		public FastReader(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public FastReader(FileReader fileReader) {
			br = new BufferedReader(fileReader);
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public boolean ready() throws IOException {
			return br.ready();
		}
	}

}