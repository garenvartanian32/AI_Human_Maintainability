import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

	private void solve() throws IOException {
		int n = ni();
		int c = ni();
		int d[][] = na(c);
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				a[i][j] = ni() - 1;

		}
		int cost[][] = new int[3][c];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				for (int k = 0; k < c; k++)
					cost[(i + j) % 3][k] += d[a[i][j]][k];
		int ans = Integer.MAX_VALUE;
		for (int c1 = 0; c1 < c; c1++)
			for (int c2 = 0; c2 < c; c2++) {
				if (c2 == c1)
					continue;
				for (int c3 = 0; c3 < c; c3++) {
					if (c2 == c3 || c1 == c3)
						continue;
					ans = Math.min(ans, cost[0][c1] + cost[1][c2] + cost[2][c3]);
				}
			}

		out.println(ans);
		out.close();
	}

	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer tok;

	private int[][] na(int n) throws IOException {
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				a[i][j] = ni();

		}
		return a;
	}

	String ns() throws IOException {
		while (!tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine(), " ");
		}
		return tok.nextToken();
	}

	int ni() throws IOException {
		return Integer.parseInt(ns());
	}

	long nl() throws IOException {
		return Long.parseLong(ns());
	}

	double nd() throws IOException {
		return Double.parseDouble(ns());
	}

	String[] nsa(int n) throws IOException {
		String[] res = new String[n];
		for (int i = 0; i < n; i++) {
			res[i] = ns();
		}
		return res;
	}

	int[] nia(int n) throws IOException {
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = ni();
		}
		return res;
	}

	long[] nla(int n) throws IOException {
		long[] res = new long[n];
		for (int i = 0; i < n; i++) {
			res[i] = nl();
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		tok = new StringTokenizer("");
		Main main = new Main();
		main.solve();
		out.close();
	}
}