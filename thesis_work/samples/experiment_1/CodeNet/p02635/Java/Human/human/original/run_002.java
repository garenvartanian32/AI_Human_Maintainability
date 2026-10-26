import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static final long MOD = 998244353;
	static int[] zeroes;
	
	public static void main(String[] args) throws IOException {
		MyScanner sc = new MyScanner();
		PrintWriter out = new PrintWriter(System.out);
		char[] vals = sc.next().toCharArray();
		int K = sc.nextInt();
		int tot = 0, z = 0;
		zeroes = new int[vals.length];
		for (int i = 0; i < vals.length; i++) {
			if (vals[i] == '1') {
				zeroes[tot++] = z;
			} else {
				z++;
			}
		}
		zeroes = Arrays.copyOf(zeroes, tot);
		if (K > zeroes.length) {
			K = zeroes.length;
		}
		int[] got = new int[z + 1];
		for (int zero : zeroes) {
			got[zero]++;
		}
		long[][][] table = new long[tot + 1][z + 2][K + 1];
		for (int i = 0; i <= z + 1; i++) {
			for (int j = 0; j <= K ; j++) {
				table[tot][i][j] = 1;
			}
		}
		for (int pos = tot - 1; pos >= 0; pos--) {
			for (int from = z; from >= 0 ; from--) {
				for (int rem = K; rem >= 0; rem--) {
					table[pos][from][rem] = table[pos][from + 1][rem];
					if (from <= zeroes[pos]) {
						for (int cant = 1; pos + cant <= tot && cant - got[from] <= rem; cant++) {
							int nRem = rem - Math.max(cant - got[from], 0);
							if (nRem >= 0) {
								table[pos][from][rem] += table[pos + cant][from + 1][nRem];
							}
						}
						table[pos][from][rem] %= MOD;
					}
				}
			}
		}
		
		out.println(table[0][0][K]);
		
		out.flush();
	}
	
	static class MyScanner {
		private BufferedReader br;
		private StringTokenizer tokenizer;
		
		public MyScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}
		
		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}
		
		public int nextInt() {
			return Integer.parseInt(next());
		}
		
		public long nextLong() {
			return Long.parseLong(next());
		}
	}
}
