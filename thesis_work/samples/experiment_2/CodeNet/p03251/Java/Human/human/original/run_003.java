import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 整数の標準入力
		int n = sc.nextInt();
		int m = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int z;
		Integer nArray[] = new Integer[n];
		int mArray[] = new int[m];
		for(int i = 0; i < n; i++) {
			nArray[i] = sc.nextInt();
		}
		for(int j = 0; j < m; j++) {
			mArray[j] = sc.nextInt();
		}
		Arrays.sort(nArray, Collections.reverseOrder());
		Arrays.sort(mArray);
		z = mArray[0];
		if(x < z && z <= y && nArray[0] < z) {
			System.out.println("No War");
		} else {
			System.out.println("War");
		}
		sc.close();
	}
}
