import java.io.*;
import java.util.*;
import java.math.*;
import java.util.concurrent.*;

class Main
{
    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static FastScanner sc=new FastScanner(br);
    static PrintWriter out=new PrintWriter(System.out);
	static Random rnd=new Random();
	static int maxn=(int)(55);
	static long mod=(long)(1e9+7);
	static boolean[][] v;
	
	static void build()
	{
		v=new boolean[10][10];
		
		int[] a=new int[9],b=new int[9];
		
		for(int i=0;i<3;i++)
		{
			a[i]=b[i]=i;
		}
		
		a[3]=a[4]=0;a[5]=a[6]=1;a[7]=a[8]=2;
		
		b[5]=b[7]=0;b[3]=b[8]=1;b[4]=b[6]=2;
		
		for(int i=0;i<9;i++)
		{
			for(int j=0;j<9;j++)	
			{
				if(a[i]!=a[j] && b[i]!=b[j])
				{
					v[i][j]=true;
				}
			}
		}
	}
	
    public static void main(String args[]) throws Exception
    {
		build();int n=sc.nextInt();char[][] a=new char[2][n];
		
		for(int i=0;i<2;i++)
		{
			a[i]=sc.next().toCharArray();
		}
		
		int[] size=new int[maxn];int ctr=0;
		
		for(int i=0;i<n;)
		{
			if(a[0][i]==a[1][i])
			{	
				size[ctr++]=1;
				
				i++;
			}
			
			else
			{
				size[ctr++]=2;
				
				i+=2;
			}
		}
		
		long[][] dp=new long[9][ctr];
		
		for(int i=0;i<9;i++)
		{
			if(size[0]==1 && i<3)
			{
				dp[i][0]=1;
			}
			
			else if(size[0]==2 && i>=3)
			{
				dp[i][0]=1;
			}
		}
		
		int[] s=new int[ctr],e=new int[ctr];
		
		for(int i=0;i<ctr;i++)
		{
			if(size[i]==1)
			{
				s[i]=0;e[i]=3;
			}
			else
			{
				s[i]=3;e[i]=9;
			}
		}
		
		/*
		
		for(int i=0;i<9;i++)
		{
			out.println(dp[i][0]);
		}
		
		*/
		
		for(int i=1;i<ctr;i++)
		{
			for(int k=s[i];k<e[i];k++)	
			{
				for(int j=s[i-1];j<e[i-1];j++)
				{
					if(v[j][k])
					{
						dp[k][i]=(dp[k][i]+dp[j][i-1])%mod;
					}
				}		
			}
		}
		
		long res=0;
		
		for(int i=0;i<9;i++)
		{
			res=(res+dp[i][ctr-1])%mod;
		}
		
		out.println(res);out.close();
    }
}
class FastScanner
{
    BufferedReader in;
    StringTokenizer st;

    public FastScanner(BufferedReader in) {
        this.in = in;
    }
	
    public String nextToken() throws Exception {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine());
        }
        return st.nextToken();
    }
	
	public String next() throws Exception {
		return nextToken().toString();
	}
	
    public int nextInt() throws Exception {
        return Integer.parseInt(nextToken());
    }

    public long nextLong() throws Exception {
        return Long.parseLong(nextToken());
    }

    public double nextDouble() throws Exception {
        return Double.parseDouble(nextToken());
    }
}