

import java.util.*;

public class GFG {
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc=new Scanner(System.in);
		int sum=0,n;
	//	if(shr.charAt(0)>mor.charAt(0))
		//	System.out.println("f yeah");
		for(int i=0;i<5;i++)
		{
			n=sc.nextInt();
			sum+=n;
		}
		if(sum%5!=0 || sum==0)
		System.out.println("-1");
		else
			System.out.println(sum/5);
	}
}
