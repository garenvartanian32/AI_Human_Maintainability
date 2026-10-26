
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			b[i] = sc.nextInt();
		}
		int[] c = new int[m];
		int[] d = new int[m];
		int diff;
		int[][] min = new int[2][n];
		for(int i = 0; i < m; i++) {
			c[i] = sc.nextInt();
			d[i] = sc.nextInt();
			for(int  j= 0; j < n; j++) {
				diff = Math.abs(a[j] - c[i]) + Math.abs(b[j] - d[i]);
				//System.out.println(diff);
				if(i==0) {
					min[1][j] = diff;
					min[0][j] = 1;
				}else if(min[1][j] > diff) {
					min[1][j] = diff;
					min[0][j] = i + 1;
				}
			}
		}
		for(int i = 0; i < n; i++) {
			System.out.println(min[0][i]);
		}
		
	}
}

