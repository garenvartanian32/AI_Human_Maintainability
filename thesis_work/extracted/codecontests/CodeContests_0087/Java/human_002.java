/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		MyScanner sc = new MyScanner();
		int n = sc.nextInt();
		int k = sc.nextInt();
		int s = sc.nextInt();
		int t = sc.nextInt();
		
		int[] c = new int[n];
		int[] v = new int[n];
		for (int i = 0; i < n; ++i){
		    c[i] = sc.nextInt();
		    v[i] = sc.nextInt();
		}
		int[] arr = new int[k];
		for (int i =0 ; i < k; ++i){
		    arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
		
		int[] dis = new int[k+1];
		int prev = 0;
		for (int i = 0; i < k; ++i){
		    int cur = arr[i];
		    dis[i] = cur - prev;
		    prev = cur;
		}
		dis[k] = s - prev;
		Arrays.sort(dis);
		
		// Work
		int best = -1;
		int minNeed = 2000000000;
        int lo = 0, hi = 1000000000;
        while (lo <= hi){
            int mid = lo + (hi - lo) / 2;
            int total = 0;
            for (int i = 0; i <= k; ++i){
                if (mid < dis[i]){
                    total = t+1;
                    break;
                }
                int turbo = Math.max(mid - dis[i], 0);
                turbo = Math.min(turbo, dis[i]);
                int rem = dis[i] - turbo;
                total += turbo + rem * 2;
            }
            //System.out.println(mid + " " + total);
            if (total <= t){
                hi = mid - 1;
                minNeed = Math.min(minNeed, mid);
            }else{
                lo = mid + 1;
            }
        }
        
        //System.out.println(minNeed);
		
		for (int i =0 ; i < n; ++i){
		    if (v[i] >= minNeed){
		        best = best == -1 ? c[i] : Math.min(best, c[i]);
		    }
		}
		
		System.out.println(best);
	}
	
		    //-----------PrintWriter for faster output---------------------------------
   public static PrintWriter out;
      
   //-----------MyScanner class for faster input----------
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
 
      String nextLine(){
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
