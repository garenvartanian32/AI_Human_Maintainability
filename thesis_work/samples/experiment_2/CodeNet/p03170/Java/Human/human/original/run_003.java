import java.util.Arrays;
import java.util.Collections;
import java.util.*;
class Main
{
	public static void main (String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int i;
		int n=sc.nextInt(); int k=sc.nextInt();
		int a[]=new int[n];
		for(i=0;i<n;i++)
			a[i]=sc.nextInt();
		Arrays.sort(a);
		int dp[]=new int[k+1];
		dp[0]=0;
		for(i=0;i<=k;i++)
		{
			for(int j=0;j<n;j++)
			{
				if(i>=a[j]&&dp[i-a[j]]==0)
					dp[i]=1;
			}
		}
		if(dp[k]==1)
			System.out.println("First");
		else
			System.out.println("Second");
		
		
	}
}