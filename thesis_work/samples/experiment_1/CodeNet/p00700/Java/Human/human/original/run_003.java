import java.util.*;
import java.math.*;
import java.lang.*;

public class Main {
	public static void main(String[] args){
		new Solution().solve();
	}
}

class Solution{
	void solve(){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		while(n-- > 0){
			int x = 0, y = 0;
			int X = 0, Y = 0;
			while(true){
				int a = scan.nextInt();
				int b = scan.nextInt();
			
				if(a == 0 && b == 0) break;
				
				x += a;
				y += b;
				
				if(X*X + Y*Y <= x*x + y*y){
					X = x;
					Y = y;
				}
			}
			System.out.println(X + " " + Y);
		}
	}

}