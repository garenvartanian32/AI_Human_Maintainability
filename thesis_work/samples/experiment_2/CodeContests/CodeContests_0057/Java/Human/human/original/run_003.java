import java.util.*;

import java.io.*;
public class EdE {
	static long[] mods = {1000000007, 998244353, 1000000009};
	static long mod = mods[0];
	public static MyScanner sc;
    public static PrintWriter out;
    static int[][] grid;
    static int n;
	public static void main(String[] omkar) throws Exception{
		// TODO Auto-generated method stub
 		sc = new MyScanner();
 		out = new PrintWriter(System.out);
 		n = sc.nextInt();
 		grid = new int[n][n];
 		for(int i=0;i<n;i++){
 			String input = sc.next();
 			
 			for(int j=0;j<n/4;j++){
 				int bin = Integer.parseInt(input.substring(j, j+1), 16);
 				grid[i][4*j+3] = bin%2;
 				bin/=2;
 				grid[i][4*j+2] = bin%2;
 				bin/=2;
 				grid[i][4*j+1] = bin%2;
 				bin/=2;
 				grid[i][4*j] = bin%2;
 			}
 		}
 		ArrayList<Integer> divisors = new ArrayList<>();
 		int max = 1;
 		for(int j = 1;j*j<=n;j++){
 			if (n%j == 0){
 				divisors.add(j);
 				divisors.add(n/j);
 			}
 		}
 		Collections.sort(divisors);
 		ArrayList<Integer> nowork = new ArrayList<>();
 		for(int j = 0;j<divisors.size();j++){
 			if (divisors.get(j)%max != 0)
 				continue;
 			boolean found = false;
 			for(int s=0;s<nowork.size();s++){
 				if (divisors.get(j)%nowork.get(s) == 0){
 					found = true;
 					break;
 				}
 			}
 			if (found)
 				continue;
 			if (check(divisors.get(j)))
 				max = divisors.get(j);	
 			else
 				nowork.add(divisors.get(j));
 		}
 		out.println(max);
 		
 		
	 		
 		out.close();	
 	}
	public static int gcd(int a, int b){ 
	      if (b == 0) 
	        return a; 
	      return gcd(b, a % b);  
	    }
	public static boolean check(int k){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if (grid[i][j] != grid[i-i%k][j-j%k]){
					return false;
				}
			}
		}
		return true;
	}
	
	public static void sort(int[] array){
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for (int i : array)
			copy.add(i);
		Collections.sort(copy);
		for(int i = 0;i<array.length;i++)
			array[i] = copy.get(i);
	}
	static String[] readArrayString(int n){
		String[] array = new String[n];
		for(int j =0 ;j<n;j++)
			array[j] = sc.next();
		return array;
	}
	static int[] readArrayInt(int n){
    	int[] array = new int[n];
    	for(int j = 0;j<n;j++)
    		array[j] = sc.nextInt();
    	return array;
    }
	static int[] readArrayInt1(int n){
		int[] array = new int[n+1];
		for(int j = 1;j<=n;j++){
			array[j] = sc.nextInt();
		}
		return array;
	}
	static long[] readArrayLong(int n){
		long[] array = new long[n];
		for(int j =0 ;j<n;j++)
			array[j] = sc.nextLong();
		return array;
	}
	static double[] readArrayDouble(int n){
		double[] array = new double[n];
		for(int j =0 ;j<n;j++)
			array[j] = sc.nextDouble();
		return array;
	}
	static int minIndex(int[] array){
		int minValue = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int j = 0;j<array.length;j++){
			if (array[j] < minValue){
				minValue = array[j];
				minIndex = j;
			}
		}
		return minIndex;
	}
	static int minIndex(long[] array){
		long minValue = Long.MAX_VALUE;
		int minIndex = -1;
		for(int j = 0;j<array.length;j++){
			if (array[j] < minValue){
				minValue = array[j];
				minIndex = j;
			}
		}
		return minIndex;
	}
	static int minIndex(double[] array){
		double minValue = Double.MAX_VALUE;
		int minIndex = -1;
		for(int j = 0;j<array.length;j++){
			if (array[j] < minValue){
				minValue = array[j];
				minIndex = j;
			}
		}
		return minIndex;
	}
	static long power(long x, long y){
		if (y == 0)
			return 1;
		if (y%2 == 1)
			return (x*power(x, y-1))%mod;
		return power((x*x)%mod, y/2)%mod;
	}
	static void verdict(boolean a){
        out.println(a ? "YES" : "NO");
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
                } 
                catch (IOException e) {
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
            try{
                str = br.readLine();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
        
    }	
}

//StringJoiner sj = new StringJoiner(" "); 
//sj.add(strings)
//sj.toString() gives string of those stuff w spaces or whatever that sequence is