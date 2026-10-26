import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			int n = sc.nextInt();
			if(n == 0) break;
			String[] f = new String[n];
			int[] w = new int[n];
			int[] s = new int[n];
			int smax = 0;
			for(int i = 0; i < n; i++) {
				f[i] = sc.next();
				w[i] = sc.nextInt();
				s[i] = sc.nextInt();
				smax = Math.max(s[i], smax);
			}
			Data[][] dp = new Data[(1 << n)][smax+1];
			
			dp[0][smax] = new Data();
			dp[0][smax].G = 0;
			dp[0][smax].ix = 1;
			dp[0][smax].id = -1;
			
			for(int i = 0; i < (1 << n); i++) {
				for(int j = smax; j >= 0; j--) {
					for(int k = 0; k < n; k++) {
						if(dp[i][j] == null) continue;
						if((i & (1 << k)) == 0 && (j - w[k]) >= 0) {
							Data a = dp[i|(1 << k)][Math.min(j - w[k], s[k])];
							Data b = new Data();
							b.id = k;
							b.ix = dp[i][j].ix;
							
							b.G = dp[i][j].G + b.ix * w[k];
							b.ix++;
							b.down = dp[i][j];
							
							if(a == null || a.G > b.G) {
								if(b.ix == 2) {
									dp[i|(1 << k)][s[k]] = b;
								}
								else {
									dp[i|(1 << k)][Math.min(j - w[k], s[k])] = b;
								}
							}
						}
					}
				}
			}

			
			
			int min = Integer.MAX_VALUE;
			int id = 0;
			
			for(int i = 0; i < smax+1; i++) {
				if(dp[(1 << n)-1][i] != null && min > dp[(1 << n)-1][i].G) {
					min = dp[(1 << n) -1][i].G;
					id = i;
				}
			}
			ArrayDeque<Data> sxx = new ArrayDeque<Data>();
			Data p = dp[(1 << n) -1][id];
			sxx.add(p);
			while(p.down != null) {
				sxx.add(p.down);
				p = p.down;
			}
			sxx.pollLast();
			while(!sxx.isEmpty()) {
				System.out.println(f[sxx.pollLast().id]);
			}

			
		}
	}
	
	static class Data {
		int id;
		Data down;
		int G;
		int ix;
	}
		
}