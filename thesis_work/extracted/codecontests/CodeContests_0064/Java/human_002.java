import java.util.*;
import java.io.*;

public class cf1497_Div2_D {
	public static void main(String args[]) throws IOException {
		FastScanner in = new FastScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = in.nextInt();
		// dp[n][IQ] 
		for ( ; t > 0; t--) {
			int n = in.nextInt();
			int[] tags = new int[n];
			long[] scores = new long[n];
			for (int i = 0; i < n; i++)
				tags[i] = in.nextInt();
			for (int i = 0; i < n; i++)
				scores[i] = in.nextInt();
			long[] dp = new long[n];
			for (int j = 1; j < n; j++) {
				for (int i = j - 1; i >= 0; i--) {
					if (tags[i] == tags[j]) continue;
					long dpi = dp[i], dpj = dp[j], score = Math.abs(scores[i] - scores[j]);
					dp[i] = Math.max(dp[i], dpj + score);
					dp[j] = Math.max(dp[j], dpi + score);
				}
			}
			long ans = 0;
			for (int i = 0; i < n; i++)
				ans = Math.max(ans, dp[i]);
			
			out.println(ans);
		}
		out.close();
	}
	
	static class FastScanner {
	    BufferedReader br;
	    StringTokenizer st;
		
	    public FastScanner(InputStream i) {
	        br = new BufferedReader(new InputStreamReader(i));
	        st = new StringTokenizer("");
	    }
				
	    public String next() throws IOException {
	        if(st.hasMoreTokens())
	            return st.nextToken();
	        else
	            st = new StringTokenizer(br.readLine());
	        return next();
	    }

	    public int nextInt() throws IOException {
	        return Integer.parseInt(next());
	    }
	    //#
	    public long nextLong() throws IOException {
	        return Long.parseLong(next());
	    }
	    public double nextDouble() throws IOException {
	        return Double.parseDouble(next());
	    }
	    //$
	}
}
