

import java.util.Scanner;



public class Main{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		n = 0;
		int min;
		for(int i=0;i<arr.length-1;i++) {
			min = i;
			for(int j=i+1;j<arr.length;j++) {
				if(arr[min]>arr[j]) {
					min = j;
				}
			}
			if(min > i) {
				int temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
				n++;
			}
		}
		for(int i=0;i<arr.length;i++) {
			if(i+1==arr.length) {
				System.out.println(arr[i]);
			}else {
				System.out.print(arr[i]+" ");
			}
		}
		System.out.println(n);
	}
}
		
