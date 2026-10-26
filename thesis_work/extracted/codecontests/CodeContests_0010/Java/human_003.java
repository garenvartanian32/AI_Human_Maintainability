import java.io.*;
import java.util.*;

/*
Shortcut Commands

makeali  : Make ArrayList of Integer
makesti : Make Stack of Integer
makeqi : Make Queue of Integer
makepqi : Make PriorityQueue of Integer

makestc : Make Stack of Character

mapii : Make Map<Integer,Integer>
mappi : Make Map<Pair,Integer>

print
println
printf

*/

public class A{

	public static void solve()throws IOException{
       int n = sc.nextInt();
       int[] arr = sc.readArray(n);

        if(arr[0]+arr[1]<=arr[n-1])
            System.out.print("1 2 "+n);
        else
            System.out.print(-1);
	}

	public static void main(String args[])throws IOException{
		int t = sc.nextInt();
		for(int i=1;i<=t;i++){
			solve();
			System.out.println();
		}
    }
    
    public static void sort(int[] arr){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int n =arr.length;
        for(int i=0;i<n;i++){
            list.add(arr[i]);
        }
        Collections.sort(list);
        for(int i=0;i<n;i++){
            arr[i] = list.get(i);
        }
    }

    static class Graph{
        int n;
        ArrayList<Integer>[] g;

        Graph(int n){
            this.n = n;
            this.g = new ArrayList[n];
            for(int i=0;i<n;i++){
                this.g[i] = new ArrayList<Integer>();
            }
        }

        void add(int a,int b){
            this.g[a].add(b);
        }
    }

    static class Pair{

        // Implementing equals() and hashCode()
        // Map<Pair, V> map = //...

        private final int x;
        private final int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

	static FastScanner sc = new FastScanner();

	static class FastScanner {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st=new StringTokenizer("");
			String next() {
				while (!st.hasMoreTokens())
					try {
						st=new StringTokenizer(br.readLine());
					} catch (IOException e) {
						e.printStackTrace();
					}
				return st.nextToken();
			}
			
			int nextInt() {
				return Integer.parseInt(next());
			}
			int[] readArray(int n) {
				int[] a=new int[n];
				for (int i=0; i<n; i++) a[i]=nextInt();
				return a;
			}
			long nextLong() {
				return Long.parseLong(next());
			}
			public long[] readLongArray(int n) {
				long[] a=new long[n];
				for (int i=0; i<n; i++) a[i]=nextLong();
				return a;
			}
	}
}