import java.awt.geom.*;
import java.io.*;
import java.util.*;

public class Main{
	double EPS = 1.0e-08;
	
	private void doit(){
		Scanner sc = new Scanner(System.in);
		while(true){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			if((a|b|c) == 0) break;
			int d = b * b - 4 * a * c;
			int e = (int) Math.sqrt(d + EPS);
			if(e * e != d){
				System.out.println("Impossible");
				continue;
			}
			int res1 = -b + e;
			int res2 = -b - e;
			int aa = 2 * a;
			int div = gcd(res1, aa);
			int p = aa / div;
			int q = res1 / div;
			if(p * q < 0){
				p = Math.abs(p);
				q = Math.abs(q);
			}
			else{
				p = Math.abs(p);
				q = Math.abs(q) * -1;
			}
			int div2 = gcd(res2, aa);
			int r = aa / div2;
			int s = res2 / div2;
			if(r * s < 0){
				r = Math.abs(r);
				s = Math.abs(s);
			}
			else{
				r = Math.abs(r);
				s = Math.abs(s) * -1;
			}
			
			if(p > r || (p == r && q >= s)){
				System.out.printf("%d %d %d %d\n", p,q,r,s);
			}
			else{
				System.out.printf("%d %d %d %d\n", r,s,p,q);
			}
		}
	}
	
	private int gcd(int a, int b){
		if(b == 0)  return a;
		else        return gcd(b, a%b);
	}
	
	private void debug(Object... o) { System.out.println("debug = " + Arrays.deepToString(o)); }

	public static void main(String[] args) {
		new Main().doit();
	}
}