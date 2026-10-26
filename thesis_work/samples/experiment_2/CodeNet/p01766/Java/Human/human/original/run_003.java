import java.util.*;
import java.math.*;
import java.awt.geom.*;
import java.io.*;
    
    
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] f = new int[n];
		int[] a = new int[n];
		int[] t = new int[n];
		int[] x = new int[n];
		int[] y = new int[n];
		
		double AM = -1;
		double BM = -1;
		double A = Integer.MAX_VALUE;
		double B = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			f[i] = sc.nextInt();
			a[i] = sc.nextInt();
			t[i] = sc.nextInt();
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
		}
		
		for(int i = 1; i < n; i++) {
			if(t[i-1] != t[i]) continue;
			if(a[i-1] == a[i]) continue;
			double d = Math.sqrt(Math.pow(x[i] - x[i-1], 2.0) + Math.pow(y[i] - y[i-1], 2.0));
			double time = f[i] - f[i-1];
			if(t[i] == 0) {
				if(d > AM || (d == AM && time < A)) {
					AM = d;
					A = time;
				}
			}
			else {
				if(d > BM || (d == BM && time < B)) {
					BM = d;
					B = time;
				}
			}
		}
		if(AM == -1) System.out.println(-1 + " " + -1);
		else System.out.printf("%.20f %.20f\n",AM,A/60.0);
		if(BM == -1) System.out.println(-1 + " " + -1);
		else System.out.printf("%.20f %.20f\n",BM,B/60.0);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}