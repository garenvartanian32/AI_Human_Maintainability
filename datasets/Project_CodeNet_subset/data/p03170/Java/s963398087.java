import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int a[] = new int[N];
		for(int i=0;i<N;i++) a[i]=sc.nextInt();
		sc.close();
		
		boolean dp[] = new boolean[K+1];
		dp[0]=false;
		for(int i=0;i<=K;i++) {
			for(int t:a) {
				if(i-t>=0)dp[i] |= !dp[i-t];
			}
		}
		if(dp[K])System.out.println("First");
		else System.out.println("Second");
	}
}
