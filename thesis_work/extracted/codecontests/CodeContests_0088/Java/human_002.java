import java.util.*;

public class prob_784B {
	private static Scanner sc;
	private static int n;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result;
		
		input();
		result = solve();
		
		System.out.println(result);
		
	}

	private static int solve() {
		// TODO Auto-generated method stub
		Integer result=0;
		int y;
		
		do {
			y = n & 15;
			n >>= 4;
			if (y == 0 || y == 4 || y == 6 || y == 9 || y == 10 || y == 13){
				result++;
			}
			if (y == 8 || y == 11){
				result += 2;
			}
		} while (n != 0); 
				
		return result;
	}

	private static void input() {
		// TODO Auto-generated method stub
		sc = new Scanner(System.in);
		
		n = sc.nextInt();
	}

}
