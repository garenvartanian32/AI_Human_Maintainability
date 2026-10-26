
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class N708 {
	static PrintWriter out;
	static Scanner sc;
	static ArrayList<Integer>q,w;
	static ArrayList<Integer>adj[];
	static HashSet<Integer>primesH;
	static boolean prime[];
	//static ArrayList<Integer>a;
	static HashSet<Long>tmp;
	static boolean[]v;
	static int[]a,b,c,d;
	static int[]l,dp;
	static char[][]mp;
	static int A,B,n,m,h;
	public static void main(String[]args) throws IOException {
		sc=new Scanner(System.in);
		out=new PrintWriter(System.out);
		//A();
		//B();
	   // C();
		//C2();
		D();
	//	E();
		//E2();
		//minimum for subarrays of fixed length
		//F();
		out.close();
	}
	  private static void A() throws IOException {
		  int t=ni();
			while(t-->0) {
				int n=ni();
				a=nai(n);
				Arrays.sort(a);
				PriorityQueue<Integer>pq1=new PriorityQueue<Integer>(),pq2=new PriorityQueue<Integer>();
				int prv=-1;
				for(int x:a) {
					pq1.add(x);
				}
				ArrayList<Integer>ans=new ArrayList<Integer>();
				while(!pq1.isEmpty()) {
					
					while(!pq1.isEmpty()) {
						int c=pq1.poll();
						if(prv!=c) {
							ans.add(c);
							prv=c;
						}else {
							pq2.add(c);
						}
					}
					prv=-1;
					while(!pq2.isEmpty()) {
						int c=pq2.poll();
						if(prv!=c) {
							ans.add(c);
							prv=c;
						}else {
							pq1.add(c);
						}
					}
				}
				for(int x:ans) {
					out.print(x+" ");
				}
				out.println();
			}
		
	   }
		static void B() throws IOException {
			int t=ni();
			while(t-->0) {
				int n=ni(),m=ni();
				a=nai(n);
				for(int i=0;i<n;i++) {
					a[i]=a[i]%m;
				}
				Arrays.sort(a);
				int i=0,ans=n;
				while(i<n&&a[i]==0)i++;
				if(i>1)ans-=(i-1);
				int cur=-1;
				if(i<n)cur=a[i++];
				int occ1=1;
				for(;i<n;) {
					while(i<n&&a[i]==cur) {i++;
						occ1++;
					}
					int trgt=m-cur;
				
					int lo=i,hi=n-1;
					boolean f=false;
					while(lo<=hi) {
						int mid=(lo+hi)/2;
						if(a[mid]==trgt) {
							f=true;
							break;
						}
						if(a[mid]<trgt) {
							lo=mid+1;
						}else {
							hi=mid-1;
						}
					}
					if(f) {
						int occ2=1;
						int mid=(lo+hi)/2;
						int e=a[mid];
						for(int j=mid+1;j<n&&a[j]==e;j++)occ2++;
						for(int j=mid-1;j>=0&&a[j]==e;j--)occ2++;
						if(occ1==occ2) {
							ans-=(2*occ1-1);
						}else {
							ans-=(2*Math.min(occ1, occ2));
						}
					}else {
						if(m%cur==0&&m/cur==2) {
							ans-=(occ1-1);
						}
					}
					if(i<n)
					cur=a[i];
					occ1=0;
				}
				out.println(ans);
			}
		}
		
		static void C() throws IOException{
			 int t=ni();
			 while(t-->0) {
				 int n=ni(),k=ni();
				 if(n%2==1) {
					 int a=n/2;
					 int b=(n-a)/2;
					 out.println(a+" "+a+" "+1);
				 }else {
					 if((n/2)%2==0) {
						 out.println(n/2+" "+n/4+" "+n/4);
					 }else {
						 int a=(n-2)/2;
						 out.println(a+" "+a+" "+2);
					 }
				 }
			 }
			
		}
		static void C2() throws IOException{
			 int t=ni();
			 while(t-->0) {
				 int n=ni(),k=ni();
				 int diff=k-3;
				 n-=diff;
				 if(n%2==1) {
					 int a=n/2;
					 int b=(n-a)/2;
					 out.print(a+" "+a+" "+1);
				 }else {
					 if((n/2)%2==0) {
						 out.print(n/2+" "+n/4+" "+n/4);
					 }else {
						 int a=(n-2)/2;
						 out.print(a+" "+a+" "+2);
					 }
				 }
				 for(int i=0;i<diff;i++) {
					 out.print(" "+1);
				 }
				 out.println();
			 }
			
		}



	static void D() throws IOException {
		int t=ni();
		while(t-->0) {
			int n=ni();
			long[]tag=nal(n);
			long[]s=nal(n);
			long[]dp=new long[n];
			for(int i=0;i<n;i++) {
				for(int j=i-1;j>=0;j--) {
					if(tag[i]==tag[j])continue;
					long p=Math.abs(s[i]-s[j]);
					long ii=dp[i],jj=dp[j];
					dp[i]=Math.max(dp[i], jj+p);
					dp[j]=Math.max(dp[j], ii+p);
				}
			}
			long mx=0;
			for(int i=0;i<n;i++) {
				mx=Math.max(mx, dp[i]);
			}
			out.println(mx);
		}
		
	}
	static void E() throws IOException {
		int t=ni();
//		HashSet<Long>sqs=new HashSet<Long>();
//		long mx=(long)1e14;
//		for(long i=1;i*i<=mx;i++) {
//			sqs.add(i*i*1l);
//		}
		while(t-->0) {
			int n=ni(),k=ni();
			long[]l=nal(n);
			//out.println(n);
			int needed=get((int)l[0]);
			HashSet<Integer>tr=new HashSet();
			tr.add(needed);
			int sets=1;
			for(int i=1;i<n;i++) {
				int n2=get((int)l[i]);
				if(tr.contains(n2)) {
					//sets-=(tr.size()-1);
					tr.clear();
					tr.add(n2);
					sets++;
				}else {
					tr.add(n2);
				}
			}
			out.println(sets);
		}
	}

	private static int get(int o) {
		int needed=1;
		int cnt=0;
		while(o%2==0) {
			cnt++;
			o/=2;
		}
		if(cnt%2==1)needed*=2;
		for(int i=3;i*i<=o;i+=2) {
			if(o%i==0) {
				cnt=0;
				while(o%i==0) {
					cnt++;
					o/=i;
				}
				if(cnt%2==1)needed*=i;
			}
		}
		if(o>1) {
			needed*=o;
		}
		return needed;
	}
	static void E2() throws IOException {
		int t=ni();
//		HashSet<Long>sqs=new HashSet<Long>();
//		long mx=(long)1e14;
//		for(long i=1;i*i<=mx;i++) {
//			sqs.add(i*i*1l);
//		}
		while(t-->0) {
			int n=ni(),k=ni();
			long[]l=nal(n);
			//out.println(n);
			int needed=get((int)l[0]);
			HashSet<Integer>tr=new HashSet();
			tr.add(needed);
			int sets=1;
			ArrayList<Integer>len=new ArrayList<Integer>();
			PriorityQueue<Integer>pq=new PriorityQueue<Integer>();
			for(int i=1;i<n;i++) {
				int n2=get((int)l[i]);
				if(tr.contains(n2)) {
					//sets-=(tr.size()-1);
					pq.add(tr.size());
					tr.clear();
					tr.add(n2);
					sets++;
				}else {
					tr.add(n2);
				}
			}
			int sum=0;
			while(!pq.isEmpty()) {
				if(sum+pq.peek()>k)break;
				sum+=pq.poll();
				sets-=2;
			}out.println(sets);
		}
	}

	private static void paint(char[][] mp, int i) {
		for(int j=0;j<mp.length;j++) {
			mp[j][i]='X';
		}
		if(i>1) {
			for(int a=0;a<mp.length;a++) {
				if(mp[a][i-2]=='X') {
					mp[a][i-1]='X';
					break;
				}
				if(a==mp.length-1) {
					mp[a][i-1]='X';
				}
			}
		}
		if(i<mp[0].length-2) {
			for(int a=0;a<mp.length;a++) {
				if(mp[a][i+2]=='X') {
					mp[a][i+1]='X';
					break;
				}
				if(a==mp.length-1) {
					mp[a][i+1]='X';
				}
			}
		}
		
	}
	static int ni() throws IOException {
		return sc.nextInt();
	}
	static double nd() throws IOException {
		return sc.nextDouble();
	}
	static long nl() throws IOException {
		return  sc.nextLong();
	}
	static String ns() throws IOException {
		return sc.next();
	}
	static int[] nai(int n) throws IOException {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = sc.nextInt();
		return a;
	}
	static long[] nal(int n) throws IOException {
		long[] a = new long[n];
		for (int i = 0; i < n; i++)
			a[i] = sc.nextLong();
		return a;
	}
	static int[][] nmi(int n,int m) throws IOException{
		int[][]a=new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				a[i][j]=sc.nextInt();
			}
		}
		return a;
	}

	static long[][] nml(int n,int m) throws IOException{
		long[][]a=new long[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				a[i][j]=sc.nextLong();
			}
		}
		return a;
	}
	static void o(String x) {
		out.print(x);
	}
	static void ol(String x) {
		out.println(x);
	}
	static void ol(int x) {
		out.println(x);
	}
	static void disp1(int []a) {
		for(int i=0;i<a.length;i++) {
			out.print(a[i]+" ");
		}
		out.println();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		public boolean hasNext() {return st.hasMoreTokens();}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public double nextDouble() throws IOException {return Double.parseDouble(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready(); }
		

	}
}
