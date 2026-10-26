import java.util.*;

//public class Main {
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int n = Integer.parseInt(sc.next());
//		int k = Integer.parseInt(sc.next());
//		String inputs = sc.next();
//		String[] input = inputs.split("");
//		String output = "";
//		for(int i=0;i<n;i++) {
//			if(i == k-1) {
//				output = output + input[i].toLowerCase();
//			}else {
//				output = output + input[i];
//			}
//		}
//		System.out.println(output);
//	}
//}

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.next());
		int b = n%100;
		int a = (n-b)/100;
		String judge = "";
		if(a>12 || a == 0) {
			if(13>b && b>0) {
				judge = "YYMM";
			}else {
				judge = "NA";
			}
		}else {
			if(13>b && b>0) {
				judge = "AMBIGUOUS";
			}else {
				judge = "MMYY";
			}
		}
		System.out.println(judge);
	}
}
