	import java.util.*;
	import java.util.Map.Entry;
	 
	 
	 class Main {
		 static int mod =  (int) (Math.pow(10,9)+7);
//		 static int mod =  (int) 998244353;
		 static List<ArrayList<Integer>> list= new ArrayList<ArrayList<Integer>>();
		 public static void main(String[] args) {
	    	
	        Scanner sc = new Scanner(System.in);
	        int N = sc.nextInt();
	        int C = sc.nextInt();
	        int[][] D = new int[C][C];
	        for(int i=0;i<C;i++) {
	        	for(int j=0;j<C;j++) {
	        		D[i][j]=sc.nextInt();
	        	}
	        }
	        int[][] c = new int[N][N];
	        int[][] m = new int[C][3];
	        for(int i=0;i<N;i++) {
	        	for(int j=0;j<N;j++) {
	        		c[i][j]=sc.nextInt()-1;
	        		m[c[i][j]][(i+j)%3]++;
	        	}
	        }

	        long ans=Long.MAX_VALUE;
	        boolean flg =false;
	        int[] d = new int[3];
	        while(d[0]<=C-1) {	        	
	        	if(d[0]==d[1])flg=true;
	        	if(d[1]==d[2])flg=true;
	        	if(d[0]==d[2])flg=true;
	        	long sum =0;
	        	if(flg==false) {
		        	for(int i=0;i<C;i++) {
		        		sum=sum+m[i][0]*D[i][d[0]];
		        		sum=sum+m[i][1]*D[i][d[1]];
		        		sum=sum+m[i][2]*D[i][d[2]];
		        	}	        		
		        	ans=Math.min(ans,sum);
	        	}

	        	
	        	d[2]++;
	        	if(d[2]>=C) {
	        		d[1]++;
	        		d[2]=0;
	        		if(d[1]>=C) {
	        			d[0]++;
	        			d[1]=0;
	        		}
	        	}
	        	flg=false;
	        }
	        
	        
	        
	        System.out.println(ans);
		 }
		 
	 
	}