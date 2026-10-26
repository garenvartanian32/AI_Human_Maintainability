import java.io.*;
import java.util.*;

public class cf294B {
	static InputReader in = new InputReader();
	static String str = "";
	static String[] arr;
	
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(in.readLine());
		arr = in.readLine().split(" ");
		
		int[] a = new int[arr.length];
		for(int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(arr[i]);
		}
		HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> count2 = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++) {
			if(count.containsKey(a[i])) {
				count.put(a[i], count.get(a[i]) + 1);
			} else {
				count.put(a[i], 1);
			}
		}
		arr = in.readLine().split(" ");
		
		int[] b = new int[arr.length];
		for(int i = 0; i < b.length; i++) {
			b[i] = Integer.parseInt(arr[i]);
		}
		for(int i = 0; i < n - 1; i++) {
			count.put(b[i], count.get(b[i]) - 1);
			if(count.get(b[i]) == 0) {
				count.remove(b[i]);
			}
			if(count2.containsKey(b[i])) {
				count2.put(b[i], count2.get(b[i]) + 1);
			} else {
				count2.put(b[i], 1);
			}
		}
		int ans = 0;
		for(int i : count.keySet()) {
			ans = i;
		}
		System.out.println(ans);
		arr = in.readLine().split(" ");
		
		int[] c = new int[arr.length];
		for(int i = 0; i < c.length; i++) {
			c[i] = Integer.parseInt(arr[i]);
		}
		for(int i = 0; i < n - 2; i++) {
			count2.put(c[i], count2.get(c[i]) - 1);
			if(count2.get(c[i]) == 0) {
				count2.remove(c[i]);
			}
		}
		for(int i : count2.keySet()) {
			ans = i;
		}
		System.out.println(ans);
	}

	static class InputReader {
		BufferedReader br;
		public InputReader() {
			try {
				br = new BufferedReader(new FileReader("cf294B.in"));
			} catch(Exception e) {
				br = new BufferedReader(new InputStreamReader(System.in));
			}
		}

		public String readLine() throws Exception {
			return br.readLine();
		}
	}
}