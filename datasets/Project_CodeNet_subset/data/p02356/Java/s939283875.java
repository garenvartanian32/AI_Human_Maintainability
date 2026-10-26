import java.util.Scanner;

public class Main {
	public static void main(String[]args) {
		try(Scanner scan = new Scanner(System.in)){
			
			int N = scan.nextInt();
			int Q = scan.nextInt();
			int []a = new int[N];
			long []s = new long[N+1];
			
			for(int i = 0;i<N;i++) {
				a[i] = scan.nextInt();
				s[i+1] = s[i]+a[i];
			}
			long []x  = new long[Q];
			for(int i = 0;i<Q;i++)x[i] = scan.nextLong();
			
			for(int i = 0;i<Q;i++) {//最大500回
				long X = x[i];
				int right=0;
				long goukei = 0;
				for(int left = 0;left<N;left++) {
					while(right<N&&s[right+1]-s[left]<=X) {
						right++;
					}
					goukei += (right-left);
				}
				
				System.out.println(goukei);
				
				
				
				
			}
			
			
		}
		
		
	}
		

}

