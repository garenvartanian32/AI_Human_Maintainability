import java.util.*; 
 
public class Main{
    public static void main(String[] args){
	    Scanner sc=new Scanner(System.in);
            int N =sc.nextInt();
  		    long[] A = new long[N];
  		    long ans=1;
		    for(int i=0; i<N; i++){
    		    A[i]=sc.nextLong();
          	    if(A[i]==0){
            	    ans=0;
                }
    	    }
  	    	if(ans!=0){
		    	for(int i=0; i<N; i++){
            	if(A[i] <= (long)Math.pow(10,18)/ans){
                	ans *= A[i];
            	}else{
          	    	ans=-1;
                	break;
            	}
    		}
  	    }
    	System.out.println(ans);
    }
}