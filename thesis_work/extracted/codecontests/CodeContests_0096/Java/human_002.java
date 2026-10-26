import java.util.*;
import java.io.*;

public class Main {
	static int mod = (int) 1e9 + 7;
	static int DX[] = { -1, 0, 1, 0 }, DY[] = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		FastScanner fs = new FastScanner(System.in);
		String n = fs.next();
		int k = fs.nextInt();
		int len = n.length();
		
		long dp[][][] = new long[len+1][k+1][2]; //最後は未満フラグ（1なら未満）
		dp[0][0][0] = 1;
		for(int i=1;i<=len;i++) {
			int now = n.charAt(i-1)-'0';
			if(now == 0) {
				for(int j=0;j<=k;j++) {
					//0を選ぶ場合
					//未満じゃない-未満じゃない
					dp[i][j][0] += dp[i-1][j][0];
					//未満->未満
					dp[i][j][1] += dp[i-1][j][1];
					if(j+1>k)continue;
					//0以外を選ぶ場合
					//未満->未満
					dp[i][j+1][1] += dp[i-1][j][1] * 9;
				}
			}
			else {
				for(int j=0;j<=k;j++) {
					//0を選ぶ場合
					//未満じゃない-未満
					dp[i][j][1] += dp[i-1][j][0];
					//未満->未満
					dp[i][j][1] += dp[i-1][j][1];
					
					if(j+1>k)continue;
					//0以外を選ぶ場合
					//未満じゃない->未満じゃない
					dp[i][j+1][0] += dp[i-1][j][0];
					//未満じゃない→未満
					dp[i][j+1][1] += dp[i-1][j][0] * (now-1);//0以外の好きな数
					//未満->未満
					dp[i][j+1][1] += dp[i-1][j][1] * 9;
				}
			}
			
			
		}
		long ans = dp[len][k][0] + dp[len][k][1];
		System.out.println(ans);
	}
}

//高速なScanner
class FastScanner {
	private BufferedReader reader = null;
	private StringTokenizer tokenizer = null;

	public FastScanner(InputStream in) {
		reader = new BufferedReader(new InputStreamReader(in));
		tokenizer = null;
	}

	public String next() {
		if (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public String nextLine() {
		if (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				return reader.readLine();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken("\n");
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public int nextInt() {
		return Integer.parseInt(next());
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
}