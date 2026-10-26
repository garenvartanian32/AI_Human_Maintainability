import java.math.*; 
import java.util.*; 

public class Main{

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in) ;
		int n = in.nextInt()   ;
		int[] a  = new int[n]  ; 
		long mod = 998244353   ;
		long S = 0;
				
		for(int i=0;i<n;i++) {
			a[i] = in.nextInt() ;
			S += (long) a[i] ;
			
		}
		
			
		long[] s2     = new long[301] ;
		long[] s3     = new long[301] ;
        s2[2] = 1 ;
		s3[3] = 1 ;
        
		for(int i=3;i<=300;i++){
			s2[i] = (1 + 2 * s2[i-1]) % mod ; 	
		}
			
		for(int i=4;i<=300;i++){
			s3[i] = (s2[i-1] + 3 * s3[i-1]) % mod ;
		}
				    

		long[] d1  = new long[90001] ;
		long[] d2  = new long[90001] ;
		
		for(int x :a) {
			for(int i=90000;i>=0;i--) {
		        if(d2[i] > 0) {
		            d2[i+x] = (d2[i+x] + 2 * d2[i]) % mod ;
		        }    		
			}
			
			for(int i=90000;i>=0;i--) {
		        if(d1[i] > 0) {
		            d1[i+x]  = (d1[i+x] + d1[i]) % mod    ;
		            d2[i+x]  = (d2[i+x] + d1[i]) % mod    ;
			    }
			}
			
		    d1[x] = (d1[x] + 1) % mod ;       
		    
		}
		    
		   
		
		long count = 0 ;

		for(int i=1; i <= (int) S / 2;i++){
			count = (count+d2[i]) % mod ;
		}
		
		System.out.println(6*(s3[n]-count+mod) % mod) ;
    }
}
