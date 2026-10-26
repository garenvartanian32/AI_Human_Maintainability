
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}

	Scanner sc = new Scanner(System.in);

	void run() {
		int t = sc.nextInt();
		for(int i = 0; i < t; i++){
			
			long a = sc.nextLong();
			long b = sc.nextLong();
			long c = sc.nextLong();
			long d = sc.nextLong();
			
			
			System.out.println(solve(a,b,c,d)?"Yes":"No");
			
		}
		
	}
	
	boolean solve(long a, long b, long c, long d){
		
		if(a < b){
			return false;
		}
		
		if( d < b){
			return false;
		}
		
		long gcd = gcd(b,d);
		
		long start = (a - c -1 )%gcd;
		
		long min = start - b;
		
//		System.out.println(gcd+" "+start+ " "+min);
		if(min < -c-1){
			return false;
		}
		return true;
	}
	
	
	long gcd(long a,long b){
		if(a < b){
			return gcd(b,a);
		}
		if(a % b == 0){
			return b;
		}
		return gcd(b,a%b);
	}
}

