//package practice;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CommonSubsequence {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int t = sc.nextInt();

		while (t-- > 0) {

			int n = sc.nextInt();
			int m = sc.nextInt();

			int[] a = new int[n];
			int[] b = new int[m];

			Set<Integer> s = new HashSet<>();

			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
				s.add(a[i]);
			}

			boolean is = false;
			int x = 0, flag = 0;
			for (int i = 0; i < m; i++) {
				b[i] = sc.nextInt();
				if (s.contains(b[i]) && flag == 0) {
					x = b[i];
					is = true;
					flag = 1;
				}
			}

			if (is) {
				System.out.println("YES");
				System.out.println(1 + " " + x);

			} else {
				System.out.println("NO");
			}

		}

		sc.close();

	}

}
