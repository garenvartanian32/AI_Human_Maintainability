import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Math.min;
import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

	static final int MOD = 998244353;

	static int add(int a, int b) {
		int res = a + b;
		return res >= MOD ? res - MOD : res;
	}

	static int sub(int a, int b) {
		int res = a - b;
		return res < 0 ? res + MOD : res;
	}

	static void solve() throws Exception {
		String s = scanString();
		int k = scanInt();
		int n = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				++n;
			}
		}
		int gs[] = new int[n + 1];
		for (int i = 0, j = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				gs[j + 1] = gs[j];
				++j;
			} else {
				++gs[j];
			}
		}
		int m = gs[n];
		if (k > m) {
			k = m;
		}
		int dyn1[][][] = new int[n + 1][m + 1][k + 1];
		int dyn2[][][] = new int[n + 1][m + 1][k + 1];
		if (k > 0 && gs[0] < m) {
			dyn1[0][gs[0] + 1][1] = 1;
		}
		dyn2[0][gs[0]][0] = 1;
		for (int i = 0; i <= n; i++) {
			for (int j = m; j > gs[i]; j--) {
				for (int j2 = 0; j2 <= k; j2++) {
					dyn2[i][j - 1][j2] = add(dyn2[i][j - 1][j2], dyn2[i][j][j2]);
				}
			}
			for (int j = 0; j < m; j++) {
				for (int j2 = 0; j2 < k; j2++) {
					dyn1[i][j + 1][j2 + 1] = add(dyn1[i][j + 1][j2 + 1], dyn1[i][j][j2]);
				}
			}
			if (i < n) {
				for (int j = gs[i]; j <= m; j++) {
					int nj = j + gs[i + 1] - gs[i];
					for (int j2 = 0; j2 <= k; j2++) {
						int v = add(dyn1[i][j][j2], dyn2[i][j][j2]);
						if (j2 < k && nj < m) {
							dyn1[i + 1][nj + 1][j2 + 1] = add(dyn1[i + 1][nj + 1][j2 + 1], v);
						}
						dyn2[i + 1][min(nj, m)][j2] = add(dyn2[i + 1][min(nj, m)][j2], v);
						if (j > 0) {
							dyn2[i + 1][j - 1][j2] = sub(dyn2[i + 1][j - 1][j2], v);
						}
					}
				}
			}
		}
		int ans = 0;
		for (int i = 0; i <= k; i++) {
			ans = add(ans, add(dyn1[n][m][i], dyn2[n][m][i]));
		}
		out.print(ans);
	}

	static int scanInt() throws IOException {
		return parseInt(scanString());
	}

	static long scanLong() throws IOException {
		return parseLong(scanString());
	}

	static String scanString() throws IOException {
		while (tok == null || !tok.hasMoreTokens()) {
			tok = new StringTokenizer(in.readLine());
		}
		return tok.nextToken();
	}

	static BufferedReader in;
	static PrintWriter out;
	static StringTokenizer tok;

	public static void main(String[] args) {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(System.out);
			solve();
			in.close();
			out.close();
		} catch (Throwable e) {
			e.printStackTrace();
			exit(1);
		}
	}
}