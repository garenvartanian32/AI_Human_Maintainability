import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int i;
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str1[] = new String[n];
        String str2[] = new String[n];
        
        for(i = 0; i < n; i++) {
        	str1[i] = sc.next();
        	str2[i] = str1[i];
        }
        
        outPut(bubble(str1, n), n);
        System.out.println("Stable");
        
        outPut(sellect(str2, n), n);
        for(i = 0; i < n; i++) {
        	if(str1[i] != str2[i]) {
        		System.out.println("Not stable");
        		return;
        	}
        }
        System.out.println("Stable");
	}
	
	//bubble sort
	public static String[] bubble(String[] a, int n) {
		int tmp;
		String stmp;
		int b[] = new int[n];
		for(int i = 0; i < n; i++) {
        	b[i] = Character.getNumericValue(a[i].charAt(1));
        }
		for(int i = 0; i < n-1; i++) {
			for(int j = n-1; j > 0; j--) {
				if(b[j] < b[j-1]) {
					tmp = b[j];
					b[j] = b[j-1];
					b[j-1] = tmp;
					stmp = a[j];
					a[j] = a[j-1];
					a[j-1] = stmp;
				}
			}
		}
		return a;
	}
	
	//sellection sort
	public static String[] sellect(String[] a, int n) {
		boolean flag = false;
		int tmp;
		String stmp;
		int[] b = new int[n];
		for(int i = 0; i < n; i++) {
        	b[i] = Character.getNumericValue(a[i].charAt(1));
        }
		for(int i = 0; i < n-1; i++){
			int minj = i;
			for(int j = i+1; j < n; j++) {
				if(b[j] < b[minj]) {
					minj = j;
					flag = true;
				}
			}
			if(flag) {
				tmp = b[i];
				b[i] = b[minj];
				b[minj] = tmp;
				stmp = a[i];
				a[i] = a[minj];
				a[minj] = stmp;
				flag = false;
				
			}
		}
		return a;
	}
	
	//output array
	public static void outPut(String[] a, int n) {
		for(int i = 0; i < n-1; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println(a[n-1]);
	}
}
