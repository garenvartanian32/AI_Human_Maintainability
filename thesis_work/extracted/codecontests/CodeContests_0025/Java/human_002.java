import java.io.*;
import java.util.*;

public class woodcutters 
{
	
	public static void main(String args[])throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int n = ip(br.readLine());
		int [] x = new int [n+1];
		int [] h = new int [n+1];
		
		for (int i = 1; i<=n; i++)
		{
			String [] tokens = br.readLine().split(" ");
			x[i] = ip(tokens[0]);
			h[i] = ip(tokens[1]);
		}
		
		int [][] dp = new int [n+1][3];
		
		if (n <= 2)
		{out.println(n);out.close(); out.flush(); return;}
		
		// 0 for stay and 1 for left and 2 for right
		
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		if (x[1]+h[1] < x[2])
			dp[1][2] = 1;
	
		for (int i = 2; i <= n; i++)
		{
			// if current stays up
			dp[i][0] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
			// if current falls right 
			
			// if current falls left
			int a = 0;
			int b = 0;
			int c = 0;
			if (x[i]-h[i] > x[i-1])
			{
				c = dp[i-1][0] + 1;
				b = dp[i-1][1] + 1;
			}
			if (x[i]-h[i] > x[i-1]+h[i-1])
				a = dp[i-1][2] + 1;
			dp[i][1] = Math.max(a, Math.max(b,  c));
			
			if (i == n)
				{dp[i][2] =  Math.max(dp[i-1][0]+1, Math.max(dp[i-1][1]+1, dp[i-1][2]+1)); break;}
			if (x[i]+h[i] < x[i+1])
				dp[i][2] = Math.max(dp[i-1][0]+1, Math.max(dp[i-1][1]+1, dp[i-1][2]+1));
			
		}
		
		out.println(Math.max(dp[n][1], dp[n][2]));
		out.close();
		out.flush();
	}
	

	static int ip(String s)
	{
		return Integer.parseInt(s);
	}
}
