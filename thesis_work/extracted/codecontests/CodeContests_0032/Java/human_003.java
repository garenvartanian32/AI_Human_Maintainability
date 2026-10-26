import java.util.Arrays;
import java.util.Scanner;

public class R421D {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] arr = new int[n];
		int[] count = new int[n];
		int S = 0;
		long sum = 0;
		for(int i = 0; i < n; i++) {
			arr[i] = scan.nextInt()-1;
			sum += Math.abs(arr[i]-i);
			if(arr[i] >= i){
				int x = arr[i]-i;
				count[x]++;
			}
			else{
				int x = n-i+arr[i];
				count[x]++;
			}
			if(arr[i] <= i) S++; 
		}
		int idx = 0;
		long minSum = sum;
		for(int i = 1; i < n; i++) {
			sum -= Math.abs(n-arr[n-i]-1);
			sum += arr[n-i];
			sum += S-1;
			sum -= n-S;
			S--;
			S += count[i];
			//System.out.println(i+" "+sum);
			if(sum < minSum) {
				minSum = sum;
				idx = i;
			}
		}
		System.out.println(minSum+" "+idx);
	}
}

/*
6
4 3 1 2 5 6
*/