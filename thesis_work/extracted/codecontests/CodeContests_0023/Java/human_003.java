import java.util.HashSet;
import java.util.Scanner;

public class File1{
	static long dp[][];
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n = in.nextInt()*2;
		int z = in.nextInt();
		int y = in.nextInt();
		int x = in.nextInt();
		int ans = solve(n,x,y,z,2);
		System.out.println(ans);
	}
	private static int solve(int n,int x,int y,int z,int f){
		if(f==0){
			if(n<=z) return 1;
			else return 0;
		}
		if(f==1){
			int t = n/2;
			t = Math.min(t,y);
			int ans = 0;
			for(int i=0;i<=t;i++)
				ans+=solve(n-2*i,x,y-i,z,0);
			return ans;
		}
		int t = n/4;
		t = Math.min(t,x);
		int ans = 0;
		for(int i=0;i<=t;i++)
			ans+=solve(n-4*i,x-i,y,z,1);
		return ans;
	}
}