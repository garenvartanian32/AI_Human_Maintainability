import java.util.*;
import java.lang.*;

public class Main {
  
    public int dp(int[] a,int[] b,int[] c,int[] dp1 ,int[] dp2,int[] dp3,int n,int index,int type) {
    	if(index==n-1) {
    		dp1[n-1]=a[n-1];
    		dp2[n-1]=b[n-1];
    		dp3[n-1]=c[n-1];
    	}
    	else {
    		if(dp1[index]==-1)
    		dp1[index]=this.dp(a, b, c, dp1,dp2,dp3,n,index+1,0)+a[index];
    	if(dp2[index]==-1)
    		dp2[index]=this.dp(a, b, c, dp1,dp2,dp3,n,index+1,1)+b[index];
    	if(dp3[index]==-1)
    	dp3[index]=this.dp(a, b, c, dp1,dp2,dp3,n,index+1,2)+c[index];
    	}
    	if(type==0) {
    		return Math.max(dp2[index], dp3[index]);
    	}
    	if(type==1) {
    		return Math.max(dp1[index],dp3[index]);
    	}
    	else {
    		return Math.max(dp2[index], dp1[index]);
    	}
    	
    }
	public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] a=new int[n];
        int[] b=new int[n];
        int[] c=new int[n];
        for(int i=0;i<n;i++) {
        	a[i]=sc.nextInt();
        	b[i]=sc.nextInt();
        	c[i]=sc.nextInt();
        }
        int[] dp1=new int[n];        int[] dp3=new int[n];
        int[] dp2=new int[n];

        for(int i=0;i<n;i++) {
        	dp1[i]=-1;
        	dp2[i]=-1;
        	dp3[i]=-1;
        }
        Main mm=new Main();
        mm.dp(a, b, c, dp1, dp2, dp3, n, 0,0);
        int temp=Math.max(dp1[0], dp2[0]);
        temp=Math.max(temp, dp3[0]);
        System.out.println(temp);
    }
}

