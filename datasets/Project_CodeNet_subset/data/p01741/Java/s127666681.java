import java.util.Scanner;

class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		double d = sc.nextDouble();
		double ans = Math.max((int)d + 1, d / Math.sqrt(2) * 2);
		System.out.println(ans);

	}
}
