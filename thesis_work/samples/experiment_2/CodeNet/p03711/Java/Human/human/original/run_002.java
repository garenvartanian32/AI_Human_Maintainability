import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		int y = sc.nextInt();

		int[] a = { 1, 3, 5, 7, 8, 10, 12 };
		int[] b = { 4, 6, 9, 11 };

		if (x == 2 && y == 2 || Arrays.binarySearch(a, x) >= 0 && Arrays.binarySearch(a, y) >= 0
				|| Arrays.binarySearch(b, x) >= 0 && Arrays.binarySearch(b, y) >= 0) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}

}
