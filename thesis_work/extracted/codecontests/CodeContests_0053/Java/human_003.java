import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int [] arr = new int[n];
		for (int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		boolean breakFlag = false;
		for (int i=0;i<n;i++) {
			if (breakFlag) break;
			for (int j=i+1;j<n;j++) {
				if (Math.abs(arr[i] - arr[j]) % (n - 1) == 0) {
					System.out.println(arr[i]+ " " + arr[j]);
					breakFlag = true;
					break;
				}
			}
		}
	}
}

