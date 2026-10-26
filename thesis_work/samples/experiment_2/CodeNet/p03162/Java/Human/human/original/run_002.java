

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		MyScanner sc = new MyScanner();
		PrintWriter pr = new PrintWriter(System.out);
		int n = sc.nextInt();
		int ar[][] = new int[n][3];
		int dp[][] = new int[n][3];

		for (int i = 0; i < n; i++) {

			ar[i][0] = sc.nextInt();
			ar[i][1] = sc.nextInt();
			ar[i][2] = sc.nextInt();

			for (int j = 0; j < 3; j++)
				dp[i][j] = -1;
		}

		int a = func(0, 0, dp, ar);
		int b = func(0, 1, dp, ar);
		int c = func(0, 2, dp, ar);
		pr.println(Math.max(a, Math.max(b, c)));
		pr.close();
	}

	private static int func(int i, int j, int dp[][], int[][] ar) {

		if (i == ar.length - 1)
			return dp[i][j] = ar[i][j];

		if (dp[i][j] != -1)
			return dp[i][j];

		int max = Integer.MIN_VALUE;

		for (int x = 0; x < 3; x++) {

			if (x != j)
				max = Math.max(max, (ar[i][j] + func(i + 1, x, dp, ar)));
		}
		return dp[i][j] = max;
	}

	public static class MyScanner {
		BufferedReader br;
		StringTokenizer st;

		public MyScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}