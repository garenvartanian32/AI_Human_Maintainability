import java.util.Scanner;


public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		String s = sc.next();
		if (s.length() % k != 0) {
			System.out.println(-1);
			return;
		}
		int[]cnt = new int[26];
		for (int i = 0; i < s.length(); i++) {
			cnt[s.codePointAt(i)-97]++;
		}
		for (int i = 0; i < 26; i++) {
			if (cnt[i] % k != 0) {
				System.out.println(-1);
				return;
			}
			cnt[i] /= k;
		}
		for (int i = 1; i <= k; i++) {
			for (int j = 0; j < 26; j++) {
				for (int j2 = 1; j2 <= cnt[j]; j2++) {
					System.out.print((char)(j+97));
				}
			}
		}
	}

}
