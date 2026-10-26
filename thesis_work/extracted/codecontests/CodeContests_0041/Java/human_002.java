import java.util.Scanner;
public class Main {
	public static void main(String[] args){
		Scanner stdIn=new Scanner(System.in);
		int N=stdIn.nextInt();
		int M=stdIn.nextInt();
		int a[]=new int[N+1];
		long dp[]=new long[N+3];
		int z=0;
		while(z<M){
			int b=stdIn.nextInt();
			a[b]=-1;
			z++;
		}z=3;
		dp[2]=1;
		while(z<=N+2){
			if(a[z-2]!=-1)
				dp[z]=(dp[z-1]+dp[z-2])%1000000007;
			else
				dp[z]=0;
			z++;
		}z=3;
		System.out.println(dp[N+2]);
	}
}