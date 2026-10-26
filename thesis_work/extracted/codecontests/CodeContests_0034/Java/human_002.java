import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Solution{
	
	
	static ArrayList<Integer>[] adjlist;
	
	
	
    public static void main(String[] args) throws IOException {
     
    	FastScanner fs = new FastScanner();
    	PrintWriter out = new PrintWriter(System.out);
    	
    	
    		
    	int tt = 1;
    	while(tt-->0) {
    		
    		int n = fs.nextInt(), d = fs.nextInt();
    		
    		int[] a = new int[n+1];
    		int[] pre = new int[n+1];
    		
    		for(int i=1;i<=n;i++) {
    			a[i] = fs.nextInt();
    			pre[i] = pre[i-1] + a[i];
    			if(pre[i]>d) {
    				out.println(-1);
    				out.flush();
    				return;
    			}
    		}
    		
    		
    		int[] max = new int[n+2];
    		Arrays.fill(max, -(int)1e9+100);
    		
    		for(int i=n;i>0;i--) {
    			max[i] = Math.max(max[i+1], pre[i]);
    		}
    		
    		
    		int sum = 0;
    		int ans = 0;
    		
    		for(int i=1;i<=n;i++) {
    			if(a[i]==0 && pre[i-1]+sum<0) {
    				int req = -(pre[i-1]+sum);
    				int maxdep = d - (max[i-1]+sum);
    				if(req>maxdep) {
    					out.println(-1);
    					out.flush();
    					return;
    				}
    				ans++;
    				sum += maxdep;
    			}
    		}
    		
    		
    		out.println(ans);
    		
    		
    		
    		
    		
    		
    		
    	}
    	
    		
    		
    		
    	out.close();
    		
    }


  
    
    
    
    
    
    
    
    
    
    
    
    
    static final Random random=new Random();
    	
    static void ruffleSort(int[] a) {
    	int n=a.length;//shuffle, then sort 
    	for (int i=0; i<n; i++) {
    		int oi=random.nextInt(n); int temp=a[oi];
    		a[oi]=a[i]; a[i]=temp;
    	}
    	Arrays.sort(a);
    }
   
  
    
    	
    	
    static class FastScanner{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer("");
     
    	public String next(){
    		while(!st.hasMoreElements()){
    			try{
    				st = new StringTokenizer(br.readLine());
    			} catch(IOException e){
    				e.printStackTrace();
    			}
    		}
    		return st.nextToken();
    	}
    		
    	public String nextLine() throws IOException {
    		return br.readLine();
    	}
    		
    	public int nextInt(){
    		return Integer.parseInt(next());
    	}
     
    	public int[] readArray(int n){
    		int[] a = new int[n];
    		for(int i=0;i<n;i++)
    			a[i] = nextInt();
    		return a;
    	}
    		
    	public long nextLong() {
    		return Long.parseLong(next());
    	}
    		
    	public char nextChar() {
    		return next().toCharArray()[0];
    	}
    }
   	
}
