import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = Integer.MAX_VALUE - 1;
		final String AMB= "ambiguous";
		final String NONE= "none";
		for (;;) {
			int N = sc.nextInt();
			if (N == 0)
				break;
			int[] a = new int[N];
			int[] b = new int[N];
			String ans = null;
			for (int i = 0; i < N; i++) {
				String s = sc.next();
				if (s.equals("x")) {
					a[i] = x;
				} else {
					a[i] = Integer.parseInt(s);
				}
				if (i >= 1 && a[i] == a[i - 1]) {
					ans = NONE;
				}
			}

			for (int i = 1; i < N; i++) {
				if (a[i] == x || a[i - 1] == x)
					continue;
				if (i % 2 == 0) {
					if (!(a[i - 1] > a[i])) {
						ans = NONE;
					}
				} else {
					if (!(a[i - 1] < a[i])) {
						ans = NONE;
					}
				}
			}

			if (ans != null) {
				System.out.println(ans);
				continue;
			}

			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			if (a[0] == x) {
				min = a[1] - 1;
			}

			if (a[a.length - 1] == x) {
				if ((a.length - 1) % 2 == 0) {
					min = a[a.length - 2] - 1;
				} else {
					max = a[a.length - 2] + 1;
				}
			}
			for (int i = 1; i < N - 1; i++) {
				if(a[i]!=x)continue;
				if (i % 2 == 0) {
					min = Math.min(min,Math.min(a[i - 1], a[i + 1]) - 1);
				} else {
					max= Math.max(max,Math.max(a[i - 1], a[i + 1]) + 1);
				}
			}
//			System.out.println(min +" "+max);
			if(max==Integer.MIN_VALUE || min == Integer.MAX_VALUE){
				System.out.println(AMB);
			} else if(max==min){
				System.out.println(max);
			} else if(max < min){
				System.out.println(AMB);
			} else {
				System.out.println(NONE);
			}
			
		}
	}

}