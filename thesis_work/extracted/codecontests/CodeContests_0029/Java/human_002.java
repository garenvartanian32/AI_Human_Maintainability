import java.util.*;
import java.io.*;

public class Main {
	public static void main(String args[]) {new Main().run();}

	FastReader in = new FastReader();
	PrintWriter out = new PrintWriter(System.out);
	void run(){
		work();
		out.flush();
	}
	long mod=998244353;
	long gcd(long a,long b) {
		return b==0?a:gcd(b,a%b);
	}
	void work() {
		int n=in.nextInt();
		int[][] A=new int[n][2];
		for(int i=0;i<n;i++)A[i][0]=in.nextInt();
		for(int i=0;i<n;i++)A[i][1]=in.nextInt();
		int sum=0;
		for(int i=0;i<n;i++)sum+=A[i][0];
		int c=0;
		Arrays.sort(A,new Comparator<int[]>() {
			public int compare(int[] a1,int[] a2) {
				return a2[1]-a1[1];
			}
		});
		for(int cur=0;;) {
			cur+=A[c++][1];
			if(cur>=sum)break;
		}
		HashMap<Integer,Integer>[] map=(HashMap<Integer,Integer>[])new HashMap[n+1];
		for(int i=0;i<=n;i++) {
			map[i]=new HashMap<>();
		}
		long ret=0;
		map[0].put(0,0);
		for(int i=0;i<n;i++) {
			int a=A[i][0];
			int b=A[i][1];
			for(int j=i;j>=0;j--) {
				for(int k:map[j].keySet()) {
					int na=map[j].get(k)+a;
					int nb=k+b;		
					map[j+1].put(nb,Math.max(na, map[j+1].getOrDefault(nb,0)));
					if(j+1==c&&nb>=sum) {
						ret=Math.max(ret, na);
					}
				}
			}
		}
		out.println(c+" "+(sum-ret));
	}
}	



class FastReader
{
	BufferedReader br;
	StringTokenizer st;

	public FastReader()
	{
		br=new BufferedReader(new InputStreamReader(System.in));
	}

	public String next() 
	{
		if(st==null || !st.hasMoreElements())
		{
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	public int nextInt() 
	{
		return Integer.parseInt(next());
	}

	public long nextLong()
	{
		return Long.parseLong(next());
	}
}